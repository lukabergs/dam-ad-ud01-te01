import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        System.out.println("Escoge el archivo cuyo texto quieres invertir: ");
        Scanner sc = new Scanner(System.in);
        String nombreArchivo = sc.next();
        String[] fragmentos = nombreArchivo.split("\\.");
        for (String s : fragmentos) {
            System.out.println(fragmentos);
        }
        try (
            FileReader leerArchivo = new FileReader(nombreArchivo);
            FileWriter escribirArchivo = new FileWriter(fragmentos[0] + "_rev." + fragmentos[1]);
        ) {
            StringBuilder contenido = new StringBuilder();
            while (leerArchivo.ready()) {
                contenido.append(leerArchivo.read());
            }
            contenido = contenido.reverse();
            escribirArchivo.write(contenido.toString());
        } catch (FileNotFoundException e) {
            System.out.println("El archivo a cargar no existe, es un directorio o no se puede abrir en modo lectura.");
        } catch (IOException e) {
            System.out.println("El archivo a escribir es un directorio o no se puede crear o sobreescribir.");
        }
    }
}
