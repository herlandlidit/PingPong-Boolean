/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.image.BufferStrategy;
import javax.swing.JLabel;
/**
 *
 * @author Herland
 */
public class clsCancha extends Thread {
    //CANCHA, COLOR, TABLERO,PUNTOS, PELOTA,TABLITA
    
    
    
    
    private Graphics pintor;
    
    private Color color;
    
    public int puntos=0;
    public int vida=3;
    //DATOS PARA LA TABLITA
    private int tx, ty,th,tw;
    //pelota
    private clsPelota objPelota;
    
    
    public clsCancha(Graphics pintor, Color color){
    this.pintor = pintor;
    this.color = color;
    this.th = 10;
    this.tw = 50;
    this.tx = 400-tw/2;
    this.ty = 350;
    this.objPelota = new clsPelota(pintor, color); 
    
    }
    //Método
    public void iniciarPelota(){
        this.objPelota.start();
        dibujarTablita();
        
    }
    
    @Override
    public void run (){
        //Lógica para el Juego
        while (true){
            
          
            this.objPelota.choco=false;
            
           
            //programa la pare logica de la interserccion
            Rectangle tabla= new Rectangle(tx, ty, tw, th);
            Rectangle pelota = new Rectangle (objPelota.getPx(), objPelota.getPy(),30,30);
            boolean sw = tabla.intersects(pelota); 
            
            if(sw){
                
              this.objPelota.choco=true;
                  
           
            }
            try{
                sleep(50);
            } catch (Exception e){
            }
            
            } 
        }
    
    
    
    
      
    public void dibujarTablita(){
        this.pintor.setColor(Color.BLACK);
        this.pintor.fillRect(tx, ty, tw, th);
    }
    public void limpiarTablita(){
        this.pintor.setColor(this.color);
        this.pintor.fillRect(tx, ty, tw, th);
    }
    
    public void moverDerechaTablita(){
        if(this.tx < 750){
        limpiarTablita();
        this.tx =this.tx+10;
        dibujarTablita();
    }
}
     public void moverIzquierdaTablita(){
        if(this.tx > 0){       
        limpiarTablita();
        this.tx =this.tx-10;
        dibujarTablita();
         }
    }

    public void score(JLabel label2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
