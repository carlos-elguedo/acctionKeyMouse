/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciontecla;

import interfaz.ExplotaGlobos;
import java.applet.AudioClip;
import java.awt.Image;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author USER
 */
public class Control extends ExplotaGlobos{
    Random aleatorio = new Random();
    
    public char obtenerLetra(){
        char vecLetras[]={'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        int ind;
        ind=aleatorio.nextInt(26);
    
        
        return vecLetras[ind];
    }
   public int pocXaleatoria(){
       int ret = 0,ram;
       ram = aleatorio.nextInt(13);
       
       if(ram == 0){ret=20;}
       if(ram == 1){ret=50;}
       if(ram == 2){ret=80;}
       if(ram == 3){ret=110;}
       if(ram == 4){ret=140;}
       if(ram == 5){ret=170;}
       if(ram == 6){ret=200;}
       if(ram == 7){ret=230;}
       if(ram == 8){ret=260;}
       if(ram == 9){ret=290;}
       if(ram == 10){ret=320;}
       if(ram == 11){ret=350;}
       if(ram == 12){ret=380;}
       
       return ret+100;
   }
    public void gameOver(){
        //Jframe 
    }
    public void pause(){
        //Jframe 
    }
     public void music(){
         new ponMusica();
     }
    public class ponMusica extends Thread{
            AudioClip sonido;
            public ponMusica(){
                start();
            }
            
          public void run()  {
            sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Sonidos/music.wav"));
            
                //sonido.play();      
          }//fin run
    }//fin clase musica
    
    public void explotar(int i){
        new explosion(i);
    }
        private class explosion extends Thread{
        private int ind;
        Image explo = getToolkit().getImage(getClass().getResource("/imagenes/explo.gif"));
            public explosion(int ind) {
                this.ind = ind;
                start();
            }
            @Override
        public void run (){
            vector.get(ind).img = explo;
            vector.get(ind).estado =false;
            try {Thread.sleep(2380);} catch (InterruptedException ex) {}
            vector.get(ind).exploto =false;
        }
    }//fin clas explo

}
