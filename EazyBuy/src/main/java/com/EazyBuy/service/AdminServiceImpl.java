package com.EazyBuy.Service;

import com.masai.Repository.ProductRepository;
import com.masai.model.Product;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
       Optional<Product> product =  productRepository.findById(id);
       
       if(product.isPresent()) {
    	   return product.get();
       }else {
    	   throw new IllegalArgumentException("Product is not avalible with this id"+id);
       }
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    
    @Override
    public void removeProduct(Integer id) {
        productRepository.deleteById(id);
    }

}
