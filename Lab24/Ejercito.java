package LabFP.Lab24;
import java.io.Serializable;
import java.util.*;

public class Ejercito implements Serializable{
  private String nombreEjercito;
  private ArrayList<Soldado> misSoldados;
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
  public Ejercito(String n) {
    Random random = new Random();
    misSoldados=new ArrayList<>();
    for(int i = 0; i < random.nextInt(3); i++) {
      Espadachin espadachin = new Espadachin(n, i);
      misSoldados.add(espadachin);}
    for(int j = 0; j < random.nextInt(3); j++) {
      Arquero arquero=new Arquero(n, j);
      misSoldados.add(arquero);}
    for(int k = 0; k < random.nextInt(3); k++) {
      Caballero caballero = new Caballero(n, k);
      misSoldados.add(caballero);}
    for(int l = 0; l < random.nextInt(3); l++){
      Lancero lanzero=new Lancero(n, l);
      misSoldados.add(lanzero);}
  }
  public void unidadesEspeciales(String n){
    if(this.reino.equalsIgnoreCase("Inglaterra")){
      EspadachinReal espadachinReal=new EspadachinReal(n, 0);
      misSoldados.add(espadachinReal);
    }else if(this.reino.equalsIgnoreCase("Francia")){
      CaballeroFranco caballeroFranco=new CaballeroFranco(n, 0);
      misSoldados.add(caballeroFranco);
    }else if(this.reino.equalsIgnoreCase("Sacro Imperio")){
      EspadachinTeutonico EspadachinTeutonico=new EspadachinTeutonico(n, 0);
      misSoldados.add(EspadachinTeutonico);
    }else if(this.reino.equalsIgnoreCase("Castilla-Aragon")){
      EspadachinConquistador EspadachinConquistador=new EspadachinConquistador(n, 0);
      misSoldados.add(EspadachinConquistador);
    }else if(this.reino.equalsIgnoreCase("Moros")){
      CaballeroMoro CaballeroMoro=new CaballeroMoro(n, 0);
      misSoldados.add(CaballeroMoro);
    }
  }
  public void mostrarSoldados() {
    System.out.printf("\n----------Datos de los soldados %s------------\n", getNombreEjercito());
    for (Soldado s : this.misSoldados) {
      String nombre=s.getNombre();
      //String code=nombre.substring(nombre.length()-2, nombre.length()-1)+"-"+nombre.substring(0, 1)+s.getvidaActual();
      String code=nombre.substring(nombre.length()-2, nombre.length()-1)+"-"+s.getNombreCode();
      System.out.printf("%-21s(%-6s) | Vida:%-2d | Fila:%d | Columna:%d | Atq:%-2d | Def:%-2d\n", s.getNombre(), code,
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
    Soldado temp;
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
    /*int espadachinReal=0;
    int espadachinConquistador=0;
    int espadachinTeutonico=0;*/
    for (Soldado s : this.misSoldados) 
      if (s instanceof Espadachin) {
        espadachines++;
      } else if (s instanceof Arquero) {
        arqueros++;
      } else if (s instanceof Caballero) {
        caballeros++;
      } else if (s instanceof Lancero) {
        lanceros++;
      }/*else if (s instanceof EspadachinReal) {
        espadachinReal++;
      } else if (s instanceof EspadachinConquistador) {
        espadachinConquistador++;
      } else if (s instanceof EspadachinTeutonico) {
        espadachinTeutonico++;
      }*/
    System.out.printf("Espadachines: %d\n", espadachines);
    System.out.printf("Arqueros: %d\n", arqueros);
    System.out.printf("Caballeros: %d\n", caballeros);
    System.out.printf("Lanceros: %d", lanceros);
    System.out.println();
  }
}
