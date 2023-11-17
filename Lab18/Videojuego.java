package LabFP.Lab18;
import java.util.*;

public class Videojuego {
  private static final int MAX_SOLDADOS=10;
  private static final String SIMBOL_EJERCITO1="Z";
  private static final String SIMBOL_EJERCITO2="X";
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    boolean jugar=true;

    while(jugar){
      System.out.println("\n----------BIENVENIDO A WARZONE----------");
      Soldado[][] tablero=new Soldado[MAX_SOLDADOS][MAX_SOLDADOS];
      Ejercito ejercito1=new Ejercito(SIMBOL_EJERCITO1);
      Ejercito ejercito2=new Ejercito(SIMBOL_EJERCITO2);
      preTablero(ejercito1, tablero);
      preTablero(ejercito2, tablero);
      Soldado s1=ejercito1.soldadoMasFuerte();
      Soldado s2=ejercito2.soldadoMasFuerte();

      mostrarTablero(tablero);

      System.out.println("\n-----Ejercito Nro1(Z)----");
      System.out.println("Soldado con mayor nivel de vida: ");
      System.out.printf("%-12s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n", s1.getNombre(),s1.getNivelVida(), s1.getFila(), s1.getColumna(), s1.getNivelAtaque(), s1.getNivelDefensa());
      System.out.printf("Promedio de nivel de vida: %.2f", promedioVida(ejercito1));
      ejercito1.mostrarSoldados();
      System.out.print("\nRANKING DE PODER");
      ejercito1.mostrarRankingPorSeleccion();
      
      System.out.println("\n-----Ejercito Nro2(X)----");
      System.out.println("Soldado con mayor nivel de vida: ");
      System.out.printf("%-12s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n", s2.getNombre(),s2.getNivelVida(), s2.getFila(), s2.getColumna(), s2.getNivelAtaque(), s2.getNivelDefensa());
      System.out.printf("Promedio de nivel de vida: %.2f", promedioVida(ejercito2));
      ejercito2.mostrarSoldados();
      System.out.print("\nRANKING DE PODER");
      ejercito2.mostrarRankingPorSeleccion();

      determinarGanador(ejercito1, ejercito2);

      System.out.println("\nQuieres seguir jugando?(true/false) ");
      boolean seguirJugando=sc.nextBoolean();
      if(seguirJugando==false)
        jugar=false;
    }
    sc.close();
  }
  public static void mostrarTablero(Soldado[][] tablero){
    System.out.println("\nTablero de soldados");
    System.out.print(" _________________________________________________\n");
    for(Soldado[] fila:tablero){
      for(Soldado columna: fila){
        if(columna==null){
          System.out.print("|____");
        }else{
          String nombre=columna.getNombre();
          System.out.print("|"+nombre.substring(nombre.length()-2, nombre.length()-1)+"-"+nombre.substring(0, 1)+columna.getNivelVida());
        }
      }
      System.out.println("|");
    }
  }
  public static void preTablero(Ejercito ejercito, Soldado[][] tablero){
    Random random=new Random();
    int fila, columna;
    for(Soldado s: ejercito.iterar()){
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero[fila][columna]!= null);
      s.setFila(fila);
      s.setColumna(columna);
      tablero[fila][columna]=s;
    }
  }
  private static void determinarGanador(Ejercito ejercito1, Ejercito ejercito2) {
    System.out.println("\n---Metrica: Ejercito con mas soldados---");
    System.out.println("Total de vida del Ejercito1: " + ejercito1.vidaTotalEjercito());
    System.out.println("Total de vida del Ejercito2: " + ejercito2.vidaTotalEjercito());
    if (ejercito1.vidaTotalEjercito() > ejercito2.vidaTotalEjercito()) 
      System.out.println("¡El Jugador 1 gana la batalla!");
    else if (ejercito1.vidaTotalEjercito() < ejercito2.vidaTotalEjercito()) 
      System.out.println("¡El Jugador 2 gana la batalla!");
    else 
      System.out.println("¡La batalla termina en empate!");
  }
  public static double promedioVida(Ejercito ejercito){
    double promedio=0;
    for(Soldado soldado: ejercito.iterar())
      promedio+=soldado.getNivelVida();
    return promedio/(double)ejercito.getTotalSoldados();
  }
}
