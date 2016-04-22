package Data;


import javax.persistence.*;

/**
 * Пользователь
 */
@Entity
@Table(name = "\"User\"")
public class Users extends Address{

  //  private Integer id;
    private String nickName;
    private String password;
    private String role;


    public  Users() {}

    public Users(Integer id, String fname, String password, String role, String email) {
    //    this.id = id;
        this.nickName = fname;
        this.password = password;
        this.role = role;
     //   this.email = email;
    }

    public   Users(String nickName, String fname, String password, String email){
        super(fname, email);
        this.nickName = nickName;
        this.password = password;
        this.role = "user";
    }


   // @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "emp_id")
  //  public Integer getId() { return id; }

   //

    //@Id
    @Column(name = "Nickname")
    public String getNickName() {
        return nickName;
    }

    @Column(name = "Password")
    public String getPassword() {
        return password;
    }

    @Column(name = "Role")
    public String getRole() {
        return role;
    }



  /*  public void setId(Integer id) {
        this.id = id;
    }*/

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
