package Application.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import Application.Model.Category;
import Application.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Application.Model.Products;
import Application.Repository.ProductRepository;
import Application.Repository.CategoryRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllProducts(@RequestParam(required = false) String title) {
        try{
            List<Products> products = new ArrayList<Products>();
            productRepository.findAll().forEach(products::add);
            if (products.isEmpty()){
                productRepository.findAll().forEach(p->System.out.println(p));
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable("id") long id){
        Optional<Products> productData = productRepository.findById(id);

        if(productData.isPresent()){
            return new ResponseEntity<>(productData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/categories/products/{id}")
    public ResponseEntity<List<Products>> getProductsByCategory(@PathVariable("id") long id){
        try{
            List<Products> products = productRepository.findByCategory(categoryRepository.findById(id));

            if (products.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Products> createProduct(@RequestBody Products products){
        try{
            Products _product = productRepository.save(new Products(products.getCategory(), products.getName(), products.getPrice(), products.getImgLink()));
            return new ResponseEntity<>(_product, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable("id") long id,@RequestBody Products products){
        Optional<Products> productsData = productRepository.findById(id);

        if(productsData.isPresent()){
            Products _product = productsData.get();
            _product.setName(products.getName());
            _product.setPrice(products.getPrice());
            _product.setCategory(products.getCategory());
            return new ResponseEntity<>(productRepository.save(_product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") long id){
        try{
            productRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
