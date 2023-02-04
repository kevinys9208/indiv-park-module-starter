package indiv.park.starter.exception;

public class ModuleException extends RuntimeException {

	private static final long serialVersionUID = 420088532511614309L;

	public ModuleException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
