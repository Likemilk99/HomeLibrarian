package Data;

import javax.persistence.*;


//@Entity
//@Table(name = "\"User\"")
//@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy= InheritanceType.JOINED)
public class Address {
    protected String fname;
    protected String email;


   // @GeneratedValue(strategy=GenerationType.AUTO)
  //  @Column(name="VEHICLE_ID")
   // private int vehicleId;

    public Address(){}

    public Address(String fname, String email) {
        this.fname = fname;
        this.email = email;
    }

    @Column(name = "\"FName\"")
    public String getFname() {
        return fname;
    }

    @Id
    @Column(name = "email")
    public String getEmail() { return email; }

    public void setFname(String nickName) {
        this.fname = nickName;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
