package form.login.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

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
    @Column(name = "roleid")
    private int roleId;
    @Column(name = "title")
    private String title;
}