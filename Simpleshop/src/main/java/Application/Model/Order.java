package Application.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private long productID;

    @Column
    private long quantity;

    public Order() {
    }

    public Order(long productID, long quantity) {
        this.productID = productID;
        this.quantity = quantity;
    }

    public Order(long id, long productID, long quantity) {
        this.id = id;
        this.productID = productID;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public long getProductID() {
        return productID;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productID=" + productID +
                ", quantity=" + quantity +
                '}';
    }
}
