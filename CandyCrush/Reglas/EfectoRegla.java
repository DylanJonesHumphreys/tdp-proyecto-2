package Reglas;

import java.util.HashSet;
import java.util.Set;

import Entidades.Entidad;

public class EfectoRegla {
    protected Set<Entidad> entidades_verificadas_a_detonar;
    protected Set<Entidad> entidades_a_incorporar;

    public EfectoRegla(){
        entidades_verificadas_a_detonar=new HashSet<Entidad>();
        entidades_a_incorporar=new HashSet<Entidad>();
    }

    public Set<Entidad> get_entidades_verificadas_a_detonar(){
        return entidades_verificadas_a_detonar;
    }

    public Set<Entidad> get_entidades_a_incorporar(){
        return entidades_a_incorporar;
    }

}
