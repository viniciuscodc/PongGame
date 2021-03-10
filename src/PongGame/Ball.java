/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PongGame;

import java.awt.*;

/**
 *
 * @author Vinicius
 */
public class Ball extends Rectangle {
    
    public int velocx = 1;
    public int velocy = 1;
    
    Ball(int x, int y, int width, int height){
        super(x,y,width, height);
    }
    
   public void move(double dt){          
      x = (int) (x+velocx*dt*320);
      y = (int) (y+velocy*dt*320); 
      int speed;
      speed =  (int) (100*dt);
    }
   
     public void draw(Graphics g){ 
     g.setColor(Color.white);
     g.fillRect(x, y, width, height);
    }

}
