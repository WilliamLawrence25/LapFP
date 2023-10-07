import java.util.*;
public class VideoJuego2 {
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    Random random=new Random();
    Soldado[] soldados=new Soldado[random.nextInt(10)+1];
    Soldado[][] tablero=new Soldado[10][10];
    String nombre;
    int vida, fila, columna;
    for(int i=0; i<soldados.length; i++){
      nombre="S"+(i);
      vida=random.nextInt(5)+1;
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero[fila][columna]!=null);
      soldados[i]=new Soldado();
      soldados[i].setNombre(nombre);
      soldados[i].setVida(vida);
      soldados[i].setFila(fila);
      soldados[i].setColumna(columna);
      tablero[fila][columna]=soldados[i];
    }
    System.out.println("\n============TABLERO============");
    mostrarTablero(tablero);

    System.out.println("\nSoldado con mayor nivel de vida:");
    System.out.println("Nombre: " + soldadoMasFuerte(soldados).getNombre());
    System.out.println("Nivel de Vida: " + soldadoMasFuerte(soldados).getVida());
    
    System.out.println("\nPromedio de nivel de vida: "+promedioVida(soldados));
    System.out.println("Nivel de vida total del Ejercito: "+vidaEjercito(soldados));

    mostrarSoldados(soldados);
    System.out.print("\nRANKING DE PODER (SELECCION)");
    mostrarRankingPorSeleccion(soldados);
    System.out.print("\nRANKING DE PODER (BURBUJA)");
    mostrarRankingPorBurbuja(soldados);
    sc.close();
  }
  public static void mostrarTablero(Soldado[][] tablero){
    System.out.print(" _____________________________\n");
    for (int i = 0; i < tablero.length; i++) {
      for (int j = 0; j < tablero[i].length; j++) {
          if (tablero[i][j] == null) {
            System.out.print("|__");
          } else {
              System.out.print("|" + tablero[i][j].getNombre() + "");
          }
      }
      System.out.println("|");
    }
  }
  public static Soldado soldadoMasFuerte(Soldado[] soldados){
    Soldado masFuerte=soldados[0];
    for(int i=0; i<soldados.length; i++)
      if(soldados[i].getVida()>masFuerte.getVida())
        masFuerte=soldados[i];
    return masFuerte;
  }
  public static int promedioVida(Soldado[] soldados){
    int promedio=0;
    for(int i=0; i<soldados.length; i++)
      promedio=promedio+soldados[i].getVida();
    return promedio/soldados.length;
  }
  public static int vidaEjercito(Soldado[] soldados){
    int vidaTotal=0;
    for(int i=0; i<soldados.length; i++)
      vidaTotal=vidaTotal+soldados[i].getVida();
    return vidaTotal;
  }
  public static void mostrarSoldados(Soldado[] soldados){
    System.out.println("\n----------Datos de los soldados------------");
    for(Soldado s:soldados)
      System.out.printf("Nombre: %s | Vida: %d | Fila: %d | Columna: %d\n" 
      , s.getNombre(), s.getVida(), s.getFila(), s.getColumna());
  }
  public static void mostrarRankingPorSeleccion(Soldado[] soldados){
    Soldado temp=new Soldado();
    for(int i=soldados.length; i>0; i--)
      for(int j=0; j<soldados.length-1; j++)
        if(soldados[j+1].getVida()>soldados[j].getVida()){
          temp=soldados[j];
          soldados[j]=soldados[j+1];
          soldados[j+1]=temp;
        }
    mostrarSoldados(soldados);
  }
  public static void mostrarRankingPorBurbuja(Soldado[] soldados){
    for(int i=0; i<soldados.length; i++){
      int max=i;
      for(int j=i+1; j<soldados.length; j++)
        if(soldados[j].getVida()>soldados[max].getVida())
          max=j;
      Soldado temp=soldados[i];
      soldados[i]=soldados[max];
      soldados[max]=temp;
    }
    mostrarSoldados(soldados);
  }
}
