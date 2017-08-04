package boot.angular2.api.exception;

/**
 * Created by sabin on 8/4/2017.
 */
public class NotFoundException  extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }
}
