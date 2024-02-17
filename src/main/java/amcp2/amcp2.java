/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package amcp2;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author alepd
 */
public class amcp2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú Principal:");
            System.out.println("1. Entrar a la parte de AFD");
            System.out.println("2. Entrar a la parte de AFND");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    menuAFD(scanner);
                    break;
                case 2:
                    menuAFND(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Introduce un valor válido.");
            }
            if (opcion != 0) {
            }

        } while (opcion != 0);
        scanner.close();
    }

    public static void menuAFD(Scanner scanner) {
        int opcion;

        do {
            System.out.println("Menú AFD:");
            System.out.println("1. Comprobar AFD desde fichero");
            System.out.println("2. Comprobar AFD desde teclado");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1: {
                    System.out.println("\tEscribe la ruta del fichero a comprobar");
                    String rutaFichero = scanner.nextLine();
                    //ESTO SE EJECUTA CON LA RUTA A MI DIRECTORIO, SI SE EJECUTA EN OTRO SITIO, HAY QUE CAMBIAR EL STRING DE ABAJO
                    rutaFichero = "C:/Users/alepd/OneDrive/Escritorio/Apuntes uni/AMC/amcp2/" + rutaFichero;
                    AFD automataD = new AFD();
                    try {
                        automataD = Ficheros.leerFicheroAFD(rutaFichero);
                    } catch (FileNotFoundException ex) {
                        System.out.println("No se encontro el fichero");
                        break;
                    }
                    System.out.println("\tIntroduce la cadena para probar el automata");
                    String cadena = scanner.nextLine();
                    if (automataD.reconocer(cadena)) {
                        System.out.println("CADENA ACEPTADA");
                    } else {
                        System.out.println("CADENA DENEGADA");
                    }
                    break;
                }
                case 2: {
                    AFD automataD = Ficheros.leerTecladoAFD(scanner);
                    System.out.println("\tIntroduce la cadena para probar el automata");
                    String cadena = scanner.nextLine();
                    if (automataD.reconocer(cadena)) {
                        System.out.println("CADENA ACEPTADA");
                    } else {
                        System.out.println("CADENA DENEGADA");
                    }
                    break;
                }
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Introduce un valor válido.");
            }
            if (opcion != 0) {
            }

        } while (opcion != 0);
    }

    public static void menuAFND(Scanner scanner) {
        int opcion;

        do {
            System.out.println("Menú AFD:");
            System.out.println("1. Comprobar AFND desde fichero");
            System.out.println("2. Comprobar AFND desde teclado");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1: {
                    System.out.println("\tEscribe la ruta del fichero a comprobar");
                    String rutaFichero = scanner.nextLine();
                    //ESTO SE EJECUTA CON LA RUTA A MI DIRECTORIO, SI SE EJECUTA EN OTRO SITIO, HAY QUE CAMBIAR EL STRING DE ABAJO
                    rutaFichero = "C:/Users/alepd/OneDrive/Escritorio/Apuntes uni/AMC/amcp2/" + rutaFichero;
                    AFND automataND = new AFND();
                    try {
                        automataND = Ficheros.leerFicheroAFND(rutaFichero);
                    } catch (FileNotFoundException ex) {
                        System.out.println("No se encontro el fichero");
                        break;
                    }
                    System.out.println("\tIntroduce la cadena para probar el automata");
                    String cadena = scanner.nextLine();
                    if (automataND.reconocer(cadena)) {
                        System.out.println("CADENA ACEPTADA");
                    } else {
                        System.out.println("CADENA DENEGADA");
                    }
                    break;
                }
                case 2: {
                    AFND automataND = Ficheros.leerTecladoAFND(scanner);
                    System.out.println("\tIntroduce la cadena para probar el automata");
                    String cadena = scanner.nextLine();
                    if (automataND.reconocer(cadena)) {
                        System.out.println("CADENA ACEPTADA");
                    } else {
                        System.out.println("CADENA DENEGADA");
                    }
                    break;
                }
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida. Introduce un valor válido.");
            }
            if (opcion != 0) {
            }

        } while (opcion != 0);
    }
}
