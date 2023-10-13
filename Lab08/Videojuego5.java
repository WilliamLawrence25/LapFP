package LabFP.Lab08;
import java.util.*;

public class Videojuego5 {
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    Random random=new Random();
    do{
      Soldado[][] tablero=new Soldado[10][10];
      HashMap<Integer, Soldado> soldados1=crearEjercito(random, tablero, "~");
      HashMap<Integer, Soldado> soldados2=crearEjercito(random, tablero, "$");

      mostrarTablero(tablero);

      System.out.println("\n-----Ejercito Nro1(~)----");
      System.out.println("Soldado con mayor nivel de vida ");
      System.out.println("Nombre: Soldado("+soldadoMasFuerte(soldados1).getNombre()+")");
      System.out.println("Nivel de Vida: " + soldadoMasFuerte(soldados1).getVida());

      System.out.println("Promedio de nivel de vida: "+promedioVida(soldados1));
      mostrarSoldados(soldados1);
      System.out.print("\nRANKING DE PODER (SELECCION)");
      mostrarRankingPorSeleccion(soldados1);
      System.out.print("\nRANKING DE PODER (BURBUJA)");
      mostrarRankingPorBurbuja(soldados1);

      System.out.println("\n\n-----Ejercito Nro2($)----");
      System.out.println("Soldado con mayor nivel de vida: ");
      System.out.println("Nombre: Soldado("+soldadoMasFuerte(soldados2).getNombre()+")");
      System.out.println("Nivel de Vida: " + soldadoMasFuerte(soldados1).getVida());
      System.out.println("Promedio de nivel de vida: "+promedioVida(soldados2));
      mostrarSoldados(soldados2);
      System.out.print("\nRANKING DE PODER(SELECCION)");
      mostrarRankingPorSeleccion(soldados2);
      System.out.print("\nRANKING DE PODER(BURBUJA)");
      mostrarRankingPorBurbuja(soldados2);

      System.out.println("\nMetrica: Ejercito con mas soldados");
      System.out.println(""+ejercitoGanador(soldados1, soldados2)+"\n");
    }while(seguirJugando(sc).equalsIgnoreCase("si"));
    sc.close();
  }
  public static HashMap<Integer, Soldado> crearEjercito (Random random, 
  Soldado[][] tablero, String n){
    HashMap<Integer, Soldado> soldados=new HashMap<>();
    String nombre;
    int vida, fila, columna;
    for(int i=0; i<random.nextInt(10)+1; i++){
      vida=random.nextInt(5)+1;
      nombre=n+vida;
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero[fila][columna]!= null);
      Soldado soldado=new Soldado();
      soldado.setNombre(nombre);
      soldado.setVida(vida);
      soldado.setFila(fila);
      soldado.setColumna(columna);
      soldados.put(i, soldado);
      tablero[fila][columna]=soldados.get(i);
    }
    return soldados;
  }
  public static void mostrarTablero(Soldado[][] tablero){
    System.out.print(" _____________________________\n");
    for(Soldado[] fila:tablero){
      for(Soldado columna: fila){
        if(columna==null){
          System.out.print("|__");
        }else{
          System.out.print("|" + columna.getNombre() + "");
        }
      }
      System.out.println("|");
    }
  }
  public static Soldado soldadoMasFuerte(HashMap<Integer, Soldado> soldados){
    Soldado masFuerte=soldados.get(0);
    for(int i=0; i<soldados.size(); i++)
      if(soldados.get(i).getVida()>masFuerte.getVida())
        masFuerte=soldados.get(i);
    return masFuerte;
  }
  public static int promedioVida(HashMap<Integer, Soldado> soldados){
    int promedio=0;
    for(int i=0; i<soldados.size(); i++)
      promedio=promedio+soldados.get(i).getVida();
    return promedio/soldados.size();
  }
  public static void mostrarSoldados(HashMap<Integer, Soldado> soldados){
    System.out.println("\n----------Datos de los soldados------------");
    for(Integer i:soldados.keySet()){
      Soldado s=soldados.get(i);
      System.out.printf("Nombre: %s | Vida: %d | Fila: %d | Columna: %d\n" 
      , s.getNombre(), s.getVida(), s.getFila(), s.getColumna());}
  }
  public static void mostrarRankingPorSeleccion(HashMap<Integer, Soldado> soldados){
    Soldado temp=new Soldado();
    for(int i=soldados.size(); i>0; i--)
      for(int j=0; j<soldados.size()-1; j++)
        if(soldados.get(j+1).getVida()>soldados.get(j).getVida()){
          temp=soldados.get(j);
          soldados.put(j, soldados.get(j+1));
          soldados.put(j+1, temp);
        }
    mostrarSoldados(soldados);
  }
  public static void mostrarRankingPorBurbuja(HashMap<Integer, Soldado> soldados){
    for(int i=0; i<soldados.size(); i++){
      int max=i;
      for(int j=i+1; j<soldados.size(); j++)
        if(soldados.get(j).getVida()>soldados.get(max).getVida())
          max=j;
      Soldado temp=soldados.get(i);
      soldados.put(i, soldados.get(max));
      soldados.put(max, temp);
    }
    mostrarSoldados(soldados);
  }
  public static String ejercitoGanador(HashMap<Integer, Soldado> ejercito1, 
  HashMap<Integer, Soldado> ejercito2) {
    if (ejercito1.size() > ejercito2.size()) {
      return "-EL EJERCITO 1 GANA-";
    } else if (ejercito2.size() > ejercito1.size()) {
      return "-EL EJERCITO 2 GANA-";
    } else {
      return "--EMPATE--";
    }
  }
  public static String seguirJugando(Scanner sc){
    String juego="true";
    System.out.println("Desea seguir jugando?");
    juego=sc.next();
    return juego;
  }
}
