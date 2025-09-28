package com.berezin.WebPrak.DAO;

import com.berezin.WebPrak.models.Post;
import lombok.Builder;
import lombok.Getter;


import java.util.List;

public interface PostDAO extends CommonDAO<Post, Long> {

    List<Post> getAllPostByName(String postName);
    Post getSinglePostByName(String postName);
    List<Post> getByFilter(Filter filter);


    @Builder
    @Getter
    class Filter {
        private String name;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }
}