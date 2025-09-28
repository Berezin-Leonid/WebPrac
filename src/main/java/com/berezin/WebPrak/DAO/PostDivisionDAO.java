package com.berezin.WebPrak.DAO;

import com.berezin.WebPrak.models.PostDivision;
import java.util.List;

public interface PostDivisionDAO extends CommonDAO<PostDivision, Long> {

    List<PostDivision> getAllByPostId(Long postId);
    List<PostDivision> getAllByDivisionId(Long divisionId);

}