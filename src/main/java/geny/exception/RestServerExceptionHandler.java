package geny.exception;

import geny.common.constant.Labels;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by dat on 1/20/2018.
 */

@ControllerAdvice
public class RestServerExceptionHandler {

    private final Logger logger = Logger.getLogger(RestServerExceptionHandler.class);

    // TODO do something more gracefully about this?
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessExceptions(BusinessException exception) {
        logger.error(exception);

        return new ResponseEntity<>(new ErrorMessage(exception.getErrorCode(), exception.getMessage()),
                exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleOtherExceptions(Exception exception) {
        logger.error(exception.getMessage() + " ," + exception.getCause());

        return new ResponseEntity<>(new ErrorMessage(Labels.INTERNAL_SERVER_ERROR_CODE, Labels.INTERNAL_SERVER_ERROR),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
