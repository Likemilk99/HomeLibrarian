package Data;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Гость
 */
//@Entity
//@Table(name = "\"Guest\"")
public class Guest {
    /**
     * nickname
     */
    protected String nickname;

    /**
     * password
     */
    protected String password;



    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    public String GetNickname() {
        return nickname;
    }

    @Column(name = "Password")
    public String GetPassword() { return password; }

    /*@Column(name = "\"rep\"")
    public Rep getRep() {
        return rep;
    }*/

    public void SetNickname(String nickname) {
        this.nickname = nickname;
    }

    public void SetPassword(String password) {
        this.password = password;
    }

    /*public void setRep(Rep rep) {
        this.rep = rep;
    }*/
}
