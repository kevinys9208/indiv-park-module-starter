package indiv.park.starter.exception;

public class SameNameException extends RuntimeException {

	private static final long serialVersionUID = 5677758809814848465L;

	public SameNameException(String name) {
		super("같은 이름의 모듈이 존재합니다. [ " + name + " ]");
	}
}