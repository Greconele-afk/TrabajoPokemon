package EjercicioPokemon;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner teclado = new Scanner(System.in);
		SistemaPokemon sistema = new SistemaPokemon();

		int opcion;

		do {

			System.out.println("\n---- MENU ----");
			System.out.println("0) Salir");
			System.out.println("1) Crear entrenador");
			System.out.println("2) Crear habilidad");
			System.out.println("3) Crear pokemon");
			System.out.println("4) Ver entrenadores");
			System.out.println("5) Iniciar combate");

			opcion = teclado.nextInt();
			teclado.nextLine();

			switch (opcion) {

			case 1:

				System.out.println("Nombre del entrenador:");
				String nombre = teclado.nextLine();

				System.out.println("Rango:");
				char rango = teclado.nextLine().toUpperCase().charAt(0);

				sistema.añadirEntrenador(new Entrenador(nombre, rango));

				break;

			case 2:

				System.out.println("Nombre habilidad:");
				nombre = teclado.nextLine();

				System.out.println("Potencia:");
				int potencia = teclado.nextInt();
				teclado.nextLine();

				System.out.println("Tipo:");
				Tipo tipo = Tipo.valueOf(teclado.nextLine().toUpperCase());

				sistema.añadirHabilidad(new Habilidad(nombre, tipo, potencia));

				break;

			case 3:

				if (sistema.getEntrenadores().isEmpty()) {

					System.out.println("No hay entrenadores.");
					break;
				}

				for (int i = 0; i < sistema.getEntrenadores().size(); i++) {

					System.out.println(i + ". " + sistema.getEntrenadores().get(i).getNombre());
				}

				System.out.println("Selecciona entrenador:");
				int indice = teclado.nextInt();
				teclado.nextLine();

				System.out.println("Nombre Pokemon:");
				nombre = teclado.nextLine();

				System.out.println("Tipo Pokemon:");
				tipo = Tipo.valueOf(teclado.nextLine().toUpperCase());

				Pokemon p = new Pokemon(nombre, tipo);

				if (!sistema.getBiblioteca().isEmpty()) {

					System.out.println("Selecciona habilidad:");

					for (int i = 0; i < sistema.getBiblioteca().size(); i++) {

						System.out.println(i + ". " + sistema.getBiblioteca().get(i));
					}

					int hab = teclado.nextInt();
					teclado.nextLine();

					if (hab >= 0 && hab < sistema.getBiblioteca().size()) {

						p.aprenderHabilidad(sistema.getBiblioteca().get(hab));
					}
				}

				sistema.getEntrenadores().get(indice).añadirPokemon(p);

				break;

			case 4:

				for (Entrenador e : sistema.getEntrenadores()) {

					e.mostrarEntrenador();
				}

				break;

			case 5:

				if (sistema.getEntrenadores().size() < 2) {

					System.out.println("No hay suficientes entrenadores.");

					break;
				}

				Combate combate = new Combate(sistema.getEntrenadores().get(0), sistema.getEntrenadores().get(1));

				while (!combate.haTerminado()) {

					Entrenador actual = combate.getAtacanteActual();

					Pokemon activo = actual.getActivo();

					System.out.println("\nTurno de " + actual.getNombre());

					System.out.println("Pokemon: " + activo.getNombre());

					for (int i = 0; i < activo.getHabilidades().size(); i++) {

						System.out.println(i + ". " + activo.getHabilidades().get(i));
					}

					int ataque = teclado.nextInt();
					teclado.nextLine();

					combate.realizarAtaque(ataque);
				}

				System.out.println("Ganador: " + combate.getGanador().getNombre());

				break;

			case 0:

				System.out.println("Hasta pronto");
				break;

			default:

				System.out.println("Opcion invalida");
			}

		} while (opcion != 0);

		teclado.close();
	}
}