package model.entity;

import model.dao.ImagesDAO;
import model.dao.impl.ImagesDaoImpl;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gandy on 27.10.14.
 *
 */
@Entity
@Table(name = "goods")
public class Goods implements Serializable {

    private static final long serialVersionUID = -9219232331871878227L;

    @Id
    @Column(name = "good_id")
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne()
    @JoinColumn(name = "id_category")
    private Categories category;

    @Column(name = "count_available")
    private int countAvailable;

    @Column(name = "count_sold")
    private int countSold;

    @Column(name = "price")
    private double price;

    @Column(name = "meta_title")
    private String metaTitle;

    @Column(name = "meta_description")
    private String metaDescription;

    @Column(name = "meta_keywords")
    private String metaKeywords;


    public Goods() { }

    public List<Images> getImages(){
        List<Images> list = null;
        ImagesDAO imagesDAO = new ImagesDaoImpl();
        try {
            list = imagesDAO.getByIdGood(this.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public int getCountAvailable() {
        return countAvailable;
    }

    public void setCountAvailable(int countAvailable) {
        this.countAvailable = countAvailable;
    }

    public int getCountSold() {
        return countSold;
    }

    public void setCountSold(int countSold) {
        this.countSold = countSold;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }
}
