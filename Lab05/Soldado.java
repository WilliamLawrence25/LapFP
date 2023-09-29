public class Soldado {
  private String nombre;
  private int vida;
  private int fila;
  private int columna;
  public void setNombre(String n){
    nombre=n;
  }
  public void setVida(int v){
    vida=v;
  }
  public void setFila(int f){
    fila = f;
  }
  public void setColumna(int c){
    columna = c;
  }
  public String getNombre(){
    return nombre;  
  }
  public int getVida(){
    return vida;
  }
  public int getFila(){
    return fila;
  }
  public int getColumna(){
    return columna;
  }

}
