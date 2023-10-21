package LabFP.Lab03;

import java.util.*;

public class Soldados {
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    Soldado[] ejercito=new Soldado[5];
    for(int i=0; i<ejercito.length; i++){
      System.out.println("Soldado "+(i+1));
      System.out.print("Nombre: ");
      String nombre=sc.next();
      System.out.print("Vida: ");
      int vida=sc.nextInt();

      ejercito[i]=new Soldado();

      ejercito[i].setNombre(nombre);
      ejercito[i].setVida(vida);
    }
    sc.close();
    mostrarSoldados(ejercito);
  }
  public static void mostrarSoldados(Soldado[] ejercito){
    for(int i=0;i<ejercito.length; i++){
      System.out.println("--- Soldado Nro "+(i+1)+" ---");
      System.out.println("Nombre: "+ejercito[i].getNombre());
      System.out.println("Vida: "+ejercito[i].getVida()+"\n");
    }
  }
}
