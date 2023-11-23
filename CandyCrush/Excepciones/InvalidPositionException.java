package Excepciones;

/**
 * Class InvalidPositionException
 * Clase que modela la excepcion de recir una posición inválida.
 */
public class InvalidPositionException extends Exception {
	
	/**
	 * Inicializa una nueva excepcion.
	 * @param msg describe el origen de la excepcion
	 */
	public InvalidPositionException(String msg) {
		super(msg);
	}
}