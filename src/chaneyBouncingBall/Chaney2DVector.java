package chaneyBouncingBall;
import java.util.ArrayList;



public class Chaney2DVector
{
	float x;
	float y;
	Point fromLocation;

	public Chaney2DVector(float x,
		float y)
	{
//		fromLocation = new Point(0, 0);
		this.x = x;
		this.y = y;
		
	}

	public Chaney2DVector(Point point1,
		Point point2)
	{
		fromLocation = new Point(point1.x, point1.y);
		this.x = point2.x - point1.x;
		this.y = point2.y - point1.y;
		
	}
	
	public float getLength()
	{
		float length = (float) Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return length;
	}
	
}