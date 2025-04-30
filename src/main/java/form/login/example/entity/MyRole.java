package form.login.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Простой класс роли. Содержит id, название роли (title)
 * и набор пользователей, которым эта роль назначена.
 */
@Entity
@Table(name="roles")
@Data
public class MyRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleid;
    @Column(name = "title")
    private String title;
    /**
    @ManyToMany(mappedBy = "roles")
    private Set<MyUser> users = new HashSet<>();

    public void addUser(MyUser user) {
        users.add(user);
    }

    public void removeUser(MyUser user) {
        users.remove(user);
    }
    **/
}