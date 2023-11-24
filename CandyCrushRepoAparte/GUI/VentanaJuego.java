package GUI;

import java.util.Map;
import Entidades.EntidadLogica;

public interface VentanaJuego {

	public EntidadGrafica agregar_entidad(EntidadLogica entidad_logica);

	public void ocultar();

	public void mostrar();

	public void reiniciar();

	public void transicionar();

	public void inicializar();

	public void restar_movimiento();

	public void actualizaObjetivos(Map<Integer,Integer> entidades_detonadas);

	public void notificar_tiempo_agotado();

	public void mostrarMensajeFinDelJuego();

	public void mostrarMensajeGanador();

	public void actualizarPuntaje(int puntaje_actual);
}
