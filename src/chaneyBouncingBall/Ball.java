package chaneyBouncingBall;
import java.awt.*;
import java.util.*;
import java.io.*;


public class Ball
{
	public double gravConst = 6.67384*(Math.pow(10,-11));
	float x, y;
	float velX, velY;
	double accelX, accelY;
	float radius;
	float mass;
	private Color color;

	Chaney2DVector position;
	Chaney2DVector velocity;
	Chaney2DVector acceleration;
	
//	Chaney2DVector position = new Chaney2DVector(x, y);
//	Chaney2DVector velocity = new Chaney2DVector(velX, velY);
//	Chaney2DVector acceleration = new Chaney2DVector(accelX, accelY);

	
	public Ball(float x, float y, float velX, 
		float velY, double accelX,
		float accelY, float radius, 
		Color color, float mass)
	{
		position = new Chaney2DVector(x, y);
		velocity = new Chaney2DVector(velX, velY);
		acceleration = new Chaney2DVector(accelY, accelY);
		
//
//		ArrayList posVelAcc = new ArrayList();
//		posVelAcc.add(position);
//		posVelAcc.add(velocity);
//		posVelAcc.add(acceleration);

//		this.x = x;
//		this.y = y;
//		this.velX = velX;
//		this.velY = velY;
//		this.accelX = accelX;
//		this.accelY = accelY;
		this.radius = radius;
		this.color = color;
		this.mass = mass;


	}
	
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillOval((int)(position.x - radius), (int)(position.y - radius),
			(int)(2 * radius), (int)(2 * radius));
	}

	public double getForceMag(Ball ball)
	{
		double forceMag = (10 * mass * ball.mass) / 
				(Math.pow((ball.position.x - position.x),2) + 
				Math.pow((ball.position.y - position.y), 2));
		if (Math.pow((ball.position.x - position.x),2) + 
		(Math.pow((ball.position.y - position.y), 2)) == 0)
		{
			System.out.println("THE FORCE IS UNDEFINED!!!!");
		}
		return forceMag;
	}
	
	public double getForceAngle(Ball ball)
	{
		double slope = (double)((ball.position.y - position.y) / 
		((double)(ball.position.x - position.x)));
		double forceAngle;
		if (position.x <= ball.position.x)
		{
			forceAngle = Math.atan(slope);
		}
		else
		{
			forceAngle = Math.atan(slope) + (Math.PI);
		}
		return forceAngle;
	}
	
	public void setVelocityX(float x)
	{
		velocity.x = x;
	}
	
	public void setVelocityY(float y)
	{
		velocity.y = y;
	}
	
	public void moveOneStepWithCollisionDetection(ContainerBox box, Ball ball)
	{
//		float forceMag = 1;
//		float forceAngle = (float) Math.PI / 2;
		float forceMag = (float) this.getForceMag(ball);
		float forceAngle = (float) this.getForceAngle(ball);
		
		System.out.println("The force magnitude is: " + forceMag);
		System.out.println("The force angle is: " + forceAngle);
		
		


		
		acceleration.x = (float) (forceMag * Math.cos(forceAngle));
		acceleration.y = (float) (forceMag * Math.sin(forceAngle));
		
		float ballMinX = box.minX + radius;
		float ballMinY = box.minY + radius;
		float ballMaxX = box.maxX - radius;
		float ballMaxY = box.maxY - radius;
		
		velocity.x = velocity.x + acceleration.x;
		velocity.y = velocity.y + acceleration.y;
		
		position.x = position.x + velocity.x;
		position.y = position.y + velocity.y;

		if (position.x < ballMinX)
		{
			velocity.x = -velocity.x;
			position.x = ballMinX;
		}
		else if (position.x > ballMaxX)
		{
			velocity.x = -velocity.x;
			position.x = ballMaxX;
		}

		if (position.y < ballMinY)
		{
			velocity.y = -velocity.y;
			position.y = ballMinY;
		}
		else if (position.y > ballMaxY)
		{
			velocity.y = -velocity.y;
			position.y = ballMaxY;
		}
	}
}