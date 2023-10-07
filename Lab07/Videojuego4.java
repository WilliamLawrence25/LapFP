package LabFP.Lab07;
import java.util.*;

public class Videojuego4 {
  public static void main(String[] args){
    Scanner sc= new Scanner(System.in);
    Random random=new Random();
    ArrayList<ArrayList<Soldado>> tablero=new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      ArrayList<Soldado> fila = new ArrayList<>();
      for (int j = 0; j < 10; j++) {
        fila.add(null);
      }
      tablero.add(fila);
    }
    ArrayList<Soldado> soldados1=crearEjercito(random, tablero, "~");
    ArrayList<Soldado> soldados2=crearEjercito(random, tablero, "$");

    mostrarTablero(tablero);




    sc.close();
  }
  public static ArrayList<Soldado> crearEjercito (Random random, 
  ArrayList<ArrayList<Soldado>> tablero, String n){
    ArrayList<Soldado> soldados=new ArrayList<>();
    String nombre;
    int vida, fila, columna;
    for(int i=0; i<random.nextInt(10)+1; i++){
      vida=random.nextInt(5)+1;
      nombre=n+vida;
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero.get(fila).get(columna) != null);
      Soldado soldado=new Soldado();
      soldado.setNombre(nombre);
      soldado.setVida(vida);
      soldado.setFila(fila);
      soldado.setColumna(columna);
      soldados.add(soldado);
      tablero.get(fila).set(columna, soldado);
    }
    return soldados;
  }
  public static void mostrarTablero(ArrayList<ArrayList<Soldado>> tablero){
    System.out.print(" _____________________________\n");
    for(ArrayList<Soldado> fila:tablero){
      for(Soldado columna: fila){
        if(columna==null){
          System.out.print("|__");
        }else{
          System.out.print("|" + columna.getNombre() + "");
        }
      }
      System.out.println("|");
    }
    System.out.println();
    
  }
}
