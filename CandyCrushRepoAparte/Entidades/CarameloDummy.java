package Entidades;

import Logica.TableroEntidad;

/*
 * Modela un caramelo cuya funcion es unicamente marcar en el tablero 
 * la posicion en la cual se deberá hacer caer un caramelo o insertar un 
 * caramelo nuevo, segun corresponda.
 * No será visible en ningun momento  (no se le asociara una entidad grafica).
 */
public class CarameloDummy extends Caramelo {

    public CarameloDummy(TableroEntidad tablero, int fila, int columna, int color) {
        super(tablero, fila, columna, color, false);
    }
    
    @Override
    public Caramelo get_caramelo() {
        return null;
    }
}
