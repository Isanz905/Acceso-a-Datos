import Excepciones.ArchivoNoValido;
import Excepciones.DirectorioNoValido;
import Excepciones.FalloEjecucion;
import Excepciones.NombreVacioException;

import java.io.*;
import java.nio.file.*;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

                String salto = linea.replace(".", ".\n");
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
            throw new FalloEjecucion("Error al abrir archivo " + archivo);
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
            throw new FalloEjecucion("Error al abrir archivo " + archivo);
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


    /***
     * 5. Añade tres métodos:
     * Un método contar que cuente el número total de caracteres del archivo.
     * Un método contarLetras que cuente el número total de letras del fichero.
     * Un método contarPuntuación que cuente el número total de signos de puntuación del fichero.
     */
    public int contar() {

        String directorio = new ArchivoTXT(getArchivo()).getArchivo();
        int contador = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(directorio))) {

            String linea;

            while ((linea = reader.readLine()) != null) {
                for (char c : linea.toCharArray()) {
                    contador++;
                }

            }


        } catch (NullPointerException nl) {
            throw new NombreVacioException("El directorio no puede ser nulo");
        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("Archivo no encontrado");
        } catch (IOException e) {
            throw new FalloEjecucion("Error al abrir archivo " + archivo);
        }

        return contador;
    }

    public int contarLetras(){

        String directorio = new ArchivoTXT(getArchivo()).getArchivo();
        int total= 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(directorio))){

            String linea;
            while((linea = reader.readLine()) != null){
                for(char c: linea.toCharArray()){
                    if(Character.isLetter(c)){
                        total++;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("Archivo no encontrado" + e);
        } catch (IOException e) {
            throw new FalloEjecucion("Error al abrir archivo " + archivo + e);
        }
        return total;
    }

    public int contarPuntuacion(){
        String directorio = new ArchivoTXT(getArchivo()).getArchivo();

        int total= 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(directorio))) {

            String linea;
            while((linea = reader.readLine()) != null){
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);
                    if(!Character.isLetterOrDigit(c)){
                        total++;
                    }
                }
            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return total;
    }

    /***
     *6. Ahora debes incluir un método cuentaLineas que cuente cuántas frases tiene
     * (hasta cada punto), ayudándose con el método aVerso.
     */
    public int cuentaLineas(){
        String texto = aVerso();
        int contador = 0;

        for (String lineas : texto.split("\n")) {
            if(lineas.contains(".")){
                contador++;
            }
        }

        return contador;
    }

    /**
     * 7. Incorpora otro método cuentaPalabras que cuente todas las palabras del fichero.
     * */
    public int cuentaPalabra(){

        String directorio =  new ArchivoTXT(getArchivo()).getArchivo();
        int  contador = 0;
        String linea;

        try(BufferedReader reader = new BufferedReader(new FileReader(directorio))) {

            while((linea = reader.readLine()) != null){
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);
                    if(c == ' '){
                        contador++;
                    }
                }
            }


        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("Archivo no encontrado" + e);
        } catch (IOException e) {
            throw new FalloEjecucion("Error al abrir archivo " + archivo + e);
        }

        return contador;
    }

    /**
     * 8. Implementa un método cuentaVocales que escriba el número de vocales de
     * cada palabra en un fichero numVocales.txt en el mismo directorio en el que
     * se encuentra el fichero original. Cada número debe ir seguido de un espacio.
     * Se deben tener en cuenta tanto mayúsculas como minúsculas pero se contaránjuntas. Es decir una a y una A incrementarán el mismo contador.*/
    public int cuentaVocales(String archivo){

        Path archivoVocales = Paths.get(archivo);
        String directorio =  new ArchivoTXT(getArchivo()).getArchivo();
        String vocales = "aeiouAEIOU";
        String linea;
        int contador = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(directorio));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoVocales.toFile(), true))){

            while((linea = reader.readLine()) != null){
                for (int i = 0; i < linea.length(); i++) {
                    char C = linea.charAt(i);
                    if(!(vocales.indexOf(C) == -1)){
                        contador ++;
                        writer.write(C);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("Archivo no encontrado" + e);
        } catch (IOException e) {
            throw new FalloEjecucion("Error al abrir archivo " + archivo + e);
        }
        return contador;
    }

    /***
     * 9. Modifica el ejercicio anterior para que tenga en cuenta las vocales con tilde y la
     * u con diéresis
     */
    public int cuentaVocales2(String archivo){
        Path archivoVocales = Paths.get(archivo);
        String directorio =  new ArchivoTXT(getArchivo()).getArchivo();
        String vocales = "aeiouüAEIOUÜáéíóúÁÉÍÓÚ";
        String linea;
        int contador = 0;

        try(BufferedReader reader = new BufferedReader(new FileReader(directorio));
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivoVocales.toFile(), true))){

            while((linea = reader.readLine()) != null){
                for (int i = 0; i < linea.length(); i++) {
                    char C = linea.charAt(i);
                    if(!(vocales.indexOf(C) == -1)){
                        contador ++;
                        writer.write(C);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("Archivo no encontrado" + e);
        } catch (IOException e) {
            throw new FalloEjecucion("Error al abrir archivo " + archivo + e);
        }
        return contador;
    }


    /**
     * 10. Implementa el método frecuenciaLetras que muestre la frecuencia de las letras (a-z incluidas mayúsculas) del fichero.*/
    public void frecuenciaLetras(){

        String directorio =  new ArchivoTXT(getArchivo()).getArchivo();
        Map<Character, Integer> frecuencia = new HashMap<>();
        String linea;

        try(BufferedReader reader = new BufferedReader(new FileReader(directorio))){

            while((linea = reader.readLine()) != null){
                for(char c : linea.toCharArray()){
                    if(Character.isLetter(c)){
                        frecuencia.put(c, frecuencia.getOrDefault(c, 0) + 1);
                    }

                }
            }

        } catch (FileNotFoundException e) {
            throw new ArchivoNoValido("Archivo no encontrado" + e);
        } catch (IOException e) {
            throw new FalloEjecucion("Error al abrir archivo" + e);
        }

        for (Map.Entry<Character, Integer> entry : frecuencia.entrySet()) {
            System.out.println( "Letra " + entry.getKey() + " aparece: "  + entry.getValue());
        }


    }


}
