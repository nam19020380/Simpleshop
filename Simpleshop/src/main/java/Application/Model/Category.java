package Application.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cateId")
    private long cateId;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Products> products;

    @Column(name = "name")
    private String name;


    public Category() {
    }

    public Category(String name) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCateId() {
        return cateId;
    }

    public void setCateId(long cateId) {
        this.cateId = cateId;
    }

    public void setProducts(Set<Products> products) {
        this.products = products;
    }

    public Set<Products> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + cateId +
                ", name='" + name + '\'' +
                '}';
    }
}
