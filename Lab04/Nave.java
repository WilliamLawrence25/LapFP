public class Nave {
    private String nombre;
    private int fila;
    private char columna;
    private boolean estado;
    private int puntos;

    public void setNombre( String n){
        nombre = n;
    }
    public void setFila(int f){
        fila = f;
    }
    public void setColumna(char c){
        columna = c;
    }
    public void setEstado(boolean e){
        estado = e;
    }
    public void setPuntos(int p){
        puntos = p;
    }
    public String getNombre(){
        return nombre;
    }
    public int getFila(){
        return fila;
    }
    public char getColumna(){
        return columna;
    }
    public boolean getEstado(){
        return estado;
    }
    public int getPuntos(){
        return puntos;
    }
}
