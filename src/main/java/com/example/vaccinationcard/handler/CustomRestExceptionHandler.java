package com.example.vaccinationcard.handler;

import com.example.vaccinationcard.domain.ApiError;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
    public CustomRestExceptionHandler() {
        super();
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request){
        logger.info(ex.getClass().getName());
        logger.error("error", ex);
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), "Error Ocurred");
        return defaultResponse(apiError);
    }
    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialException(final Exception ex, final WebRequest request){
        final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(),"User or password is incorrect");
        return defaultResponse(apiError);
    }
    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolation(final Exception ex, final WebRequest request){
        logger.info(ex.getClass().getName());
        logger.error("Error on persisting data: ", ex);
        final ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), "Data Integrity Violation");
        return defaultResponse(apiError);
    }
    public ResponseEntity<Object> defaultResponse(ApiError apiError){
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
