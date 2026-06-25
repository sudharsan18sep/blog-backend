package com.study.blog.repositories;

import com.study.blog.domain.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository <Category, UUID> {

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.posts")
    //Return all rows from category (left table), even if there is no matching post
    //LEFT Join : Give me all Categories, and attach Posts if they exist
    //Fetch removes N+1 problem
    List<Category> findAllWithPostCount();
}
