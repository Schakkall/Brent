package enem;

public class FullFileException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Illegal Operation. The file is full.";
	}

}
