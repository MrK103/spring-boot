package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.User;

import java.util.List;

public interface Repository extends JpaRepository<User, Long> {
}
