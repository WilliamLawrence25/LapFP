package LabFP.Lab23;

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
    if(ejercito.getReino().equals(nombresReinos[0])&&this.terreno.equals(terreno[0]))
      for(Soldado s: ejercito.iterar())
        s.actualizarVida(1);
    if(ejercito.getReino().equals(nombresReinos[1])&&this.terreno.equals(terreno[1]))
      for(Soldado s: ejercito.iterar())
        s.actualizarVida(1);
    if(ejercito.getReino().equals(nombresReinos[2])&&(this.terreno.equals(terreno[0])||this.terreno.equals(terreno[1])||this.terreno.equals(terreno[2])))
      for(Soldado s: ejercito.iterar())
        s.actualizarVida(1);
    if(ejercito.getReino().equals(nombresReinos[3])&&this.terreno.equals(terreno[3]))
      for(Soldado s: ejercito.iterar())
        s.actualizarVida(1);
    if(ejercito.getReino().equals(nombresReinos[4])&&this.terreno.equals(terreno[4]))
      for(Soldado s: ejercito.iterar())
        s.actualizarVida(1);
  }
  public void mostrarTableroEjercitos(){
    System.out.println("\nTablero de ejercitos");
    System.out.print(" _____________________________________________________________________\n");
    for(Ejercito[] fila: this.tableroReinos){
      for(Ejercito columna: fila){
        if(columna==null){
          System.out.print("|______");
        }else{
          String vida = String.format("%02d", columna.vidaTotalEjercito());
          System.out.print("|"+columna.getNombreEjercito()+"-"+vida);
        }
      }
      System.out.println("|");
    }
  }
  public void mostrarTableroSoldados(Soldado[][] tablero){
    System.out.println("\nTablero de soldados");
    System.out.print(" _____________________________________________________________________\n");
    for(Soldado[] fila:tablero){
      for(Soldado columna: fila){
        if(columna==null){
          System.out.print("|______");
        }else{
          String nombre=columna.getNombre();
          //System.out.printf("|"+nombre.substring(nombre.length()-2, nombre.length()-1)+"-%-3s",(nombre.substring(0, 1)+columna.getvidaActual()));
          System.out.printf("|%s-%-4s",nombre.substring(nombre.length()-2, nombre.length()-1),columna.getNombreCode());

        }
      }
      System.out.println("|");
    }
  }
}

