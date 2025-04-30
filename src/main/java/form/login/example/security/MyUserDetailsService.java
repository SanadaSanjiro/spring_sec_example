package form.login.example.security;

import form.login.example.entity.MyUser;
import form.login.example.repository.MyUserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * UserDetailsService  — это основной компонент для загрузки пользовательских данных.
 * Он отвечает за извлечение информации о пользователе из внутреннего источника данных,
 * такого как база данных или внешняя служба, и возвращает экземпляр интерфейса UserDetails.
 */
@Service
@Log4j2
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepository myUserRepository;

    @Autowired
    public MyUserDetailsService(MyUserRepository myUserRepository) {
        this.myUserRepository = myUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("User Details Service searching for a user: {}", username);
        MyUser user = myUserRepository.findByName(username);
        if (Objects.nonNull(user)) {
            return new MyUserDetails(user);
        } else {
            throw new UsernameNotFoundException("user not found");
        }
    }
}