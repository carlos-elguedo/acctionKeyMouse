/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package acciontecla;




import interfaz.ExplotaGlobos;
import interfaz.PegTopos;
import java.awt.Container;
import java.awt.Image;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;


/**
 *
 * @author USER
 */
public class Manejadora extends Principal{
    public Image imgTab;
/*    JMenuBar Menu = new JMenuBar();
    JMenu m1 = new JMenu("Juego");
    JMenu m2 = new JMenu("Ver");
    JMenu m3 = new JMenu("Configuracion");
    JMenu m4 = new JMenu("God bay");
  */
    public Manejadora(){
        
    }
    //Metodos
    public void Globos(){
        //String nom = JOptionPane.showInputDialog(" NOMBRE del Jugador 1: ");
                        ventanaPrinc.setContentPane((Container)new ExplotaGlobos());
                        ventanaPrinc.setSize(600,550);
                        ventanaPrinc.setLocation(300,100);
                        ventanaPrinc.setVisible(true);
       
    }
    public void Topos(){
        //String nom = JOptionPane.showInputDialog(" NOMBRE del Jugador 1: ");
        
                        ventanaPrinc.setContentPane((Container)new PegTopos());
                        //ventanaPrinc.setJMenuBar(Menu);
                        ventanaPrinc.setSize(800,725);
                        ventanaPrinc.setLocation(200,30);
                        ventanaPrinc.setVisible(true);
       
    }
    public void Configuracion(){
        
    }
    public void Creditos(){
        
    }
    public void Salir(){
        System.exit(0);
    }
    
}
