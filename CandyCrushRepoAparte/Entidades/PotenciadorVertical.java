package Entidades;

import java.util.LinkedList;

import Logica.Tablero;
import Logica.TableroEntidad;

public class PotenciadorVertical extends Potenciador {
	
	public PotenciadorVertical(TableroEntidad tablero, int fila, int columna, int color) {
		super(tablero, fila, columna, color, "/imagenes/potenciadores/rayados/verticales/", true);
		puntajeEntidad=35;
	}
	
	public PotenciadorVertical(TableroEntidad tablero, int fila, int columna, int color, boolean visible) {
		super(tablero, fila, columna, color, "/imagenes/potenciadores/rayados/verticales/", visible);
		puntajeEntidad=35;
	}

	public int getPuntajeEntidad(){
		return puntajeEntidad;
	}

	public LinkedList<Entidad> efecto_detonacion(Entidad[][] entidades){
		LinkedList<Entidad> detonados_por_efecto = new LinkedList<Entidad>();
		for(int i=0;i< tablero.get_filas();i++){
			detonados_por_efecto.add(entidades[i][columna]);
		}
		return detonados_por_efecto;
	}

	@Override
	public LinkedList<Entidad> calcular_detonados_por_adyacencia(Entidad[][] entidades) {
		LinkedList<Entidad> detonados_por_adyacencia = new LinkedList<Entidad>();
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
