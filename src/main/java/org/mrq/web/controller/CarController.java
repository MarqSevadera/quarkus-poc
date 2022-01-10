package org.mrq.web.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.mrq.service.CarService;
import org.mrq.web.model.Car;
import org.mrq.web.model.RootModel;
import org.springframework.data.domain.Page;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigInteger;
import java.util.List;

@Path("/api/v1/cars")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GET
    public CarListResponse getAllCars(@QueryParam("make") String make,
                                      @QueryParam("model") String model,
                                      @QueryParam("year") Integer year,
                                      @QueryParam("page") @DefaultValue("0") Integer page,
                                      @QueryParam("limit") @DefaultValue("5") Integer limit) {
        final CarListResponse response = new CarListResponse();
        final Page<Car> carPage = carService.getAllCars(make, model, year, page, limit);
        response.setData(carPage.getContent());
        response.setPageNum(carPage.getPageable().getPageNumber());
        response.setPageSize(carPage.getPageable().getPageSize());
        response.setNumberOfElements(carPage.getNumberOfElements());
        response.setTotalElements(carPage.getTotalElements());
        response.setTotalPages(carPage.getTotalPages());
        return response;
    }

    @GET
    @Path("/{id}")
    public CarRequestResponse getCarById(@PathParam("id") BigInteger id) {
        final CarRequestResponse resp = new CarRequestResponse();
        resp.setData(carService.getCarById(id));
        return resp;
    }


    @POST
    public CarRequestResponse save(@Valid CarRequestResponse car) {
        final CarRequestResponse resp = new CarRequestResponse();
        resp.setData(carService.save(car.getData()));
        return resp;
    }

    @PUT
    @Path("/{id}")
    public CarRequestResponse update(@Valid CarRequestResponse car) {
        final CarRequestResponse resp = new CarRequestResponse();
        resp.setData(carService.update(car.getData()));
        return resp;
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") BigInteger id) {
        carService.deleteById(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}

@Getter
@Setter
class CarListResponse extends RootModel<List<Car>> {
    @Schema(description = "page size")
    Integer pageSize;
    @Schema(description = "page number")
    Integer pageNum;
    @Schema(description = "number of elements in the page")
    Integer numberOfElements;
    @Schema(description = "total number of elements")
    Long totalElements;
    @Schema(description = "total number of pages")
    Integer totalPages;
}

class CarRequestResponse extends RootModel<Car> {

}