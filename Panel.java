import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.*;
import java.util.Random;

public class Panel extends JPanel {
Sprite particle[] = new Sprite[10];
 Timer gameLoop;
 boolean odd = true;
 boolean collide = false;
 double inVelX, inVelY;
 Random rand = new Random();
 int x,y;
 double XDir,YDir;
 double force = .05;
	public Panel(){
		//System.out.println("Start");

		for(int i = 0; i<particle.length; i++){
			if(odd){
				x = rand.nextInt(900)+50;
				y = rand.nextInt(900)+50;
				XDir = /*(rand.nextInt(10)-5)/3.0*/0;
				YDir = /*(rand.nextInt(10)-5)/3.0*/0;
				particle[i]= new Sprite(x,y,XDir,YDir);
				odd = false;
			}
			else{
				x = rand.nextInt(400)+50;
				y = rand.nextInt(400)+50;
				XDir= -XDir;
				YDir = -YDir;
				particle[i]= new Sprite(x,y,XDir,YDir);
				odd = true;
			}
		}

		gameLoop = new Timer(5, new GameLoop());
		gameLoop.start();
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i<particle.length; i++){

			particle[i].paint(g, this);
		}
	}
	void gravity(){
		for(int i = 0; i<particle.length; i++){
			double YDist=0;
			double XDist=0;
			double Dist=0;
			double force2=0;
			double xforce=0;
			double yforce=0;
			for(int n = 0; n<particle.length; n++){
				if(n!=i){
					YDist = particle[n].getY() - particle[i].getY();
					XDist = particle[n].getX() - particle[i].getX();
					Dist = Math.sqrt(Math.pow(YDist, 2)+(Math.pow(XDist, 2)));
					force2 = force/(Math.pow(Dist, 1));
					xforce += force2*XDist/Dist;
					yforce += force2*YDist/Dist;
				}
				particle[i].setXDir(xforce);
				particle[i].setYDir(yforce);
			}
		}
	}
	public void anyCollide(){
		for (int outer = 0; outer < particle.length; outer++){
			for (int iner = outer+1; iner < particle.length; iner++){
				if(doesCollide(particle[outer], particle[iner])){
					collide = true;
					if(converge(particle[outer],particle[iner])){
						Bounce(particle[outer], particle[iner]);
						//System.out.println("Bounce");
					}
				}
				else{
					collide = false;
				}
				setColor(particle[outer],particle[iner]);
			}
		}
	}


	public void Bounce(Sprite partA, Sprite partB){
		double temp;


		temp=partA.getXspeed();
		partA.setXspeed(partB.getXspeed());
		partB.setXspeed(temp);

		temp=partA.getYspeed();
		partA.setYspeed(partB.getYspeed());
		partB.setYspeed(temp);

	}
	public void setColor(Sprite partA, Sprite partB){
		if(collide){
			partA.setColor(Color.RED);
			partB.setColor(Color.RED);
		}
		else{
			partA.setColor(Color.BLACK);
			partB.setColor(Color.BLACK);
		}
	}

	public boolean 	doesCollide(Sprite Victim, Sprite Assailant ){
		//System.out.println("CALCULATE");
		int x1, y1;
		int x2, y2;
		double dist;
		x1 = Victim.getX();
		y1 = Victim.getY();
		x2 = Assailant.getX();
		y2 = Assailant.getY();
		dist = Math.sqrt(Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2));
		if(dist <= Victim.getWidth()/2+Assailant.getWidth()/2){
			return true;
		}
		else
			return false;

	}
	public boolean converge(Sprite PartA, Sprite PartB){
		double x1=PartA.getX();
		double y1=PartA.getY();
		double x2=PartB.getX();
		double y2=PartB.getY();
		double xv1=PartA.getXspeed();
		double yv1=PartA.getYspeed();
		double xv2=PartB.getXspeed();
		double yv2=PartB.getYspeed();
		if(calcConverge(x1,x2,xv1,xv2)||calcConverge(y1,y2,yv1,yv2)){
			return true;
		}
		return false;
	}
	private boolean calcConverge(double posA, double posB, double speedA, double speedB){
		double[] speed=new double[2];
		if(posA<posB){
			speed[0]=speedA;
			speed[1]=speedB;
		}
		else{
			speed[0]=speedB;
			speed[1]=speedA;
		}
		if(speed[1]<speed[0])
			return true;
		else
			return false;
	}

	class GameLoop implements ActionListener{
		public void actionPerformed(ActionEvent e){
			for(int i = 0; i<particle.length; i++){
				particle[i].move();
			}
			anyCollide();
			repaint();
			gravity();
		}
	}
}
