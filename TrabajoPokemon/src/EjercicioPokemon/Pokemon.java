package EjercicioPokemon;
 
import java.util.ArrayList;
 
public class Pokemon {
 
    private static final int VIDA_BASE = 100;
    private static final int DEFENSA_BASE = 20;
 
    private String nombre;
    private Tipo tipo;
    private int vidaMax;
    private int vidaActual;
    private int defensa;
    private Estado estado;
    private ArrayList<Habilidad> habilidades;
 
    public Pokemon(String nombre, Tipo tipo) {
 
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
 
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo no puede ser null");
        }
 
        this.nombre = nombre;
        this.tipo = tipo;
        this.vidaMax = VIDA_BASE;
        this.vidaActual = VIDA_BASE;
        this.defensa = DEFENSA_BASE;
        this.estado = Estado.ACTIVO;
        this.habilidades = new ArrayList<>();
    }
 
    public void recibirDano(int potenciaAtaque) {
 
        int dano = potenciaAtaque - defensa;
 
        if (dano < 1) {
            dano = 1;
        }
 
        vidaActual -= dano;
 
        if (vidaActual <= 0) {
            vidaActual = 0;
            estado = Estado.DEBILITADO;
        }
    }
 
    public void curarse() {
        vidaActual = vidaMax;
        estado = Estado.ACTIVO;
    }
 
    public void reiniciarEstado() {
 
        if (estado == Estado.DEBILITADO) {
            estado = Estado.ACTIVO;
            vidaActual = vidaMax;
        }
    }
 
    public boolean estaDebilitado() {
        return estado == Estado.DEBILITADO;
    }
 
    public void aprenderHabilidad(Habilidad h) {
 
        if (h == null) {
            return;
        }
 
        if (habilidades.size() < 4) {
            habilidades.add(h);
        } else {
            System.out.println(nombre + " ya tiene 4 habilidades.");
        }
    }
 
    public void mostrarPokemon() {
 
        System.out.println(
                "Pokemon: " + nombre + " [" + tipo + "] | HP: " +
                vidaActual + "/" + vidaMax + " | Estado: " + estado);
 
        if (!habilidades.isEmpty()) {
 
            System.out.println("Habilidades:");
 
            for (Habilidad h : habilidades) {
                h.mostrarHabilidad();
            }
        }
    }
 
    @Override
    public String toString() {
        return nombre + " [" + tipo + "]";
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public Tipo getTipo() {
        return tipo;
    }
 
    public ArrayList<Habilidad> getHabilidades() {
        return habilidades;
    }
 
    public Estado getEstado() {
        return estado;
    }
 
    public int getVidaActual() {
        return vidaActual;
    }
 
    public int getVidaMax() {
        return vidaMax;
    }
 
    public int getDefensa() {
        return defensa;
    }
}