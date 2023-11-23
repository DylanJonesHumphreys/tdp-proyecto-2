package Logica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import GUI.Ventana;

public class Tiempo extends JLabel {

    private int tiempoRestante;
    private Timer timer;
    private JLabel tiempoLabel;
    private Juego juego;

    public Tiempo(int segundos, Juego juego) {
        this.juego=juego;
        tiempoRestante = segundos;
        tiempoLabel = new JLabel("Tiempo restante: " + tiempoRestante + " segundos.");
        add(tiempoLabel);
        this.setText("Tiempo restante: " + tiempoRestante + " segundos.");
        if(segundos!=0){
            iniciarTiempo();
        }
    }

    public void iniciarTiempo() {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tiempoRestante > 0) {
                    tiempoRestante--;
                    setText("Tiempo restante: " + tiempoRestante + " segundos.");
                }else{
                    if (tiempoRestante == 0) {
                        timer.stop();
                        juego.notificar_tiempo_agotado();
                        //setText("¡Tiempo agotado!");
                       
                    }
                }
            }
        });
        timer.start();
    }

    public int getTiempoRestante(){
        return tiempoRestante;
    }

    public void reiniciarTiempo(int segundos) {
        // Detener el temporizador actual si está en ejecución
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        // Restablecer el tiempo restante
        tiempoRestante = segundos;

        // Actualizar la etiqueta con el nuevo tiempo restante
        setText("Tiempo restante: " + tiempoRestante + " segundos.");

        // Iniciar el temporizador nuevamente si el tiempo no es cero
        if (tiempoRestante != 0) {
            iniciarTiempo();
        }
    }

    /* public static void main(String[] args) {
        int tiempoMaximo = 120;  // 2 minutos
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Tiempo(tiempoMaximo);
            }
        });
    } */
}
