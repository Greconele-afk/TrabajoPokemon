package EjercicioPokemon;
 
import java.util.ArrayList;
import java.util.List;
 
public class Entrenador extends Personaje {
 
    private List<Pokemon> equipo;
    private Pokemon activo;
    private char rango;
 
    public Entrenador(String nombre, char rango) {
 
        super(nombre);
 
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido");
        }
 
        if (rango < 'A' || rango > 'Z') {
            throw new IllegalArgumentException("Rango inválido");
        }
 
        this.rango = rango;
        this.equipo = new ArrayList<>();
    }
 
    public void añadirPokemon(Pokemon p) {
 
        if (p == null) {
            return;
        }
 
        if (equipo.size() < 6) {
 
            equipo.add(p);
 
            if (activo == null) {
                activo = p;
            }
 
        } else {
 
            System.out.println("El equipo ya está completo.");
        }
    }
 
    public boolean tienePokemonDisponibles() {
 
        for (Pokemon p : equipo) {
 
            if (!p.estaDebilitado()) {
                return true;
            }
        }
 
        return false;
    }
 
    public int contarDebilitados() {
 
        int contador = 0;
 
        for (Pokemon p : equipo) {
 
            if (p.estaDebilitado()) {
                contador++;
            }
        }
 
        return contador;
    }
 
    public void cambiarActivo(int indice) {
 
        if (indice >= 0 && indice < equipo.size()) {
 
            Pokemon p = equipo.get(indice);
 
            if (!p.estaDebilitado()) {
                activo = p;
            }
        }
    }
 
    public void subirRango() {
 
        if (rango > 'A') {
            rango--;
        }
    }
 
    public void bajarRango() {
 
        if (rango < 'Z') {
            rango++;
        }
    }
 
    public void mostrarEntrenador() {
 
        System.out.println("\nEntrenador: " + nombre + " | Rango: " + rango);
        System.out.println("Equipo:");
 
        for (Pokemon p : equipo) {
            p.mostrarPokemon();
        }
    }
 
    public Pokemon getActivo() {
        return activo;
    }
 
    public List<Pokemon> getEquipo() {
        return equipo;
    }
 
    public char getRango() {
        return rango;
    }
}