package Entidades;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import Logica.Tablero;

public class CarameloTdp2 extends Caramelo{

    //Tiempo limite en segundos
    private static final int TIEMPO_LIMITE = 60; 
    private Timer timer;
    private int tiempo_restante;
    private boolean nivel_reiniciado;

    public CarameloTdp2(Tablero tablero, int fila, int columna, int color) {
        super(tablero, fila, columna, color, "imagenes/caramelos_tdp/tdp2/", true);
        iniciarTimer();
	}

    //Operaciones interfaz intercambiable
	
	public boolean es_posible_intercambiar(Entidad entidad) {
		return false;
	}

	public boolean puede_recibir(Caramelo caramelo) {
		return false;
	}

	public boolean puede_recibir(Glaseado glaseado) {
		return false;
	}

	public boolean puede_recibir(Potenciador potenciador) {
		return false;
	}

	public boolean puede_recibir(Gelatina gelatina) {
		return false;
	}

	public void intercambiar(Entidad entidad) {}

	public void intercambiar_con(Caramelo caramelo) {}

	public void intercambiar_con(Potenciador potenciador) {}
	
	public void intercambiar_con(Glaseado glaseado) {}

	public void intercambiar_con(Gelatina gelatina) {}

    //Operaciones interfaz matchable

	public boolean se_produce_match_con(Entidad e) {
		return false;
	}
	
	public boolean aplica_match_con(Caramelo c) {
		return false;
	}
	
	public boolean aplica_match_con(Potenciador p) {
		return false;
	}
	
	public boolean aplica_match_con(Glaseado g) {
		return false;
	}
	
	public boolean aplica_match_con(Gelatina g) {
		return false;
	}

    //Operaciones interfaz detonable

	public void detonar(){
		super.detonar();
	}

	public boolean detona_de_a_dos(Entidad entidad){
		return false;
	}

	public boolean detona_de_a_dos_con(Caramelo caramelo){
		return false;
	}

	public boolean detona_de_a_dos_con(Glaseado glaseado){
		return false;
	}

	public boolean detona_de_a_dos_con(Potenciador potenciador){
		return false;
	}

	public boolean detona_de_a_dos_con(Gelatina gelatina){
		return false;
	}

    //Operaciones
	public Caramelo get_caramelo(){
		return null;
	}

	public void set_caramelo(Caramelo caramelo_a_insertar){
		tablero.reubicar(caramelo_a_insertar);
	}

	@Override
	public void reposicionar_en_eje_z(){
		entidad_grafica.reposicionarse_en_eje_z();
	}

	public boolean se_produce_detonacion_adyacente(Entidad entidad){
		return entidad.es_detonable_por_adyacencia_con(this);
	}

    public boolean es_detonable_por_adyacencia_con(Caramelo caramelo){
		return true;
	}

    public boolean es_detonable_por_adyacencia_con(Glaseado glaseado){
		return false;
	}

    public boolean es_detonable_por_adyacencia_con(Potenciador potenciador){
		return true;
	}
    
    public boolean es_detonable_por_adyacencia_con(Gelatina gelatina){
		return false;
	}

    public boolean es_detonable_por_adyacencia_con(CarameloTdp2 caramelo_tdp2) {
        return false;
    }

	public List<Entidad> efecto_detonacion(Entidad[][] entidades){
		List<Entidad> detonados_por_efecto = new LinkedList<Entidad>();
		detonados_por_efecto.add(this);
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

    //Operaciones Timer
    private void iniciarTimer() {
        timer = new Timer();
        tiempo_restante = TIEMPO_LIMITE;
        nivel_reiniciado = false;

        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                tiempo_restante--;

                if (tiempo_restante == 40) {
                    cambiar_imagen_caramelo_tdp2(3);
                }

                if (tiempo_restante == 20) {
                    cambiar_imagen_caramelo_tdp2(4);
                }

                 // El tiempo ha expirado, implementa la lógica de reinicio del nivel
                if(tiempo_restante == 0 && !nivel_reiniciado ) {
                    ejecutarAccionTiempoAgotado();
                }
            }
        }, 1000, 1000); // Programa la tarea para ejecutarse cada segundo

    }

    private void ejecutarAccionTiempoAgotado() {
        nivel_reiniciado = true;
        detonar_carameloTdp2();
    }

    public void detonar_carameloTdp2() {
        super.detonar();
    }

    public void reiniciarNivel() {
        tablero.get_Juego().resetear_nivel();
    }

    private void cambiar_imagen_caramelo_tdp2(int color) {
        entidad_grafica.notificarse_cambio_estado_carameloTdp2(color);
    }

    
}

