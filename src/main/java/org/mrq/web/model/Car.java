package org.mrq.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;


@Data
public class Car {

    @JsonIgnore
    public static final String DEFAULT_SORTER = "YEAR";

    private BigInteger id;

    @NotBlank
    private String make;

    @NotBlank
    private String model;

    @NotNull
    @Size(min = 1800, max = 2022)
    private Integer year;

}
