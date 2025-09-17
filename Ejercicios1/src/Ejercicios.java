
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.channels.FileLockInterruptionException;
import java.util.Arrays;
import java.util.Scanner;


public class Ejercicios {


    /*1. Crea un metodo listarDirectorio() que devuelva una array con el listado del
         contenido (archivos y carpetas) del directorio actual.
        ¿Este metodo debería ser dinámico o estático? ¿por qué?*/

    // en este caso estatico porque no depende de un objeto para funcionar
    public static void listarDirectorio(){

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

    /*2. Crea un metodo listarDirectorio(String directorio) que devuelva una
         array con el listado del contenido del directorio indicado como argumento siempre y cuando este sea un directorio
         y no un archivo. Pruébalo pasándole al menos
         una ruta absoluta y una relativa.
        ¿Qué devolvería en caso de que la ruta que nos proporcionan no se corresponding con un directorio?*/

    public void listarDirectorio2(String directorio){

        //declaramos el objeto con la ruta que nos pasan por parametro
        File ruta = new File(directorio);

        // comprobamos si la ruta existe o si no es un directorio
        if(!ruta.exists() || !ruta.isDirectory()){
            System.err.println("Error en la ruta");
        }
        //declaracion del array donde se guarda la informacion obtenida y la imprimimos
        String [] directorios = ruta.list();

        for(String directorio2 : directorios){
            System.out.println(directorio2);
        }
    }

    //3. Crea un metodo existeFichero(String directorio, String fichero) que
    //compruebe si existe dicho fichero en el directorio indicado.

     public void existeFichero(String directorio, String fichero){

        // creamos la ruta con el directorio y el fichero pasados por parametros
        File rutaCompleta =  new File(directorio, fichero);

        //comprobamos con el metodo exists si el archivo existe en la ruta que nos han mandado

         if (rutaCompleta.exists()) {
             System.out.println("Fichero existe");
         } else {
             System.out.println("Fichero no existe");
         }



     }

     //4. Crea un metodo generarArchivo que a partir de una ruta que se le pase como
    //argumento, cree un archivo txt con nombre TunombreTuapellido en la ruta en la
    //que se le ha proporcionado. Presta atención a los posibles errores que puedan
    //darse.
    //¿Qué pasa si la ruta no existe? ¿puedes solucionarlo?


    public void generarArchivo()  {
        // declaro un escaner por el que se introducira el directorio
        Scanner sc = new Scanner(System.in);
        String directorio = "";
        boolean escrito = false;

        // con el bucle compruebo que solo me pasan un directorio
        do{
            System.out.println("Escribe un directorio: ");
            directorio = sc.nextLine();
            File ruta = new File(directorio);
            // comprobacion de si es un directorio para salir del bucle y crear el fichero

            // debido a posibles erroes de ejecicion como permisos o fallos en el sistema de archivos hay que meterlo en un try catch para manejar las posbiles excepciones
            if(ruta.isDirectory()){
                try{
                    File fichero = new File(ruta, "IgnacioSanz.txt");
                    fichero.createNewFile();
                    escrito = true;
                }catch(IOException ioe){ // significa que ocurrió un problema durante una operación de entrada/salida (I/O) en un programa
                    ioe.printStackTrace();
                }

            }else{
                System.out.println("No es un directorio");
            }


        }while(escrito==false);
    }
    //5. Crea un metodo renombrarArchivo que coja un archivo cuyo path absoluto se
    //le pase como argumento y lo renombre añadiendo delante DAM2.
    //Pruébalo con el archivo creado en el ejercicio anterior.
    //El archivo antiguo, ¿desaparece? se sobreEscribe

    public boolean renombrarArchivo(String directorio){

        // recogemos la ruta y declaramos el objeto
        File ruta = new File(directorio);
        if(!ruta.exists()){
            return false;
        }
        try{
            //declaramos el nuevo nombre del archivo
            File nuevoNombre = new File(ruta.getParent(),"IgnacioSanzDAM2.txt");

            if(nuevoNombre.exists()){
                return true;
            }
            // si devuelve true, que se ha renombrado, se imprimirá el mensaje de exito
            boolean exito = ruta.renameTo(nuevoNombre);
            return exito;

        }catch(SecurityException se){
            return false;
        }catch (NullPointerException npe){
            return false;
        }


    }

    // 6. Crea un metodo que se llame borrarArchivo que reciba un path absoluto y
    //elimine el archivo indicado.
    //Pruébalo con el archivo del ejercicio anterior.
    //En la clase File hay métodos para cambiar los atributos del archivo. Prueba a
    //modificar el metodo haciendo el archivo de solo lectura antes de eliminarlo. ¿Qué
    //sucede? ¿por qué?

    public void borrarArchivo(String directorio){
        // usar el metodo setReadOnly();
        File ruta = new File(directorio);

        try{
            if(!ruta.exists()){
                throw new FileNotFoundException("El archivo no existe.");

            }
            boolean lectura = ruta.setReadable(true);
            if(lectura){
                System.out.println("Permiso cambiado");
            }

            boolean eliminado = ruta.delete();
            if(eliminado){
                System.out.println("Eliminado exitosamente");
            }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }catch (SecurityException se){ // esta se hace por si falla la ejecucion
            se.printStackTrace();
        }catch (NullPointerException npe){
            npe.printStackTrace();
        }



    }
    // 7. Crea un metodo eliminarDirectorio que reciba una ruta y elimine el directorio
    //indicado por ella.
    //¿Elimina directorios con contenido? ¿cómo se puede solucionar?
    //Modifica el metodo para que elimine directorios que contengan solo archivos o
    //estén vacíos e indique que no puede hacerlo si contienen otros directorios.

    public void eliminarDirectorio(String directorio){
        File ruta = new File(directorio);
            try{
                if(!ruta.exists()){
                    throw new FileNotFoundException("El directorio no existe.");
                }
                // listamos los archivos dentro del directorio
                File[] ficheros = ruta.listFiles();

                // este bucle for recorre el arreglo y llama al metodo de forma recursiva para eliminar los archivos dentro del directorio
                for(int i = 0; i < ficheros.length; i++){
                    if(ficheros[i].isDirectory()){
                        eliminarDirectorio(ficheros[i].getAbsolutePath());
                    }

                    ficheros[i].delete();
                }
                // elimina el directorio
                boolean eliminado = ruta.delete();
                if(eliminado){
                    System.out.println("Eliminado exitosamente");
                }

            }catch (FileNotFoundException e){
                System.out.println(e.getMessage());
            }catch (SecurityException se){ // esta se hace por si falla la ejecucion
                se.printStackTrace();
            }catch (NullPointerException npe){
                npe.printStackTrace();
            }

    }


}
