/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amcp2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author alepd
 */
public class Ficheros {

    public static AFD leerFicheroAFD(String rutaFichero) throws FileNotFoundException {
        AFD automataD = new AFD();
        // Crear un objeto File a partir de la ruta proporcionada
        File fichero = new File(rutaFichero);

        Scanner scan = new Scanner(fichero);

        for (int i = 0; i < 5; i++) {
            String linea = scan.nextLine();
            String[] partes = linea.split(" ");

            switch (partes[0]) {
                case "INICIAL:":
                    String[] ei = partes[1].split("q");
                    automataD.setEstadoInicial(Integer.parseInt(ei[1]));
                    break;

                case "FINALES:":
                    for (int j = 1; j < partes.length; j++) {
                        String[] ef = partes[j].split("q");
                        automataD.añadirEstadoFinal(Integer.parseInt(ef[1]));
                    }
                    break;

                case "TRANSICIONES:":
                    linea = scan.nextLine();
                    while (!linea.equals("FIN")) {
                        partes = linea.split(" ");

                        String[] eo = partes[0].split("q");
                        String[] simb = partes[1].split("'");
                        String[] ed = partes[2].split("q");

                        automataD.agregarTransicion(Integer.parseInt(eo[1]), simb[1].charAt(0),
                                Integer.parseInt(ed[1]));

                        linea = scan.nextLine();
                    }
                    break;
            }
        }

        return automataD;
    }

    public static AFD leerTecladoAFD(Scanner sc) {
        AFD automata = new AFD();
        System.out.println("\tCual es el estado inicial del automata?");
        int estadoInicial = sc.nextInt();
        automata.añadirEstadoFinal(estadoInicial);
        sc.nextLine();
        System.out.println("\tCuales son los estados finales (la estrucura tiene que ser :0 1 2 3)");
        String linea = sc.nextLine();
        String[] strArray = linea.split("\\s+");
        for (String str : strArray) {
            automata.añadirEstadoFinal(Integer.parseInt(str));
        }
        System.out.println("\tCuantas transiciones va a tener el automata?");
        int nTransiciones = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < nTransiciones; i++) {
            System.out.println("\tEscribe la transicion " + (i+1) + " (la estructura tiene que ser :0 1 2)");
            linea = sc.nextLine();
            strArray = linea.split("\\s+");
            automata.agregarTransicion(Integer.parseInt(strArray[0]), strArray[1].charAt(0), Integer.parseInt(strArray[2]));
        }
        return automata;
    }
    
    public static AFND leerTecladoAFND(Scanner sc) {
        AFND automata = new AFND();
        System.out.println("\tCual es el estado inicial del automata?");
        int estadoInicial = sc.nextInt();
        automata.añadirEstadoFinal(estadoInicial);
        sc.nextLine();
        System.out.println("\tCuales son los estados finales (la estrucura tiene que ser :0 1 2 3)");
        String linea = sc.nextLine();
        String[] strArray = linea.split("\\s+");
        for (String str : strArray) {
            automata.añadirEstadoFinal(Integer.parseInt(str));
        }
        System.out.println("\tCuantas transiciones va a tener el automata?");
        int nTransiciones = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < nTransiciones; i++) {
            System.out.println("\tEscribe la transicion " + (i+1) + " (la estructura tiene que ser :0 1 2 ...)");
            linea = sc.nextLine();
            strArray = linea.split("\\s+");
            ArrayList<Integer> estadosDestino = new ArrayList<>();
            for(int j = 2; j < strArray.length; j++){
                estadosDestino.add(Integer.parseInt(strArray[j]));
            }
            automata.agregarTransicion(Integer.parseInt(strArray[0]), strArray[1].charAt(0), estadosDestino);
        }
        System.out.println("\tCuantas transiciones lambda va a tener el automata?");
        int nTransicionesLambda = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < nTransicionesLambda; i++) {
            System.out.println("\tEscribe la transicion lambda " + (i+1) + " (la estructura tiene que ser :0 1 ...)");
            linea = sc.nextLine();
            strArray = linea.split("\\s+");
            ArrayList<Integer> estadosDestino = new ArrayList<>();
            for(int j = 1; j < strArray.length; j++){
                estadosDestino.add(Integer.parseInt(strArray[j]));
            }
            automata.agregarTransicionLambda(Integer.parseInt(strArray[0]), estadosDestino);
        }
        return automata;
    }

    public static AFND leerFicheroAFND(String rutaFichero) throws FileNotFoundException {
        AFND automataND = new AFND();
        // Crear un objeto File a partir de la ruta proporcionada
        File fichero = new File(rutaFichero);

        Scanner scan = new Scanner(fichero);

        for (int i = 0; i < 6; i++) {
            String linea = scan.nextLine();
            String[] partes = linea.split(" ");

            switch (partes[0]) {
                case "INICIAL:":
                    String[] ei = partes[1].split("q");
                    automataND.setEstadoInicial(Integer.parseInt(ei[1]));
                    break;

                case "FINALES:": {
                    for (int j = 1; j < partes.length; j++) {
                        String[] ef = partes[j].split("q");
                        automataND.añadirEstadoFinal(Integer.parseInt(ef[1]));
                    }
                    break;
                }
                case "TRANSICIONES:": {
                    linea = scan.nextLine();
                    while (!linea.equals("FIN")) {
                        partes = linea.split(" ");

                        String[] eo = partes[0].split("q");
                        String[] simb = partes[1].split("'");

                        StringBuilder edBuilder = new StringBuilder();
                        for (int k = 2; k < partes.length; k++) {
                            String[] edPart = partes[k].split("q");
                            edBuilder.append(edPart[1]);
                            if (k < partes.length - 1) {
                                edBuilder.append(" ");
                            }
                        }
                        String[] ed = edBuilder.toString().trim().split(" ");
                        ArrayList<Integer> ef = new ArrayList<>();

                        for (int j = 0; j < ed.length; j++) {
                            ef.add(Integer.parseInt(ed[j]));
                        }

                        automataND.agregarTransicion(Integer.parseInt(eo[1]), simb[1].charAt(0), ef);
                        linea = scan.nextLine();
                    }
                    break;
                }
                case "TRANSICIONES_LAMBDA:": {
                    linea = scan.nextLine();
                    while (!linea.equals("FIN")) {
                        partes = linea.split(" ");
                        int eo = 0;
                        ArrayList<Integer> estadosLambda = new ArrayList<>();
                        for (int j = 0; j < partes.length; j++) {
                            String[] ef = partes[j].split("q");
                            if (j == 0) {
                                eo = Integer.parseInt(ef[1]);
                            } else {
                                estadosLambda.add(Integer.parseInt(ef[1]));
                            }
                        }
                        
                        automataND.agregarTransicionLambda(eo, estadosLambda);

                        linea = scan.nextLine();
                    }
                    
                    break;
                }
            }
        }

        return automataND;
    }
}
