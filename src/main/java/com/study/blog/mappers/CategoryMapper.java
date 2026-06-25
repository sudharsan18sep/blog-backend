package com.study.blog.mappers;


import com.study.blog.domain.dtos.CategoryDto;
import com.study.blog.domain.entity.Category;
import com.study.blog.domain.entity.Post;
import com.study.blog.domain.entity.PostStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
//componentModel = "spring"
//
//Tells MapStruct to generate the mapper as a Spring bean
//If the target DTO has fields that are not mapped, MapStruct will not generate warnings/errors.
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    //The @Mapping annotation is only used to describe how individual fields are mapped.
    //target refers to a field/property in the destination object, not the destination object itself.
    //MapStruct already knows that the target object is CategoryDto from the method signature:
    CategoryDto toDto(Category category);


    //qualifiedByName = "calculatePostCount":
    // Take the value from category.getPosts() and pass it to this method to produce postCount.

    @Named("calculatePostCount")
    default long calculatePostCount(Set<Post> posts){
        if( posts == null){
            return 0;
        }

        return posts.stream().filter(post -> post.getStatus()==PostStatus.PUBLISHED).count();

    }
}
