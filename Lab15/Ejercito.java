package LabFP.Lab15;
import java.util.*;

public class Ejercito{
  private String nombreEjercito;
  private HashMap<Integer, Soldado> misSoldados;
  private final int MAX_SOLDADOS=10;
  private int fila, columna;
  private String reino;
  
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
  public HashMap<Integer, Soldado> iterar(){
    return this.misSoldados;
  }
  //Ejercito personalizado
  public Ejercito( String n, String nombreE){
    Random random=new Random();
    setNombreEjercito(nombreE);
    misSoldados=new HashMap<>();
    for(int i=0; i<random.nextInt(MAX_SOLDADOS)+1; i++){
      Soldado soldado=new Soldado(i, n);
      misSoldados.put(i, soldado);
    }
  }
  //Ejercito generico
  public Ejercito(){
    Random random=new Random();
    setNombreEjercito("Ejercito Generico");
    misSoldados=new HashMap<>();
    for(int i=0; i<random.nextInt(MAX_SOLDADOS)+1; i++){
      Soldado soldado=new Soldado();
      misSoldados.put(i, soldado);
    }
  }
  public static void mostrarSoldados(HashMap<Integer, Soldado> soldados){
    System.out.println("\n----------Datos de los soldados------------");
    for(Integer i:soldados.keySet()){
      Soldado s=soldados.get(i);
      System.out.printf("%s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n" 
      , s.getNombre(), s.getNivelVida(), s.getFila(), s.getColumna(), s.getNivelAtaque(), s.getNivelDefensa());}
  }
  public int vidaTotalEjercito(){
    int total=0;
    for(int i=0; i<misSoldados.size(); i++)
      total=total+misSoldados.get(i).getNivelVida();
    return total;
  }
  public void setReino(String reino){
    this.reino=reino;
  }
  public String getReino(){
    return this.reino;
  }
  public static Soldado buscarSoldado(String soldado, HashMap<Integer, Soldado> soldados){
    for(int i=0; i<soldados.size(); i++)
      if(soldados.get(i)!=null){
        if(soldados.get(i).getNombre().equalsIgnoreCase(soldado)){
        return soldados.get(i);
        }
      }
    return null;
  }
  public static void eliminarSoldado(Soldado eliminar, HashMap<Integer, Soldado> soldados){
    for (Map.Entry<Integer, Soldado> entry : soldados.entrySet())
      if (entry.getValue()==eliminar){
        soldados.remove(entry.getKey());
        break;
      }
  }
  public static void agregarSoldado(Soldado[][] tablero, HashMap<Integer, Soldado> soldados, String n, Random random, Soldado s){
    int num=soldados.size()+1;
    Soldado soldado=new Soldado(num, n);
    /*int fila, columna;
    do{
      fila=random.nextInt(10);
      columna=random.nextInt(10);
    }while(tablero[fila][columna]!= null);
    soldado.setFila(fila);
    soldado.setColumna(columna);*/
    soldados.put(num, soldado);
    //tablero[fila][columna]=soldado;

  }
  public static Integer encontrarClave(HashMap<Integer, Soldado> soldados, Soldado soldado){
    for (Map.Entry<Integer, Soldado> entry:soldados.entrySet()) 
      if (entry.getValue().equals(soldado))
        return entry.getKey();
    return null;
  }
  public Soldado soldadoMasFuerte(){
    Soldado masFuerte=misSoldados.get(0);
    for(int i=0; i<misSoldados.size(); i++)
      if(misSoldados.get(i).getNivelVida()>masFuerte.getNivelVida())
        masFuerte=misSoldados.get(i);
    return masFuerte;
  }
  public void mostrarRankingPorSeleccion(){
    Soldado temp=new Soldado();
    for(int i=misSoldados.size(); i>0; i--)
      for(int j=0; j<misSoldados.size()-1; j++)
        if(misSoldados.get(j+1).getNivelVida()>misSoldados.get(j).getNivelVida()){
          temp=misSoldados.get(j);
          misSoldados.put(j, misSoldados.get(j+1));
          misSoldados.put(j+1, temp);
        }
    mostrarSoldados(misSoldados);
  }
  public void modoPersonalizado(String n, Scanner sc){
    mostrarSoldados(misSoldados);
    Random random=new Random();
    boolean continuar=true;
    while(continuar==true){
      System.out.println("Escoja\n1. Crear Soldado\n2. Eliminar Soldado\n3. Modificar Soldado\n4. Ver Soldado\n5. Ver ejercito\n6. Ranking de poder\n7. Soldado con mas ataque\n8. Jugar");
      int opcion=sc.nextInt();
      switch (opcion) {
        case 1:
          if(misSoldados.size()<MAX_SOLDADOS){
            agregarSoldado(null, misSoldados, n, random, null);
            System.out.println("\n*Soldado creado*");
          }else
            System.out.println("Ejercito completo, no es posible crear mas soldados");
          break;
        case 2:
          if(misSoldados.size()>1){
            System.out.print("\nIngrese soldado a eliminar: ");
            String s=sc.next();
            Soldado soldado=buscarSoldado(s, misSoldados);
            //tablero[soldado.getFila()][soldado.getColumna()]=null;
            eliminarSoldado(soldado, misSoldados);
          }else
            System.out.println("No es posible dejar un ejercito vacio");
          break;
        case 3:
          System.out.print("\nIngrese soldado a modificar: ");
          String sMod=sc.next();
          Soldado soldado=buscarSoldado(sMod, misSoldados);
          int keyS=encontrarClave(misSoldados, soldado);
          System.out.println("Indique atributo a modificar:\n1. Nivel Ataque\n2. Nivel Defensa\n3. Nivel Vida");
          boolean check=false;
          while(!check){
            int numAtr=sc.nextInt();
            if(numAtr==1){
              System.out.print("Nuevo valor de ataque: ");
              int atq=sc.nextInt();
              soldado.setAtaque(atq);
              misSoldados.put(keyS, soldado); check=true;
            }else if(numAtr==2){
              System.out.print("Nuevo valor de defensa: ");
              int def=sc.nextInt();
              soldado.setDefensa(def);
              misSoldados.put(keyS, soldado); check=true;
            }else if(numAtr==3){
              System.out.print("Nuevo valor de vida actual: ");
              int vida=sc.nextInt();
              soldado.setVidaActual(vida);
              misSoldados.put(keyS, soldado); check=true;
            }else
              System.out.println("Ingrese una opcion valida");
          }
          break;
        case 4:
          System.out.print("\nIngrese soldado a buscar: ");
          String sb=sc.next();
          Soldado sB=buscarSoldado(sb, misSoldados);
          System.out.printf("*Soldado encontrado*\n%s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n"
          , sB.getNombre(), sB.getNivelVida(), sB.getFila(), sB.getColumna(), sB.getNivelAtaque(), sB.getNivelDefensa());
          break;
        case 5:
          System.out.printf("\n---------Ejercito Nro1(%s)--------\n", n);
          mostrarSoldados(misSoldados);
          break;
        case 6:
          System.out.println("-Soldado con mayor nivel de vida(mas fuerte)-");
          System.out.println("Nombre: "+soldadoMasFuerte().getNombre());
        case 7:
          System.out.print("\nRANKING DE PODER");
          mostrarRankingPorSeleccion();
        case 8:
          continuar=false;
          break;
        default:
          System.out.println("Ingrese una opcion valida");
          break;
      }
    }
  }
}
