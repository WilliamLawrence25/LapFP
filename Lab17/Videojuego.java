package LabFP.Lab17;
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

      Mapa mapa=new Mapa(random.nextInt(5));
      HashMap<Integer, Ejercito> reino1=crearReino(mapa, random, SIMBOL_EJERCITO1, reinoJugador1);
      HashMap<Integer, Ejercito> reino2=crearReino(mapa, random, SIMBOL_EJERCITO2, reinoJugador2);
      mapa.mostrarTableroEjercitos();
      /*System.out.println("\nJugador 1: ");
      modificarEjercito(reino1, SIMBOL_EJERCITO1, sc);
      System.out.println("\nJugador 2: ");
      modificarEjercito(reino2, SIMBOL_EJERCITO2, sc);*/
      
      while(reino1.size()!=0&&reino2.size()!=0){
        Ejercito myEjercito1=null;
        while(myEjercito1==null){
          System.out.print("\nJugador 1, seleccione un ejercito: ");
          String s1=sc.next();
          myEjercito1=buscarEjercito(s1, reino1);
          if(myEjercito1==null)
            System.out.println("Soldado no encontrado");
        }
        realizarAccion(mapa, myEjercito1, reino1, reino2, sc);

        if(reino2.size()>0 && reino1.size()>0){
          Ejercito myEjercito2=null;
          while(myEjercito2==null){
            System.out.print("\nJugador 2, seleccione un ejercito: ");
            String s2=sc.next();
            myEjercito2=buscarEjercito(s2, reino2);
            if(myEjercito2==null)
            System.out.println("Soldado no encontrado");
          }
          realizarAccion(mapa, myEjercito2, reino1, reino2, sc);
        }
        if(reino2.size()==0){
          System.out.printf("\n%s GANA!!!\n\n", reinoJugador1);
        }else if(reino1.size()==0){
          System.out.printf("\n%s GANA!!!\n\n", reinoJugador2);
        }
      }
      System.out.println("Quieres seguir jugando?(true/false) ");
      boolean seguirJugando=sc.nextBoolean();
      if(seguirJugando==false)
        jugar=false;
    }
    sc.close();
  }
  public static HashMap<Integer, Ejercito> crearReino(Mapa mapa, Random random, String n, String reinoN){
    HashMap<Integer, Ejercito> reino=new HashMap<>();
    int fila, columna;
    for(int i=0; i<random.nextInt(MAX_SOLDADOS-1)+1; i++ ){
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(mapa.tablero()[fila][columna]!= null);
      reino.put(i, new Ejercito(n, n+i));
      reino.get(i).setFilaEjercito(fila);
      reino.get(i).setColumnaEjercito(columna);
      reino.get(i).setNombreEjercito(n+i);  
      reino.get(i).setReino(reinoN);
      mapa.agregarEjercito(fila, columna, reino.get(i), reino.get(i).vidaTotalEjercito());
    }
    return reino;
  }
  public static Ejercito buscarEjercito(String nombre, HashMap<Integer, Ejercito> reino){
    for(int i=0; i<reino.size(); i++)
      if(reino.get(i)!=null)
        if(reino.get(i).getNombreEjercito().equalsIgnoreCase(nombre)){
        return reino.get(i);
        }
    return null;
  }
  public static void eliminarEjercito(Ejercito eliminar, HashMap<Integer, Ejercito> ejercito){
    for (Map.Entry<Integer, Ejercito> entry : ejercito.entrySet())
      if (entry.getValue()==eliminar){
        ejercito.remove(entry.getKey());
        break;
      }
  }
  public static int vidaTotalReino(HashMap<Integer, Ejercito> reino){
   int total=0;
   for(int i=0; i<reino.size(); i++)
    total+=reino.get(i).vidaTotalEjercito();
   return total;
  }
  public static int ataqueTotalReino(HashMap<Integer, Ejercito> reino){
   int total=0;
   for(int i=0; i<reino.size(); i++)
    total+=reino.get(i).ataqueTotalEjercito();
   return total;
  }
  public static int totalSoldadosReino(HashMap<Integer, Ejercito> reino){
    int total=0;
    for(int i=0; i<reino.size(); i++)
      if(reino.get(i)!=null)
        total+=reino.get(i).iterar().size();
    return total;
  }
  public static Integer encontrarClave(HashMap<Integer, Ejercito> reino, Ejercito ejercito){
    for (Map.Entry<Integer, Ejercito> entry: reino.entrySet()) 
      if (entry.getValue().equals(ejercito))
        return entry.getKey();
    return null;
  }
  public static void realizarAccion(Mapa mapa, Ejercito ejercito, HashMap<Integer, Ejercito> reino, HashMap<Integer, Ejercito> reinoE, Scanner sc){
    Random random=new Random();
    int fila=mapa.tablero().length;
    int columna=mapa.tablero()[0].length;

    System.out.print("Escriba coordenadas(fila, columna): ");
    int fila0=sc.nextInt();
    int columna0=sc.nextInt();
    if(fila0>=0 && fila0<fila && columna0>=0 && columna0<columna){
      if(mapa.tablero()[fila0][columna0]!=null ){//&& !reino.containsValue(tableroReinos[fila0][columna0])
        System.out.println("*Enemigo encontrado*");
        /*Soldado[][] tablero=new Soldado[MAX_SOLDADOS][MAX_SOLDADOS];
        int filaS, columnaS;
        for(int i=0; i<ejercito.iterar().size(); i++){
          do{
            filaS=random.nextInt(10);
            columnaS=random.nextInt(10);
          }while(tablero[filaS][columnaS]!= null);
          ejercito.iterar().get(i).setFila(filaS);
          ejercito.iterar().get(i).setColumna(columnaS);
          tablero[filaS][columnaS]=ejercito.iterar().get(i);
        }
        mapa.mostrarTablero(tablero);*/

        Ejercito ejercitoE=mapa.tablero()[fila0][columna0];
        double vida1=ejercito.vidaTotalEjercito();
        double vida2=ejercitoE.vidaTotalEjercito();
        System.out.println("Vida total de: "+ejercito.getNombreEjercito()+(int)vida1);
        System.out.println("Vida total de: "+ejercitoE.getNombreEjercito()+(int)vida2);
        int numGanador=(int)random.nextDouble(vida1+vida2)+1;
        System.out.printf("Probabilidades de vencer: %s: %.2f%% | %s: %.2f%%\n", ejercito.getNombreEjercito(),
          (100/(vida1+vida2)*vida1), ejercitoE.getNombreEjercito(), (100/(vida1+vida2)*vida2));
        if(numGanador<=ejercito.vidaTotalEjercito()){
          System.out.println("*Enemigo derrotado*");
          mapa.tablero()[fila0][columna0]=ejercito;
          mapa.tablero()[ejercito.getFilaEjercito()][ejercito.getColumnaEjercito()]=null;
          ejercito.setFilaEjercito(fila0);
          ejercito.setColumnaEjercito(columna0);
          eliminarEjercito(ejercitoE, reinoE);
          //No se pude hacer que los nuevos datos de vida se actualizaran tambien en el tablero
          for(Soldado s: mapa.tablero()[fila0][columna0].iterar().values())
            s.actualizarVida(1);
          /*for (Soldado s : ejercito.iterar().values())
            s.actualizarVida(3);*/
          /*for (Soldado s : reino.get(encontrarClave(reino, ejercito)).iterar().values())
            s.actualizarVida(3);*/
        }else if(numGanador>ejercito.vidaTotalEjercito()){
          System.out.println("*Aliado derrotado*");
          mapa.tablero()[ejercito.getFilaEjercito()][ejercito.getColumnaEjercito()]=null;
          eliminarEjercito(ejercito, reino);
          for(Soldado s: mapa.tablero()[fila0][columna0].iterar().values())
            s.actualizarVida(1);
          /*for (Soldado s : ejercitoE.iterar().values())
          s.actualizarVida(3);*/
          /*for (Soldado s : reino.get(encontrarClave(reino, ejercito)).iterar().values())
            s.actualizarVida(3);*/
        }
        mapa.mostrarTableroEjercitos();
      }else
        System.out.println("No se encontro ejercitos\n");
    }else
      System.out.println("Coordenadas fuera del limite!!!");
  }
  /*public static void modificarEjercito(HashMap<Integer, Ejercito> reino, String n, Scanner sc){
    System.out.println("Desea modificar algun ejercito?(si/no)");
    String answer=sc.next();
    if(answer.equalsIgnoreCase("si")){
      System.out.println("Escriba el ejercito a modificar");
      String opcionMod=sc.next();
      Ejercito ejercitoMod=buscarEjercito(opcionMod, reino);
      ejercitoMod.modoPersonalizado(n, sc);
    }
  }*/
}
