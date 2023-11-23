package Reglas;

import java.util.HashSet;
import java.util.Set;

import Entidades.Entidad;
import Entidades.Envuelto;

public class Regla_match_L implements Regla{

    Entidad aux=null;
   
    
    protected Set<Entidad> set_vertical = new HashSet<Entidad>();
    protected Set<Entidad> set_horizontal = new HashSet<Entidad>();

    public EfectoRegla verificar_regla(Entidad[][] entidades, Entidad entidadOrigen, Entidad entidadDestino) {
        EfectoRegla ER= new EfectoRegla();

        // Verificar coincidencias en forma de L
        boolean coincidenciaFormaLOrigen = verificarCoincidenciasFormaL(entidades, entidadOrigen.get_fila(), entidadOrigen.get_columna());
        if (coincidenciaFormaLOrigen) {
            ER.entidades_verificadas_a_detonar.addAll(set_vertical);
            ER.entidades_verificadas_a_detonar.addAll(set_horizontal);
            ER.entidades_verificadas_a_detonar.addAll(set_vertical);
            ER.entidades_verificadas_a_detonar.addAll(set_horizontal);
        }

        boolean coincidenciaFormaLDestino = verificarCoincidenciasFormaL(entidades, entidadDestino.get_fila(), entidadDestino.get_columna());
        if (coincidenciaFormaLDestino) {
            ER.entidades_verificadas_a_detonar.addAll(set_vertical);
            ER.entidades_verificadas_a_detonar.addAll(set_horizontal);
            ER.entidades_verificadas_a_detonar.addAll(set_vertical);
            ER.entidades_verificadas_a_detonar.addAll(set_horizontal);
        }


        //coincidencias en forma de L desde entidadOrigen
        if (coincidenciaFormaLOrigen){
            aux= new Envuelto(entidadOrigen.get_tablero(), entidadOrigen.get_fila(), entidadOrigen.get_columna(), entidadOrigen.get_color(),false);
            ER.entidades_a_incorporar.add(aux);
            aux= new Envuelto(entidadOrigen.get_tablero(), entidadOrigen.get_fila(), entidadOrigen.get_columna(), entidadOrigen.get_color(),false);
            ER.entidades_a_incorporar.add(aux);
        } 
        //coincidencias en forma de L desde entidadDestino
        if (coincidenciaFormaLDestino){
            aux= new Envuelto(entidadDestino.get_tablero(), entidadDestino.get_fila(), entidadDestino.get_columna(), entidadDestino.get_color(),false);
            ER.entidades_a_incorporar.add(aux);
            aux= new Envuelto(entidadDestino.get_tablero(), entidadDestino.get_fila(), entidadDestino.get_columna(), entidadDestino.get_color(),false);
            ER.entidades_a_incorporar.add(aux);
        }
        return ER;
    }

    // Método para verificar coincidencias en forma de L
    private boolean verificarCoincidenciasFormaL(Entidad[][] entidades, int fila, int columna) {
        int filas = entidades.length;
        int columnas = entidades[0].length;

        set_vertical.clear(); // Limpiar el set de coincidencias
        set_horizontal.clear();
        set_vertical.add(entidades[fila][columna]);
        set_horizontal.add(entidades[fila][columna]);
        int contadorVertical=1;
        int contadorHorizontal=1;

        // Verificar hacia arriba
        for (int i = fila - 1; i >= 0 && entidades[i][columna].se_produce_match_con(entidades[fila][columna]); i--) {
            set_vertical.add(entidades[i][columna]);
            contadorVertical++;
        }
        if (contadorVertical<=2){
            set_vertical.clear(); // Limpiar el set de coincidencias
            set_vertical.add(entidades[fila][columna]); //agrego de nuevo la pos donde estoy
            contadorVertical=1;
            // Verificar hacia abajo
            for (int i = fila + 1; i < filas && entidades[i][columna].se_produce_match_con(entidades[fila][columna]); i++) {
                set_vertical.add(entidades[i][columna]);
                contadorVertical++;
            }
            if (contadorVertical<=2){
                set_vertical.clear();
                set_vertical.add(entidades[fila][columna]);//agrego de nuevo la pos donde estoy
                contadorVertical=1;
            }
        }

        // Verificar hacia la izquierda
        for (int i = columna - 1; i >= 0 && entidades[fila][i].se_produce_match_con(entidades[fila][columna]); i--) {
            set_horizontal.add(entidades[fila][i]);
            contadorHorizontal++;
        }
        if (contadorHorizontal<=2){
            set_horizontal.clear();// Limpiar el set de coincidencias
            set_horizontal.add(entidades[fila][columna]);//agrego de nuevo la pos donde estoy
            contadorHorizontal=1;
            // Verificar hacia la derecha
            for (int i = columna + 1; i < columnas && entidades[fila][i].se_produce_match_con(entidades[fila][columna]); i++) {
                set_vertical.add(entidades[fila][i]);
                contadorHorizontal++;
            }
            if(contadorHorizontal<=2){
                set_horizontal.clear();
                set_horizontal.add(entidades[fila][columna]);//agrego de nuevo la pos donde estoy
                contadorHorizontal=1;
            }
        }
        return (contadorHorizontal>=3 && contadorVertical>=3); // Tiene que haber match de tres arriba/abajo y match de tres izquierda/derecha
    }
    
}
