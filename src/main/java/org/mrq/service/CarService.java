package org.mrq.service;

import lombok.RequiredArgsConstructor;
import org.mrq.repository.dao.CarJpaRepository;
import org.mrq.repository.model.CarEntity;
import org.mrq.web.model.Car;
import org.mrq.web.model.mapper.CarMapper;
import org.springframework.data.domain.*;
import org.springframework.util.StringUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@RequiredArgsConstructor
public class CarService {

    private final CarJpaRepository carJpaRepository;

    public Page<Car> getAllCars(String make, String model, Integer year, Integer page, Integer limit) {
        final Pageable pageRequest = PageRequest.of(page, limit, Sort.by(Car.DEFAULT_SORTER).descending());
        final Page<CarEntity> result = resolveParameters(make, model, year, pageRequest);
        final List<Car> carList = result.getContent().stream().map(CarMapper.INSTANCE::mapEntity)
                .collect(Collectors.toList());
        return new PageImpl<>(carList, pageRequest, result.getTotalElements());
    }

    public Car getCarById(BigInteger carId) {
        final CarEntity carEntity = carJpaRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car with id: " + carId + " not found."));
        return CarMapper.INSTANCE.mapEntity(carEntity);
    }


    public Car save(Car car) {
        final CarEntity ce = CarMapper.INSTANCE.map(car);
        return CarMapper.INSTANCE.mapEntity(carJpaRepository.save(ce));
    }

    private Page<CarEntity> resolveParameters(String make, String model, Integer year, Pageable pageRequest) {
        if (!StringUtils.isEmpty(make) && !StringUtils.isEmpty(model) && year != null) {
            return carJpaRepository.findAllByMakeAndModelAndYear(make, model, year, pageRequest);
        }
        if (!StringUtils.isEmpty(make) && !StringUtils.isEmpty(model) && year == null) {
            return carJpaRepository.findAllByMakeAndModel(make, model, pageRequest);
        }
        if (!StringUtils.isEmpty(make) && year != null && StringUtils.isEmpty(model)) {
            return carJpaRepository.findAllByMakeAndYear(make, year, pageRequest);
        }
        if (!StringUtils.isEmpty(model) && year != null && StringUtils.isEmpty(make)) {
            return carJpaRepository.findAllByModelAndYear(model, year, pageRequest);
        }
        if (!StringUtils.isEmpty(make) && StringUtils.isEmpty(model) && year == null) {
            return carJpaRepository.findByMake(make, pageRequest);
        }
        if (!StringUtils.isEmpty(model) && StringUtils.isEmpty(make) && year == null) {
            return carJpaRepository.findByModel(model, pageRequest);
        }
        if (year != null && StringUtils.isEmpty(make) && StringUtils.isEmpty(model)) {
            return carJpaRepository.findByYear(year, pageRequest);
        }
        return carJpaRepository.findAll(pageRequest);

    }

    public Car update(Car car) {
        final CarEntity carEntity = CarMapper.INSTANCE.map(car);
        return CarMapper.INSTANCE.mapEntity(carJpaRepository.save(carEntity));
    }

    @Transactional
    public void deleteById(BigInteger id) {
        carJpaRepository.deleteById(id);
    }
}
