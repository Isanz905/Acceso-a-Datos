import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArchivoTXT {
    private String archivo;

    public ArchivoTXT(String archivo) {
         Path path = Paths.get(archivo);

        if(path.toFile().exists()){
            this.archivo = archivo;

        }else{
            this.archivo = null;
        }
        if(path.toFile().isDirectory()){
            this.archivo = null;

        }

    }

    public String getArchivo(){
        return archivo;
    }
    public void setArchivo(String archivo){

        this.archivo = archivo;
    }


    // Añade un método aVerso que lea el contenido del archivo y
    //  lo devuelva introduciendo un salto de línea después de cada punto

    public String aVerso(){

        try(BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                boolean caracter = linea.contains(".");
                if(caracter){
                    return null;
                }
                return linea;
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e); // quitar esto
        } catch (IOException e) {
            throw new RuntimeException(e); // quitar esto
        }


        return null;

    }
}
