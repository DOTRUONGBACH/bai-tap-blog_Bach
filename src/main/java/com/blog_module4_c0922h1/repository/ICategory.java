package com.blog_module4_c0922h1.repository;
import com.blog_module4_c0922h1.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategory extends PagingAndSortingRepository<Category, Integer> {

}
