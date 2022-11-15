package mipl.icmodule.login;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LoginException extends RuntimeException {
  
	private String status;
    private String error;
    private String message;

    public LoginException ( String status, String error, String message) {
        super(String.format("%s not found with %s : '%s'", status, error, message));
        this.status = status;
        this.error = error;
        this.message = message;
    }

    public LoginException getException() {
        return this;
    }
}
