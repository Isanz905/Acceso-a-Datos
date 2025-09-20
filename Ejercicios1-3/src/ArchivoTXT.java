import Excepciones.ArchivoNoValido;
import Excepciones.DirectorioNoValido;

import java.io.*;
import java.nio.file.*;
import java.util.Arrays;
import java.util.List;

public class ArchivoTXT {
    public static final String LINE_BREAK = "\n";
    private String archivo;

    public ArchivoTXT(String archivo) {
        setArchivo(archivo);

    }
    public ArchivoTXT() {}


    public String getArchivo(){
        return archivo;
    }

    public void setArchivo(String archivo){
        try {
            Path path = Paths.get(archivo);
            if(path.toFile().exists()){
                this.archivo = archivo;
            }
        }catch(ArchivoNoValido e){
            throw new ArchivoNoValido("Error al abrir archivo " + archivo);
        }
    }
    /**
     *  2. Añade un método aVerso que lea el contenido del archivo y
     *      lo devuelva introduciendo un salto de línea después de cada punto
     * */

    public String aVerso(){
        StringBuilder sb = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(archivo))) {

            String linea;
            while ((linea = reader.readLine()) != null) {

                String salto = linea.replace(".", "\n");
                sb.append(salto);

                return sb.toString();
            }

        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("No se ha encontrado el archivo");
        } catch (IOException e) {
            return "Error al abrir archivo " + archivo;
        }

        return null;
    }

    /**  3. Añade otro método codifica que reciba un String indicando la ruta de otro
     * fichero (puede existir o no). Debe leer el contenido del archivo original, eliminar
     * todas las vocales y escribir el resultado en el archivo recibido como argumento.
     * Usa únicamente FileReader y FileWriter.
     * Añade otros dos métodos (variaciones de este):
     * Un método codificaBuffer usando buffers.
     * Un método codificaFiles obteniendo las clases con buffer a partir de la
     * clase Files
     * */

    public String codifica(String archivo){
        String directorio = new ArchivoTXT(getArchivo()).getArchivo(); // recogemos el archivo del constructor

        if(archivo == null){
            throw new ArchivoNoValido("El archivo no puede ser nulo");

        }
        if(archivo.equals(directorio)){
            throw new ArchivoNoValido("Estas intentando sobreescribir el archivo");
        }


        int caracter;
        String VOCALES = "AEIOUaeiou"; // declaramos las vocales
        try(FileReader reader = new FileReader(directorio);
            FileWriter writer = new FileWriter(archivo, true)
        ) {

            while((caracter = reader.read()) != -1) {
                char c = (char) caracter;
                if(VOCALES.indexOf(c) == -1){
                    writer.write(c);
                }
            }
        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("Archivo no encontrado");
        } catch (IOException e) {
            return "Error al abrir archivo " + archivo;
        }
        return null;
    }

    public String codificaBuffer(String archivo){
        String directorio = new ArchivoTXT(getArchivo()).getArchivo();
        String linea;
        String VOCALES = "AEIOUaeiou";

        if(archivo == null){
            throw new ArchivoNoValido("El archivo no puede ser nulo");

        }
        if(archivo.equals(directorio)){
            throw new ArchivoNoValido("Estas intentando sobreescribir el archivo");
        }

        try(BufferedReader br = new BufferedReader(new FileReader(directorio));
        BufferedWriter wr = new BufferedWriter(new FileWriter(archivo, true))
        ){

            while ((linea = br.readLine()) != null) {

                for (int i = 0; i < linea.length(); i++) {

                    char c =  linea.charAt(i);
                    if(VOCALES.indexOf(c) == -1){
                        wr.write(linea.charAt(i));
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("Archivo no encontrado");
        } catch (IOException e) {
            return "Error al abrir archivo " + archivo;
        }
        return  null;
    }

    public String codificaFiles(String archivo){

        String archivo1 = new ArchivoTXT(getArchivo()).getArchivo();
        String VOCALES = "AEIOUaeiou";

        try{
          StringBuilder builder = new StringBuilder();
          String lineas = String.valueOf(Files.readAllLines(Paths.get(archivo1)));

            for (int i = 0; i < lineas.length(); i++) {
                char c =  lineas.charAt(i);
                if(VOCALES.indexOf(c) == -1){
                    builder.append(c);
                    Files.write(Paths.get(archivo),builder.toString().getBytes());

                }
            }
        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("Archivo no encontrado");
        } catch (IOException e) {
            return "Error al abrir archivo " + archivo;
        }


        return null;
    }
    /**
     *4. Incorpora un método mover que reciba otra ruta y mueva el archivo a ella. Si el
     * directorio en el que se encontraba queda vacío, debe eliminarse también.
     * */
    public void mover(String ruta) throws IOException{
        Path directorioDestino = Paths.get(ruta);
        String archivo1 = new ArchivoTXT(getArchivo()).getArchivo();
        Path origen  = Paths.get(archivo1);


        if(!Files.exists(origen)){
            throw new DirectorioNoValido("El directorio no existe");
        }
        Files.move(origen, directorioDestino);

    }

}
