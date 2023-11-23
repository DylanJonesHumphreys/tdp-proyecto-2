package Reglas;

import java.util.HashSet;
import java.util.Set;

import Entidades.Entidad;
    import Entidades.Envuelto;

    public class Regla_match_T implements Regla{
        protected Set<Entidad> set_aux = new HashSet<Entidad>();
        protected Entidad aux=null;

        public EfectoRegla verificar_regla(Entidad[][] entidades, Entidad entidad_con_quien_hizo_intercambio, Entidad entidad_intercambiada_para_formar_match) {
            EfectoRegla ER=new EfectoRegla();
            // Verificar coincidencias en forma de T
            boolean coincidenciaFormaTOrigen = verificarCoincidenciasFormaT(entidades, entidad_intercambiada_para_formar_match.get_fila(),entidad_intercambiada_para_formar_match.get_columna(), entidad_con_quien_hizo_intercambio);
            if (coincidenciaFormaTOrigen) {
                ER.entidades_verificadas_a_detonar.addAll(set_aux);      
                
                aux= new Envuelto(entidad_intercambiada_para_formar_match.get_tablero(), entidad_intercambiada_para_formar_match.get_fila(), entidad_intercambiada_para_formar_match.get_columna(), entidad_intercambiada_para_formar_match.get_color(),false);
                ER.entidades_a_incorporar.add(aux);
            }
            // Devolver true si hay coincidencias en forma de T
            return ER;
        }

        // Método para verificar coincidencias en forma de T
        private boolean verificarCoincidenciasFormaT(Entidad[][] entidades, int fila_entidad_intercambiada_para_formar_match, int columna_entidad_intercambiada_para_formar_match, Entidad entidad_con_quien_hizo_intercambio) {
            set_aux.clear(); // Limpiar el set de coincidencias
            set_aux.add(entidades[fila_entidad_intercambiada_para_formar_match][ columna_entidad_intercambiada_para_formar_match]);

            verificar_direccion_movimiento(entidades, fila_entidad_intercambiada_para_formar_match,  columna_entidad_intercambiada_para_formar_match, entidad_con_quien_hizo_intercambio);

            return set_aux.size() == 5; // Se requieren 5 elementos para formar la T
        }

        private void verificar_direccion_movimiento(Entidad[][] entidades, int fila_entidad_intercambiada_para_formar_match, int columna_entidad_intercambiada_para_formar_match, Entidad entidad_con_quien_hizo_intercambio) {
            int fila_entidad_con_quien_hizo_intercambio = entidad_con_quien_hizo_intercambio.get_fila();
            int columna_entidad_con_quien_hizo_intercambio = entidad_con_quien_hizo_intercambio.get_columna();

            if (fila_entidad_con_quien_hizo_intercambio + 1 == fila_entidad_intercambiada_para_formar_match) {
                // Se movio el caramelo hacia abajo
                forma_T_hacia_abajo(entidades, fila_entidad_intercambiada_para_formar_match, columna_entidad_intercambiada_para_formar_match);
            } else {
                if (fila_entidad_con_quien_hizo_intercambio - 1 == fila_entidad_intercambiada_para_formar_match) {
                    // Se movio el caramelo hacia arriba
                    forma_T_hacia_arriba(entidades, fila_entidad_intercambiada_para_formar_match, columna_entidad_intercambiada_para_formar_match);
                } else {
                    if (columna_entidad_con_quien_hizo_intercambio + 1 == columna_entidad_intercambiada_para_formar_match) {
                        // Se movio el caramelo hacia la derecha
                        forma_T_hacia_derecha(entidades, fila_entidad_intercambiada_para_formar_match, columna_entidad_intercambiada_para_formar_match);
                    } else {
                        if (columna_entidad_con_quien_hizo_intercambio - 1 == columna_entidad_intercambiada_para_formar_match) {
                            // Se movio el caramelo hacia la izquierda
                            forma_T_hacia_izquierda(entidades, fila_entidad_intercambiada_para_formar_match, columna_entidad_intercambiada_para_formar_match);
                        }
                    }
                }
            }
        }
        
        private boolean forma_T_hacia_abajo(Entidad[][] entidades, int fila, int columna) {
            int cant_caramelos_hicieron_match_izquierda = 0;
            int cant_caramelos_hicieron_match_derecha = 0;
            int cant_caramelos_hicieron_match_abajo = 0;

            // Verificar hacia la izquierda
            for (int i = columna - 1; i >= 0 && entidades[fila][i].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_izquierda != 1; i--) {
                set_aux.add(entidades[fila][i]);
                cant_caramelos_hicieron_match_izquierda++;
            }
                
            // Verificar hacia la derecha
            for (int i = columna + 1; i < entidades[0].length && entidades[fila][i].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_derecha != 1; i++) {
                set_aux.add(entidades[fila][i]);
                cant_caramelos_hicieron_match_derecha++;
            }

            // Verificar hacia abajo
            for (int i = fila + 1; i < entidades.length && entidades[i][columna].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_abajo != 2; i++) {
                set_aux.add(entidades[i][columna]);
                cant_caramelos_hicieron_match_abajo++;
            }

            return set_aux.size() == 5;    
        }

        
        private boolean forma_T_hacia_arriba(Entidad[][] entidades, int fila, int columna) {
            int cant_caramelos_hicieron_match_izquierda = 0;
            int cant_caramelos_hicieron_match_derecha = 0;
            int cant_caramelos_hicieron_match_arriba = 0;
            
            // Verificar hacia la izquierda
            for (int i = columna - 1; i >= 0 && entidades[fila][i].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_izquierda != 1; i--) {
                set_aux.add(entidades[fila][i]);
                cant_caramelos_hicieron_match_izquierda++;
            }
                
            // Verificar hacia la derecha
            for (int i = columna + 1; i < entidades[0].length && entidades[fila][i].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_derecha != 1; i++) {
                set_aux.add(entidades[fila][i]);
                cant_caramelos_hicieron_match_derecha++;
            }

            // Verificar hacia arriba
                for (int i = fila - 1; i >= 0 && entidades[i][columna].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_arriba != 2; i--) {
                    set_aux.add(entidades[i][columna]);
                    cant_caramelos_hicieron_match_arriba++;
                }

            return set_aux.size() == 5;    
        }

        private boolean forma_T_hacia_izquierda(Entidad[][] entidades, int fila, int columna) {
            int cant_caramelos_hicieron_match_izquierda = 0;
            int cant_caramelos_hicieron_match_abajo = 0;
            int cant_caramelos_hicieron_match_arriba = 0;

            // Verificar hacia la izquierda
            for (int i = columna - 1; i >= 0 && entidades[fila][i].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_izquierda != 2; i--) {
                set_aux.add(entidades[fila][i]);
                cant_caramelos_hicieron_match_izquierda++;
            }
                
            // Verificar hacia la arriba
            for (int i = fila - 1; i >= 0 && entidades[fila][i].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_arriba != 1; i--) {
                set_aux.add(entidades[i][columna]);
                cant_caramelos_hicieron_match_arriba++;
            }

            // Verificar hacia abajo
                for (int i = fila + 1; i < entidades.length && entidades[i][columna].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_abajo != 1; i++) {
                    set_aux.add(entidades[i][columna]);
                    cant_caramelos_hicieron_match_abajo++;
                }

            return set_aux.size() == 5;    
        }

        private boolean forma_T_hacia_derecha(Entidad[][] entidades, int fila, int columna) {
            int cant_caramelos_hicieron_match_derecha = 0;
            int cant_caramelos_hicieron_match_abajo = 0;
            int cant_caramelos_hicieron_match_arriba = 0;

            // Verificar hacia la derecha
            for (int i = columna + 1; i < entidades[0].length && entidades[fila][i].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_derecha != 2; i++) {
                set_aux.add(entidades[fila][i]);
                cant_caramelos_hicieron_match_derecha++;
            }
                
            // Verificar hacia arriba
            for (int i = fila - 1; i >= 0 && entidades[i][columna].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_arriba != 1; i--) {
                set_aux.add(entidades[i][columna]);
                cant_caramelos_hicieron_match_arriba++;
            }

            // Verificar hacia abajo
                for (int i = fila + 1; i < entidades.length && entidades[i][columna].se_produce_match_con(entidades[fila][columna]) && cant_caramelos_hicieron_match_abajo != 1; i++) {
                    set_aux.add(entidades[i][columna]);
                    cant_caramelos_hicieron_match_abajo++;
                }

            return set_aux.size() == 5;    
        }
    }
