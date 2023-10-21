package LabFP.Lab03;

import java.util.*;

public class Soldados2 {
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    int random1 = (int)(Math.random()*5+1);
    int random2 = (int)(Math.random()*5+1);
    Soldado[] ejercito1=new Soldado[random1];
    Soldado[] ejercito2=new Soldado[random2];
    crearEjercito(ejercito1);
    crearEjercito(ejercito2);
    System.out.println("\n--- Ejercito 1 ---");
    mostrarSoldados(ejercito1);
    System.out.println("\n--- Ejercito 2 ---");
    mostrarSoldados(ejercito2);
    ganador(ejercito1, ejercito2);
    sc.close();
  }
  public static void mostrarSoldados(Soldado[] ejercito){
    for(int i=0;i<ejercito.length; i++){
      System.out.println("- "+ejercito[i].getNombre());
    }
  }
  public static void ganador (Soldado[] a,Soldado[] b){
    if(a.length==b.length){
        System.out.println("\nEmpate");
    } else if(a.length>b.length){
        System.out.println("\nEjercito 1 GANA");
    } else {
        System.out.println("\nEjercito 2 GANA");
    }
  }
  public static Soldado[] crearEjercito (Soldado[] ejercito ){
    for(int i=0; i<ejercito.length; i++){
      ejercito[i]=new Soldado();
      ejercito[i].setNombre("Soldado"+(i+1));

    }
    return ejercito;
}
}
