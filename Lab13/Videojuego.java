package LabFP.Lab13;
import java.util.*;

public class Videojuego {
  private static final int MAX_SOLDADOS=10;
  private static final String SIMBOL_EJERCITO1="Z";
  private static final String SIMBOL_EJERCITO2="X";
  private static final int OPCION_RAPIDO = 1;
  private static final int OPCION_PERSONALIZADO = 2;
  private static final int OPCION_SALIR = 3;
  public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    Random random=new Random();
    boolean jugar=true;
    while(jugar){
      System.out.println("\n----------BIENVENIDO A BATTLEFIELD'S GAME----------\n\nMenu:\n1. Juego rapido\n2. Juego personalizado\n3. Salir");
      int opcion=sc.nextInt();
      switch (opcion) {
        case OPCION_RAPIDO:
          do{
            Soldado[][] tablero=new Soldado[MAX_SOLDADOS][MAX_SOLDADOS];
            HashMap<Integer, Soldado> soldados1=crearEjercito(random, tablero, SIMBOL_EJERCITO1);
            HashMap<Integer, Soldado> soldados2=crearEjercito(random, tablero, SIMBOL_EJERCITO2);

            infoDeLosSoldados(tablero, soldados1, soldados2, random, sc);

            comenzarJuego(tablero, soldados1, soldados2, random, sc);

          }while(seguirJugando(sc));
          break;
        case OPCION_PERSONALIZADO:
          do{
            Soldado[][] tablero=new Soldado[10][10];
            HashMap<Integer, Soldado> soldados1=crearEjercito(random, tablero, SIMBOL_EJERCITO1);
            HashMap<Integer, Soldado> soldados2=crearEjercito(random, tablero, SIMBOL_EJERCITO2);

            infoDeLosSoldados(tablero, soldados1, soldados2, random, sc);

            System.out.println("\nEscoja un ejercito (1/2) a gestionar: ");
            int gestionar=sc.nextInt();
            if(gestionar==1)
            modoPersonalizado(SIMBOL_EJERCITO1, sc, random, soldados1, tablero);
            else if(gestionar==2)
            modoPersonalizado(SIMBOL_EJERCITO2, sc, random, soldados2, tablero);

            comenzarJuego(tablero, soldados1, soldados2, random, sc);
            
          }while(seguirJugando(sc));
          break;
        case OPCION_SALIR:
         jugar=false;
         break;
        default:
          System.out.println("Opcion invalida, intente nuevamente");
          break;
      }
    }
    sc.close();
  }
  public static HashMap<Integer, Soldado> crearEjercito (Random random, Soldado[][] tablero, String n){
    HashMap<Integer, Soldado> soldados=new HashMap<>();
    int fila, columna;
    for(int i=0; i<random.nextInt(MAX_SOLDADOS)+1; i++){
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero[fila][columna]!= null);
      Soldado soldado=new Soldado(i, n);
      soldado.setFila(fila);
      soldado.setColumna(columna);
      soldados.put(i, soldado);
      tablero[fila][columna]=soldados.get(i);
    }
    return soldados;
  }
  public static void mostrarTablero(Soldado[][] tablero){
    System.out.print(" _____________________________\n");
    for(Soldado[] fila:tablero){
      for(Soldado columna: fila){
        if(columna==null){
          System.out.print("|__");
        }else{
          System.out.print("|"+columna.getNombre().substring(7, 9)+"");
        }
      }
      System.out.println("|");
    }
  }
  public static void infoDeLosSoldados(Soldado[][] tablero, HashMap<Integer, Soldado> soldados1
  , HashMap<Integer, Soldado> soldados2, Random random, Scanner sc){
    mostrarTablero(tablero);
    
    System.out.printf("\n---------Ejercito Nro1(%s)--------\n", SIMBOL_EJERCITO1);
    System.out.println("-Soldado con mayor nivel de vida-");
    System.out.println("Nombre: "+soldadoMasFuerte(soldados1).getNombre());
    System.out.println("Nvl Vida: " + soldadoMasFuerte(soldados1).getNivelVida());
    System.out.println("Promedio de vida: "+promedioVida(soldados1));
    mostrarSoldados(soldados1);
    System.out.print("\nRANKING DE PODER (SELECCION)");
    mostrarRankingPorSeleccion(soldados1);

    System.out.printf("\n\n---------Ejercito Nro2(%s)--------\n", SIMBOL_EJERCITO2);
    System.out.println("-Soldado con mayor nivel de vida-");
    System.out.println("Nombre: "+soldadoMasFuerte(soldados2).getNombre());
    System.out.println("Nvl Vida: " + soldadoMasFuerte(soldados1).getNivelVida());
    System.out.println("Promedio de vida: "+promedioVida(soldados2));
    mostrarSoldados(soldados2);
    System.out.print("\nRANKING DE PODER(SELECCION)");
    mostrarRankingPorSeleccion(soldados2);
  }
  public static void comenzarJuego(Soldado[][] tablero
  , HashMap<Integer, Soldado> soldados1, HashMap<Integer, Soldado> soldados2, Random random, Scanner sc){
    System.out.printf("\nControles\n(1)Atacar\n(2)Defender\n(3)Huir\n(4)Retroceder\n");
    System.out.println("\nEscriba 'salir' para cancelar del juego");
    outerLoop:
    while(soldados1.size()!=0&&soldados2.size()!=0){
      Soldado mySoldado1=null;
      while(mySoldado1==null){
        System.out.print("\nJugador 1, seleccione soldado: ");
        String s1=sc.next();
        if(s1.equalsIgnoreCase("salir")){break outerLoop;}
        mySoldado1=buscarSoldado(s1, soldados1);
        if(mySoldado1==null)
          System.out.println("Soldado no encontrado");
      }
      System.out.print("Escoja una accion: ");
      int control1=sc.nextInt();
      realizarAccion(mySoldado1, sc, control1, soldados1, soldados2, tablero);
      soldadosRestantes(soldados1, soldados2);
      mostrarSoldados(soldados1);
      mostrarSoldados(soldados2);

      if(soldados2.size()>0 && soldados1.size()>0){
        Soldado mySoldado2=null;
        while(mySoldado2==null){
          System.out.print("\nJugador 2, seleccione soldado: ");
          String s2=sc.next();
          if(s2.equalsIgnoreCase("salir")){break outerLoop;}
          mySoldado2=buscarSoldado(s2, soldados2);
          if(mySoldado2==null)
            System.out.println("Soldado no encontrado");
        }
        System.out.print("Escoja una accion: ");
        int control2=sc.nextInt();
        realizarAccion(mySoldado2, sc, control2, soldados2, soldados1, tablero);
        soldadosRestantes(soldados1, soldados2);
        mostrarSoldados(soldados1);
        mostrarSoldados(soldados2);
      }
      if(soldados2.size()==0){
        System.out.println("\nJugador 1 GANA!!!\n");
      }else if(soldados1.size()==0){
        System.out.println("\nJugador 2 GANA!!!\n");
      }
    }
  }
  public static Soldado soldadoMasFuerte(HashMap<Integer, Soldado> soldados){
    Soldado masFuerte=soldados.get(0);
    for(int i=0; i<soldados.size(); i++)
      if(soldados.get(i).getNivelVida()>masFuerte.getNivelVida())
        masFuerte=soldados.get(i);
    return masFuerte;
  }
  public static int promedioVida(HashMap<Integer, Soldado> soldados){
    int promedio=0;
    for(int i=0; i<soldados.size(); i++)
      promedio=promedio+soldados.get(i).getNivelVida();
    return promedio/soldados.size();
  }
  public static void mostrarSoldados(HashMap<Integer, Soldado> soldados){
    System.out.println("\n----------Datos de los soldados------------");
    for(Integer i:soldados.keySet()){
      Soldado s=soldados.get(i);
      System.out.printf("%s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n" 
      , s.getNombre(), s.getNivelVida(), s.getFila(), s.getColumna(), s.getNivelAtaque(), s.getNivelDefensa());}
  }
  public static void mostrarRankingPorSeleccion(HashMap<Integer, Soldado> soldados){
    Soldado temp=new Soldado();
    for(int i=soldados.size(); i>0; i--)
      for(int j=0; j<soldados.size()-1; j++)
        if(soldados.get(j+1).getNivelVida()>soldados.get(j).getNivelVida()){
          temp=soldados.get(j);
          soldados.put(j, soldados.get(j+1));
          soldados.put(j+1, temp);
        }
    mostrarSoldados(soldados);
  }
  //Este no se imprime para no alargar la ejecucion
  public static void mostrarRankingPorBurbuja(HashMap<Integer, Soldado> soldados){
    for(int i=0; i<soldados.size(); i++){
      int max=i;
      for(int j=i+1; j<soldados.size(); j++)
        if(soldados.get(j).getNivelVida()>soldados.get(max).getNivelVida())
          max=j;
      Soldado temp=soldados.get(i);
      soldados.put(i, soldados.get(max));
      soldados.put(max, temp);
    }
    mostrarSoldados(soldados);
  }
  public static void soldadosRestantes(HashMap<Integer, Soldado> soldados1, HashMap<Integer, Soldado> soldados2){
    int s1=0;
    int s2=0;
    for(Soldado soldado:soldados1.values())
      if(soldado!=null)
        s1++;
    for(Soldado soldado:soldados2.values())
      if(soldado!=null)
        s2++;
    System.out.println("\nEjercito 1 - Soldados restantes: "+s1);
    System.out.println("Ejercito 2 - Soldados restantes: "+s2);
  }
  public static boolean seguirJugando(Scanner sc){
    System.out.println("\nSeleccione\n1. Comenzar nuevo juego\n2. Menu principal");
    String juego=sc.next();
    if(juego.equalsIgnoreCase("1")){
      return true;
    }else{return false;}
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
  public static Integer encontrarClave(HashMap<Integer, Soldado> soldados, Soldado soldado){
    for (Map.Entry<Integer, Soldado> entry:soldados.entrySet()) 
      if (entry.getValue().equals(soldado))
        return entry.getKey();
    return null;
  }
  public static void realizarAccion(Soldado soldado, Scanner sc, int control, 
  HashMap<Integer, Soldado> soldados, HashMap<Integer, Soldado> soldadosE, Soldado[][] tablero){
    Random random=new Random();
    int fila=tablero.length;
    int columna=tablero[0].length;
    if(control==1){
      System.out.print("Escriba coordenadas(fila, columna): ");
      int fila0=sc.nextInt();
      int columna0=sc.nextInt();
      
      if(fila0>=0 && fila0<fila && columna0>=0 && columna0<columna){
        if(tablero[fila0][columna0]!=null && !soldados.containsValue(tablero[fila0][columna0])){
          System.out.println("*Enemigo encontrado*");
          Soldado enemigo=tablero[fila0][columna0];
          
          double vida1=soldado.getvidaActual();
          double vida2=enemigo.getvidaActual();
          int ganador=(int)random.nextDouble(vida1+vida2)+1;
          System.out.printf("Probabilidades de vencer: %s: %.2f | %s: %.2f\n", soldado.getNombre(),
           (100/(vida1+vida2)*vida1), enemigo.getNombre(), (100/(vida1+vida2)*vida2));
          if(ganador<=soldado.getvidaActual()){
            System.out.println("*Enemigo derrotado*");
            tablero[fila0][columna0]=soldado;
            tablero[soldado.getFila()][soldado.getColumna()]=null;
            soldado.setFila(fila0);
            soldado.setColumna(columna0);
            soldado.actualizarVida(1);
            eliminarSoldado(enemigo, soldadosE);
          }else if(ganador>soldado.getvidaActual()){
            System.out.println("*Aliado derrotado*");
            tablero[soldado.getFila()][soldado.getColumna()]=null;
            eliminarSoldado(soldado, soldados);
          }
          mostrarTablero(tablero);
        }else
          System.out.println("No se encontro soldados\n");
      }else
        System.out.println("Coordenadas fuera del limite!!!");
    }
  }
  public static void agregarSoldado(int modo, Soldado[][] tablero, HashMap<Integer, Soldado> soldados, String n, Random random, Soldado s){
    if(modo==1){
      int num=soldados.size()+1;
      Soldado soldado=new Soldado(num, n);
      int fila, columna;
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero[fila][columna]!= null);
      soldado.setFila(fila);
      soldado.setColumna(columna);
      soldados.put(num, soldado);
      tablero[fila][columna]=soldado;
    }else{
      int fila, columna;
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero[fila][columna]!= null);
      s.setFila(fila);
      s.setColumna(columna);
      soldados.put(soldados.size()+1, s);
      tablero[fila][columna]=s;
    }
  }
  public static void modoPersonalizado(String n, Scanner sc, Random random, HashMap<Integer, Soldado> soldados, Soldado[][] tablero){
    boolean continuar=true;
    while(continuar==true){
      System.out.println("Escoja\n1. Crear Soldado\n2. Eliminar Soldado\n3. Clonar Soldado\n4. Modificar Soldado\n5. Comparar Soldado\n6. Intercambiar Soldado\n7. Ver Soldado\n8. Ver ejercito\n9. Sumar niveles\n10. Jugar\n11. Volver al menu");
      int opcion=sc.nextInt();
        switch (opcion) {
          case 1:
            if(soldados.size()<MAX_SOLDADOS){
              agregarSoldado(1, tablero, soldados, n, random, null);
              System.out.println("\n*Soldado creado*");
            }else
              System.out.println("Ejercito completo, no es posible crear mas soldados");
            break;
          case 2:
            if(soldados.size()>1){
              System.out.print("\nIngrese soldado a eliminar: ");
              String s=sc.next();
              Soldado soldado=buscarSoldado(s, soldados);
              tablero[soldado.getFila()][soldado.getColumna()]=null;
              eliminarSoldado(soldado, soldados);
            }else
              System.out.println("No es posible dejar un ejercito vacio");
            break;
          case 3:
            if(soldados.size()<MAX_SOLDADOS){
              System.out.print("\nIngrese soldado a clonar: ");
              String s=sc.next();
              Soldado soldado=buscarSoldado(s, soldados);
              agregarSoldado(2, tablero, soldados, n, random, soldado);
              System.out.println("*Soldado clonado*");
            }else
              System.out.println("Ejercito completo, no es posible crear mas soldados");
            break;
          case 4:
            System.out.print("\nIngrese soldado a modificar: ");
            String sMod=sc.next();
            Soldado soldado=buscarSoldado(sMod, soldados);
            int keyS=encontrarClave(soldados, soldado);
            System.out.println("Indique atributo a modificar:\n1. Nivel Ataque\n2. Nivel Defensa\n3. Nivel Vida");
            boolean check=false;
            while(!check){
              int numAtr=sc.nextInt();
              if(numAtr==1){
                System.out.print("Nuevo valor de ataque: ");
                int atq=sc.nextInt();
                soldado.setAtaque(atq);
                soldados.put(keyS, soldado); check=true;
              }else if(numAtr==2){
                System.out.print("Nuevo valor de defensa: ");
                int def=sc.nextInt();
                soldado.setDefensa(def);
                soldados.put(keyS, soldado); check=true;
              }else if(numAtr==3){
                System.out.print("Nuevo valor de vida actual: ");
                int vida=sc.nextInt();
                soldado.setVidaActual(vida);
                soldados.put(keyS, soldado); check=true;
              }else
                System.out.println("Ingrese una opcion valida");
            }
            break;
          case 5:
              if(soldados.size()>1){
                System.out.print("\nIngrese soldados a comparar\nSoldado 1: ");
                String s1c=sc.next();
                System.out.print("Soldado 2: ");
                String s2c=sc.next();
                Soldado s1C=buscarSoldado(s1c, soldados);
                Soldado s2C=buscarSoldado(s2c, soldados);
                System.out.println("- Atributos iguales");
                if(s1C.getNivelAtaque()==s2C.getNivelAtaque())
                  System.out.println("Ataque: "+s1C.getNivelAtaque());
                if(s1C.getNivelDefensa()==s2C.getNivelDefensa())
                  System.out.println("Defensa: "+s1C.getNivelDefensa());
                if(s1C.getvidaActual()==s2C.getvidaActual())
                  System.out.println("Vida Actual: "+s1C.getvidaActual());
              }else
                System.out.println("Debe haber al menos dos soldados");
            break;
          case 6:
            System.out.print("\nIngrese soldados a intercambiar\nSoldado 1: ");
            String s1i=sc.next();
            System.out.println("Soldado 2: ");
            String s2i=sc.next();
            Soldado s1I=buscarSoldado(s1i, soldados);
            Soldado s2I=buscarSoldado(s2i, soldados);
            soldados.get(encontrarClave(soldados, s1I)).intercambiarDatos(soldados.get(encontrarClave(soldados, s2I)));
            System.out.println("*Intercambio realizado*");
            break;
          case 7:
            System.out.print("\nIngrese soldado a buscar: ");
            String sb=sc.next();
            Soldado sB=buscarSoldado(sb, soldados);
            System.out.printf("*Soldado encontrado*\n%s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n"
            , sB.getNombre(), sB.getNivelVida(), sB.getFila(), sB.getColumna(), sB.getNivelAtaque(), sB.getNivelDefensa());
            break;
          case 8:
            System.out.printf("\n---------Ejercito Nro1(%s)--------\n", n);
            mostrarSoldados(soldados);
            break;
          case 9:
            System.out.println("Sumatoria de niveles");
            Soldado sumatoria=new Soldado("Suma", 0, 0, 0
            , 0, 0, null, true);
            for(Soldado sTemp:soldados.values())
              sumatoria=sumatoria.sumar(sTemp);
            System.out.printf("Vida:%d | Atq:%d | Def:%d | Vel:%d\n"
            , sumatoria.getvidaActual(), sumatoria.getNivelAtaque(), sumatoria.getNivelDefensa(), sumatoria.getVelocidad());
            break;
          case 10:
            continuar=false;
            break;
          case 11:
            continuar=false;
            break;
          default:
            System.out.println("Ingrese una opcion valida");
            break;
        }
    }
  }
}