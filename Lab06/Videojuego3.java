package LabFP.Lab06;
import java.util.*;

public class Videojuego3 {
  public static void main(String[] args){
    Scanner sc= new Scanner(System.in);
    Random random=new Random();
    ArrayList<ArrayList<Soldado>> tablero=new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      ArrayList<Soldado> fila = new ArrayList<>();
      for (int j = 0; j < 10; j++) {
        fila.add(null);
      }
      tablero.add(fila);
    }
    ArrayList<Soldado> soldados1=crearEjercito(random, tablero, "S");
    ArrayList<Soldado> soldados2=crearEjercito(random, tablero, "X");

    mostrarTablero(tablero);

    System.out.println("\n-----Ejercito Nro1(S)----");
    System.out.println("Soldado con mayor nivel de vida ");
    System.out.println("Nombre: Soldado "+soldadoMasFuerte(soldados1).getNombre());
    System.out.println("Nivel de Vida: " + soldadoMasFuerte(soldados1).getVida());

    System.out.println("Promedio de nivel de vida: "+promedioVida(soldados1));
    mostrarSoldados(soldados1);
    System.out.print("\nRANKING DE PODER (SELECCION)");
    mostrarRankingPorSeleccion(soldados1);
    System.out.print("\nRANKING DE PODER (BURBUJA)");
    mostrarRankingPorBurbuja(soldados1);

    System.out.println("\n\n-----Ejercito Nro2(X)----");
    System.out.println("Soldado con mayor nivel de vida: ");
    System.out.println("Nombre: Soldado "+soldadoMasFuerte(soldados2).getNombre());
    System.out.println("Nivel de Vida: " + soldadoMasFuerte(soldados1).getVida());
    System.out.println("Promedio de nivel de vida: "+promedioVida(soldados2));
    mostrarSoldados(soldados2);
    System.out.print("\nRANKING DE PODER(SELECCION)");
    mostrarRankingPorSeleccion(soldados2);
    System.out.print("\nRANKING DE PODER(BURBUJA)");
    mostrarRankingPorBurbuja(soldados2);

    System.out.println("\n"+ejercitoGanador(soldados1, soldados2)+"\n");
    sc.close();

  }
  public static ArrayList<Soldado> crearEjercito (Random random, 
  ArrayList<ArrayList<Soldado>> tablero, String n){
    ArrayList<Soldado> soldados=new ArrayList<>();
    String nombre;
    int vida, fila, columna;
    for(int i=0; i<random.nextInt(10)+1; i++){
      nombre=n+i;
      vida=random.nextInt(5)+1;
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero.get(fila).get(columna) != null);
      Soldado soldado=new Soldado();
      soldado.setNombre(nombre);
      soldado.setVida(vida);
      soldado.setFila(fila);
      soldado.setColumna(columna);
      soldados.add(soldado);
      tablero.get(fila).set(columna, soldado);
    }
    return soldados;
  }
  public static void mostrarTablero(ArrayList<ArrayList<Soldado>> tablero){
    System.out.print(" _____________________________\n");
    for(ArrayList<Soldado> fila:tablero){
      for(Soldado columna: fila){
        if(columna==null){
          System.out.print("|__");
        }else{
          System.out.print("|" + columna.getNombre() + "");
        }
      }
      System.out.println("|");
    }
  }
  public static Soldado soldadoMasFuerte(ArrayList<Soldado> soldados){
    Soldado masFuerte=soldados.get(0);
    for(int i=0; i<soldados.size(); i++)
      if(soldados.get(i).getVida()>masFuerte.getVida())
        masFuerte=soldados.get(i);
    return masFuerte;
  }
  public static int promedioVida(ArrayList<Soldado> soldados){
    int promedio=0;
    for(int i=0; i<soldados.size(); i++)
      promedio=promedio+soldados.get(i).getVida();
    return promedio/soldados.size();
  }
  public static void mostrarSoldados(ArrayList<Soldado> soldados){
    System.out.println("\n----------Datos de los soldados------------");
    for(Soldado s:soldados)
      System.out.printf("Nombre: %s | Vida: %d | Fila: %d | Columna: %d\n" 
      , s.getNombre(), s.getVida(), s.getFila(), s.getColumna());
  }
  public static void mostrarRankingPorSeleccion(ArrayList<Soldado> soldados){
    Soldado temp=new Soldado();
    for(int i=soldados.size(); i>0; i--)
      for(int j=0; j<soldados.size()-1; j++)
        if(soldados.get(j+1).getVida()>soldados.get(j).getVida()){
          temp=soldados.get(j);
          soldados.set(j, soldados.get(j+1));
          soldados.set(j+1, temp);
        }
    mostrarSoldados(soldados);
  }
  public static void mostrarRankingPorBurbuja(ArrayList<Soldado> soldados){
    for(int i=0; i<soldados.size(); i++){
      int max=i;
      for(int j=i+1; j<soldados.size(); j++)
        if(soldados.get(j).getVida()>soldados.get(max).getVida())
          max=j;
      Soldado temp=soldados.get(i);
      soldados.set(i, soldados.get(max));
      soldados.set(max, temp);
    }
    mostrarSoldados(soldados);
  }
  public static String ejercitoGanador(ArrayList<Soldado> ejercito1, 
  ArrayList<Soldado> ejercito2) {
    if (ejercito1.size() > ejercito2.size()) {
      return "EL EJERCITO 1 GANA";
    } else if (ejercito2.size() > ejercito1.size()) {
      return "EL EJERCITO 2 GANA";
    } else {
      return "EMMPATE";
    }
  }
}
