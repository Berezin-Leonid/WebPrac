package web.DAO.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import web.DAO.PostDAO;
import web.models.Post;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostDAOImpl extends CommonDAOImpl<Post, Long> implements PostDAO {

    public PostDAOImpl(){
        super(Post.class);
    }



    @Override
    public List<Post> getAllPostByName(String postName) {
        try (Session session = sessionFactory.openSession()) {
            Query<Post> query = session.createQuery("FROM Post WHERE name LIKE :gotName", Post.class)
                    .setParameter("gotName", likeExpr(postName));
            return query.getResultList().size() == 0 ? null : query.getResultList();
        }
    }

    @Override
    public Post getSinglePostByName(String postName) {
        List<Post> candidates = this.getAllPostByName(postName);
        return candidates == null ? null :
                candidates.size() == 1 ? candidates.get(0) : null;
    }


    @Override
    public List<Post> getByFilter(Filter filter) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Post> criteriaQuery = builder.createQuery(Post.class);
            Root<Post> root = criteriaQuery.from(Post.class);

            List<Predicate> predicates = new ArrayList<>();
            if (filter.getName() != null)
                predicates.add(builder.like(root.get("name"), likeExpr(filter.getName())));

                criteriaQuery.where(predicates.toArray(new Predicate[0]));

            return session.createQuery(criteriaQuery).getResultList();
        }
    }

    private String likeExpr(String param) {
        return "%" + param + "%";
    }
}