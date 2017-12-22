/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pong;

/**
 *
 * @author snord
 */
public class Ball {
    double x; 
    double y;
    double veloX;
    double veloY;
    public Ball(double x, double y,double vx, double vy )
    {
        this.x = x;
        this.y = y;
        this.veloX = vx;
        this.veloY = vy;
    }
}
