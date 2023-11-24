package LabFP.Lab21;
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
    //setNombreEjercito(n);
    misSoldados=new ArrayList<>();
    for(int i = 0; i < random.nextInt(3)+1; i++) {
      Espadachin espadachin = new Espadachin(n, i);
      misSoldados.add(espadachin);}
    for(int j = 0; j < random.nextInt(3)+1; j++) {
      Arquero arquero=new Arquero(n, j);
      misSoldados.add(arquero);}
    for(int k = 0; k < random.nextInt(2)+1; k++) {
      Caballero caballero = new Caballero(n, k);
      misSoldados.add(caballero);}
    for(int l = 0; l < random.nextInt(2)+1; l++){
      Lancero lanzero=new Lancero(n, l);
      misSoldados.add(lanzero);}
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
    System.out.printf("\n----------Datos de los soldados %s------------\n", getNombreEjercito());
    for (Soldado s : this.misSoldados) {
      String nombre=s.getNombre();
      String code=nombre.substring(nombre.length()-2, nombre.length()-1)+"-"+nombre.substring(0, 1)+s.getvidaActual();
      System.out.printf("%-12s(%-5s) | Vida:%-2d | Fila:%d | Columna:%d | Atq:%-2d | Def:%-2d\n", s.getNombre(), code,
          s.getvidaActual(), s.getFila(), s.getColumna(), s.getNivelAtaque(), s.getNivelDefensa());
    }
  }
  public int vidaTotalEjercito(){
    int total=0;
    for (Soldado soldado : misSoldados)
      total+=soldado.getvidaActual();
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
  public Soldado buscarSoldado(String soldado, ArrayList<Soldado> soldados) {
    for (Soldado s : soldados)
      if (s.getNombreCode().equalsIgnoreCase(soldado)) {
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
        if(misSoldados.get(j+1).getvidaActual()>misSoldados.get(j).getvidaActual()){
          temp=misSoldados.get(j);
          misSoldados.set(j, misSoldados.get(j+1));
          misSoldados.set(j+1, temp);
        }
    this.mostrarSoldados();
  }
  public void resumenEjercito(String reinoJugador1){
    System.out.printf("\nEjercito1: %s\n", reinoJugador1);
    System.out.printf("Cantidad total de soldados creados: %d\n", this.misSoldados.size());
    int espadachines = 0;
    int arqueros = 0;
    int caballeros = 0;
    int lanceros = 0;
    for (Soldado s : this.misSoldados) 
      if (s instanceof Espadachin) {
        espadachines++;
      } else if (s instanceof Arquero) {
        arqueros++;
      } else if (s instanceof Caballero) {
        caballeros++;
      } else if (s instanceof Lancero) {
        lanceros++;
      }
    System.out.printf("Espadachines: %d\n", espadachines);
    System.out.printf("Arqueros: %d\n", arqueros);
    System.out.printf("Caballeros: %d\n", caballeros);
    System.out.printf("Lanceros: %d", lanceros);
    System.out.println();
  }
}
