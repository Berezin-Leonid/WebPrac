package web.DAO;

import lombok.Builder;
import lombok.Getter;
import web.models.PostDivision;

import java.util.List;

public interface PostDivisionDAO extends CommonDAO<PostDivision, Long> {


    List<PostDivision> getAllByPostId(Long postId);
    List<PostDivision> getAllByDivisionId(Long divisionId);

}