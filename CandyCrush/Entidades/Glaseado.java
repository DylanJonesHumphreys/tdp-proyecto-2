package Entidades;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Logica.Tablero;

public class Glaseado extends Entidad {

	public Glaseado(Tablero tablero, int fila, int columna, int color) {
		super(tablero, fila, columna, color, "imagenes/glaseados/", true);
		puntajeEntidad=25;
	}
	
	public Glaseado(Tablero tablero, int fila, int columna, int color, boolean visible) {
		super(tablero, fila, columna, color, "imagenes/glaseados/", visible);
		puntajeEntidad=25;
	}

	public int getPuntajeEntidad(){
		return puntajeEntidad;
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
		return new LinkedList<Entidad>();
	}

	@Override
	public List<Entidad> calcular_detonados_por_adyacencia(Entidad[][] entidades) {
		return new LinkedList<Entidad>();
	}
}
