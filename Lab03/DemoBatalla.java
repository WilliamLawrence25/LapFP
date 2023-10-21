package LabFP.Lab03;
import java.util.*;
public class DemoBatalla {
    public static void main(String[] args){
        Nave [] misNaves = new Nave[10];
        Scanner sc = new Scanner(System.in);
        String nomb; 
        //char col;
        int fil, punt;
        boolean est;
        for (int i = 0; i < misNaves.length; i++) {
            System.out.println("Nave " + (i+1));
            System.out.print("Nombre: ");
            nomb = sc.next();
            System.out.print("Fila: ");
            fil = sc.nextInt();
            System.out.print("Columna: ");
            //col = (char)('a'+(int)(Math.random()*15));
            System.out.print("Estado: ");
            est = sc.nextBoolean();
            System.out.print("Puntos: ");
            punt = sc.nextInt();
           
            misNaves[i] = new Nave();
           
            misNaves[i].setNombre(nomb);
            misNaves[i].setFila(fil);
            //misNaves[i].setColumna(col);
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
        System.out.println("\nNaves ordenadas aleatoriamente: \n");
        navesAleatorias(misNaves);
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
    public static void navesAleatorias(Nave[] flota){
        Random random=new Random();
        Nave[] flotaDes = new Nave[flota.length];
        System.arraycopy(flota, 0, flotaDes, 0, flota.length);
        for(int i=flotaDes.length-1; i>0; i--){
            int c=random.nextInt(i+1);
            Nave temp=flotaDes[i];
            flotaDes[i]=flotaDes[c];
            flotaDes[c]=temp;
        }
        mostrarNaves(flotaDes);
    }
}
