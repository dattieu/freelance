package geny.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Created by dat on 1/20/2018.
 */
public class RestServerException extends RuntimeException {

    private HttpStatus httpStatus;
    private List<String> errorList;

    public RestServerException(String message) {
        super(message);
    }

    public RestServerException(String message, HttpStatus httpStatus, List<String> errorList) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorList = errorList;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public RestServerException setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public RestServerException setErrorList(List<String> errorList) {
        this.errorList = errorList;
        return this;
    }
}
