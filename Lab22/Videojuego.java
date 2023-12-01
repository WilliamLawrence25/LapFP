package LabFP.Lab22;
import java.util.*;

public class Videojuego {
  private static final int MAX_SOLDADOS=10;
  private static final String SIMBOL_EJERCITO1="Z";
  private static final String SIMBOL_EJERCITO2="X";
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    Random random=new Random();
    boolean jugar=true;

    while(jugar){
      System.out.println("\n----------BIENVENIDO A WARZONE----------");
      
      String[] nombresReinos={"Inglaterra", "Francia", "Sacro Imperio", "Castilla-Aragon", "Moros"};      
      System.out.println("Jugador 1 y 2, escojan un reino(diferentes):\n(1)Inglaterra\n(2)Francia\n(3)Sacro Imperio\n(4)Castilla-Aragon\n(5)Moros");
      int select1=sc.nextInt()-1;
      int select2=sc.nextInt()-1;
      String reinoJugador1=nombresReinos[select1];
      String reinoJugador2=nombresReinos[select2];
      Mapa mapa=new Mapa(random.nextInt(4));

      Soldado[][] tablero=new Soldado[MAX_SOLDADOS][MAX_SOLDADOS];
      ArrayList<Ejercito> reino1=crearReino(mapa, SIMBOL_EJERCITO1, reinoJugador1);
      ArrayList<Ejercito> reino2=crearReino(mapa, SIMBOL_EJERCITO2, reinoJugador2);

      preTablero(reino1.get(0), tablero);
      preTablero(reino2.get(0), tablero);
      mapa.mostrarTableroSoldados(tablero);
      reino1.get(0).mostrarSoldados();
      reino2.get(0).mostrarSoldados();

      while(reino1.get(0).iterar().size()!=0&&reino2.get(0).iterar().size()!=0){
        Soldado mySoldado1=null;
        while(mySoldado1==null){
          System.out.print("\nJugador 1, seleccione un soldado: ");
          String s1=sc.next();
          mySoldado1=reino1.get(0).buscarSoldado(s1, reino1.get(0).iterar());
          if(mySoldado1==null)
            System.out.println("Soldado no encontrado");
        }
        realizarAccion(tablero, mySoldado1, reino1.get(0).iterar(), reino2.get(0).iterar(), sc);
        mapa.mostrarTableroSoldados(tablero);
        reino1.get(0).mostrarSoldados();
        reino2.get(0).mostrarSoldados();
        reino1.get(0).resumenEjercito(reinoJugador1);
        if(reino2.get(0).iterar().size()>0 && reino1.get(0).iterar().size()>0){
          Soldado mySoldado2=null;
          while(mySoldado2==null){
            System.out.print("\nJugador 2, seleccione un soldado: ");
            String s2=sc.next();
            mySoldado2=reino2.get(0).buscarSoldado(s2, reino2.get(0).iterar());
            if(mySoldado2==null)
            System.out.println("Soldado no encontrado");
          }
          realizarAccion(tablero, mySoldado2, reino1.get(0).iterar(), reino2.get(0).iterar(), sc);
          reino1.get(0).mostrarSoldados();
          reino2.get(0).mostrarSoldados();
        }
        reino1.get(0).resumenEjercito(reinoJugador1);
        if(reino2.get(0).iterar().size()==0){
          System.out.printf("\n%s GANA!!!\n\n", "Jugador 1");
        }else if(reino1.get(0).iterar().size()==0){
          System.out.printf("\n%s GANA!!!\n\n", "Jugador 2");
        }
      }
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
