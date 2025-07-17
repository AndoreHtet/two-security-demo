package com.htet.data.repo.base;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class BaseRepoImpl<T, ID> extends SimpleJpaRepository<T,ID> implements BaseRepo<T , ID>{

    private final EntityManager entityManager;

    public BaseRepoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }



    @Override
    public <R> Optional<R> findOne(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunction) {
        var cq = queryFunction.apply(entityManager.getCriteriaBuilder());
        var query = entityManager.createQuery(cq);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public <R> List<R> findAll(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunction) {
        var cq = queryFunction.apply(entityManager.getCriteriaBuilder());
        var query = entityManager.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public <R> Page<R> findAllWithPagnation(Function<CriteriaBuilder, CriteriaQuery<Long>> countFunction, Function<CriteriaBuilder, CriteriaQuery<R>> queryFunction, int pageNumber, int pageSize) {
        var total = findOne(countFunction).orElse(0L);
        var pageable = PageRequest.of(pageNumber, pageSize);

        var cq = queryFunction.apply(entityManager.getCriteriaBuilder());
        var query = entityManager.createQuery(cq);

        query.setFirstResult(pageNumber * pageSize);
        query.setMaxResults(pageSize);

        var content = query.getResultList();
        return new PageImpl<>(content, pageable, total);
    }
}
