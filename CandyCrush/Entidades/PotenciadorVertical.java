package Entidades;

import java.util.LinkedList;
import java.util.List;

import Logica.Tablero;

public class PotenciadorVertical extends Potenciador {
	
	public PotenciadorVertical(Tablero tablero, int fila, int columna, int color) {
		super(tablero, fila, columna, color, "/imagenes/potenciadores/rayados/verticales/", true);
		puntajeEntidad=35;
	}
	
	public PotenciadorVertical(Tablero tablero, int fila, int columna, int color, boolean visible) {
		super(tablero, fila, columna, color, "/imagenes/potenciadores/rayados/verticales/", visible);
		puntajeEntidad=35;
	}

	public int getPuntajeEntidad(){
		return puntajeEntidad;
	}

	public List<Entidad> efecto_detonacion(Entidad[][] entidades){
		List<Entidad> detonados_por_efecto = new LinkedList<Entidad>();
		for(int i=0;i< tablero.get_filas();i++){
			detonados_por_efecto.add(entidades[i][columna]);
		}
		return detonados_por_efecto;
	}

	@Override
	public List<Entidad> calcular_detonados_por_adyacencia(Entidad[][] entidades) {
		List<Entidad> detonados_por_adyacencia = new LinkedList<Entidad>();
		int variar_posicion=-1;

		for(int i=0;i<2;i++){
			if(tablero.en_rango(fila+variar_posicion, columna) && se_produce_detonacion_adyacente(entidades[fila+variar_posicion][columna])){
				detonados_por_adyacencia.add(entidades[fila+variar_posicion][columna]);
			}
			if(tablero.en_rango(fila,columna+variar_posicion) && se_produce_detonacion_adyacente(entidades[fila][columna+variar_posicion])){
				detonados_por_adyacencia.add(entidades[fila][columna+variar_posicion]);
			}
			variar_posicion=1;
		}

		return detonados_por_adyacencia;
	}
}
