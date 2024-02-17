/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package amcp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author alepd
 */
public class AFND implements Proceso {

    private ArrayList<Integer> estados; //indica cuales son los estados
    private int estadoinicial;
    private ArrayList<Integer> estadosFinales; //indica cuales son los estados Finales
    private ArrayList<Transicion> transiciones; //indica la lista de transiciones del AFND
    private ArrayList<Transicion> transicionesLambda; //indica la lista de transiciones lambda del AFND

    public AFND() {
        estados = new ArrayList<>();
        estadoinicial = 0;
        estadosFinales = new ArrayList<>();
        transiciones = new ArrayList<>();
        transicionesLambda = new ArrayList<>();
    }
    
    private ArrayList<Integer> transicion(int estado, char simbolo) { 
    //por cada estado origen, miramos el simbolo de transicion y vamos a su estado destino correspondiente
        int i = 0;

        while (i < transiciones.size()) {
            if (estado == transiciones.get(i).getEstadoinicial() && simbolo == transiciones.get(i).getSimbolo()) {
                return transiciones.get(i).getEstadosFinales();
            } else {
                i++;
            }
        }

        return null;
    }

    public ArrayList<Integer> transicion(ArrayList<Integer> macroestado, char simbolo) { //lo mismo pero para varios estados destino
        ArrayList<Integer> macroEstDevolver = new ArrayList<>();

        for (int i = 0; i < macroestado.size(); i++) {
            ArrayList<Integer> aux = transicion(macroestado.get(i), simbolo);

            if (aux != null) {
                for (int j = 0; j < aux.size(); j++) {
                    if (!macroEstDevolver.contains(aux.get(j))) {
                        macroEstDevolver.add(aux.get(j));
                    }
                }
            }
        }

        System.out.println("\nMACROESTADO DESPUES TRANSICION:");
        for (int j = 0; j < macroEstDevolver.size(); j++) {
            System.out.print(" q" + macroEstDevolver.get(j));
        }

        return lambda_clausura(macroEstDevolver);
    }
    
    public ArrayList<Integer> transicionLambda(int estado) {
    //en este caso no se comprueba el simbolo, si llamamos a este método automáticamente vamos a su(s) destino(s)
        int i = 0;

        while (i < transicionesLambda.size()) {
            if (estado == transicionesLambda.get(i).getEstadoinicial()) {
                return transicionesLambda.get(i).getEstadosFinales();
            } else {
                i++;
            }
        }

        return null;
    }
    
    private ArrayList<Integer> lambda_clausura(ArrayList<Integer> macroestado) {
        ArrayList<Integer> macroEstDevolver = new ArrayList<>();
        for (int i = 0; i < macroestado.size(); i++) {
            macroEstDevolver.add(macroestado.get(i)); //igualamos los 2 arrays
        }
        
        for (int i = 0; i < macroestado.size(); i++){
            ArrayList<Integer> aux = transicionLambda(macroestado.get(i)); 
            //obtenemos los estados destino de las transiciones lambda del estado que estamos revisando

            if (aux != null) {
                for (int j = 0; j < aux.size(); j++) {
                    if (!macroEstDevolver.contains(aux.get(j))) {
                        macroEstDevolver.add(aux.get(j)); //si no lo contiene ya, lo añadimos
                    }
                    if (!macroestado.contains(aux.get(j))) {
                        macroestado.add(aux.get(j)); 
                        //tambien lo tenemos que añadir aqui para reevaluarlo al volver al for, es decir, 
                        //tenemos que hacer lambda clausura de la lambda clausura
                        
                    }
                }
            }
        }
        
        System.out.println("\n\nMACROESTADO DESPUES DE LAMBDA_CLAUSURA:");
        for (int j = 0; j < macroEstDevolver.size(); j++) {
            System.out.print(" q" + macroEstDevolver.get(j));
        }

        return macroEstDevolver;
    }

    @Override
    public boolean reconocer(String cadena) { //se comprueba cada caracter de la cadena llamando al metodo transicion
        char[] simbolo = cadena.toCharArray();

        ArrayList<Integer> macroestado = new ArrayList<>();
        macroestado.add(estadoinicial); 
        System.out.println("MACROESTADO INICIAL:");
        for (int j = 0; j < macroestado.size(); j++) {
            System.out.print(" q" + macroestado.get(j));
        }

        macroestado = lambda_clausura(macroestado);

        for (int i = 0; i < simbolo.length; i++) {
            System.out.println("\n\nSIMBOLO: "+simbolo[i]);
            macroestado = transicion(macroestado, simbolo[i]);
        }
        
        System.out.println("\n");

        return esFinal(macroestado);
    }

    public void setEstadoInicial(int ei) {
        estadoinicial = ei;
    }

    public void añadirEstado(int e) {
        if (!estados.contains(e)) {
            estados.add(e);
        }
    }

    public void añadirEstadoFinal(int ef) {
        estadosFinales.add(ef);
    }

    public void agregarTransicion(int e1, char simbolo, ArrayList<Integer> e2) {
        transiciones.add(new Transicion(e1, simbolo, e2));
    }

    public void agregarTransicionLambda(int e1, ArrayList<Integer> e2) {
        transicionesLambda.add(new Transicion(e1, e2));
    }

    @Override
    public boolean esFinal(int estado) {
        int i = 0;
        while (i < estadosFinales.size()) {
            if (estado == estadosFinales.get(i)) {
                return true;
            }
            i++;
        }

        return false;
    }

    public boolean esFinal(ArrayList<Integer> macroestado) {
        boolean aux = false;

        for (int i = 0; i < macroestado.size(); i++) {
            aux = aux || esFinal(macroestado.get(i));
            if (aux) {
                return aux;
            }
        }

        return aux;
    }

    @Override
    public String toString() {
        String s = "TIPO: AFND";

        s += "\nESTADOS:";
        for (int i = 0; i < estados.size(); i++) {
            s += " q" + estados.get(i);
        }

        s += "\nINICIAL: q" + estadoinicial;

        s += "\nFINALES:";
        for (int i = 0; i < estadosFinales.size(); i++) {
            s += " q" + estadosFinales.get(i);
        }

        s += "\nTRANSICIONES:\n";
        for (int i = 0; i < transiciones.size(); i++) {
            Transicion t = transiciones.get(i);
            s += "q" + t.getEstadoinicial() + " '" + t.getSimbolo() + "'";
            ArrayList<Integer> aux = t.getEstadosFinales();
            for (int j = 0; j < aux.size(); j++) {
                s += " q" + aux.get(j);
            }
            if (i != transiciones.size() - 1) {
                s += "\n";
            }
        }
        s += "\nFIN\n";
        s += "\nTRANSICIONES LAMBDA:\n";
        for (int i = 0; i < transicionesLambda.size(); i++) {
            Transicion t = transicionesLambda.get(i);
            s += "q" + t.getEstadoinicial();
            ArrayList<Integer> aux = t.getEstadosFinales();
            for (int j = 0; j < aux.size(); j++) {
                s += " q" + aux.get(j);
            }
            if (i != transicionesLambda.size() - 1) {
                s += "\n";
            }
        }

        s += "\nFIN";
        return s;
    }

}