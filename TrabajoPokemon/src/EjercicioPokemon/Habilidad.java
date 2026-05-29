package EjercicioPokemon;
 
public class Habilidad {
 
    private String nombre;
    private Tipo tipo;
    private int potencia;
 
    public Habilidad(String nombre, Tipo tipo, int potencia) {
 
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Nombre inválido");
        }
 
        if (tipo == null) {
            throw new IllegalArgumentException("Tipo inválido");
        }
 
        if (potencia <= 0) {
            throw new IllegalArgumentException("Potencia inválida");
        }
 
        this.nombre = nombre;
        this.tipo = tipo;
        this.potencia = potencia;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public Tipo getTipo() {
        return tipo;
    }
 
    public int getPotencia() {
        return potencia;
    }
 
    @Override
    public String toString() {
        return nombre + " | Tipo: " + tipo + " | Potencia: " + potencia;
    }
 
    public void mostrarHabilidad() {
        System.out.println("   - " + toString());
    }
}