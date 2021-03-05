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
public class Score extends Rectangle {
    
    private int player;
    String scoreText;
    
    Score(int x, int y, int player,String scoreText){
        this.x = x;
        this.y =y;
        this.player = player;
        this.scoreText = scoreText;
    }
    
    public void draw(Graphics g){
        if(player==1){
            g.setFont(new Font("SansSerif", Font.PLAIN, 24));    
            g.setColor(Color.red);
            g.drawString(scoreText, x, y);
        }
        if(player==2){
            g.setFont(new Font("SansSerif", Font.PLAIN, 24));
            g.setColor(Color.blue);
            g.drawString(scoreText, x, y);
        }
    
    }
}
