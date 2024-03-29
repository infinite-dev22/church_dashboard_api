package io.nomard.spring_boot_api_template_v1.services.interfaces;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.nomard.spring_boot_api_template_v1.entities.Product;
import io.nomard.spring_boot_api_template_v1.errors.NotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    List<Product> getAll(int pageNo, int pageSize);

    Product getById(Long id) throws NotFoundException;

    List<Product> getByContains(String search);

    List<Product> getWarning();

    ResponseEntity<ObjectNode> save(Product product);

    ResponseEntity<ObjectNode> update(Product product) throws NotFoundException;

    ResponseEntity<ObjectNode> delete(Long id);
}
