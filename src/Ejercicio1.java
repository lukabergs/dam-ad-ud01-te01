import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Ejercicio1 {
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Por favor, introduce el nombre del fichero cuyo contenido quieres invertir: ");
        String rutaFicheroEntrada = teclado.next();
        String[] fragmentosRuta = rutaFicheroEntrada.split("\\.(?=[^.]*$)");
        String rutaFicheroSalida = fragmentosRuta[0] + "_rev." + fragmentosRuta[1];
        try (
            FileReader leerFichero = new FileReader(
                rutaFicheroEntrada,
                StandardCharsets.UTF_8
            );
            FileWriter escribirFichero = new FileWriter(
                rutaFicheroSalida,
                StandardCharsets.UTF_8
            );
        ) {
            StringBuilder contenidoFichero = new StringBuilder();
            while (leerFichero.ready()) {
                contenidoFichero.append((char) leerFichero.read());
            }
            contenidoFichero = contenidoFichero.reverse();
            escribirFichero.write(contenidoFichero.toString());
            System.out.println("Contenido del fichero " + rutaFicheroEntrada + " invertido con éxito, resultado guardado en " + rutaFicheroSalida + ".");
        } catch (IOException e) {
            System.err.println("El fichero " + rutaFicheroEntrada + " no existe, es un directorio o no se puede abrir en modo lectura.");
        }
        teclado.close();
    }
}
