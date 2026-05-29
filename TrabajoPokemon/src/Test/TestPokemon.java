package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EjercicioPokemon.Entrenador;
import EjercicioPokemon.Estado;
import EjercicioPokemon.Habilidad;
import EjercicioPokemon.Pokemon;
import EjercicioPokemon.Tipo;

class TestPokemon {

	@Test
	void testPokemon_PikachuValido() {
		Pokemon p = new Pokemon("Pikachu", Tipo.ELECTRICO);

		Habilidad impactrueno = new Habilidad("Impactrueno", Tipo.ELECTRICO, 40);
		p.aprenderHabilidad(impactrueno);

		assertNotNull(p.getNombre());
		assertFalse(p.getNombre().isBlank());
		assertNotNull(p.getHabilidades());
		assertEquals(1, p.getHabilidades().size());
		assertTrue(p.getVidaMax() > 0);
		assertTrue(p.getDefensa() > 0);
		assertEquals(Estado.ACTIVO, p.getEstado());
	}

	@Test
	void testPokemon_CharizardDebilitado() {
		Pokemon p = new Pokemon("Charizard", Tipo.FUEGO);
		p.aprenderHabilidad(new Habilidad("Llamarada", Tipo.FUEGO, 90));
		p.aprenderHabilidad(new Habilidad("Vuelo", Tipo.VOLADOR, 80));
		assertEquals("Charizard", p.getNombre());
		assertEquals(2, p.getHabilidades().size());
		assertNotNull(p.getEstado());
		assertTrue(p.getVidaMax() > 0); // CP 4
		assertTrue(p.getDefensa() > 0); // CP 5
	}

	@Test
	void testPokemon_NombreVacio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon("", Tipo.AGUA);
		});
	}

	@Test
	void testPokemon_NombreNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Pokemon(null, Tipo.AGUA);
		});
	}

	@Test
	void testPokemon_VidaMaxSiemprePositiva() {
		Pokemon eevee = new Pokemon("Eevee", Tipo.NORMAL);
		assertTrue(eevee.getVidaMax() > 0,
				"La vida máxima debe ser positiva; CP 4.1 queda cubierto porque el constructor no admite vida negativa");
		assertTrue(eevee.getVidaActual() > 0);
	}

	@Test
	void testPokemon_DanoMinimoUno() {
		Pokemon onix = new Pokemon("Onix", Tipo.ROCA);
		int vidaAntes = onix.getVidaActual();
		onix.recibirDano(1);
		assertEquals(vidaAntes - 1, onix.getVidaActual(), "El daño mínimo debe ser 1");
	}

	@Test
	void testPokemon_EstadoInvalidoDormido() {

		Pokemon p = new Pokemon("Mew", Tipo.PSIQUICO);

		Estado estado = p.getEstado();

		assertNotEquals("Dormido", estado.toString());

		assertTrue(estado == Estado.ACTIVO || estado == Estado.DEBILITADO);
	}

	@Test
	void testPokemon_SinHabilidades() {
		Pokemon ditto = new Pokemon("Ditto", Tipo.NORMAL);
		assertEquals(0, ditto.getHabilidades().size(), "Un Pokémon recién creado no tiene habilidades");
	}

	@Test
	void testPokemon_NoMasDeCuatroHabilidades() {
		Pokemon dragonite = new Pokemon("Dragonite", Tipo.DRAGON);
		dragonite.aprenderHabilidad(new Habilidad("Llamarada", Tipo.FUEGO, 90));
		dragonite.aprenderHabilidad(new Habilidad("Hidrobomba", Tipo.AGUA, 110));
		dragonite.aprenderHabilidad(new Habilidad("Látigo Cepa", Tipo.PLANTA, 45));
		dragonite.aprenderHabilidad(new Habilidad("Robustez", Tipo.ROCA, 60));
		dragonite.aprenderHabilidad(new Habilidad("Trueno", Tipo.ELECTRICO, 110));

		assertEquals(4, dragonite.getHabilidades().size(), "Un Pokémon no puede aprender más de 4 habilidades");
	}

	@Test
	void testHabilidad_ImpactruenoBValida() {
		Habilidad h = new Habilidad("Impactrueno", Tipo.ELECTRICO, 40);
		assertNotNull(h.getNombre());
		assertFalse(h.getNombre().isBlank());
		assertNotNull(h.getTipo());
		assertTrue(h.getPotencia() > 0);
	}

	@Test
	void testHabilidad_LlamaradaValida() {
		Habilidad h = new Habilidad("Llamarada", Tipo.FUEGO, 90);
		assertEquals("Llamarada", h.getNombre());
		assertEquals(Tipo.FUEGO, h.getTipo());
		assertEquals(90, h.getPotencia());
	}

	@Test
	void testHabilidad_NombreVacio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("", Tipo.AGUA, 50);
		});
	}

	@Test
	void testHabilidad_TipoNull() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("Hidrobomba", null, 110);
		});
	}

	@Test
	void testHabilidad_PotenciaNegativa() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("Placaje", Tipo.NORMAL, -10);
		});
	}

	@Test
	void testEntrenador_AshValido() {
		Entrenador ash = new Entrenador("Ash", 'B');
		ash.añadirPokemon(new Pokemon("Pikachu", Tipo.ELECTRICO));
		ash.añadirPokemon(new Pokemon("Charizard", Tipo.FUEGO));
		ash.añadirPokemon(new Pokemon("Squirtle", Tipo.AGUA));

		assertNotNull(ash.getNombre());
		assertFalse(ash.getNombre().isBlank());
		assertEquals(3, ash.getEquipo().size());
		assertTrue(ash.tienePokemonDisponibles());
		assertNotNull(ash.getActivo());
	}

	@Test
	void testHabilidad_TodosParametrosInvalidos() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Habilidad("", null, -5);
		});
	}

	@Test
	void testEntrenador_RedConPokemonDebilitado() {
		Entrenador red = new Entrenador("Red", 'Z');
		Pokemon raichu = new Pokemon("Raichu", Tipo.ELECTRICO);
		raichu.recibirDano(9999);
		red.añadirPokemon(raichu);

		assertEquals("Red", red.getNombre());
		assertEquals(1, red.getEquipo().size());
		assertFalse(red.tienePokemonDisponibles());
		assertEquals(1, red.contarDebilitados());
	}

	@Test
	void testEntrenador_NombreVacio() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Entrenador("", 'C');
		});
	}

	@Test
	void testEntrenador_SinPokemon() {
		Entrenador brock = new Entrenador("Brock", 'D');
		assertEquals(0, brock.getEquipo().size(), "El entrenador no debería tener Pokémon");
		assertFalse(brock.tienePokemonDisponibles());
	}

	@Test
	void testEntrenador_MaximoPokemon() {
		Entrenador gary = new Entrenador("Gary", 'A');
		for (int i = 0; i < 8; i++) {
			gary.añadirPokemon(new Pokemon("Pokemon" + i, Tipo.NORMAL));
		}
		assertEquals(6, gary.getEquipo().size(), "El entrenador no puede tener más de 6 Pokémon");
	}

	@Test
	void testEntrenador_TodosDebilitados() {
		Entrenador misty = new Entrenador("Misty", 'B');
		Pokemon p1 = new Pokemon("Starmie", Tipo.AGUA);
		Pokemon p2 = new Pokemon("Goldeen", Tipo.AGUA);
		p1.recibirDano(9999);
		p2.recibirDano(9999);
		misty.añadirPokemon(p1);
		misty.añadirPokemon(p2);

		assertFalse(misty.tienePokemonDisponibles(), "Misty no tiene Pokémon disponibles (todos debilitados)");
		assertEquals(2, misty.contarDebilitados());
	}

	@Test
	void testEntrenador_RangoInvalido() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Entrenador("Lance", '1');
		});
	}

	@Test
	void testEntrenador_CambiarActivoDebilitado() {
		Entrenador e = new Entrenador("Trainer", 'C');
		Pokemon p1 = new Pokemon("Bulbasaur", Tipo.PLANTA);
		Pokemon p2 = new Pokemon("Ivysaur", Tipo.PLANTA);
		p2.recibirDano(9999);
		e.añadirPokemon(p1);
		e.añadirPokemon(p2);

		e.cambiarActivo(1);
		assertEquals(p1, e.getActivo(), "No se puede cambiar al Pokémon debilitado");
	}

	@Test
	void testEntrenador_CambiarActivoValido() {
		Entrenador e = new Entrenador("Trainer", 'C');
		Pokemon p1 = new Pokemon("Charmander", Tipo.FUEGO);
		Pokemon p2 = new Pokemon("Charmeleon", Tipo.FUEGO);
		e.añadirPokemon(p1);
		e.añadirPokemon(p2);

		e.cambiarActivo(1);
		assertEquals(p2, e.getActivo());
	}
}
