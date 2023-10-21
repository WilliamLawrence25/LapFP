package LabFP.Lab04;

import java.util.*;
public class DemoBatallaReal {
    public static void main(String[] args){
        Nave [] misNaves = new Nave[10];
        Scanner sc = new Scanner(System.in);
        String nomb;
        char col;
        int fil, punt;
        boolean est;
        for (int i = 0; i < misNaves.length; i++) {
            System.out.println("Nave " + (i+1));
            System.out.print("Nombre: ");
            nomb = sc.next();
            System.out.print("Fila: ");
            fil = sc.nextInt();
            System.out.print("Columna: ");
            col = (char)('a'+(int)(Math.random()*15));
            System.out.print("Estado: ");
            est = sc.nextBoolean();
            System.out.print("Puntos: ");
            punt = sc.nextInt();
           
            misNaves[i] = new Nave();
           
            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            misNaves[i].setColumna(col);
            misNaves[i].setEstado(est);
            misNaves[i].setPuntos(punt);
        }
        System.out.println("\nNaves creadas:");
        mostrarNaves(misNaves);
        mostrarPorNombre(misNaves, sc);
        mostrarPorPuntos(misNaves, sc);
        System.out.println("\nNave con mayor número de puntos: \n" + 
        misNaves[(mostrarMayorPuntos(misNaves))].getNombre()+
        (" (Nave Nro ")+(mostrarMayorPuntos(misNaves)+1)+")");
        /*System.out.println("\nNaves ordenadas aleatoriamente: \n");
        navesAleatorias(misNaves);*/
/* 
        //leer un nombre
        //mostrar los datos de la nave con dicho nombre, mensaje de “no encontrado” en caso contrario
        int pos=busquedaLinealNombre(misNaves,nombre);
        ordenarPorPuntosBurbuja(misNaves);
        mostrarNaves(misNaves);
        ordenarPorNombreBurbuja(misNaves);
        mostrarNaves(misNaves);
        //mostrar los datos de la nave con dicho nombre, mensaje de “no encontrado” en caso contrario
        pos=busquedaBinariaNombre(misNaves,nombre);
        ordenarPorPuntosSeleccion(misNaves);
        mostrarNaves(misNaves);
        ordenarPorNombreSeleccion(misNaves);
        mostrarNaves(misNaves);
        ordenarPorPuntosInsercion(misNaves);
        mostrarNaves(misNaves);
        ordenarPorNombreInsercion(misNaves);
        mostrarNaves(misNaves);
*/

    }
    public static void mostrarNaves(Nave [] flota){
        for(int i = 0; i<flota.length; i++){
            System.out.println("---- Nave Nro " + (i+1) + " ----");
            System.out.println("Nombre: " + flota[i].getNombre());
            System.out.println("Fila: " + flota[i].getFila());
            System.out.println("Columna: " + flota[i].getColumna());
            System.out.println("Estado: " + flota[i].getEstado());
            System.out.println("Puntos: " + flota[i].getPuntos() + "\n");
        }
    }
    public static void mostrarPorNombre(Nave [] flota, Scanner sc){
        System.out.print("Ingrese nave a buscar: ");
        String nave = sc.next();
        System.out.println("Naves encontradas: ");
        for(int i=0; i<flota.length; i++){
            if(nave.equals(flota[i].getNombre())){
                System.out.println("- "+flota[i].getNombre()+
                " (Nave Nro "+(i+1)+")");
            }
        }
    }
    public static void mostrarPorPuntos(Nave [] flota, Scanner sc){
        System.out.println("\nIngrese Nro de puntos a buscar: ");
        int puntos = sc.nextInt();
        System.out.println("Naves encontradas: ");
        for(int i=0; i<flota.length; i++){
            if(flota[i].getPuntos()<=puntos){
                System.out.println("- "+(" ("+(flota[i].getPuntos()+1)+" pts) ")+
                flota[i].getNombre()+
                " (Nave Nro "+(i+1)+")");
            }
        }
    }
    public static int mostrarMayorPuntos(Nave [] flota){
        int max = 0;
        for(int i=0; i<flota.length; i++){
            if(flota[i].getPuntos()>max){
                max=flota[i].getPuntos();
            }
        }
        for(int l=0; l<flota.length; l++){
            if(max==flota[l].getPuntos()){
                max = l;
                break;
            }
        }
        return max;
    }
    //Método para buscar la primera nave con un nombre que se pidió por teclado
    /*public static int busquedaLinealNombre(Nave[] flota, String s){

    }*/
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosBurbuja(Nave[] flota){

    }
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreBurbuja(Nave[] flota){

    }
    //Método para buscar la primera nave con un nombre que se pidió por teclado
    /*public static int busquedaBinariaNombre(Nave[] flota, String s){

    }*/
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosSeleccion(Nave[] flota){

    }
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreSeleccion(Nave[] flota){

    }
    //Método que muestra las naves ordenadas por número de puntos de mayor a menor
    public static void ordenarPorPuntosInsercion(Nave[] flota){

    }
    //Método que muestra las naves ordenadas por nombre de Z a A
    public static void ordenarPorNombreInsercion(Nave[] flota){
        
    }







}
