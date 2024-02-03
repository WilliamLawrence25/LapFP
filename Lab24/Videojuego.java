package LabFP.Lab24;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import java.io.*;

public class Videojuego {
  private static final int MAX_SOLDADOS=10;
  private static final String SIMBOL_EJERCITO1="Z";
  private static final String SIMBOL_EJERCITO2="X";
  public static void main(String[] args) throws FileNotFoundException{
    Scanner sc=new Scanner(System.in);
    Random random=new Random();
    boolean jugar=true;

    while(jugar){
      System.out.println("\n----------BIENVENIDO A WARZONE----------");
      
      String[] nombresReinos={"Inglaterra", "Francia", "Sacro Imperio", "Castilla-Aragon", "Moros"};      
      System.out.println("Jugador 1 y 2, escojan un reino(diferentes):\n(1)Inglaterra\n(2)Francia\n(3)Sacro Imperio\n(4)Castilla-Aragon\n(5)Moros");
      
      String reinoJugador1, reinoJugador2;

      try {
        PrintWriter archivo=new PrintWriter(new FileWriter("LabFP\\Lab24\\nombresReinos.txt"), true);
        archivo.println(2-1);
        archivo.println(3-1);
        archivo.close();
      } catch (FileNotFoundException e) {
        System.out.println("Error: "+e.getMessage());
      } catch (IOException e) {
        System.out.println("Error: "+e.getMessage());
      }
      Scanner leer=new Scanner(new FileReader("LabFP\\Lab24\\nombresReinos.txt"));
      int line=leer.nextInt();
      reinoJugador1=nombresReinos[line];
      line=leer.nextInt();
      reinoJugador2=nombresReinos[line];
      leer.close();

      Mapa mapa=new Mapa(random.nextInt(4));

      Soldado[][] tablero=new Soldado[MAX_SOLDADOS][MAX_SOLDADOS];
      ArrayList<Ejercito> reino1=crearReino(mapa, SIMBOL_EJERCITO1, reinoJugador1);
      ArrayList<Ejercito> reino2=crearReino(mapa, SIMBOL_EJERCITO2, reinoJugador2);

      Soldado s1=reino1.get(0).soldadoMasFuerte();
      Soldado s2=reino2.get(0).soldadoMasFuerte();

      preTablero(reino1.get(0), tablero);
      preTablero(reino2.get(0), tablero);

      /*try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("LabFP\\Lab24\\reinos.dat"))) {
        oos.writeObject(reino1.get(0));
        oos.writeObject(reino2.get(0));
      } catch (IOException e) {
        System.out.println("Error al escribir en el archivo binario: " + e.getMessage());
      }
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("LabFP\\Lab24\\reino1.dat"))) {
        Ejercito ejercitoLeido1 = (Ejercito) ois.readObject(); 
        Ejercito ejercitoLeido2 = (Ejercito) ois.readObject(); 
        // Puedes hacer algo con el ejército leído, como mostrar información
        ejercitoLeido1.mostrarSoldados();
        ejercitoLeido2.mostrarSoldados();
        System.out.println("Ejército leído desde el archivo binario:");
      } catch (IOException | ClassNotFoundException e) {
        System.out.println("Error al leer desde el archivo binario: " + e.getMessage());
      }*/
    
      mapa.mostrarTableroSoldados(tablero);
      reino1.get(0).mostrarSoldados();
      reino2.get(0).mostrarSoldados();
      SwingUtilities.invokeLater(() -> {
        new TableroGUI(tablero);
      });

      System.out.println("\n-----Ejercito Nro1(Z)----");
      System.out.println("Soldado con mayor nivel de vida: ");
      System.out.printf("%-12s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n", s1.getNombre(),s1.getNivelVida(), s1.getFila(), s1.getColumna(), s1.getNivelAtaque(), s1.getNivelDefensa());
      System.out.printf("Promedio de nivel de vida: %.2f", promedioVida(reino1.get(0)));
      reino1.get(0).mostrarSoldados();
      System.out.print("\nRANKING DE PODER");
      reino1.get(0).mostrarRankingPorSeleccion();
      
      System.out.println("\n-----Ejercito Nro2(X)----");
      System.out.println("Soldado con mayor nivel de vida: ");
      System.out.printf("%-12s | Vida:%d | Fila:%d | Columna:%d | Atq:%d | Def:%d\n", s2.getNombre(),s2.getNivelVida(), s2.getFila(), s2.getColumna(), s2.getNivelAtaque(), s2.getNivelDefensa());
      System.out.printf("Promedio de nivel de vida: %.2f", promedioVida(reino2.get(0)));
      reino2.get(0).mostrarSoldados();
      System.out.print("\nRANKING DE PODER");
      reino2.get(0).mostrarRankingPorSeleccion();

      reino1.get(0).resumenEjercito(reinoJugador1);
      reino2.get(0).resumenEjercito(reinoJugador2);
      determinarGanador(reino1.get(0), reino2.get(0), reinoJugador1, reinoJugador2);
      

      //JuegoDAO.insertarDatos("Inglaterra", 1, 9);

      // Crear instancias de la clase para simular múltiples procesos o métodos
      ArchivoLog archivoLog = new ArchivoLog();

      // Iniciar hilos simulando ejecución de procesos
      Thread thread1 = new Thread(() -> archivoLog.ejecutarQuerySQL());
      Thread thread2 = new Thread(() -> archivoLog.muerteDeSoldado());
      Thread thread3 = new Thread(() -> archivoLog.batallaTerminada());

      thread1.start();
      try {
          thread1.join(); // Espera a que thread1 termine antes de iniciar thread2
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      thread2.start();
      try {
          thread2.join(); // Espera a que thread2 termine antes de iniciar thread3
      } catch (InterruptedException e) {
          e.printStackTrace();
      }

      thread3.start();
      try {
        thread3.join(); // Espera a que thread3 termine 
      } catch (InterruptedException e) {
          e.printStackTrace();
      }


      System.out.println("Quieres seguir jugando?(true/false) ");
      boolean seguirJugando=sc.nextBoolean();
      if(seguirJugando==false)
        jugar=false;
    }
    sc.close();
  }
  public static ArrayList<Ejercito> crearReino(Mapa mapa, String n, String reinoN){
    Random random=new Random();
    ArrayList<Ejercito> reino=new ArrayList<>();
    int fila, columna;
    for(int i=0; i<1; i++ ){
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(mapa.tablero()[fila][columna]!= null);
      reino.add(i, new Ejercito(n));
      reino.get(i).setFilaEjercito(fila);
      reino.get(i).setColumnaEjercito(columna);
      reino.get(i).setNombreEjercito(n+i);  
      reino.get(i).setReino(reinoN);
      reino.get(i).unidadesEspeciales(n);
      mapa.agregarEjercito(fila, columna, reino.get(i), reino.get(i).vidaTotalEjercito());
    }
    return reino;
  }
  public static void preTablero(Ejercito ejercito, Soldado[][] tablero){
    Random random=new Random();
    int fila, columna;
    for(Soldado s: ejercito.iterar()){
      do{
        fila=random.nextInt(10);
        columna=random.nextInt(10);
      }while(tablero[fila][columna]!= null);
      s.setFila(fila);
      s.setColumna(columna);
      tablero[fila][columna]=s;
    }
  }
  public static double promedioVida(Ejercito ejercito){
    double promedio=0;
    for(Soldado soldado: ejercito.iterar())
      promedio+=soldado.getNivelVida();
    return promedio/(double)ejercito.getTotalSoldados();
  }
  private static void determinarGanador(Ejercito ejercito1, Ejercito ejercito2, String reinoJugador1, String reinoJugador2) {
    Random random=new Random();
    System.out.println("\n---Metrica: Ejercito con mas soldados---");
    double vida1=ejercito1.vidaTotalEjercito();
    double vida2=ejercito2.vidaTotalEjercito();
    System.out.printf("Ejercito 1: %-10s: %d   %.2f%% de probabilidad de victoria\n", reinoJugador1, ejercito1.vidaTotalEjercito(), (100/(vida1+vida2)*vida1));
    System.out.printf("Ejercito 2: %-10s: %d   %.2f%% de probabilidad de victoria\n", reinoJugador2, ejercito2.vidaTotalEjercito(), (100/(vida1+vida2)*vida2));
    double numGanador=random.nextDouble(100)+1;
    String ganador="";
    if(numGanador<=(100/(vida1+vida2)*vida1)){
      ganador="Ejercito 1 de: "+reinoJugador1;      
    }else if(numGanador>(100/(vida1+vida2)*vida2)){
      ganador="Ejercito 2 de: "+reinoJugador2;
    }
    System.out.printf("\nEl ganador es el %s. Ya que al generar los\nporcentajes de probabilidad de victoria basada en los niveles de\nvida de sus soldados y aplicando un experimento aleatorio salió\nvencedor. (Aleatorio generado: %.2f%%)\n", ganador, numGanador);
  }
}


class ConexionBD {
    private static final String URL = "jdbc:mysql://localhost:3306/GameData";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "root";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }
}

class JuegoDAO {
  private static final String INSERTAR_DATOS = "INSERT INTO gameplayStatus (ganador, soldados, soldadosEnemigos) VALUES (?, ?)";

  public static void insertarDatos(String valorCampo1, int valorCampo2, int valorCampo3) {
      try (Connection conexion = ConexionBD.obtenerConexion();
          PreparedStatement statement = conexion.prepareStatement(INSERTAR_DATOS)) {
          statement.setString(1, valorCampo1);
          statement.setInt(2, valorCampo2);
          statement.setInt(3, valorCampo3);
          statement.executeUpdate();
          System.out.println("Datos insertados correctamente");
      } catch (SQLException e) {
          e.printStackTrace();
      }
  }
}

class ArchivoLog {

  private static final String ARCHIVO_LOG = "LabFP\\Lab24\\my_log_game.log";

  // Método sincronizado para simular ejecución de querys SQL
  public synchronized void ejecutarQuerySQL() {
      escribirEnArchivo("Ejecucion de consulta SQL");
  }

  // Método sincronizado para simular muerte de soldado
  public synchronized void muerteDeSoldado() {
      escribirEnArchivo("Muerte de soldado");
  }

  // Método sincronizado para simular batalla terminada
  public synchronized void batallaTerminada() {
      escribirEnArchivo("Batalla terminada");
  }

  // Método para escribir en el archivo de ArchivoLog
  public void escribirEnArchivo(String mensaje) {
      try (FileWriter writer = new FileWriter(ARCHIVO_LOG, true)) {
          writer.write(mensaje + "\n\n");
          System.out.println("Escrito en el archivo: " + mensaje);
      } catch (IOException e) {
          e.printStackTrace();
      }
  }
}