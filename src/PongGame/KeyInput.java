/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PongGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 *
 * @author Vinicius
 */
public class KeyInput extends KeyAdapter{
    
    private Paddle pad1;
    private Paddle pad2;
    private boolean up1 = false;
    private boolean up2 = false;
    private boolean down1 = false;
    private boolean down2 = false;
    
     public KeyInput(Paddle p1, Paddle p2 ){
         pad1 = p1;
         pad2 = p2;
     }
    
    @Override
    public void keyPressed(KeyEvent e){

        int key = e.getKeyCode();   
        
            if(key == KeyEvent.VK_W) {
                System.out.println(key);
                pad1.switchDirection(-1);
                up1 = true;
            }               
            else if(key == KeyEvent.VK_S ){
                pad1.switchDirection(1);
                down1 = true;
            }
      
            if(key == KeyEvent.VK_UP){
                pad2.switchDirection(-1); 
                up2 = true;
            }
           
            else if(key == KeyEvent.VK_DOWN){
                pad2.switchDirection(1);
                down2 = true;
            }  
                
    }
        
    @Override
        public void keyReleased(KeyEvent e){
            
         int key = e.getKeyCode();  
          
            if(key == KeyEvent.VK_W){
                up1 = false;
            }  
            else if(key == KeyEvent.VK_S ){
                down1 = false;
            }
            if(key == KeyEvent.VK_UP) {
                    up2 = false;
            }
                
            else if(key == KeyEvent.VK_DOWN) {
                    down2 = false;
            } 
   
            if(!up1 && !down1 ){
                  pad1.stop();
            }
            
            if(!up2 && !down2){
                  pad2.stop();
            }  
             
        }  
}
