package LabFP.Lab04;
import java.util.*;
public class DemoBatalla2 {
    public static void main(String[] args){
        Nave [] misNaves = new Nave[10];
        Scanner sc = new Scanner(System.in);
        String nomb;
        char col;
        int fil, punt;
        boolean est;
        String[] nombres={"Vel", "Will", "Velmork", "Eve", "Osu", "Mrekk"};
        for (int i = 0; i < misNaves.length; i++) {
            nomb = nombres[(int)(Math.random()*nombres.length)];
            fil = (int)(Math.random()*(5-1)+1);
            col = (char)('a'+(int)(Math.random()*15));
            est = Math.random()<0.5;
            punt = (int)(Math.random()*(20-1)+1);
           
            misNaves[i] = new Nave();
            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            misNaves[i].setColumna(col);
            misNaves[i].setEstado(est);
            misNaves[i].setPuntos(punt);
        }
        System.out.println("\nNAVES CREADAS:");
        mostrarNaves(misNaves);
        //mostrarPorNombre(misNaves, sc);
        //mostrarPorPuntos(misNaves, sc);
        //System.out.println("\nNave con mayor número de puntos: \n" + 
        //misNaves[(mostrarMayorPuntos(misNaves))].getNombre()+
        //(" (Nave Nro ")+(mostrarMayorPuntos(misNaves)+1)+")");

        System.out.print("\nIngrese nave a buscar: ");
        String nombre=sc.nextLine();
        System.out.println("(Busqueda Lineal)");
        int pos=busquedaLinealNombre(misNaves,nombre);
        if(pos>=0){
            System.out.println("---- Nave Nro " + (pos+1) + " ----");
            System.out.println("Nombre: " + misNaves[pos].getNombre());
            System.out.println("Fila: " + misNaves[pos].getFila());
            System.out.println("Columna: " + misNaves[pos].getColumna());
            System.out.println("Estado: " + misNaves[pos].getEstado());
            System.out.println("Puntos: " + misNaves[pos].getPuntos() + "\n");
        } else {System.out.println("no encontrado");}
        
        System.out.println("ORDENAMIENTO POR PUNTOS(BURBUJA)");
        ordenarPorPuntosBurbuja(misNaves);
        mostrarNaves(misNaves);
        System.out.println("ORDENAMIENTO POR NOMBRE(BURBUJA)");
        ordenarPorNombreBurbuja(misNaves);
        mostrarNaves(misNaves);

        System.out.println("\n(Busqueda Binaria)");
        pos=busquedaBinariaNombre(misNaves,nombre);
        if(pos>=0){
            System.out.println("---- Nave Nro " + (pos+1) + " ----");
            System.out.println("Nombre: " + misNaves[pos].getNombre());
            System.out.println("Fila: " + misNaves[pos].getFila());
            System.out.println("Columna: " + misNaves[pos].getColumna());
            System.out.println("Estado: " + misNaves[pos].getEstado());
            System.out.println("Puntos: " + misNaves[pos].getPuntos() + "\n");
        } else {System.out.println("no encontrado");}
        
        ordenarPorPuntosSeleccion(misNaves);
        System.out.println("ORDENAMIENTO POR PUNTOS(SELECCION)");
        mostrarNaves(misNaves);
        ordenarPorNombreSeleccion(misNaves);
        System.out.println("ORDENAMIENTO POR NOMBRE(SELECCION)");
        mostrarNaves(misNaves);
        ordenarPorPuntosInsercion(misNaves);
        System.out.println("ORDENAMIENTO POR PUNTOS(INSERCION)");
        mostrarNaves(misNaves);
        ordenarPorNombreInsercion(misNaves);
        System.out.println("ORDENAMIENTO POR NOMBRE(INSERCION)");
        mostrarNaves(misNaves);
        sc.close();
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
        sc.nextLine();
        System.out.println("Naves encontradas: ");
        for(int i=0; i<flota.length; i++){
            if(flota[i].getPuntos()<=puntos){
                System.out.println("- "+(" ("+(flota[i].getPuntos())+" pts) ")+
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
    public static int busquedaLinealNombre(Nave[] flota, String s){
        for(int i=0; i<flota.length; i++){
            if(s.equals(flota[i].getNombre())){
                return i;
            }
        }
        return -1; 
    }
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosBurbuja(Nave[] flota){
        int n = flota.length;
        boolean intercambiado;
        do {
            intercambiado = false;
            for (int i = 1; i < n; i++) {
                if (flota[i - 1].getPuntos()>flota[i].getPuntos()) {
                    Nave temp = flota[i - 1];
                    flota[i - 1] = flota[i];
                    flota[i] = temp;
                    intercambiado = true;
                }
            }
        } while (intercambiado);
    }
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreBurbuja(Nave[] flota){
        int n = flota.length;
        boolean intercambiado;
        do {
            intercambiado = false;
            for (int i = 1; i < n; i++) {
                if (flota[i - 1].getNombre().compareTo(flota[i].getNombre()) > 0) {
                    Nave temp = flota[i - 1];
                    flota[i - 1] = flota[i];
                    flota[i] = temp;
                    intercambiado = true;
                }
            }
        } while (intercambiado);
    }
    //Método para buscar la primera nave con un nombre que se pidió por teclado
    public static int busquedaBinariaNombre(Nave[] flota, String s){
        int baja = 0;
        int alta = flota.length - 1;
        while (baja <= alta) {
            int media = baja + (alta - baja) / 2;
            String nombreNaveMedio = flota[media].getNombre();
            int comparacion = nombreNaveMedio.compareTo(s);
            if (comparacion == 0) {
                return media;
            } else if (comparacion < 0) {
                baja = media + 1;
            } else {
                alta = media - 1;
            }
        }
        return -1;
    }
    //Método que ordena por número de puntos de menor a mayor
    public static void ordenarPorPuntosSeleccion(Nave[] flota){
        int n = flota.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (flota[j].getPuntos() < flota[minIndex].getPuntos()) {
                    minIndex = j;
                }
            }
            Nave temp = flota[i];
            flota[i] = flota[minIndex];
            flota[minIndex] = temp;
        }
    }
    //Método que ordena por nombre de A a Z
    public static void ordenarPorNombreSeleccion(Nave[] flota){
        int n = flota.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (flota[j].getNombre().compareTo(flota[minIndex].getNombre()) < 0) {
                    minIndex = j;
                }
            }
            Nave temp = flota[i];
            flota[i] = flota[minIndex];
            flota[minIndex] = temp;
        }
    }
    //Método que muestra las naves ordenadas por número de puntos de mayor a menor
    public static void ordenarPorPuntosInsercion(Nave[] flota){
        int n = flota.length;
        for (int i = 1; i < n; i++) {
            Nave clave = flota[i];
            int j = i - 1;
            while (j >= 0 && flota[j].getPuntos() < clave.getPuntos()) {
                flota[j + 1] = flota[j];
                j = j - 1;
            }
            flota[j + 1] = clave;
        }
    }
    //Método que muestra las naves ordenadas por nombre de Z a A
    public static void ordenarPorNombreInsercion(Nave[] flota){
        int n = flota.length;
        for (int i = 1; i < n; i++) {
            Nave clave = flota[i];
            int j = i - 1;
            while (j >= 0 && flota[j].getNombre().compareTo(clave.getNombre()) > 0) {
                flota[j + 1] = flota[j];
                j = j - 1;
            }
            flota[j + 1] = clave;
        }
    }

}
