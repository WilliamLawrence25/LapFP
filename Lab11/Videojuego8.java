package LabFP.Lab11;
import java.util.*;

public class Videojuego8 {
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

      System.out.printf("\nControles\n(1)Atacar\n(2)Defender\n(3)Huir\n(4)Retroceder\n");
      
      while(soldados1.size()!=0&&soldados2.size()!=0){
        System.out.print("\nJugador 1, seleccione soldado: ");
        String s1=sc.next();
        Soldado mySoldado1=buscarSoldado(s1, soldados1);
        if(mySoldado1==null)
          System.out.println("Soldado no encontrado");
        System.out.print("Escoja una accion: ");
        int control1=sc.nextInt();
        realizarAccion(mySoldado1, sc, control1, soldados1, soldados2, tablero);
        if(soldados2.size()>0 && soldados1.size()>0){
          System.out.print("\nJugador 2, seleccione soldado: ");
          String s2=sc.next();
          Soldado mySoldado2=buscarSoldado(s2, soldados2);
          if(mySoldado2==null)
            System.out.println("Soldado no encontrado");
          System.out.print("Escoja una accion: ");
          int control2=sc.nextInt();
          realizarAccion(mySoldado2, sc, control2, soldados2, soldados1, tablero);
        }
      }
      if(soldados2.size()==0){
        System.out.println("\nJugador 1 GANA!!!\n");
      }else{
        System.out.println("\nJugador 2 GANA!!!\n");
      }
      //System.out.println("\nMetrica: Ejercito con mas soldados");
      //System.out.println(""+ejercitoGanador(soldados1, soldados2)+"\n");
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
  public static Soldado buscarSoldado(String soldado, HashMap<Integer, Soldado> soldados){
    for(int i=0; i<soldados.size(); i++)
      if(soldados.get(i)!=null){
        if(soldados.get(i).getNombre().equalsIgnoreCase(soldado)){
        return soldados.get(i);
        }
      }
    return null;
  }
  public static void eliminarSoldado(Soldado eliminar, HashMap<Integer, Soldado> soldados){
    for (Map.Entry<Integer, Soldado> entry : soldados.entrySet())
      if (entry.getValue()==eliminar){
        soldados.remove(entry.getKey());
        break;
      }
  }
  /*public static void actualizarTablero(Soldado s, Soldado[][] tablero){
    Soldado[][] tableroNew=new Soldado[tablero.length][tablero[0].length];
    for (int i=0; i<tablero.length; i++) 
      for (int j=0; j<tablero[0].length; j++) 
        if (tablero[i][j]!=s) 
          tableroNew[i][j]=tablero[i][j];
    return tableroNew;
  }*/
  public static void realizarAccion(Soldado soldado, Scanner sc, int control, 
  HashMap<Integer, Soldado> soldados, HashMap<Integer, Soldado> soldadosE, Soldado[][] tablero){
    Random random=new Random();
    int fila=tablero.length;
    int columna=tablero[0].length;
    if(control==1){
      System.out.print("Escriba coordenadas(fila, columna): ");
      int fila0=sc.nextInt();
      int columna0=sc.nextInt();
      
      if(fila0>=0 && fila0<fila && columna0>=0 && columna0<columna){
        if(tablero[fila0][columna0]!=null && !soldados.containsValue(tablero[fila0][columna0])){
          System.out.println("*Enemigo encontrado*");
          Soldado enemigo=tablero[fila0][columna0];
          /*soldado.atacar(enemigo);
          if(enemigo.getVive()==false){
            eliminarSoldado(enemigo, soldadosE);
            System.out.println("Enemigo derrotado");
          }else
            System.out.println("Ataque efectuado, vida del enemigo: "+enemigo.getvidaActual());*/

          /*if(soldado.getvidaActual()>enemigo.getvidaActual()){
            System.out.println("*Enemigo derrotado*");
            tablero[fila0][columna0]=null;
            eliminarSoldado(enemigo, soldadosE);
            mostrarTablero(tablero);
          }else if(soldado.getvidaActual()<enemigo.getvidaActual()){
            System.out.println("*Aliado derrotado*");
            tablero[soldado.getFila()][soldado.getColumna()]=null;
            eliminarSoldado(soldado, soldados);
            mostrarTablero(tablero);
          }else{
            boolean gana=random.nextBoolean();
            if(gana){
              System.out.println("*Enemigo derrotado*");
              tablero[fila0][columna0]=null;
              eliminarSoldado(enemigo, soldadosE);
              mostrarTablero(tablero);
            }else{
              System.out.println("*Aliado derrotado*");
              tablero[soldado.getFila()][soldado.getColumna()]=null;
              eliminarSoldado(soldado, soldados);
              mostrarTablero(tablero);
            }
          }*/
          double vida1=soldado.getvidaActual();
          double vida2=enemigo.getvidaActual();
          int ganador=(int)random.nextDouble(vida1+vida2)+1;
          System.out.printf("Probabilidades de vencer: %s: %.2f | %s: %.2f\n", soldado.getNombre(),
           (100/(vida1+vida2)*vida1), enemigo.getNombre(), (100/(vida1+vida2)*vida2));
          if(ganador<=soldado.getvidaActual()){
            System.out.println("*Enemigo derrotado*");
            tablero[fila0][columna0]=soldado;
            tablero[soldado.getFila()][soldado.getColumna()]=null;
            soldado.actualizarVida(1);
            eliminarSoldado(enemigo, soldadosE);

          }else{
            System.out.println("*Aliado derrotado*");
            tablero[soldado.getFila()][soldado.getColumna()]=null;
            eliminarSoldado(soldado, soldados);
          }
          mostrarTablero(tablero);
        }else
          System.out.println("No se encontro soldados\n");
      }else
        System.out.println("Coordenadas fuera del limite!!!");
    }
  }
}
