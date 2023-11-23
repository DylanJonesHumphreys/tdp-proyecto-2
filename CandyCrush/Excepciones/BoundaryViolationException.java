package Excepciones;


public class BoundaryViolationException extends Exception {
	/**
	 * Inicializa una nueva excepcion.
	 * @param msg describe el origen de la excepcion
	 */
	public BoundaryViolationException(String msg) {
		super(msg);
	}
}