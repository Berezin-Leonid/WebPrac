package ru.msu.cmc.webprak.DAO;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.models.Post;

import java.util.List;

public interface PostDAO extends CommonDAO<Post, Long> {

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