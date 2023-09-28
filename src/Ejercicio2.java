import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Ejercicio2 {
    
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Por favor, introduce el nombre del fichero cuyas palabras palíndromas quieres extraer: ");
        String rutaFicheroEntrada = teclado.next();
        String[] fragmentosRuta = rutaFicheroEntrada.split("\\.(?=[^.]*$)");
        String rutaFicheroSalida = fragmentosRuta[0] + "_pal." + fragmentosRuta[1];
        try (
            BufferedReader leerFichero = new BufferedReader(new FileReader(
                rutaFicheroEntrada,
                StandardCharsets.UTF_8
            ));
            FileWriter escribirFichero = new FileWriter(
                rutaFicheroSalida,
                StandardCharsets.UTF_8
            );
        ) {
            String linea;
            boolean esPalindroma;
            while ((linea = leerFichero.readLine()) != null) {
                String[] palabras = linea.split("[^a-zA-Z0-9]+");
                for (String palabra : palabras) {
                    if (palabra.length() > 1) {
                        String palabraMayus = palabra.toUpperCase();
                        int i = 0;
                        int longitudPalabra = palabra.length();
                        esPalindroma = palabraMayus.charAt(0) == palabraMayus.charAt(longitudPalabra - 1);
                        while (esPalindroma && i < longitudPalabra / 2) {
                            esPalindroma &= palabraMayus.charAt(i) == palabraMayus.charAt(longitudPalabra - i - 1);
                            i++;
                        }
                        if (esPalindroma) {
                            escribirFichero.write(palabra + " ");
                        }
                    } else if (palabra.length() == 1) {
                        escribirFichero.write(palabra + " ");
                    }
                }
                escribirFichero.write("\n");
            }
            System.out.println("Contenido del fichero " + rutaFicheroEntrada + " analizado con éxito, resultado guardado en " + rutaFicheroSalida + ".");
        } catch (IOException e) {
            System.err.println("El fichero " + rutaFicheroEntrada + " no existe, es un directorio o no se puede abrir en modo lectura.");
        }
        teclado.close();
    }
}
