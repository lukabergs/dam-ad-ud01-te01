import java.io.*;
import java.util.Scanner;


public class Ejercicio4C {
    
    public static final String RUTA_FICHERO_PERSONAJES = "marvel.dat";
    public static final int[] LONG_BUFFERS = {9, 12, 20, 10};
    public static final int BYTES_POR_REGISTRO = 114;
    
    public static void main(String[] args) {
        try (
            RandomAccessFile fichero = new RandomAccessFile(RUTA_FICHERO_PERSONAJES, "r");
            Scanner sc = new Scanner(System.in);
        ) {
            System.out.print("Introduce un tipo de personaje: ");
            String tipoObjetivo = sc.nextLine();

            int posDni = 4, posTipo = 86, posPeso = 106;
            
            char[] arDni = new char[LONG_BUFFERS[0]];
            char[] arNombre = new char[LONG_BUFFERS[1]];
            char[] arIdentidad = new char[LONG_BUFFERS[2]];
            char[] arTipo = new char[LONG_BUFFERS[3]];
            String dni;
            String nombre;
            String identidad;
            String tipo;
            int peso;
            int altura;
            int coincidencias = 0;

            boolean tipoCoincide;
            
            while (posDni < fichero.length()) {
                fichero.seek(posTipo);
                
                for (int i = 0; i < LONG_BUFFERS[3]; i++) {
                    arTipo[i] = fichero.readChar();
                }
                tipo = new String(arTipo).trim();
                tipoCoincide = tipoObjetivo.equals(tipo);
                
                if (tipoCoincide) {
                    coincidencias++;
                    fichero.seek(posDni);

                    for (int i = 0; i < arDni.length; i++) {
                        arDni[i] = fichero.readChar();
                    }
                    dni = new String(arDni);
    
                    for (int i = 0; i < arNombre.length; i++) {
                        arNombre[i] = fichero.readChar();
                    }
                    nombre = new String(arNombre);
    
                    for (int i = 0; i < arIdentidad.length; i++) {
                        arIdentidad[i] = fichero.readChar();
                    }
                    identidad = new String(arIdentidad);
                    
                    fichero.seek(posPeso);
                    
                    peso = fichero.readInt();
                    altura = fichero.readInt();
                    
                    System.out.printf("Personaje [dni=%s, , nombre=%s, identidad=%s, tipo=%s, peso=%d, altura=%d]\n", dni, nombre, identidad, tipoObjetivo, peso, altura);
                }
                posDni += BYTES_POR_REGISTRO;
                posTipo += BYTES_POR_REGISTRO;
                posPeso += BYTES_POR_REGISTRO;
            }

            if (coincidencias < 1) {
                System.out.printf("No existen %ss en el fichero.\n", tipoObjetivo);
            }
            
        } catch (FileNotFoundException e) {
            System.out.println("El fichero " + RUTA_FICHERO_PERSONAJES + " no existe, o no se puede abrir en modo lectura. Error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error de E/S durante la escritura en el fichero " + RUTA_FICHERO_PERSONAJES + ". Error: " + e.getMessage());
        }
    }
}
