package Reglas;

import java.util.HashSet;
import java.util.Set;

import Entidades.Entidad;

public class Regla_match_3 implements Regla{

    Set<Entidad> set_aux=new HashSet<Entidad>();
    Entidad aux=null;

   
        
    public EfectoRegla verificar_regla(Entidad[][] entidades, Entidad entidadOrigen, Entidad entidadDestino) {
        EfectoRegla ER=new EfectoRegla();
        
        // Verificar coincidencias en fila y columna
       
        boolean coincidenciaFilaDestino = verificarCoincidencias(entidades, entidadDestino.get_fila(), entidadDestino.get_columna(), true);
        if(coincidenciaFilaDestino){
            for (Entidad e: set_aux){
                ER.get_entidades_verificadas_a_detonar().add(e);
            }
        }

        
        boolean coincidenciaFilaOrigen = verificarCoincidencias(entidades, entidadOrigen.get_fila(), entidadOrigen.get_columna(), true);
        if(coincidenciaFilaOrigen){
            for (Entidad e: set_aux){
                ER.get_entidades_verificadas_a_detonar().add(e);
            }
        
        }

        
        boolean coincidenciaColumnaDestino = verificarCoincidencias(entidades, entidadDestino.get_fila(), entidadDestino.get_columna(), false);
        if(coincidenciaColumnaDestino){
            for (Entidad e: set_aux){
                ER.get_entidades_verificadas_a_detonar().add(e);
            }
        }

        boolean coincidenciaColumnaOrigen = verificarCoincidencias(entidades, entidadOrigen.get_fila(), entidadOrigen.get_columna(), false);
        if(coincidenciaColumnaOrigen){
            for (Entidad e: set_aux){
                ER.get_entidades_verificadas_a_detonar().add(e);
            }
        }

        // Devolver true si hay coincidencias en fila o columna
        return ER;
    }

    // Método para verificar coincidencias en fila o columna

    
    private boolean verificarCoincidencias(Entidad[][] entidades, int fila, int columna, boolean verificarFila) {
        int filas = entidades.length;
        int columnas = entidades[0].length;

        int contador = 1;
        set_aux.clear(); // Limpiar el set de coincidencias
        set_aux.add(entidades[fila][columna]);


        // Verificar en la fila
        if (verificarFila) {
            // Verificar a la izquierda
            for (int i = columna - 1; (i >= 0 && contador<3) && entidades[fila][i].se_produce_match_con(entidades[fila][columna]); i--) {
                contador++;
                set_aux.add(entidades[fila][i]);
            }

            // Verificar a la derecha
            for (int i = columna + 1; (i < columnas && contador<3) && entidades[fila][i].se_produce_match_con(entidades[fila][columna]); i++) {
                contador++;
                set_aux.add(entidades[fila][i]);
            }
        } else { 
        // Verificar en la columna
            // Verificar hacia arriba
            for (int i = fila - 1; (i >= 0 && contador<3) && entidades[i][columna].se_produce_match_con(entidades[fila][columna]); i--) {
                contador++;
                set_aux.add(entidades[i][columna]);
            }

            // Verificar hacia abajo
            for (int i = fila + 1; (i < filas && contador<3) && entidades[i][columna].se_produce_match_con(entidades[fila][columna]); i++) {
                contador++;
                set_aux.add(entidades[i][columna]);
            }
        }
        // Devolver false si no hay 3 coincidencias
        return contador>=3;
    }
}
