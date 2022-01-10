package org.mrq.repository.dao;


import org.mrq.repository.model.CarEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigInteger;

@ApplicationScoped
public interface CarJpaRepository extends JpaRepository<CarEntity, BigInteger> {

    Page<CarEntity> findByMake(String make, Pageable pageRequest);

    Page<CarEntity> findByModel(String model, Pageable pageRequest);

    Page<CarEntity> findByYear(Integer year, Pageable pageRequest);

    Page<CarEntity> findAllByMakeAndModel(String make, String model, Pageable pageRequest);

    Page<CarEntity> findAllByMakeAndYear(String make, Integer year, Pageable pageRequest);

    Page<CarEntity> findAllByModelAndYear(String model, Integer year, Pageable pageRequest);

    Page<CarEntity> findAllByMakeAndModelAndYear(String make, String model, Integer year, Pageable pageRequest);


}
