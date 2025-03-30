package web.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.DAO.PostDivisionDAO;
import web.models.PostDivision;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDivisionDAOImpl extends CommonDAOImpl<PostDivision, Long> implements PostDivisionDAO {

    public PostDivisionDAOImpl(){
        super(PostDivision.class);
    }

    @Override
    public List<PostDivision> getAllByPostId(Long postId) {
        try (Session session = sessionFactory.openSession()) {
            Query<PostDivision> query = session.createQuery("FROM PostDivision WHERE post.id = :postId", PostDivision.class).setParameter("postId", postId);
            List<PostDivision> result = query.getResultList();
            return result.isEmpty() ? null : result;
        }
    }

    @Override
    public List<PostDivision> getAllByDivisionId(Long divisionId) {
        try (Session session = sessionFactory.openSession()) {
            Query<PostDivision> query = session.createQuery("FROM PostDivision WHERE division.id = :divisionId", PostDivision.class)
                    .setParameter("divisionId", divisionId);
            List<PostDivision> result = query.getResultList();
            return result.isEmpty() ? null : result;
        }
    }
}