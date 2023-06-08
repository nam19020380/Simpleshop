package Application.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.lang.annotation.Target;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cateId", nullable = false, referencedColumnName = "cateId")
    @JsonBackReference
    private Category category;

    @Column
    private String name;

    @Column
    private long price;

    @Column
    private String imgLink;

    public Products() {
    }

    public Products(Category category, String name, long price, String imgLink) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.imgLink = imgLink;
    }

    public Products(long id, Category category, String name, long price, String imgLink) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.price = price;
        this.imgLink = imgLink;
    }

    public long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imgLink='" + imgLink + '\'' +
                '}';
    }
}
