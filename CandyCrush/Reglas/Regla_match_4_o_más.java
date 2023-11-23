package Reglas;

import java.util.HashSet;
import java.util.Set;

import Entidades.Entidad;
import Entidades.PotenciadorHorizontal;
import Entidades.PotenciadorVertical;


public class Regla_match_4_o_más implements Regla{
    
    Set<Entidad> set_aux=new HashSet<Entidad>();
    Entidad aux=null;
        

    public EfectoRegla verificar_regla(Entidad[][] entidades, Entidad entidadOrigen, Entidad entidadDestino) {
        EfectoRegla ER= new EfectoRegla();
        
        // Verificar coincidencias en fila y columna
        
        boolean coincidenciaFilaOrigen = verificarCoincidencias(entidades, entidadOrigen.get_fila(), entidadOrigen.get_columna(), true);
        if(coincidenciaFilaOrigen){
            for(Entidad e: set_aux){
                ER.get_entidades_verificadas_a_detonar().add(e);
            }
            aux= new PotenciadorHorizontal(entidadOrigen.get_tablero(), entidadOrigen.get_fila(), entidadOrigen.get_columna(), entidadOrigen.get_color(), false);
            ER.entidades_a_incorporar.add(aux);
            ER.entidades_a_incorporar.add(aux);
        }

       
        boolean coincidenciaFilaDestino = verificarCoincidencias(entidades, entidadDestino.get_fila(), entidadDestino.get_columna(), true);
        if(coincidenciaFilaDestino){
            for(Entidad e: set_aux){
                ER.get_entidades_verificadas_a_detonar().add(e);
            }
            aux= new PotenciadorHorizontal(entidadDestino.get_tablero(), entidadDestino.get_fila(), entidadDestino.get_columna(), entidadDestino.get_color(), false);
            ER.entidades_a_incorporar.add(aux);
            ER.entidades_a_incorporar.add(aux);
        }

        
        boolean coincidenciaColumnaOrigen = verificarCoincidencias(entidades, entidadOrigen.get_fila(), entidadOrigen.get_columna(), false);
        if(coincidenciaColumnaOrigen){
            for(Entidad e: set_aux){
                ER.get_entidades_verificadas_a_detonar().add(e);
            }
            aux= new PotenciadorVertical(entidadOrigen.get_tablero(), entidadOrigen.get_fila(), entidadOrigen.get_columna(), entidadOrigen.get_color(), false);
            ER.entidades_a_incorporar.add(aux);
            ER.entidades_a_incorporar.add(aux);
        }

        
        boolean coincidenciaColumnaDestino = verificarCoincidencias(entidades, entidadDestino.get_fila(), entidadDestino.get_columna(), false);
        if(coincidenciaColumnaDestino){
            for(Entidad e: set_aux){
                ER.get_entidades_verificadas_a_detonar().add(e);
            }
            aux= new PotenciadorVertical(entidadDestino.get_tablero(), entidadDestino.get_fila(), entidadDestino.get_columna(), entidadDestino.get_color(), false);
            ER.entidades_a_incorporar.add(aux);
            ER.entidades_a_incorporar.add(aux);
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
            for (int i = columna - 1; i >= 0 && entidades[fila][i].se_produce_match_con(entidades[fila][columna]); i--) {
                contador++;
                set_aux.add(entidades[fila][i]);
            }

            // Verificar a la derecha
            for (int i = columna + 1; i < columnas && entidades[fila][i].se_produce_match_con(entidades[fila][columna]); i++) {
                contador++;
                set_aux.add(entidades[fila][i]);
            }
        } else { // Verificar en la columna
            // Verificar hacia arriba
            
            for (int i = fila - 1; i >= 0 && entidades[i][columna].se_produce_match_con(entidades[fila][columna]); i--) {
                contador++;
                set_aux.add(entidades[i][columna]);
            }

            // Verificar hacia abajo
            for (int i = fila + 1; i < filas && entidades[i][columna].se_produce_match_con(entidades[fila][columna]); i++) {
                contador++;
                set_aux.add(entidades[i][columna]);
            }
        }
        return contador>=4;
    }
    
}
