package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import project.model.Category;
import project.model.Product;
import project.repository.ProductRepository;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Page<Product> getList(Specification specification, int page, int limit) {
        return productRepository.findAll(specification, PageRequest.of(page - 1, limit));
    }


        public Product create(Product product) {
            product.setCreatedAt(Calendar.getInstance().getTimeInMillis());
            return productRepository.save(product);
        }

        public Optional<Product> getById(int id) {
            Optional<Product> optionalProduct = productRepository.findById(id);
            return optionalProduct;
        }

        public boolean delete(Product product) {
            product.setStatus(-1);
            product.setDeletedAt(Calendar.getInstance().getTimeInMillis());
            productRepository.save(product);
            return true;
        }

        public Product update(Product product) {
            product.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
            return productRepository.save(product);
        }

}
