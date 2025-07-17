package com.htet.data.repo.base;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public interface BaseRepo<T , ID> extends JpaRepository<T, ID> {

    <R> Optional<R> findOne(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunction);
    <R> List<R> findAll(Function<CriteriaBuilder, CriteriaQuery<R>> queryFunction);
    <R> Page<R> findAllWithPagnation(Function<CriteriaBuilder, CriteriaQuery<Long>> countFunction,
                        Function<CriteriaBuilder, CriteriaQuery<R>> queryFunction,
                        int pageNumber, int pageSize);
}
