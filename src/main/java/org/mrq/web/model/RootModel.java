package org.mrq.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class RootModel<T> {
    @Valid
    @NotNull
    private T data;
}
