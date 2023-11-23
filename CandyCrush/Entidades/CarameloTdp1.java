package Entidades;

import java.util.LinkedList;
import java.util.List;

import Logica.Tablero;

public class CarameloTdp1 extends Potenciador {

    public CarameloTdp1(Tablero tablero, int fila, int columna, int color) {
        super(tablero, fila, columna, color, "imagenes/caramelos_tdp/tdp1/", true);
	}

    @Override
    public boolean detona_de_a_dos_con(Caramelo caramelo) {
        return color == caramelo.get_color();
    }

	@Override
	public boolean detona_de_a_dos_con(Potenciador potenciador) {
		return false;
	}
	

    public List<Entidad> efecto_detonacion(Entidad[][] entidades){
		List<Entidad> detonados_por_efecto = new LinkedList<Entidad>();
        for(int i=0;i< tablero.get_filas();i++){
			detonados_por_efecto.add(entidades[i][columna]);
		}
		for(int i=0;i< tablero.get_columnas();i++){
			detonados_por_efecto.add(entidades[fila][i]);
		}
		return detonados_por_efecto;
	}
    
}
