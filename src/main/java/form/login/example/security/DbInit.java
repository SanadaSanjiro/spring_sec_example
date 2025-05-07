package form.login.example.security;

import form.login.example.entity.MyRole;
import form.login.example.entity.MyUser;
import form.login.example.repository.MyRoleRepository;
import form.login.example.repository.MyUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Log4j2
public class DbInit {
    private final MyRoleRepository myRoleRepository;
    private final MyUserRepository myUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DbInit(MyRoleRepository myRoleRepository, MyUserRepository myUserRepository, PasswordEncoder passwordEncoder) {
        this.myRoleRepository = myRoleRepository;
        this.myUserRepository = myUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        createDefaultUsers();
    }

    private void createDefaultUsers() {
        MyRole adminRole =  createRole("ADMIN");
        MyRole userRole = createRole("USER");
        createUser("admin", adminRole);
        createUser("user", userRole);
    }

    private void createUser(String userName, MyRole role) {
        log.info("Creating user {}", userName);
        MyUser user = myUserRepository.findByName(userName);
        if (Objects.isNull(user)) {
            user = new MyUser();
            user.setName(userName);
            user.setPassword(passwordEncoder.encode(userName));
            user.addRole(role);
            myUserRepository.save(user);
        }
    }

    private MyRole createRole(String title) {
        log.info("Creating role {}", title);
        MyRole role = myRoleRepository.findByTitle(title);
        if (Objects.isNull(role)) {
            role=new MyRole();
            role.setTitle(title);
        }
        myRoleRepository.save(role);
        return role;
    }
}
