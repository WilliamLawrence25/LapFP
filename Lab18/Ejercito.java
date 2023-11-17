package LabFP.Lab18;
import java.util.*;

public class Ejercito{
  private String nombreEjercito;
  private ArrayList<Soldado> misSoldados;
  private final int MAX_SOLDADOS = 10;
  private int fila, columna;
  private String reino;
  private int totalSoldados;
  
  public void setNombreEjercito(String nombre){
    nombreEjercito=nombre;
  }
  public String getNombreEjercito(){
    return nombreEjercito;
  }
  public void setFilaEjercito(int f){
    fila = f;
  }
  public void setColumnaEjercito(int c){
    columna = c;
  }
  public int getFilaEjercito(){
    return fila;
  }
  public int getColumnaEjercito(){
    return columna;
  }
  public ArrayList<Soldado> iterar() {
    return this.misSoldados;
  }
  public int getTotalSoldados() {
    this.totalSoldados = this.misSoldados.size();
    return this.totalSoldados;
  }
  //Ejercito personalizado
  public Ejercito(String n) {
    Random random = new Random();
    setNombreEjercito(n);
    misSoldados=new ArrayList<>();
    for (int i = 0; i < random.nextInt(MAX_SOLDADOS-6)+1; i++) {
      Espadachin espadachin = new Espadachin(n, i);
      misSoldados.add(espadachin);}
    for (int j = 0; j < random.nextInt(MAX_SOLDADOS-7)+1; j++) {
      Arquero arquero=new Arquero(n, j);
      misSoldados.add(arquero);}
    for (int k = 0; k < random.nextInt(MAX_SOLDADOS-7)+1; k++) {
      Caballero caballero = new Caballero(n, k);
      misSoldados.add(caballero);}
  }
  // Ejercito generico
  public Ejercito() {
    Random random = new Random();
    setNombreEjercito("Ejercito Generico");
    misSoldados = new ArrayList<>();
    for (int i = 0; i < random.nextInt(MAX_SOLDADOS - 2) + 3; i++) {
      Soldado soldado = new Soldado();
      misSoldados.add(soldado);
    }
  }
  public void mostrarSoldados() {
    System.out.println("\n----------Datos de los soldados------------");
    for (Soldado s : this.misSoldados) {
      System.out.printf("%-12s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n", s.getNombre(),
          s.getNivelVida(), s.getFila(), s.getColumna(), s.getNivelAtaque(), s.getNivelDefensa());
    }
  }
  public int vidaTotalEjercito(){
    int total=0;
    for (Soldado soldado : misSoldados)
      total+=soldado.getNivelVida();
    return total;
  }
  public int ataqueTotalEjercito(){
    int total=0;
    for (Soldado soldado : misSoldados)
      total+=soldado.getNivelAtaque();
    return total;
  }
  public void setReino(String reino){
    this.reino=reino;
  }
  public String getReino(){
    return this.reino;
  }
  public static Soldado buscarSoldado(String soldado, ArrayList<Soldado> soldados) {
    for (Soldado s : soldados)
      if (s.getNombre().equalsIgnoreCase(soldado)) {
        return s;
      }
    return null;
  }
  public static void eliminarSoldado(Soldado eliminar, ArrayList<Soldado> soldados) {
    soldados.remove(eliminar);
  }
  public static void agregarSoldado(Soldado[][] tablero, ArrayList<Soldado> soldados, String n, Random random, Soldado s){
    int num=soldados.size()+1;
    Soldado soldado=new Soldado(n, num);
    /*int fila, columna;
    do{
      fila=random.nextInt(10);
      columna=random.nextInt(10);
    }while(tablero[fila][columna]!= null);
    soldado.setFila(fila);
    soldado.setColumna(columna);*/
    soldados.add(soldado);
    //tablero[fila][columna]=soldado;
  }
  public static Integer encontrarClave(ArrayList<Soldado> soldados, Soldado soldado) {
    return soldados.indexOf(soldado);
  }
  public Soldado soldadoMasFuerte() {
    Soldado masFuerte = misSoldados.get(0);
    for (Soldado s : misSoldados)
      if (s.getNivelVida() > masFuerte.getNivelVida()) 
        masFuerte = s;
    return masFuerte;
  }
  public void mostrarRankingPorSeleccion(){
    Soldado temp=new Soldado();
    for(int i=misSoldados.size(); i>0; i--)
      for(int j=0; j<misSoldados.size()-1; j++)
        if(misSoldados.get(j+1).getNivelVida()>misSoldados.get(j).getNivelVida()){
          temp=misSoldados.get(j);
          misSoldados.set(j, misSoldados.get(j+1));
          misSoldados.set(j+1, temp);
        }
    this.mostrarSoldados();
  }
}
