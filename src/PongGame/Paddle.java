/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PongGame;

/**
 *
 * @author Vinicius
 */
import java.awt.*;


public class Paddle extends Rectangle  {
 
    private int player;
    public int velocity;
    private int speed = 1;
 
    Paddle(int x, int y , int width,int height, int player){
        super(x, y ,width,height);
        this.player = player;
    }
    
    Paddle(){
    }
    
    public void draw(Graphics g){ 
        if(player == 1){   
            g.setColor(Color.red);
            g.fillRect(x, y, width, height);          
        }
        else
            g.setColor(Color.blue);
            g.fillRect(x, y, width, height);
    }
    
    public void switchDirection(int direction){
        velocity=speed*direction;
    }
    
    public void stop(){
        velocity=0;
    }
    
     public void move(double dt){
        y = (int) (y+velocity*dt*400);
    }  
     
}


    
    
    
   
    



    
      
