/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciontecla;

import interfaz.Bienvwnida;
import interfaz.Menu;
import java.awt.Container;
import static java.lang.Thread.sleep;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class Principal extends Thread{

    /**
     * @param args the command line arguments
     */
        static JFrame ventanaPrinc = new JFrame("Piensa rapido");
        
    public static void main(String[] args) {
        // TODO code application logic here
        
        ventanaPrinc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrinc.setResizable(false);
        ventanaPrinc.setVisible(true);
        ventanaPrinc.add(new Bienvwnida());
        ventanaPrinc.setLocation(450,150);
        ventanaPrinc.setSize(500,500);
        iniciar();
        
    }
     @Override
    public void run(){
                    try{
                        sleep(1000);
                        ventanaPrinc.setContentPane((Container)new Menu());
                        ventanaPrinc.setSize(600,720);
                        ventanaPrinc.setLocation(450,20);
                        ventanaPrinc.setVisible(true);
                        
                             }catch(InterruptedException e){
                        System.out.println("Se interrumpio");}//fin catch


    }
    public static void iniciar(){
        Principal vent = new Principal();
        vent.start();
    }
    }
    

