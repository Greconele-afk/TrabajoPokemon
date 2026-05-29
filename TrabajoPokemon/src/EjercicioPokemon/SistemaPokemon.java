package EjercicioPokemon;

import java.util.ArrayList;
import java.util.List;

public class SistemaPokemon {

	private List<Entrenador> entrenadores;
	private List<Habilidad> biblioteca;

	public SistemaPokemon() {

		this.entrenadores = new ArrayList<>();
		this.biblioteca = new ArrayList<>();
	}

	public void añadirEntrenador(Entrenador e) {
		entrenadores.add(e);
	}

	public void añadirHabilidad(Habilidad h) {
		biblioteca.add(h);
	}

	public List<Entrenador> getEntrenadores() {
		return entrenadores;
	}

	public List<Habilidad> getBiblioteca() {
		return biblioteca;
	}
}
