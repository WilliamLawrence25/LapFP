package LabFP.Lab12;
import java.util.*;

public class Soldado {
  private String nombre;
  private int nivelAtaque;
  private int nivelDefensa;
  private int nivelVida;
  private int vidaActual;
  private int velocidad;
  private String actitud;
  private boolean vive;
  private int fila;
  private int columna;

  public Soldado(int i, String n){
    Random random=new Random();
    this.nombre="Soldado"+n+i;
    this.nivelAtaque=random.nextInt(5)+1;
    this.nivelDefensa=random.nextInt(5)+1; 
    this.nivelVida=random.nextInt(5)+1;
    this.vidaActual=this.nivelVida;
    this.velocidad=0;
    this.actitud="ofensiva";//Actitud por default
    this.vive=true;
  }
  //Soldado generico
  public Soldado() {
    Random random=new Random();
    this.nombre="Soldado";
    this.nivelAtaque=random.nextInt(5)+1;
    this.nivelDefensa=random.nextInt(5)+1; 
    this.nivelVida=random.nextInt(5)+1;
    this.vidaActual=this.nivelVida;
    this.velocidad=0;
    this.actitud="ofensiva";
    this.vive=true;
  }
  //Soldado personalizado
  public Soldado(String nombre, int nivelAtaque, int nivelDefensa, int nivelVida,
  int vidaActual, int velocidad, String actitud, boolean vive) {
    this.nombre=nombre;
    this.nivelAtaque=nivelAtaque;
    this.nivelDefensa=nivelDefensa;
    this.nivelVida=nivelVida;
    this.vidaActual=vidaActual;
    this.velocidad=velocidad;
    this.actitud=actitud;
    this.vive=vive;
  }
  public void setAtaque(int a){
    nivelAtaque=a;
  }
  public void setDefensa(int d){
    nivelDefensa=d;
  }
  public void setVidaActual(int v){
    vidaActual=v;
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
  public int getNivelAtaque(){
    return nivelAtaque;
  }
  public int getNivelDefensa(){
    return nivelDefensa;
  }
  public int getNivelVida(){
    return nivelVida;
  }
  public int getvidaActual(){
    return vidaActual;
  }
  public int getVelocidad(){
    return velocidad;
  }
  public String getActitud(){
    return actitud;
  }
  public boolean getVive(){
    return vive;
  }
  public int getFila(){
    return fila;
  }
  public int getColumna(){
    return columna;
  }
  public void actualizarVida(int v){
    this.vidaActual+=v;
  }
  public void atacar(Soldado enemigo) {
    enemigo.serAtacado(this.nivelAtaque);
    this.avanzar();
  }
  public void defender() {
    this.actitud="defensiva";
    this.velocidad=0;
  }
  public void avanzar() {
    this.velocidad+=1;
  }
  public void retroceder() {
    if (this.velocidad>0) {
      this.velocidad=0;
      this.actitud="defensiva";
    } else {
      this.velocidad-=1;
    }
  }
  public void serAtacado(int dano) {
    if (this.vidaActual>dano) {
      this.vidaActual-=dano;
    } else {
      this.vidaActual=0;
      this.morir();
    }
  }
  public void huir() {
    this.actitud="fuga";
    this.velocidad+=2;
  }
  public void morir() {
    this.vive=false;
  }
  public void IntercambiarDatos(Soldado s2){
    int tempVida=this.nivelVida;
    int tempAtq=this.nivelAtaque;
    int tempDef=this.nivelDefensa;
    int tempVidaActual=this.vidaActual;
    int tempVelocidad=this.velocidad;
    String tempActitud=this.actitud;
    boolean tempVive=this.vive;

    this.nivelAtaque=s2.nivelAtaque;
    this.nivelDefensa=s2.nivelDefensa;
    this.nivelVida=s2.nivelVida;
    this.vidaActual=s2.vidaActual;
    this.velocidad=s2.velocidad;
    this.actitud=s2.actitud;
    this.vive=s2.vive;

    s2.nivelVida=tempVida;
    s2.nivelAtaque=tempAtq;
    s2.nivelDefensa=tempDef;
    s2.vidaActual=tempVidaActual;
    s2.velocidad=tempVelocidad;
    s2.actitud=tempActitud;
    s2.vive=tempVive;
  }
  public Soldado sumar(Soldado s2) {
    Soldado s=new Soldado();
    s.nivelVida=this.nivelVida+s2.nivelVida;
    s.vidaActual=this.vidaActual+s2.vidaActual;
    s.nivelAtaque=this.nivelAtaque+s2.nivelAtaque;
    s.nivelDefensa=this.nivelDefensa+s2.nivelDefensa;
    s.velocidad=this.velocidad+s2.velocidad;
    return s;
  }
}
