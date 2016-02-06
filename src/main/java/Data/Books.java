package Data;

import javax.persistence.*;

/**
 * Created by likemilk on 31.12.2015.
 */
@Entity
@Table(name = "\"Book\"")
public class Books {
    /**
     * Уникальный номер для БД
     */
    private Integer Id;
    private String Title;
    private String Year;
    private String Author; // FName + SName
    private String Res; // ссылка на обложку книги

    public void Books (String Title, String Year, String Author, String Res){
        this.Title = Title;
        this.Year  = Year;
        this.Author = Author;
        this.Res = Res;
        this.Id = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return Id;
    }

    @Column(name = "Title")
    public String getTitle() {
        return Title;
    }

    @Column(name = "Year")
    public String getYear() {
        return Year;
    }

    @Column(name = "Author")
    public String getAuthor() {
        return Author;
    }

    @Column(name = "Res")
    public String getRes() {
        return Res;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    @Column(name = "Title")
    public void setTitle(String Title) {
        this.Title = Title;
    }

    @Column(name = "Year")
    public void setYear(String Year) {
        this.Year = Year;
    }

    @Column(name = "Author")
    public void setAuthor(String Author) {
        this.Author = Author;
    }

    @Column(name = "Res")
    public void setRes(String Res) {
        this.Res = Res;
    }
}
