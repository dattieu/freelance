package geny.exception;

import org.springframework.http.HttpStatus;

/**
 * Created by dat on 1/20/2018.
 */
public class RestServerException extends RuntimeException {

    private HttpStatus httpStatus;
    private String errorCode;

    public RestServerException(String message, HttpStatus httpStatus, String errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
