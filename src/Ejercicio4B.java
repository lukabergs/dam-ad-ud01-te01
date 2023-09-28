import java.io.*;
import java.util.Scanner;


public class Ejercicio4B {

    public static final String RUTA_FICHERO_PERSONAJES = "marvel.dat";
    public static final int[] LONG_BUFFERS = {9, 12, 20, 10};
    public static final int BYTES_POR_REGISTRO = 114;
    
    public static void main(String[] args) {
        try (
            RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO_PERSONAJES, "rw");
            Scanner teclado = new Scanner(System.in);
        ) {
            System.out.println("Introduzca el DNI (con letra) del personaje para control de peso:");
            String dniObjetivo = teclado.next();

            if (dniObjetivo.length() == 9) {
                int posPuntero = 4;
                boolean personajeEncontrado = false;
    
                while (!personajeEncontrado && posPuntero < fichero.length()) {
                    fichero.seek(posPuntero);
    
                    boolean dniCoincide = true;
                    int i = 0;
                    while (dniCoincide && i < LONG_BUFFERS[0]) {
                        dniCoincide &= dniObjetivo.charAt(i) == fichero.readChar();
                        i++;
                    }
                    if (dniCoincide) {
                        personajeEncontrado = true;
                    } else {
                        posPuntero += BYTES_POR_REGISTRO;
                    }
                }
                if (personajeEncontrado) {
                    int pesoUltimaMedicion;
                    char[] arrayNombre = new char[LONG_BUFFERS[1]];
                    String nombre;
                    for (int i = 0; i < arrayNombre.length; i++) {
                        arrayNombre[i] = fichero.readChar();
                    }
                    nombre = new String(arrayNombre).trim();
    
                    for (int i : LONG_BUFFERS) {
                        posPuntero += 2*i;
                    }
                    fichero.seek(posPuntero);
                    pesoUltimaMedicion = fichero.readInt();
    
                    System.out.println("Personaje encontrado: " + nombre + ". Introduzca su peso actual:");
                    int pesoNuevaMedicion = teclado.nextInt();
    
                    int difPeso = pesoNuevaMedicion - pesoUltimaMedicion;
                    if (difPeso == 0) {
                        System.out.printf("%s se mantiene en su peso anterior.\n", nombre);
                    } else {
                        fichero.seek(posPuntero);
                        fichero.writeInt(pesoNuevaMedicion);
                        if (difPeso > 0) {
                            System.out.printf("%s ha engordado %d kilos.\n", nombre, difPeso);
                        } else {
                            System.out.printf("%s ha adelgazado %d kilos.\n", nombre, -difPeso);
                        }
                    }
                } else {
                    System.out.println("No se ha encontrado ningún personaje con el DNI " + dniObjetivo + ".");
                }
            } else {
                System.out.println("El DNI " + dniObjetivo + " tiene un formato incorrecto.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("El fichero " + RUTA_FICHERO_PERSONAJES + " no se puede abrir en modo escritura. Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error de E/S durante la escritura en el fichero " + RUTA_FICHERO_PERSONAJES + ". Error: " + e.getMessage());
        }
    }
}
