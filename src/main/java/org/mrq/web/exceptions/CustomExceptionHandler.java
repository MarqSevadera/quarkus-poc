package org.mrq.web.exceptions;


import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        final ValidationError validationError = new ValidationError();
        validationError.setTitle(RuntimeException.class.getName());
        validationError.setDetail(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(validationError).build();
    }
}
