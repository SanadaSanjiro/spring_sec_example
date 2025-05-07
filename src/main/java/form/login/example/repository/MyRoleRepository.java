package form.login.example.repository;

import form.login.example.entity.MyRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRoleRepository extends JpaRepository<MyRole, Integer> {
    MyRole findByTitle(String title);
}