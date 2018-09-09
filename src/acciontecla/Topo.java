/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciontecla;

import java.awt.Image;

/**
 *
 * @author USER
 */
public class Topo {
 
    public int pocX,pocY;
    public boolean estado;
    public Image imgSub,imgBaj,img;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }
    public int pocFila,pocCol;

    public Topo(int pocX, int pocY, boolean estado, Image imgs,Image imgb, int pocFila, int pocCol) {
        this.pocX = pocX;
        this.pocY = pocY;
        this.estado = estado;
        this.imgSub = imgs;
        this.imgBaj = imgb;
        this.pocFila = pocFila;
        this.pocCol = pocCol;
    }
    
}
