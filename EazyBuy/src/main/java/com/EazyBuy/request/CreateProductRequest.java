package com.EazyBuy.request;

import java.util.HashSet;
import java.util.Set;
import com.EazyBuy.model.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
	
    private String title;

    private String description;

    private int price;

    private int quantity;

    private String brand;

    private String color;

    private Set<Size> size=new HashSet<>();

    private String imageUrl;
    
    private String category;
    
}
