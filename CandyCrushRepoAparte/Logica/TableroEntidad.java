package Logica;

import Entidades.Entidad;

public interface TableroEntidad {
	
	public void reubicar(Entidad e);

	public boolean en_rango(int fila, int columna);

	public int get_filas();

	public int get_columnas();

	public void terminar_partida();

	public void resetear_nivel();


}
