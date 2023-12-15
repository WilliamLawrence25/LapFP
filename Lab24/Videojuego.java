package LabFP.Lab24;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingUtilities;
import java.io.*;

public class Videojuego {
  private static final int MAX_SOLDADOS=10;
  private static final String SIMBOL_EJERCITO1="Z";
  private static final String SIMBOL_EJERCITO2="X";
  public static void main(String[] args) throws FileNotFoundException{
    Scanner sc=new Scanner(System.in);
    Random random=new Random();
    boolean jugar=true;

    while(jugar){
      System.out.println("\n----------BIENVENIDO A WARZONE----------");
      
      String[] nombresReinos={"Inglaterra", "Francia", "Sacro Imperio", "Castilla-Aragon", "Moros"};      
      System.out.println("Jugador 1 y 2, escojan un reino(diferentes):\n(1)Inglaterra\n(2)Francia\n(3)Sacro Imperio\n(4)Castilla-Aragon\n(5)Moros");
      
      String reinoJugador1, reinoJugador2;

      try {
        PrintWriter archivo=new PrintWriter(new FileWriter("LabFP\\Lab24\\nombresReinos.txt"), true);
        archivo.println(2-1);
        archivo.println(3-1);
        archivo.close();
      } catch (FileNotFoundException e) {
        System.out.println("Error: "+e.getMessage());
      } catch (IOException e) {
        System.out.println("Error: "+e.getMessage());
      }
      Scanner leer=new Scanner(new FileReader("LabFP\\Lab24\\nombresReinos.txt"));
      int line=leer.nextInt();
      reinoJugador1=nombresReinos[line];
      line=leer.nextInt();
      reinoJugador2=nombresReinos[line];
      leer.close();

      Mapa mapa=new Mapa(random.nextInt(4));

      Soldado[][] tablero=new Soldado[MAX_SOLDADOS][MAX_SOLDADOS];
      ArrayList<Ejercito> reino1=crearReino(mapa, SIMBOL_EJERCITO1, reinoJugador1);
      ArrayList<Ejercito> reino2=crearReino(mapa, SIMBOL_EJERCITO2, reinoJugador2);

      Soldado s1=reino1.get(0).soldadoMasFuerte();
      Soldado s2=reino2.get(0).soldadoMasFuerte();

      preTablero(reino1.get(0), tablero);
      preTablero(reino2.get(0), tablero);

      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("LabFP\\Lab24\\reinos.dat"))) {
        oos.writeObject(reino1.get(0));
        oos.writeObject(reino2.get(0));
      } catch (IOException e) {
        System.out.println("Error al escribir en el archivo binario: " + e.getMessage());
      }
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("LabFP\\Lab24\\reino1.dat"))) {
        Ejercito ejercitoLeido1 = (Ejercito) ois.readObject(); 
        Ejercito ejercitoLeido2 = (Ejercito) ois.readObject(); 
        // Puedes hacer algo con el ejército leído, como mostrar información
        ejercitoLeido1.mostrarSoldados();
        ejercitoLeido2.mostrarSoldados();
        System.out.println("Ejército leído desde el archivo binario:");
      } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error al leer desde el archivo binario: " + e.getMessage());
      }
    
      mapa.mostrarTableroSoldados(tablero);
      reino1.get(0).mostrarSoldados();
      reino2.get(0).mostrarSoldados();
      SwingUtilities.invokeLater(() -> {
        new TableroGUI(tablero);
      });

      System.out.println("\n-----Ejercito Nro1(Z)----");
      System.out.println("Soldado con mayor nivel de vida: ");
      System.out.printf("%-12s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n", s1.getNombre(),s1.getNivelVida(), s1.getFila(), s1.getColumna(), s1.getNivelAtaque(), s1.getNivelDefensa());
      System.out.printf("Promedio de nivel de vida: %.2f", promedioVida(reino1.get(0)));
      reino1.get(0).mostrarSoldados();
      System.out.print("\nRANKING DE PODER");
      reino1.get(0).mostrarRankingPorSeleccion();
      
      System.out.println("\n-----Ejercito Nro2(X)----");
      System.out.println("Soldado con mayor nivel de vida: ");
      System.out.printf("%-12s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n", s2.getNombre(),s2.getNivelVida(), s2.getFila(), s2.getColumna(), s2.getNivelAtaque(), s2.getNivelDefensa());
      System.out.printf("Promedio de nivel de vida: %.2f", promedioVida(reino2.get(0)));
      reino2.get(0).mostrarSoldados();
      System.out.print("\nRANKING DE PODER");
      reino2.get(0).mostrarRankingPorSeleccion();

      reino1.get(0).resumenEjercito(reinoJugador1);
      reino2.get(0).resumenEjercito(reinoJugador2);
      determinarGanador(reino1.get(0), reino2.get(0), reinoJugador1, reinoJugador2);

      System.out.println("Quieres seguir jugando?(true/false) ");
      boolean seguirJugando=sc.nextBoolean();
      if(seguirJugando==false)
        jugar=false;
    }
    sc.close();
  }
  public static ArrayList<Ejercito> crearReino(Mapa mapa, String n, String reinoN){
    Random random=new Random();
    ArrayList<Ejercito> reino=new ArrayList<>();
    int fila, columna;
    for(int i=0; i<1; i++ ){
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(mapa.tablero()[fila][columna]!= null);
      reino.add(i, new Ejercito(n));
      reino.get(i).setFilaEjercito(fila);
      reino.get(i).setColumnaEjercito(columna);
      reino.get(i).setNombreEjercito(n+i);  
      reino.get(i).setReino(reinoN);
      reino.get(i).unidadesEspeciales(n);
      mapa.agregarEjercito(fila, columna, reino.get(i), reino.get(i).vidaTotalEjercito());
    }
    return reino;
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
  public static double promedioVida(Ejercito ejercito){
    double promedio=0;
    for(Soldado soldado: ejercito.iterar())
      promedio+=soldado.getNivelVida();
    return promedio/(double)ejercito.getTotalSoldados();
  }
  private static void determinarGanador(Ejercito ejercito1, Ejercito ejercito2, String reinoJugador1, String reinoJugador2) {
    Random random=new Random();
    System.out.println("\n---Metrica: Ejercito con mas soldados---");
    double vida1=ejercito1.vidaTotalEjercito();
    double vida2=ejercito2.vidaTotalEjercito();
    System.out.printf("Ejercito 1: %-10s: %d   %.2f%% de probabilidad de victoria\n", reinoJugador1, ejercito1.vidaTotalEjercito(), (100/(vida1+vida2)*vida1));
    System.out.printf("Ejercito 2: %-10s: %d   %.2f%% de probabilidad de victoria\n", reinoJugador2, ejercito2.vidaTotalEjercito(), (100/(vida1+vida2)*vida2));
    double numGanador=random.nextDouble(100)+1;
    String ganador="";
    if(numGanador<=(100/(vida1+vida2)*vida1)){
      ganador="Ejercito 1 de: "+reinoJugador1;      
    }else if(numGanador>(100/(vida1+vida2)*vida2)){
      ganador="Ejercito 2 de: "+reinoJugador2;
    }
    System.out.printf("\nEl ganador es el %s. Ya que al generar los\nporcentajes de probabilidad de victoria basada en los niveles de\nvida de sus soldados y aplicando un experimento aleatorio salió\nvencedor. (Aleatorio generado: %.2f%%)\n", ganador, numGanador);
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

        if(soldado instanceof Caballero || soldado instanceof CaballeroFranco || soldado instanceof CaballeroMoro){
          if(soldadoE instanceof Arquero){
            if(soldado instanceof CaballeroFranco || soldado instanceof CaballeroMoro){ 
              soldado.actualizarVida(2);
            }else{soldado.actualizarDefensa(1);}}
          if(soldadoE instanceof Lancero){
            soldadoE.actualizarVida(1);}
          if(soldadoE instanceof Espadachin){
            soldado.actualizarVida(1);}
          if(soldadoE instanceof CaballeroFranco || soldadoE instanceof CaballeroMoro){
            soldadoE.actualizarVida(1);}
        }
        if(soldado instanceof Espadachin || soldado instanceof EspadachinConquistador || soldado instanceof EspadachinReal){
          if(soldadoE instanceof Lancero){
            if(soldado instanceof EspadachinConquistador || soldado instanceof EspadachinReal){
              soldado.actualizarVida(2);}
            else{soldado.actualizarVida(1);}}
          if(soldadoE instanceof EspadachinConquistador || soldadoE instanceof EspadachinReal){
            soldadoE.actualizarVida(1);}
        }
        if(soldado instanceof Arquero){
          if(soldadoE instanceof Lancero){
            soldado.actualizarVida(1);}
        }    

        double vida1=soldado.getvidaActual();
        double vida2=soldadoE.getvidaActual();
        System.out.println("Vida total de: "+soldado.getNombre()+"("+(int)vida1+")");
        System.out.println("Vida total de: "+soldadoE.getNombre()+"("+(int)vida2+")");
        int numGanador=(int)random.nextDouble(vida1+vida2)+1;
        System.out.printf("Probabilidades de vencer: \n%s: %.2f%% | %s: %.2f%%\n", soldado.getNombre(),
          (100/(vida1+vida2)*vida1), soldadoE.getNombre(), (100/(vida1+vida2)*vida2));

        if(numGanador<=vida1){
          System.out.println("*Enemigo derrotado*");
          tablero[fila0][columna0]=soldado;
          tablero[soldado.getFila()][soldado.getColumna()]=null;
          soldado.setFila(fila0);
          soldado.setColumna(columna0);
          soldado.actualizarVida(1);
          eliminarSoldado(soldadoE, ejercitoE);
          
        }else if(numGanador>vida1){
          System.out.println("*Aliado derrotado*");
          tablero[soldado.getFila()][soldado.getColumna()]=null;
          soldadoE.actualizarVida(1);
          eliminarSoldado(soldado, ejercito);
          
        }
      }else
        System.out.println("No se encontro enemigos\n");
    }else
      System.out.println("Coordenadas fuera del limite!!!");
  }
}
