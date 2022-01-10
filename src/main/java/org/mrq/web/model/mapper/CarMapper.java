package org.mrq.web.model.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.mrq.repository.model.CarEntity;
import org.mrq.web.model.Car;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarEntity map(Car car);

    Car mapEntity(CarEntity carEntity);

}
