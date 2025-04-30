package form.login.example.security;


import form.login.example.entity.MyUser;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * UserDetails — это интерфейс Spring Security, предоставляющий основную информацию о пользователе.
 * Он служит мостом между пользовательской моделью данных и внутренними механизмами Spring Security.
 * Основные функции UserDetails:
 * Аутентификация. Содержит информацию, необходимую для аутентификации пользователя,
 * такую как имя пользователя и пароль.
 * Авторизация. Интерфейс предоставляет методы для получения ролей и прав доступа пользователя,
 * что используется при авторизации.
 * Управление состоянием аккаунта. UserDetails содержит методы для проверки состояния аккаунта
 * (активен, заблокирован, истёк срок действия и т.д.).
 */
@Log4j2
public class MyUserDetails implements UserDetails {
    private final MyUser user;

    public MyUserDetails(MyUser user) {
        log.info("UserDetails created for a user {}", user.getName());
        this.user = user;
    }

    /**
     * Метод getAuthorities() интерфейса UserDetails в Spring Security возвращает все полномочия,
     * которые есть у пользователя.
     * Эти полномочия описывают привилегии пользователя (например, чтение, запись, обновление и т.д.)
     * или действия, которые он может выполнять.
     * Метод возвращает результат в виде коллекции, отсортированной по естественному ключу.
     * @return Collection<? extends GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.info("User Details providing grants for a user: {}", user.getName());
        Set<GrantedAuthority> authorities = new HashSet<>();
        // Помещаем в коллекцию объекты SimpleGrantedAuthority, созданные на основе каждой из назначенной
        // пользователю роли, добавляя префикс "ROLE_" для корректной работы механизмов Spring Security.
        // При желании, префикс по умолчанию можно изменить, задав свой в настройках Spring Security.
        log.info("User's roles: {}", user.getRoles());
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getTitle())));
        for (GrantedAuthority authority:authorities) {
            System.out.println("Authorities: " + authority.getAuthority());
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        log.info("User Details providing password for a user: {}", user.getName());
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        log.info("User Details providing username: {}", user.getName());
        return user.getName();
    }

    /**
     * Предоставляет информацию о том, не истек ли срок действия данного аккаунта. В нашем проекте срок действия
     * неограничен, поэтому данный метод всегда возвращает true.
     * @return boolean. False - если срок действия аккаунта истек.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Предоставляет информацию о том, не был ли пользователь заблокирован. Этот метод можно использовать,
     * например, для реализации временной блокировки пользователей после нескольких неудачных
     * попыток входа.
     * В нашем проекте для простоты данный функционал не реализован, поэтому метод всегда возвращает true.
     * @return boolean. False - если аккаунт заблокирован.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Credentials в контексте Spring Security — это то, что подтверждает учётную запись пользователя,
     * как правило, пароль. Соответственно, данный метод предоставляет информацию о том,
     * не истек ли срок действия пароля пользователя.
     * @return boolean. False — если аккаунт заблокирован.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Указывает, включён ли пользователь (enabled) или отключён (disabled).
     * @return boolean. Если пользователь включён, метод возвращает значение true, в противном случае — false.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}