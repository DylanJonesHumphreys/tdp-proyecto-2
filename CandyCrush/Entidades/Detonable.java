package Entidades;

import java.util.List;
import java.util.Set;

public interface Detonable {

	public void detonar();

	public boolean detona_de_a_dos(Entidad entidad);

	public boolean detona_de_a_dos_con(Caramelo caramelo);

	public boolean detona_de_a_dos_con(Glaseado glaseado);

	public boolean detona_de_a_dos_con(Potenciador potenciador);

	public boolean detona_de_a_dos_con(Gelatina gelatina);

	public Set<Entidad> get_entidades_alcanzadas_por_detonacion(Entidad[][] entidades);

	public List<Entidad> efecto_detonacion(Entidad[][] entidades);

}
