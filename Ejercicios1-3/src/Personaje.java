import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Personaje {

    private String nombre;
    private String herramienta;
    private int edad;
    private double peso;
    private boolean usado;

    public Personaje(String nombre, String herramienta, int edad, double peso, boolean usado) {
        setNombre(nombre);
        setHerramienta(herramienta);
        setEdad(edad);
        setPeso(peso);
        this.usado = usado;
    }

    public Personaje() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre == null){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }else{
            this.nombre = nombre;
        }
    }

    public String getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        if(herramienta == null){
            throw new IllegalArgumentException("La herramienta es obligatorio");
        }else{
            this.herramienta = herramienta;
        }

    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if(edad <= 0){
            throw new IllegalArgumentException("El edad no puede ser menor a 0");
        }else{
            this.edad = edad;
        }

    }

    public double getPeso() {
        return peso;

    }

    public void setPeso(double peso) {
        if(peso <= 0){
            throw new IllegalArgumentException("El peso no puede ser menor a 0");
        }else{
            this.peso = peso;
        }
    }

    public boolean isUsado() {
        return usado;
    }

    public void setUsado(boolean usado) {
        this.usado = usado;
    }

    public void toCSV(List<Personaje> personajes, String archivoCSV) {
        File file = new File(archivoCSV);
        try (FileWriter fw = new FileWriter(file, true);) {
            // Escribir cada personaje
            for (Personaje personaje : personajes) {
                String[] datos = {
                        personaje.getNombre(),
                        personaje.getHerramienta(),
                        String.valueOf(personaje.getEdad()),
                        String.valueOf(personaje.getPeso()),
                        String.valueOf(personaje.isUsado())
                };
                fw.write(String.join(",", datos) + "\n");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void escribirFinalCSV(Personaje personaje, String archivoCSV) {
        File file = new File(archivoCSV);
        try(FileWriter fw = new FileWriter(file, true)){
            String[] datos = {
                    personaje.getNombre(),
                    personaje.getHerramienta(),
                    String.valueOf(personaje.getEdad()),
                    String.valueOf(personaje.getPeso()),
                    String.valueOf(personaje.isUsado())

            };
            fw.write(String.join(",", datos) + "\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Personaje> leerCSV(String archivoCSV) {
        List<Personaje> personajes = new ArrayList<>();
        String linea;

        File file = new File(archivoCSV);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");

                String nombre = datos[0];
                String herramienta = datos[1];
                int edad = Integer.parseInt(datos[2]);
                double peso = Double.parseDouble(datos[3]);
                boolean usado = Boolean.parseBoolean(datos[4]);

                personajes.add(new Personaje(nombre, herramienta, edad, peso, usado));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return personajes;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " \n " + "Herramienta: "+ herramienta + " \n " + "Edad: " + edad + " \n "+"Peso: " + peso + "\n" + "Usado: " + usado;
    }


}
