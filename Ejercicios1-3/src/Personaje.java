public class Personaje {

    private String nombre;
    private String herramienta;
    private int edad;
    private float peso;
    private boolean usado;

    public Personaje(String nombre, String herramienta, int edad, float peso, boolean usado) {
        this.nombre = nombre;
        this.herramienta = herramienta;
        this.edad = edad;
        this.peso = peso;
        this.usado = usado;
    }

    public Personaje(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }
}
