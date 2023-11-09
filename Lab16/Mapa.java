package LabFP.Lab16;

import java.util.HashMap;

public class Mapa {
  private String terreno;
  private static final int MAX_SOLDADOS=10;
  private Ejercito[][] tableroReinos=new Ejercito[MAX_SOLDADOS][MAX_SOLDADOS]; 

  public Mapa(int i){
    String[] terreno={"bosque", "campo abierto", "playa", ",montana", "desierto"};
    this.terreno=terreno[i];
  }
  public Ejercito[][] tablero(){
    return this.tableroReinos;
  }
  public void agregarEjercito(int fila, int columna, Ejercito ejercito, int totalSoldados){
    this.tableroReinos[fila][columna]=ejercito;
    String[] terreno={"bosque", "campo abierto", "playa", ",montana", "desierto"};
    String[] nombresReinos={"Inglaterra", "Francia", "Sacro Imperio", "Castilla-Aragon", "Moros"};
    if(ejercito.getReino().equals(nombresReinos[0])&&this.terreno.equals(terreno[0])){
      for(Soldado s: ejercito.iterar().values()){
        s.actualizarVida(1);
      }
    }
    if(ejercito.getReino().equals(nombresReinos[1])&&this.terreno.equals(terreno[1])){
      for(Soldado s: ejercito.iterar().values()){
        s.actualizarVida(1);
      }
    }
    if(ejercito.getReino().equals(nombresReinos[0])&&(this.terreno.equals(terreno[0])||this.terreno.equals(terreno[1])||this.terreno.equals(terreno[2]))){
      for(Soldado s: ejercito.iterar().values()){
        s.actualizarVida(1);
      }
    }
    if(ejercito.getReino().equals(nombresReinos[3])&&this.terreno.equals(terreno[3])){
      for(Soldado s: ejercito.iterar().values()){
        s.actualizarVida(1);
      }
    }
    if(ejercito.getReino().equals(nombresReinos[4])&&this.terreno.equals(terreno[4])){
      for(Soldado s: ejercito.iterar().values()){
        s.actualizarVida(1);
      }
    }
  }
  public void mostrarTableroEjercitos(HashMap<Integer, Ejercito> reino1, HashMap<Integer, Ejercito> reino2){
    System.out.println("\nTablero de ejercitos");
    System.out.print(" ___________________________________________________________\n");
    for(Ejercito[] fila: this.tableroReinos){
      int i=0;
      for(Ejercito columna: fila){
        if(columna==null){
          System.out.print("|_____");
        }else{
          String vida = String.format("%02d", columna.vidaTotalEjercito());
          System.out.print("|"+columna.getNombreEjercito()+"-"+vida);
        }
        i++;
        /*if(i==fila.length){
          System.out.print("\n|     |     |     |     |     |     |     |     |     |     |     |     \n");
        }*/
      }
      System.out.println("|");
    }
  }
  public void mostrarTablero(Soldado[][] tablero){
    System.out.println("\nTablero de soldados");
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
}

