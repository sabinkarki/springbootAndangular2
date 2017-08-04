package boot.angular2.api.exception.handler;

import boot.angular2.api.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by sabin on 8/4/2017.
 */
@ControllerAdvice
public class RootExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException exception){

        return new ResponseEntity<String>(exception.getMessage(), HttpStatus.NOT_FOUND);

    }
}
