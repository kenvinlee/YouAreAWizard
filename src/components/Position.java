package components;

import com.artemis.Component;

public class Position extends Component
{
	private float x, y, theta;
	boolean moving;
	boolean casting;

	public Position(float x, float y)
	{
		this.x = x;
		this.y = y;
		
		moving = false;
		theta = 0;
	}
	
	public boolean isCasting()
	{
		return casting;
	}
	
	public void makeCast()
	{
		casting = true;
	}
	
	public void stopCast()
	{
		casting = false;
	}
	
	public boolean isMoving()
	{
		return moving;
	}
	
	public float getX()
	{
		return x;
	}
	
	public float getY()
	{
		return y;
	}
	
	public float getTheta()
	{
		return theta;
	}
	
	public void makeMove()
	{
		moving = true;
	}
	
	public void detectMove()
	{
		moving = false;
	}
	
	public void setX(float n)
	{
		x = n;
	}
	
	public void setY(float n)
	{
		y = n;
	}
	
	public void setTheta(float n)
	{
		theta = n;
	}
	
	public void addX(float d)
	{
		x += d;
	}
	
	public void addY(float d)
	{
		y += d;
	}
	
	public void addTheta(float d)
	{
		theta += d;
	}

}
