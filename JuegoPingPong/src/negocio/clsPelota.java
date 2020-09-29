/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Herland
 */
public class clsPelota extends Thread {
        private int px, py, w, h;
        private int dificultad=50;
        private int score = 0;
        private int vida=3;
        private boolean sw=false;
        private boolean alea=true;
        private boolean sw1=false;
         private boolean sw2=false;
        private boolean perdio=true;
        private Graphics pintor;
        private Color color;
        public boolean choco=false;
        public boolean inicio=false;
        private boolean controlador=true;
        
        
    public clsPelota(Graphics pintor, Color color){
        this.w = 20;
        this.h = 20;
        this.px = (int)(Math.random()*800)-30 ;
        this.py = 50;
        this.pintor = pintor;
        this.color = color;
    }
    
    @Override
    public void run(){
        while (this.controlador){
            
            // Aqui inicializamos el score y la vida
           // pintarScore();
            //pintarVida();
            pintarBloque();
            pintarBloque2();
           
            //Inicio sera true solo cuando choque con la tabletica
            if(this.inicio){
             //como choco con la tablita movemos hacia arriba
                  
             
                if(this.py<50){
                     this.inicio=false;
                     this.sw1=!this.sw1;  
                }else{
           
                   if(this.sw1){
                       //Cuando choca del lado izquierdo
                       if(this.px<20){
                           this.sw1=false;
                          
                       }else{
                       diagonalIzqArriba();
                       }
                      
                       
                   }else{
                       if(sw2){
                       diagonalIzqAbajo();
                      this.sw1=!this.sw1;
                       }else{
                       diagonalDerArriba(); 
                       }
                       
                       
                       
                   }
                    
                }
                
                if(this.px >800){
                    this.sw2=true;                    
                }else{
                this.sw2=false;}
                
            }else{
            //Se mueve la pelota hacia abajo mientras no choque con la tablita
          if(this.perdio){
           moverAbajo();
           
         
          }else{
              
              if(this.px<20){
                  this.sw1=true;
              }
                  if(this.sw1){

                      if(this.px>800){
                      this.sw1=false;
                      }
                     
                    diagonalDerAbajo();   
                  }else{
                  diagonalIzqAbajo();
                  }
        
          
         
          }
           
            if (this.py > 400 || this.choco){
                
                if(this.choco){
                    
                    this.sw=true;
                    this.inicio=true;
                    this.perdio=false;
                    
                    //Si choco la paletita aumentamos el score
                    this.score++;
                    //Borramos el score anterior 
                    borrarScore();
                    //Pintamos el nuevo score
                    pintarScore();
                    //Aumenta la dificultad cuando el score es par
                    if(this.score % 2 == 0){
                    this.dificultad=this.dificultad-10;
                    }
                   
                  
                }
                //Solo entra al if cuando no choca la pelota a la pelotita
                if(this.sw==false){
                this.px = (int)(Math.random()*800)-30 ;
                this.py = 50; 
                this.perdio=true;
                //Si no choca la paletita, restamos las vidas
                this.vida--;
                if(vida <0){
                   controlador=false;
                }else{
                 borrarVida();
                 pintarVida();
                }
                }
                
                this.sw=false;
            }
                
          
            }
            try{
                sleep(this.dificultad);
            }catch (Exception e){
               
            }
        }
         System.out.println("JUEGO FINALIZADO!!!"); 
         pintarResultado();
    }
    
    
    private void moverAbajo(){
            borrarP();
            this.py = this.py + 5;
            pintarP();  
    }
    
     private void moverArriba(){
            borrarP();
            this.py = this.py - 5;
            pintarP();  
    }
     
     private void diagonalIzqAbajo(){
            borrarP();
            this.px=this.px-5;
            this.py = this.py + 5;
            pintarP();  
    }
    
     
     private void diagonalDerAbajo(){
            borrarP();
            this.px=this.px+5;
            this.py = this.py + 5;
            pintarP();  
    }
     
    
     
     
     private void diagonalIzqArriba(){
            borrarP();
            this.px=this.px-5;
            this.py = this.py - 5;
            pintarP();  
    }
     
     
     private void diagonalDerArriba(){
            borrarP();
            this.px=this.px+5;
            this.py = this.py -5;
            pintarP();  
    }
     
     
    private void pintarP(){
        this.pintor.setColor(Color.RED);
        this.pintor.fillOval(px, py, w, h);
    }
     private void borrarP(){
        this.pintor.setColor(this.color);
        this.pintor.fillOval(px, py, w, h);
    }
     
     private void borrarScore(){
       this.pintor.setColor(this.color);
        this.pintor.fillOval(70, 130,30, 30);
         }
     
     private void borrarVida(){
       this.pintor.setColor(this.color);
        this.pintor.fillOval(60, 183, 22, 22);
     }
    
     private void pintarScore(){
        pintor.setColor(color.RED);
       pintor.setFont(new Font("Arial", Font.BOLD, 16));
       pintor.drawString("Score: "+ this.score , 20, 150);    
     }
     
     private void pintarVida(){
         pintor.setColor(color.RED);
       pintor.setFont(new Font("Arial", Font.BOLD, 16));
       pintor.drawString("Vida: "+ this.vida , 20, 200); 
       
     }
     
     private void pintarResultado(){
     //Borramos la pantalla
     this.pintor.setColor(this.color);
     this.pintor.fillOval(10, 80, 200, 200);
     
     //Pintamos el score
       pintor.setColor(color.RED);
       pintor.setFont(new Font("Arial", Font.BOLD, 32));
       pintor.drawString("GAME OVER!!! " , 250, 150);
       pintor.drawString("Score: "+ this.score , 250, 195);
       pintor.drawString("Autor: Herland Lidit Mendoza " , 250, 230);
     
     }
     
     private void pintarCuadrado1(int x){
        this.pintor.setColor(Color.RED);    
        this.pintor.fillRect(10,55, 40, 40);
        
        this.pintor.fillRect(10+x,55, 40, 40);     
     
     }
     
      private void pintarCuadrado2(int y){
        this.pintor.setColor(Color.RED);    
        this.pintor.fillRect(10,105, 40, 40);
        
        this.pintor.fillRect(10+y,105, 40, 40);     
     
     }
     
     
     private void pintarBloque(){
         int i=16;
         int x=50;
        while(i > 0){
         
        pintarCuadrado1(x);
        x=x+50;
        i--;
        } 
     
     }
     
     private void pintarBloque2(){
         int x=16;
         int y=50;
        while(x > 0){
         
        pintarCuadrado2(y);
        y=y+50;
        x--;
        } 
     
     }
     
         
     
     
    public int getPx() {
        return px;
    }

    public void setPx(int px) {
        this.px = px;
    }

    public int getPy() {
        return py;
    }

    public void setPy(int py) {
        this.py = py;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }
    
}
