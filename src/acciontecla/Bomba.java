/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciontecla;

import java.awt.Image;
import static sun.applet.AppletResourceLoader.getImage;

/**
 *
 * @author USER
 */
public class Bomba {
    
    public  boolean estado;
    public int pocX,pocY;
    public Image img;
    public char letra;
    public boolean exploto;

    public Bomba(boolean estado, int pocX, int pocY, char letra) {
        this.estado = estado;
        this.pocX = pocX;
        this.pocY = pocY;
        this.letra = letra;
        PonreImg(letra);
        exploto =true;
     //   System.out.println("Letra recibida en el contructor "+letra +" - "+this.letra);
    
    }
    
    
    public void PonreImg(char let){
        img = getImage(getClass().getResource("/imagenes/gifs/"+let+".gif"));
    }
    
    public int getpocX(){
        return pocX;
    }
    public int getpocY(){
        return pocY;
    }
    public void setPocX(int x){
        this.pocX=x;
    }
    public void setPocY(int y){
        this.pocY=y;
    }
    public boolean isEstado() {
        return estado;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    
    
}
