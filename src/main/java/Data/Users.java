package Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Пользователь
 */
@Entity
@Table(name = "\"User\"")
public class Users{//} extends Guest{

    private Integer id;
    private String fname;
    private String password;
    private String role;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    public Integer getId() {
        return id;
    }

    @Column(name = "FName")
    public String getFname() {
        return fname;
    }

    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    @Column(name = "Role")
    public String getRole() {
        return role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
