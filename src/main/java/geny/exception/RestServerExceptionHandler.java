package geny.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by dat on 1/20/2018.
 */

@ControllerAdvice
public class RestServerExceptionHandler extends ResponseEntityExceptionHandler {

    // TODO categorize specific exception handlers, here handle all exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleInternalServerError(final Exception exception, final WebRequest request) {
        logger.info(exception.getClass().getName());
        logger.error("error", exception);
        final RestServerException serverException = new RestServerException(exception.getLocalizedMessage())
                .setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(serverException, new HttpHeaders(), serverException.getHttpStatus());
    }

}
