package exception;

/**
 * Classe qui envoit une {@link MatiereAlreadyExistsException}
 * 
 * @author Christine Dos Santos
 * @author bamnishé Alao
 *
 */
public class MatiereAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MatiereAlreadyExistsException(String message) {
		super(message);
	}

}
