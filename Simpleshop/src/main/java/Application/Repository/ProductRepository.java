package Application.Repository;

import Application.Model.Category;
import Application.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Products,Long> {
    List<Products> findByCategory(Optional<Category> category);
}
