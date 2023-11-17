package LabFP.Lab18;
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

  public Soldado(String ejercito, int i){
    Random random=new Random();
    this.nombre="Soldado"+ejercito+i;
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
  public void setNombre(String n){
    this.nombre=n;
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
  public void setNivelVida(int v){
    this.nivelVida=v;
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
  public void actualizarDefensa(int d){
    this.nivelDefensa+=d;
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
}
class Espadachin extends Soldado{
  private int longitudEspada;

  public Espadachin(String ejercito, int i){
    super(ejercito, i);
    Random random=new Random();
    super.setNombre("Espadachin"+ejercito+i);
    this.longitudEspada=random.nextInt(5)+1;
    super.setNivelVida(random.nextInt(2)+3);
  }
  public void crearMuroEscudo(){
    super.actualizarDefensa(1);
  }
  public int getLongitudEspada(){
    return this.longitudEspada;
  }
}
class Arquero extends Soldado{
  private int flechas;

  public Arquero(String ejercito, int i){
    super(ejercito, i);
    Random random=new Random();
    this.flechas=random.nextInt(5)+1;
    super.setNombre("Arquero"+ejercito+i);
    super.setNivelVida(random.nextInt(3)+1);
  }
  public void dispararFlechas(){
    if(this.flechas>0)
      this.flechas--;
  }
}
class Caballero extends Soldado{
  private boolean montado;
  private String armaActual;

  public Caballero(String ejercito, int i){
    super(ejercito, i);
    Random random=new Random();
    this.montado=false;
    this.armaActual="espada";
    super.setNombre("Caballero"+ejercito+i);
    super.setNivelVida(random.nextInt(3)+3);
  }
  public void alternarArma(){
    if(armaActual.equals("espada"))
      armaActual="lanza";
    else
      armaActual="espada";
  }
  public void desmontar(){
    if(montado)
      montado=false;
      super.defender();
      armaActual="espada";
  }
  public void montar(){
    if(!montado)
      montado=true;
      armaActual="lanza";
      envestir();
  }
  public void envestir(){
    if(montado){
      for(int i=0; i<3; i++)
        super.atacar(null);
    }else
      for(int i=0; i<2; i++)
        super.atacar(null);
  }
}
