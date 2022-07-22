package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.Service;

@Controller
public class UserController {
    private final Service userService;

    @Autowired
    public UserController(Service userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUsers(@RequestParam(defaultValue = "0") int count, Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/user-create")
    public String showFormNewUser(User user){
        return "user-create";
    }

    @PostMapping("/user-create")
    private String saveUser(User user){
        if (!((user.getFirstName() + user.getLastName())).equals("")){
            userService.saveUser(user);
        }
        return "redirect:/user";
    }

    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/user";
    }

    @GetMapping("/user-update/{id}")
    public String createUpdateUserForm(@PathVariable("id") Long id, Model model){
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(User user){
        //merge
        userService.saveUser(user);
        return "redirect:/user";
    }
}
