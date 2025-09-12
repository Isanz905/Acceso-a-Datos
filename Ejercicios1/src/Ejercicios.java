
import java.io.File;
import java.util.Arrays;


public class Ejercicios {


    /*1. Crea un método listarDirectorio() que devuelva una array con el listado del
         contenido (archivos y carpetas) del directorio actual.
        ¿Este método debería ser dinámico o estático? ¿por qué?*/

    public void listarDirectorio(){

        //Declaramos el objeto file
        File ruta = new File(".");
        //comprobamos si la ruta existe
        if(!ruta.exists()){
            System.out.println("Directorio no encontrado");
        }
        // obtenemos la ruta en la que estamos
        ruta = ruta.getAbsoluteFile();
        // ruta = new File(ruta.getPath()); probé haciendolo con getPath y tambien funcionaba
        String [] directorios = ruta.list();

        //leemos el array y lo imprimimos
        for(String directorio: directorios){
            System.out.println(directorio);
        }
    }

    /*2. Crea un método listarDirectorio(String directorio) que devuelva una
         array con el listado del contenido del directorio indicado como argumento siempre y cuando este sea un directorio
         y no un archivo. Pruébalo pasándole al menos
         una ruta absoluta y una relativa.
        ¿Qué devolvería en caso de que la ruta que nos proporcionan no se corresponding con un directorio?*/

    public void listarDirectorio2(String directorio){

        //declaramos el objeto con la ruta que nos pasan por parametro
        File ruta = new File(directorio);

        // comprobamos si la ruta existe o si no es un directorio
        if(!ruta.exists() || !ruta.isDirectory()){
            System.out.println("Error en la ruta");
        }
        //declaracion del array donde se guarda la informacion obtenida y la imprimimos
        String [] directorios = ruta.list();

        for(String directorio2 : directorios){
            System.out.println(directorio2);
        }
    }

    // Crea un método existeFichero(String directorio, String fichero) que
    //compruebe si existe dicho fichero en el directorio indicado.

     public void existeFichero(String directorio, String fichero){

        File rutaCompleta =  new File(directorio);
        if(!rutaCompleta.exists()){
            System.out.println("Directorio no encontrado");
        }

        File rutaConArchivo = new File(directorio + "/" + fichero);
        if(rutaConArchivo.exists()){
            System.out.println("Existe");
        }


     }




}
