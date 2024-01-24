package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.CategoryDto;
import com.devsuperior.aula.dto.ProductDto;
import com.devsuperior.aula.entities.Category;
import com.devsuperior.aula.entities.Product;
import com.devsuperior.aula.repositories.CategoryRepository;
import com.devsuperior.aula.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    public ProductDto insert(ProductDto dto){

        Product entity =  new Product();
        entity.setName(dto.getName());
        entity.setPrice (dto.getPrice());

        for (CategoryDto categoryDto : dto.getCategories()){
            Category category = categoryRepository.getReferenceById(categoryDto.getId());
            //            Category category = new Category();
//            category.setId(categoryDto.getId());
            entity.getCategories().add(category);
        }

        entity = productRepository.save(entity);

        return new ProductDto(entity);

    }
}
