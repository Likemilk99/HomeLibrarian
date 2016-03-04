package Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Пользователь
 */
@Entity
@Table(name = "\"User\"")
public class Users{

    private Integer id;
    private String fname;
    private String password;
    private String role;
    private String email;

    public   Users(String fname, String password, String email){
            this.fname = fname;
        this.password = password;
        this.email = email;
        this.role = "user";
        this.id = 0;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    public Integer getId() { return id; }

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

    @Column(name = "Email")
    public String getEmail() { return email; }

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

    public void setEmail(String email) {
        this.email = email;
    }
}
