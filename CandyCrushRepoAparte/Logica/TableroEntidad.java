package Logica;

import Entidades.Entidad;

public interface TableroEntidad {
	
	public int get_filas();

	public int get_columnas();
	
	public boolean en_rango(int fila, int columna);
	
	public void reubicar(Entidad e);

	public void resetear_nivel();
	
	public void terminar_partida();


}
