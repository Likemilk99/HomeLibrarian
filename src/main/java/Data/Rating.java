package Data;

import javax.persistence.*;

/**
 * Created by likemilk on 07.05.2016.
 */
@Entity
@Table(name = "\"Rating\"")
public class Rating {
    private String email;
    private String title;
    private double raiting;
    private Integer id;

    public Rating() {

    }

    public Rating(double raiting, String email, String title) {
        this.raiting = raiting;
        this.email = email;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }


    @Column(name = "\"Email\"")
    public String getEmail() {
        return email;
    }

    @Column(name = "\"Title\"")
    public String getTitle() {
        return title;
    }

    @Column(name = "\"Rating\"")
    public double getRaiting() {
        return raiting;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRaiting(double raiting) {
        this.raiting = raiting;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
