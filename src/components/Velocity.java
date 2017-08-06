package components;
import com.artemis.Component;
public class Velocity extends Component{
	private float magnitude;
	private float direction;
	private boolean ghost;
	
	public Velocity(float magnitude, float direction, boolean ghost)
	{
		this.magnitude = magnitude;
		this.direction = direction;
		this.ghost = ghost;
	}
	
	public float getXComponent()
	{
		return (float) (magnitude * Math.cos(direction));
	}
	
	public float getYComponent()
	{
		return (float) (magnitude * Math.sin(direction));
	}
	
	public void setMagnitude(float m)
	{
		magnitude = m;
	}
	
	public void setDirection(float d)
	{
		direction = d;
	}
	
	public float getDirection()
	{
		return direction;
	}
	
	public boolean getGhost()
	{
		return ghost;
	}
	
	public void setGhost(boolean g)
	{
		ghost = g;
	}
}
