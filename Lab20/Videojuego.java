package LabFP.Lab20;
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
    /*while(jugar){
      System.out.println("\n----------BIENVENIDO A WARZONE----------");
      System.out.println("Jugador 1 -> SoldadosZ");
      System.out.println("Jugador 2 -> SoldadosX");
      Soldado[][] tablero=new Soldado[MAX_SOLDADOS][MAX_SOLDADOS];
      Ejercito ejercito1=new Ejercito(SIMBOL_EJERCITO1);
      Ejercito ejercito2=new Ejercito(SIMBOL_EJERCITO2);
      preTablero(ejercito1, tablero);
      preTablero(ejercito2, tablero);

      mostrarTablero(tablero);
      ejercito1.mostrarSoldados();
      ejercito2.mostrarSoldados();

      
      while(ejercito1.iterar().size()!=0&&ejercito2.iterar().size()!=0){
        Soldado mySoldado1=null;
        while(mySoldado1==null){
          System.out.print("\nJugador 1, seleccione un soldado: ");
          String s1=sc.next();
          mySoldado1=ejercito1.buscarSoldado(s1, ejercito1.iterar());
          if(mySoldado1==null)
            System.out.println("Soldado no encontrado");
        }
        realizarAccion(tablero, mySoldado1, ejercito1.iterar(), ejercito2.iterar(), sc);
        ejercito1.mostrarSoldados();
        ejercito2.mostrarSoldados();
        if(ejercito2.iterar().size()>0 && ejercito1.iterar().size()>0){
          Soldado mySoldado2=null;
          while(mySoldado2==null){
            System.out.print("\nJugador 2, seleccione un soldado: ");
            String s2=sc.next();
            mySoldado2=ejercito2.buscarSoldado(s2, ejercito2.iterar());
            if(mySoldado2==null)
            System.out.println("Soldado no encontrado");
          }
          realizarAccion(tablero, mySoldado2, ejercito1.iterar(), ejercito2.iterar(), sc);
          ejercito1.mostrarSoldados();
          ejercito2.mostrarSoldados();
        }
        if(ejercito2.iterar().size()==0){
          System.out.printf("\n%s GANA!!!\n\n", "Jugador 1");
        }else if(ejercito1.iterar().size()==0){
          System.out.printf("\n%s GANA!!!\n\n", "Jugador 2");
        }
      }
      System.out.println("Quieres seguir jugando?(true/false) ");
      boolean seguirJugando=sc.nextBoolean();
      if(seguirJugando==false)
        jugar=false;
    }*/
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
          System.out.print("|"+nombre.substring(nombre.length()-2, nombre.length()-1)+"-"+nombre.substring(0, 1)+columna.getvidaActual());
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
  public static void eliminarSoldado(Soldado eliminar, ArrayList<Soldado> soldados) {
    Iterator<Soldado> iterator = soldados.iterator();
    while (iterator.hasNext()) {
        Soldado entry = iterator.next();
        if (entry.getNombre().equals(eliminar.getNombre())) {
            iterator.remove();
            break;
        }
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
  public static void realizarAccion(Soldado[][] tablero, Soldado soldado, ArrayList<Soldado> ejercito, ArrayList<Soldado> ejercitoE, Scanner sc){
    Random random=new Random();
    int fila=tablero.length;
    int columna=tablero[0].length;

    System.out.print("Escriba coordenadas(fila, columna): ");
    int fila0=sc.nextInt();
    int columna0=sc.nextInt();
    if(fila0>=0 && fila0<fila && columna0>=0 && columna0<columna){
      if(tablero[fila0][columna0]!=null){
        System.out.println("*Enemigo encontrado*");

        Soldado soldadoE=tablero[fila0][columna0];
        double vida1=soldado.getvidaActual();
        double vida2=soldadoE.getvidaActual();
        System.out.println("Vida total de: "+soldado.getNombre()+"("+(int)vida1+")");
        System.out.println("Vida total de: "+soldadoE.getNombre()+"("+(int)vida2+")");
        int numGanador=(int)random.nextDouble(vida1+vida2)+1;
        System.out.printf("Probabilidades de vencer: \n%s: %.2f%% | %s: %.2f%%\n", soldado.getNombre(),
          (100/(vida1+vida2)*vida1), soldadoE.getNombre(), (100/(vida1+vida2)*vida2));
        if(numGanador<=vida1){
          System.out.println("*Enemigo derrotado*");
          //ejercito.get(ejercito.indexOf(soldado)).actualizarVida(1);
          tablero[fila0][columna0]=soldado;
          tablero[soldado.getFila()][soldado.getColumna()]=null;
          soldado.setFila(fila0);
          soldado.setColumna(columna0);
          
          soldado.actualizarVida(1);
          eliminarSoldado(soldadoE, ejercitoE);
          
        }else if(numGanador>vida1){
          System.out.println("*Aliado derrotado*");
          //ejercitoE.get(ejercitoE.indexOf(soldadoE)).actualizarVida(1);
          tablero[soldado.getFila()][soldado.getColumna()]=null;
          
          soldadoE.actualizarVida(1);
          eliminarSoldado(soldado, ejercito);
          
        }
        mostrarTablero(tablero);
      }else
        System.out.println("No se encontro enemigos\n");
    }else
      System.out.println("Coordenadas fuera del limite!!!");
  }
}
