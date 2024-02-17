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
public class AFD implements Proceso {

    private int estadoinicial;
    private ArrayList<Integer> estadosFinales; //indica cuales son los estados finales
    private ArrayList<Transicion> transiciones; //indica la lista de transiciones del AFD

    public AFD() {
        estadoinicial = 0;
        transiciones = new ArrayList<>();
        estadosFinales = new ArrayList<>();
    }

    public int transicion(int estado, char simbolo) { //según el estado actual y el símbolo de transición, devuelve el estado cambio o destino
        int estadocambio = -1, i = 0;

        while (i < transiciones.size()) {
            if (estado == transiciones.get(i).getEstadoinicial() && simbolo == transiciones.get(i).getSimbolo()) {
                estadocambio = transiciones.get(i).getEstadofinal();
                break;
            }
            i++;
        }

        return estadocambio;
    }

    @Override
    public boolean reconocer(String cadena) { //se comprueba cada caracter de la cadena llamando al metodo transicion
        char[] simbolo = cadena.toCharArray();
        int estado = estadoinicial;
        for (int i = 0; i < simbolo.length; i++) {
            System.out.println("ESTADO INICIAL: " + estado);
            System.out.println("SIMBOLO: " + simbolo[i]);

            estado = transicion(estado, simbolo[i]);
            if (estado == -1) {
                System.out.println("ESTADO MAL: " + estado);
                return false;
            }

            System.out.println("ESTADO CAMBIO: " + estado + "\n");
        }
        return esFinal(estado);
    }

    public void setEstadoInicial(int ei) {
        estadoinicial = ei;
    }

    public void añadirEstadoFinal(int ef) {
        estadosFinales.add(ef);
    }

    public void agregarTransicion(int eo, char simbolo, int ed) {
        transiciones.add(new Transicion(eo, simbolo, ed));
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

    @Override
    public String toString() {
        return "AFD{" + "estadoinicial=" + estadoinicial + ", estadosFinales=" + estadosFinales + ", transiciones=" + transiciones + '}';
    }

}
