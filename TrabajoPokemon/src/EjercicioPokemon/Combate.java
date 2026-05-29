package EjercicioPokemon;

public class Combate {

	private Entrenador jugador1;
	private Entrenador jugador2;
	private int turno;
	private Entrenador ganador;

	public Combate(Entrenador j1, Entrenador j2) {

		this.jugador1 = j1;
		this.jugador2 = j2;
		this.turno = 1;
	}

	public void realizarAtaque(int indiceHabilidad) {

		Entrenador atacante;
		Entrenador defensor;

		if (turno == 1) {

			atacante = jugador1;
			defensor = jugador2;

		} else {

			atacante = jugador2;
			defensor = jugador1;
		}

		Pokemon pAtacante = atacante.getActivo();
		Pokemon pDefensor = defensor.getActivo();

		if (indiceHabilidad < 0 || indiceHabilidad >= pAtacante.getHabilidades().size()) {

			System.out.println("Ataque invalido.");
			return;
		}

		Habilidad h = pAtacante.getHabilidades().get(indiceHabilidad);

		System.out.println("¡" + pAtacante.getNombre() + " usa " + h.getNombre() + "!");

		pDefensor.recibirDano(h.getPotencia());

		if (pDefensor.estaDebilitado()) {

			System.out.println(pDefensor.getNombre() + " se ha debilitado.");
		}

		comprobarFinal();

		if (turno == 1) {
			turno = 2;
		} else {
			turno = 1;
		}
	}

	public void comprobarFinal() {

		if (!jugador1.tienePokemonDisponibles()) {

			ganador = jugador2;

		} else if (!jugador2.tienePokemonDisponibles()) {

			ganador = jugador1;
		}
	}

	public Entrenador getAtacanteActual() {

		if (turno == 1) {
			return jugador1;
		}

		return jugador2;
	}

	public boolean haTerminado() {
		return ganador != null;
	}

	public Entrenador getGanador() {
		return ganador;
	}
}