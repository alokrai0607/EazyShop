package com.EazyBuy.Service;

import java.util.List;

import com.masai.model.Product;

public interface AdminService {
    public List<Product> getProductList();
    public Product getProductById(Integer id);
    public void addProduct(Product product);
    public void updateProduct(Product product);
    public void removeProduct(Integer id);

}
