package LabFP.Lab23;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;

public class TableroGUI extends JFrame {
  private Soldado[][] tablero;

  public TableroGUI(Soldado[][] tablero) {
    this.tablero = tablero;

    setTitle("Tablero de Soldados");
    setSize(600, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    JPanel tableroPanel = new TableroPanel(this.tablero);
    add(tableroPanel);

    setVisible(true);
  }

  private class TableroPanel extends JPanel {
    private Soldado[][] tablero;

    public TableroPanel(Soldado[][] tablero) {
      this.tablero = tablero;
    }

    @Override
    protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      int celdaSize = getWidth() /10;

      for (int fila = 0; fila < tablero.length; fila++) {
        for (int columna = 0 ; columna < tablero[fila].length; columna++) {
          g.setColor(Color.decode("#008744"));
          
          
          Soldado soldado = tablero[fila][columna];
          if(soldado!=null){
            
            String nombre=soldado.getNombre();

            if((nombre.substring(nombre.length()-2, nombre.length()-1).equals("Z"))){
              g.setColor(Color.decode("#0057e7"));
            }else {
              g.setColor(Color.decode("#d62d20"));
            }
              
          }

          g.fillRect(columna * celdaSize, fila * celdaSize, celdaSize, celdaSize);
          g.setColor(Color.decode("#ffa700"));
          g.drawRect(columna * celdaSize, fila * celdaSize, celdaSize, celdaSize);

          if(soldado!=null){
            String nombre=soldado.getNombre();
            String resultado=String.format("%s-%-4s",nombre.substring(nombre.length()-2, nombre.length()-1),soldado.getNombreCode());

            g.setColor(Color.WHITE);
            FontMetrics fm = g.getFontMetrics();
            int x = columna * celdaSize + (celdaSize - fm.stringWidth(resultado)) / 2;
            int y = fila * celdaSize + (celdaSize - fm.getHeight()) / 2 + fm.getAscent();
            g.drawString(resultado, x, y);
          }
           
        }
      }
    }
  }
}
