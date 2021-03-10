/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PongGame;

import javax.swing.*;
        
/**
 *
 * @author Vinicius
 */
public class Window extends JFrame {
    
    private PongGame pong;
    
    public Window( ){
        
        pong = new PongGame();
        
        //create frame
        this.add(pong); 
        this.setTitle("PongGame");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setLocationRelativeTo(null);
    } 
}
  