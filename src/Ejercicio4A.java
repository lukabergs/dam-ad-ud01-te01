import java.io.*;


public class Ejercicio4A {

    public static final String RUTA_FICHERO_PERSONAJES = "marvel.dat";
    public static final int[] IDS= {1, 2, 3, 4, 5, 6, 7};
	public static final String[] DNIS = {"01010101A", "03030303C", "05050505E", "07070707G", "02020202B", "04040404D", "06060606F"};
	public static final String[] NOMBRES = {"Spiderman", "Green Goblin", "Storm", "Wolverine", "Mystique", "IronMan", "Mandarin"};
	public static final String[] IDENTIDADES = {"Peter Parker", "Norman Osborn", "Ororo Munroe","James Howlett", "Raven Darkholme", "Tony Stark", "Zhang Tong"};
	public static final String[] TIPOS = {"heroe","villano","heroe","heroe","villano","heroe","villano"};
	public static final int[] PESOS = {76,84,66,136,78,102,70};
	public static final int[] ALTURAS = {178,183,156,152,177,182,188};
    public static final int[] LONG_BUFFERS = {9, 12, 20, 10};
    
    public static void main(String[] args) {
        try (
            RandomAccessFile ficheroSalida = new RandomAccessFile(RUTA_FICHERO_PERSONAJES, "rw");
        ) {
            StringBuffer bufDni, bufNombre, bufIdentidad, bufTipo;
            int n = IDS.length;
            for (int i = 0; i < n; i++) {
                ficheroSalida.writeInt(IDS[i]);

                bufDni = new StringBuffer(DNIS[i]);
                bufDni.setLength(LONG_BUFFERS[0]);
                ficheroSalida.writeChars(bufDni.toString());

                bufNombre = new StringBuffer(NOMBRES[i]);
                bufNombre.setLength(LONG_BUFFERS[1]);
                ficheroSalida.writeChars(bufNombre.toString());

                bufIdentidad = new StringBuffer(IDENTIDADES[i]);
                bufIdentidad.setLength(LONG_BUFFERS[2]);
                ficheroSalida.writeChars(bufIdentidad.toString());

                bufTipo = new StringBuffer(TIPOS[i]);
                bufTipo.setLength(LONG_BUFFERS[3]);
                ficheroSalida.writeChars(bufTipo.toString());

                ficheroSalida.writeInt(PESOS[i]);

                ficheroSalida.writeInt(ALTURAS[i]);
            }
            System.out.println("La carga de los personajes ha terminado correctamente. Resultado almacenado en " + RUTA_FICHERO_PERSONAJES + ".");
        } catch (FileNotFoundException e) {
            System.err.println("El fichero " + RUTA_FICHERO_PERSONAJES + " no se puede abrir en modo escritura. Error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ha ocurrido un error de E/S durante la escritura en el fichero " + RUTA_FICHERO_PERSONAJES + ". Error: " + e.getMessage());
        }
    }
}
