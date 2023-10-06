import java.io.*;
import java.util.Scanner;


public class Ejercicio3 {

    public static final int[] CABECERA_ZIP = {80, 75, 3, 4};

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Por favor, introduce el nombre del fichero cuya cabecera quieras analizar: ");
        String rutaFicheroEntrada = teclado.next();
        try (
            FileInputStream ficheroEntrada = new FileInputStream(rutaFicheroEntrada);
        ) {
            System.out.println("El fichero " + rutaFicheroEntrada + (esZip(ficheroEntrada) ? "" : " no") + " es un fichero ZIP.");
        } catch (FileNotFoundException e) {
            System.err.println("El fichero " + rutaFicheroEntrada + " no existe, es un directorio o no se puede abrir en modo lectura.");
        } catch (IOException e) {
            System.err.println("Ha ocurrido un error de E/S durante la lectura del fichero " + rutaFicheroEntrada + ". Error: " + e.getMessage());
        }
        teclado.close();
    }

    public static boolean esZip(FileInputStream fichero) throws IOException {
        boolean esZip = fichero.read() == CABECERA_ZIP[0];
        int i = 1;
        while (esZip && i < CABECERA_ZIP.length) {
            esZip &= fichero.read() == CABECERA_ZIP[i];
            i++;
        }
        return esZip;
    }
}
