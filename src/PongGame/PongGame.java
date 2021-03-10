/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PongGame;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Vinicius
 */

   

public class PongGame extends JPanel implements Runnable{
   
   private final int WINDOW_WIDTH = 800;
   private final int WINDOW_HEIGHT = 500;
   private final int PADDLE_WIDTH = 20;
   private final int PADDLE_HEIGHT = 75;
   private final int BALL_DIAMETER = 20;
   
   private Thread panelThread;
   private int posx = WINDOW_WIDTH/2;
   private int posy = WINDOW_HEIGHT/2;  
   private int scoreCount1 =0;
   private int scoreCount2 =0;
   private  double dt;
   private boolean running;
   
   Ball balls;
   Paddle pad2;
   Paddle pad1;
   Score score1;
   Score score2;
      
    PongGame(){
    this.setPreferredSize(new Dimension (WINDOW_WIDTH,WINDOW_HEIGHT));
    paddleStart();
    this.addKeyListener(new KeyInput(pad1,pad2));
    this.setFocusable(true);
    gameStart();
    resetBall();
    scoreStart();
    gameStart();  
    }

    public void moveUpdate(double dt){
        balls.move(dt);
        pad1.move(dt);
        pad2.move(dt);
        collision();
    }
 
    
    public void resetBall(){
        
        //random position
        int randomx = (int) (Math.random() * ((WINDOW_WIDTH/2+50) - (WINDOW_WIDTH/2-50) + 1) + (WINDOW_WIDTH/2-50)); 
        int randomy = (int) (Math.random() * ((WINDOW_HEIGHT-50) - (50) + 1) + 50); 
        
        //random velocity
        int randomSpeedx = (int) (Math.random() * ((2) - (1) +1) + 1); 
        int randomSpeedy = (int) (Math.random() * ((2) - (1) +1) + 1);
         
        if (randomSpeedx==2){
            randomSpeedx = -1;
        }
        
        if (randomSpeedy==2){
            randomSpeedy = -1;
        }
        
        //pass arguments to ball
        balls = new Ball(randomx,randomy,BALL_DIAMETER,BALL_DIAMETER);
        balls.velocx = randomSpeedx;
        balls.velocy = randomSpeedy;
    }
    
    public void gameStart(){ 
       panelThread = new Thread(this);    
       panelThread.start();
       running = true; 
    }

    public void collision(){     
    
        //paddle window collision
        if(pad1.y<=0){
            pad1.y = 0;    
        }
        
        if(pad1.y>=WINDOW_HEIGHT-PADDLE_HEIGHT){
            pad1.y = WINDOW_HEIGHT-PADDLE_HEIGHT;    
        }
        
        if(pad2.y<=0){
             pad2.y = 0;    
        }
        
        if(pad2.y>=WINDOW_HEIGHT-PADDLE_HEIGHT){
             pad2.y = WINDOW_HEIGHT-PADDLE_HEIGHT;    
        }
               
       //ball window collision
        if(balls.y<=0){   
            balls.velocy= -balls.velocy;
        }
        
        if(balls.y >= WINDOW_HEIGHT-BALL_DIAMETER){   
            balls.velocy= -balls.velocy;
        }
       
       //ball paddle collision
       
       //pad1 top
         if(balls.x<=pad1.x+PADDLE_WIDTH && balls.y+BALL_DIAMETER>=pad1.y && balls.y+BALL_DIAMETER<pad1.y+20){
            balls.velocx= -balls.velocx;
            balls.y = pad1.y-BALL_DIAMETER-2;
            balls.velocy= -balls.velocy;
        }
       
        //pad1 middle
        
        if((balls.x< pad1.x+pad1.width) &&
            (balls.x>pad1.x)&& 
            (balls.y+BALL_DIAMETER > pad1.y) && 
            (balls.y< pad1.y+PADDLE_HEIGHT))
        {
            if(balls.velocx<0 ){
                 balls.velocx= -balls.velocx;
            }
        }
         
        //pad1 bottom    
        if(balls.x<=pad1.x+PADDLE_WIDTH && balls.y<=pad1.y+PADDLE_HEIGHT && balls.y>pad1.y+PADDLE_HEIGHT-20){
            balls.y = pad1.y+PADDLE_HEIGHT+2;
            balls.velocy= -balls.velocy;
        }
       

        //pad2 middle
        if((balls.x+BALL_DIAMETER> pad2.x) &&
            (balls.x+BALL_DIAMETER<pad2.x+pad2.width)&& 
            (balls.y+BALL_DIAMETER > pad2.y) && 
            (balls.y< pad2.y+PADDLE_HEIGHT))
        {
            if(balls.velocx>0 ){
                 balls.velocx= -balls.velocx;
            }
        }
          
        
        //pad2 bottom    
        if(balls.x>=pad2.x && balls.y<=pad2.y+PADDLE_HEIGHT && balls.y>pad2.y+PADDLE_HEIGHT-20){
            balls.y = pad2.y+PADDLE_HEIGHT+2;
            balls.velocy= -balls.velocy;
        }
       
        
        //pad2 top
         if(balls.x>=pad2.x && balls.y+BALL_DIAMETER>=pad2.y && balls.y+BALL_DIAMETER<pad2.y+20){
            balls.velocx= -balls.velocx;
            balls.y = pad2.y-BALL_DIAMETER-2;
            balls.velocy= -balls.velocy;
        }
    }
    
    public void scoreStart(){
        
        String scoreText1;
        String scoreText2;
        
         //game score count
       if(balls.x+BALL_DIAMETER<=0){  
          scoreCount2=scoreCount2+1;  
          resetBall();
        }
       
        if(balls.x>WINDOW_WIDTH){   
           scoreCount1=scoreCount1+1;
           resetBall();
        }
       
        scoreText1 = Integer.toString(scoreCount1);
        scoreText2 = Integer.toString(scoreCount2);      
                
        score1 = new Score(WINDOW_WIDTH/2-PADDLE_HEIGHT,PADDLE_WIDTH,1,scoreText1);
        score2 = new Score(WINDOW_WIDTH/2+PADDLE_HEIGHT,PADDLE_WIDTH,2,scoreText2);

    }
    
    public void paddleStart(){
        pad1 = new Paddle(0,WINDOW_HEIGHT/2-PADDLE_HEIGHT,PADDLE_WIDTH,PADDLE_HEIGHT,1);
        pad2 = new Paddle(WINDOW_WIDTH-PADDLE_WIDTH,WINDOW_HEIGHT/2-PADDLE_HEIGHT,PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }
    
   @Override
    public void paintComponent(Graphics g){
      super.paintComponent(g);
      setBackground(Color.black);
      pad1.draw(g);
      pad2.draw(g);
      balls.draw(g);
      score1.draw(g);
      score2.draw(g);
      g.setColor(Color.white);
      g.fillRect(WINDOW_WIDTH/2, 0, 1, WINDOW_HEIGHT);
    }
    
    public synchronized void run(){
        
         
        final int FPS = 60;
        final int UPS = 60;
        
        final double fOPTIMAL_TIME = 1000000000/FPS;
        final double uOPTIMAL_TIME = 1000000000/UPS;
        
        int frames =0,updates =0;
        double fDeltaTime =0, uDeltaTime =0;
       
        long startTime = System.nanoTime();
        long timer = System.currentTimeMillis();
                     
        while (running){
            
            long currentTime = System.nanoTime();
            
            fDeltaTime += (currentTime-startTime);
            uDeltaTime += (currentTime-startTime);
            startTime = currentTime;
            
            //update movement
            if(uDeltaTime >=uOPTIMAL_TIME ){
                scoreStart();
                moveUpdate(uDeltaTime/1000000000);
                updates++;
                uDeltaTime -= uOPTIMAL_TIME;
            }
            
            //update frames
            if(fDeltaTime >= fOPTIMAL_TIME){
                repaint();
                frames++;
                fDeltaTime -= fOPTIMAL_TIME;
            }
           
            //print frames and updates
            if(System.currentTimeMillis()-timer >= 1000){
                System.out.println("frames:"+frames+" "+"updates:"+updates);
                updates =0;
                frames =0;
                timer +=1000;
                
            }
        }
    }    
}

