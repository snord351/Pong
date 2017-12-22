/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author snord
 */
public class Pong extends Application {
    int BALLWIDTH = 15;
    int BALLHEIGHT = 15;
    double BALLRADIUS = Math.sqrt(BALLHEIGHT);
     int  HEIGHT = 500;
    int WIDTH = 500;
    int PADDLEWIDTH = 70;
    int PADDLEHEIGHT = 12;
    int frame = 0;
    int BLOCKWIDTH  = 40;
    int BLOCKHEIGHT  = 10;
   static boolean blocks[][] = new boolean[10][10];

    Paddle paddle = new Paddle(100,480);
    public  Ball ball = new Ball(10,300,-2,6);
    Canvas canvas = new Canvas (WIDTH,HEIGHT);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    @Override
    public void start(Stage primaryStage) {
            makeBlocks();
        BorderPane root = new BorderPane();
        root.setCenter(canvas);
        
        Scene scene = new Scene(root, WIDTH,HEIGHT);
        drawBoard();
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        
        canvas.setOnMouseMoved(e->{
        paddle.x = e.getX();
        if(e.getY()<= 144)
        {
           int x = ((int)e.getX())/(BLOCKWIDTH+10);
           int y = ((int)e.getY())/(BLOCKHEIGHT+4);
        }
        });
    new AnimationTimer() 
        {

            @Override
            public void handle(long now)
            {  
                moveBall();
                ballHitblock();
                drawBoard();
                  frame++;
            }
          
     }.start();

    
        primaryStage.show();
    }
    public void ballHitblock()
    {
     if(ball.y <=144)   
     {
          int x = ((int)ball.x)/(BLOCKWIDTH+10);
           int y = ((int)ball.y)/(BLOCKHEIGHT+4);
             if(x !=10 && y != 10)
               {
           if(blocks[y][x] == true)
           {             
              blocks[y][x] = false;
             // ball.veloX = -ball.veloX;
              ball.veloY = -ball.veloY;
               
           }
               }
     }
    }
    public void moveBall()
    {
        if(ball.x+ball.veloX >=WIDTH- BALLRADIUS)
        {
            ball.veloX = -1*ball.veloX;
        }
          if(ball.x+ball.veloX-BALLRADIUS <=0)
        {
             ball.veloX = -1*ball.veloX;
        }
        
            if(ball.y+ball.veloY >=(HEIGHT-BALLRADIUS))
        {
            System.out.println("HIT THE GROUND");
            ball.veloY = -1*ball.veloY;
        }
        
              if(ball.y+ball.veloY-BALLRADIUS <=0)
        {
             ball.veloY = -1*ball.veloY;
        }
        double xd = paddle.x -(ball.x-BALLRADIUS);
        double yd = paddle.y -(ball.y-BALLRADIUS);
        double distance = Math.sqrt(xd*xd + yd*yd);
       // System.out.println(distance);
        if(ball.y>=480 && ball.y<490 && ball.x >=paddle.x- PADDLEWIDTH/2&&ball.x<=paddle.x+ PADDLEWIDTH/2)
        {
            ball.veloY = -1*ball.veloY;
        }
        
        ball.x+=ball.veloX;
        ball.y+=ball.veloY;
    }
public void drawBoard()
{
    gc.setFill(Color.LIGHTBLUE);
     gc.fillRect(0, 0, WIDTH, HEIGHT);
     double blockStartX = 4;
     double blockStartY = 4;
     for(int i =0; i<10; i++ )
     {
         for(int j =0; j<10;j++)
         {
             if(blocks[i][j] == true)
             {
                gc.setFill(Color.GREEN); 
                gc.fillRect(blockStartX, blockStartY, BLOCKWIDTH, BLOCKHEIGHT);
             }
  
             blockStartX += BLOCKWIDTH+10;
             
         }
          blockStartX =4;
          blockStartY +=BLOCKHEIGHT+4;
     }
     gc.setFill(Color.TAN);
     gc.fillRect(paddle.x-PADDLEWIDTH/2, paddle.y, PADDLEWIDTH, PADDLEHEIGHT);
     
     gc.setFill(Color.YELLOW);
     gc.fillOval(ball.x-BALLWIDTH/2, ball.y-BALLWIDTH/2, BALLWIDTH, BALLHEIGHT );
}
    /**
     * @param args the command line arguments
     */
    public void makeBlocks()
    {
        for(int i =0 ; i< 10;i++)
        {
            for(int j = 0;j<10;j++)
            {
              blocks[i][j] = true;  
            }
        }
    }
       
    public static void main(String[] args) {
        launch(args);
       
    }
        

}
