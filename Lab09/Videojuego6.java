package LabFP.Lab09;
import java.util.*;

public class Videojuego6 {
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    Random random=new Random();
    do{
      Soldado[][] tablero=new Soldado[10][10];
      HashMap<Integer, Soldado> soldados1=crearEjercito(random, tablero, "Z");
      HashMap<Integer, Soldado> soldados2=crearEjercito(random, tablero, "X");

      mostrarTablero(tablero);

      System.out.println("\n---------Ejercito Nro1(Z)--------");
      System.out.println("-Soldado con mayor nivel de vida-");
      System.out.println("Nombre: "+soldadoMasFuerte(soldados1).getNombre());
      System.out.println("Nvl Vida: " + soldadoMasFuerte(soldados1).getNivelVida());
      System.out.println("Promedio de vida: "+promedioVida(soldados1));
      mostrarSoldados(soldados1);
      System.out.print("\nRANKING DE PODER (SELECCION)");
      mostrarRankingPorSeleccion(soldados1);
      System.out.print("\nRANKING DE PODER (BURBUJA)");
      mostrarRankingPorBurbuja(soldados1);

      System.out.println("\n\n---------Ejercito Nro2(X)--------");
      System.out.println("-Soldado con mayor nivel de vida-");
      System.out.println("Nombre: "+soldadoMasFuerte(soldados2).getNombre());
      System.out.println("Nvl Vida: " + soldadoMasFuerte(soldados1).getNivelVida());
      System.out.println("Promedio de vida: "+promedioVida(soldados2));
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
  public static HashMap<Integer, Soldado> crearEjercito (Random random, Soldado[][] tablero, String n){
    HashMap<Integer, Soldado> soldados=new HashMap<>();
    int fila, columna;
    for(int i=0; i<random.nextInt(10)+1; i++){
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero[fila][columna]!= null);
      Soldado soldado=new Soldado(i, n);
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
          System.out.print("|"+columna.getNombre().substring(7, 9)+"");
        }
      }
      System.out.println("|");
    }
  }
  public static Soldado soldadoMasFuerte(HashMap<Integer, Soldado> soldados){
    Soldado masFuerte=soldados.get(0);
    for(int i=0; i<soldados.size(); i++)
      if(soldados.get(i).getNivelVida()>masFuerte.getNivelVida())
        masFuerte=soldados.get(i);
    return masFuerte;
  }
  public static int promedioVida(HashMap<Integer, Soldado> soldados){
    int promedio=0;
    for(int i=0; i<soldados.size(); i++)
      promedio=promedio+soldados.get(i).getNivelVida();
    return promedio/soldados.size();
  }
  public static void mostrarSoldados(HashMap<Integer, Soldado> soldados){
    System.out.println("\n----------Datos de los soldados------------");
    for(Integer i:soldados.keySet()){
      Soldado s=soldados.get(i);
      System.out.printf("%s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n" 
      , s.getNombre(), s.getNivelVida(), s.getFila(), s.getColumna(), s.getNivelAtaque(), s.getNivelDefensa());}
  }
  public static void mostrarRankingPorSeleccion(HashMap<Integer, Soldado> soldados){
    Soldado temp=new Soldado();
    for(int i=soldados.size(); i>0; i--)
      for(int j=0; j<soldados.size()-1; j++)
        if(soldados.get(j+1).getNivelVida()>soldados.get(j).getNivelVida()){
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
        if(soldados.get(j).getNivelVida()>soldados.get(max).getNivelVida())
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
