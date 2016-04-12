package Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Пользователь
 */
@Entity
@Table(name = "\"User\"")
public class Users{

  //  private Integer id;
    private String fname;
    private String password;
    private String role;
    private String email;
    private String nickName;
    public Users() {}

    public Users(Integer id, String fname, String password, String role, String email) {
    //    this.id = id;
        this.fname = fname;
        this.password = password;
        this.role = role;
        this.email = email;
    }

    public   Users(String nickName, String fname, String password, String email){
        this.nickName = nickName;
        this.fname = fname;
        this.password = password;
        this.email = email;
        this.role = "user";
      //  this.id = 0;
    }


   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "emp_id")
  //  public Integer getId() { return id; }

   //
   @Id
  // @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "emp_id")
   public String getNickName() {
       return nickName;
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

    @Column(name = "Email")
    public String getEmail() { return email; }

  /*  public void setId(Integer id) {
        this.id = id;
    }*/

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
