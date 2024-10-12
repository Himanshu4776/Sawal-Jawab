package com.sawaljawab.SawalJawab.Repositories.CustomRepository;

import com.sawaljawab.SawalJawab.entities.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomCategoryRepositoryImpl implements CustomCategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Category findByCategoryName(String categoryName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Category> query = criteriaBuilder.createQuery(Category.class);
        // now make the query:
        // Entity defined
        Root<Category> categoryRoot = query.from(Category.class);

        // value needs to be matched will be added in Predicates
        List<Predicate> predicates = new ArrayList<>();

        // check input data is correct or not?
        if (categoryName != null && !categoryName.isEmpty() && !categoryName.isBlank()) {
            predicates.add(criteriaBuilder.equal(categoryRoot.get("categoryName"), categoryName));
        }

        // now entity and predicates are set, lets match them
        query.select(categoryRoot).where(predicates.toArray(new Predicate[0]));
        List<Category> resultList = entityManager.createQuery(query).getResultList();
        if (!resultList.isEmpty()) {
            return resultList.get(0);
        }

        return null;
    }
}
