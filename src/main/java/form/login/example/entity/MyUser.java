package form.login.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Простой класс пользователя, содержащий id, имя, пароль и набор ролей.
 */
@Entity()
@Table(name="users")
@Data
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;
    private String name;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name= "user_role",
            joinColumns=  @JoinColumn(name= "users", referencedColumnName= "userid"),
            inverseJoinColumns= @JoinColumn(name= "roles", referencedColumnName= "roleid"))
    private Set<MyRole> roles = new HashSet<>();

    public void addRole(MyRole role) {
        roles.add(role);
    }

    public void removeRole(MyRole role) {
        roles.remove(role);
    }
}