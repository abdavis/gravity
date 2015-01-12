package andrew;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Sprite {
	private int x,y;//location
	private double xspeed = 0, yspeed = 0;
	private double xp, yp;
	double XDir, YDir;//direction
	ImageIcon image;
	int width = 20;
	Color color = Color.BLACK;
	public Sprite(int xp, int yp, double XDir, double YDir){
		this.image = null;
		this.xp = xp;
		this.yp = yp;
		x = xp;
		y = yp;
		this.XDir = XDir;
		this.YDir = YDir;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}	
	public int getWidth(){
		return width;
	}
	public void setXDir(double XDir){
		this.XDir = XDir;
	}
	public void setYDir(double YDir){
		this.YDir = YDir;
	}
	public void setX(int x){
		this.x = x;
		xp = x;
	}
	public void setY(int y){
		this.y = y;
		yp = y;
	}
	public void setColor(Color color){
		this.color = color;
	}
	
	public double getXspeed(){
		return xspeed;
	}
	public double getYspeed(){
		return yspeed;
	}
	public void setXspeed(double xvelocity){
		this.xspeed = xvelocity;
	}
	public void setYspeed(double yvelocity){
		this.yspeed = yvelocity;
	}
	
	public void move(){
		xspeed+=XDir;
		yspeed+=YDir;
		xp += xspeed;
		yp += yspeed;
		XDir=0;
		YDir=0;
		
		x = (int)xp;
		y = (int)yp;
		
	}
	

	public void paint(Graphics g, JPanel panel){
		if(image == null){
			g.setColor(color);
			g.fillOval(x, y,width,width);
		}
		else{
			image.paintIcon(panel, g, x, y);
		}
	}
}