package Animadores;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import GUI.Celda;
import ManejadorAnimaciones.ManejadorAnimaciones;

public class AnimadorEstadoTdp2 implements Animador {
    
	protected ManejadorAnimaciones manager;
	protected Celda celda_animada;
	protected int prioridad;
	protected String path_imagen_estado;

	public AnimadorEstadoTdp2(ManejadorAnimaciones manager, Celda celda, int color) {
		this.manager = manager;
		this.celda_animada = celda;
		this.prioridad = PrioridadAnimaciones.PRIORIDAD_ESTADO;
		path_imagen_estado = cargar_imagen(color);
	}
	
	public Celda get_celda_asociada() {
		return celda_animada;
	}
	
	public int get_prioridad() {
		return prioridad;
	}

	public void comenzar_animacion() {
		int size_label = celda_animada.get_size_label();
		ImageIcon icono_imagen = new ImageIcon(this.getClass().getResource(path_imagen_estado));
		Image imagen_escalada = icono_imagen.getImage().getScaledInstance(size_label, size_label, Image.SCALE_REPLICATE);
		Icon icono_escalado = new ImageIcon(imagen_escalada);
		celda_animada.setIcon(icono_escalado);
		manager.notificarse_finalizacion_animador(this);
	}

    private String cargar_imagen(int color) {
        path_imagen_estado = "/temas/clasico/imagenes/caramelos_tdp/tdp2/" + color + ".png";
        return path_imagen_estado;
    }
}
