package org.mrq.web.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Getter
@Setter
@Schema(description = "represents a validation error for 4xx series")
public class ValidationError {

    @Schema(description = "error title that summarizes the error", required = true)
    private String title;
    @Schema(description = "error detail that gives a detailed description of the error", required = true)
    private String detail;

}
