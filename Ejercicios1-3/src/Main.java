public class Main {
    public static void main(String[] args) {
        ArchivoTXT arch = new ArchivoTXT("C:/Users/ignac/Documents/Lorem ipsum.txt");
        // System.out.println(arch.aVerso());
        // arch.codifica("C:/Users/ignac/Documents/nuevo.txt");
        // arch.codificaBuffer("C:/Users/ignac/Documents/nuevo.txt");
         arch.codificaFiles("C:/Users/ignac/Documents/nuevo.txt");
    }
}
