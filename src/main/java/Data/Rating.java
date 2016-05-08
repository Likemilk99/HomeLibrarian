package Data;

import javax.persistence.*;

/**
 * Created by likemilk on 07.05.2016.
 */
@Entity
@Table(name = "\"Rating\"")
public class Rating {
    private String email;
    private Integer book_id;

    private double raiting;
    private Integer id;

    public Rating() {

    }

    public Rating(double raiting, String email, Integer book_id) {
        this.raiting = raiting;
        this.email = email;
        this.book_id = book_id;
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

    @Column(name = "book")
    public Integer getBook() {
        return book_id;
    }

    @Column(name = "\"Rating\"")
    public double getRaiting() {
        return raiting;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBook(Integer book_id) {
        this.book_id = book_id;
    }

    public void setRaiting(double raiting) {
        this.raiting = raiting;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
