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
    private String Series;
    private String Year;
    private String Author;
    private String Description;
    private String Image; // ссылка на обложку книги
    private String File;  // ссылка на файл

    public Books() {
        this.Title = "empty";
        this.Series = "empty";
        this.Year = "empty";
        this.Author = "empty";
        this.Description = "empty";
        this.Image = "./resource/default/Logo.png";
        this.File = "";

        this.Id = 0;
    }
    public Books (String Title,
                  String Series,
                  String Year,
                  String Author,
                  String Description,
                  String Image,
                  String File) {
        this.Title = Title;
        this.Series = Series;
        this.Year  = Year;
        this.Author = Author;
        this.Description = Description;
        this.Image = Image;
        this.File = File;

        this.Id = 0;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return Id;
    }

    @Column(name = "\"Title\"")
    public String getTitle() {
        return Title;
    }

    @Column(name = "\"Series\"")
    public String getSeries() {
        return Series;
    }

    @Column(name = "\"Year\"")
    public String getYear() {
        return Year;
    }

    @Column(name = "\"Author\"")
    public String getAuthor() {
        return Author;
    }

    @Column(name = "\"Description\"", columnDefinition = "TEXT")
    public String getDescription() {
        return Description;
    }

    @Column(name = "\"Image\"")
    public String getImage() {
        return Image;
    }

    @Column(name = "\"File\"")
    public String getFile() {
        return File;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setSeries(String Series) {
        this.Series = Series;
    }

    public void setYear(String Year) {
        this.Year = Year;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setFile(String File) {
        this.File = File;
    }
}
