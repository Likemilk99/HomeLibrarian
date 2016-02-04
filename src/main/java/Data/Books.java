package Data;

import org.hibernate.annotations.GenericGenerator;

import javax.lang.model.element.Name;
import javax.persistence.*;

/**
 * Created by likemilk on 31.12.2015.
 */

public class Books {
    /**
     * Уникальный номер для БД
     */
    private Integer id;
    private String Title;
    private String Year;
    /**
     * Fname + LName
     */
    private String Author;
    /**
     * Ссылка на обложку книги???
     */
    private String Res;

     public Books (Integer Sid, String STitle, String SYear, String SAuthor, String SRes){
         id = Sid;
         Title = STitle;
         Year  = SYear;
         Author = SAuthor;
         Res = SRes;
    }

    //Setter
    public void SetId(Integer id) { this.id = id; }

    public void SetRes(String res) {
        this.Res = res;
    }

    public void SetTitle(String name) {
        this.Title = name;
    }

    public void SetPages(String pages) {
        this.Year = pages;
    }

    public void SetAuthor(String author) {
        this.Author = author;
    }

    //Getter
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
   // @Column(name = "\"ID\"")
    public Integer GetID() {
        return id;
    }

    @Column(name = "\"Res\"")
    public String GetRes() {
        return Res;
    }

    @Column(name = "\"Title\"")
    public String getTitle() {
        return Title;
    }

    @Column(name = "\"Pages\"")
    public String GetPages() {
        return Year;
    }

    @Column(name = "\"Author\"")
    public String GetAuthor() {
        return Author;
    }


}
