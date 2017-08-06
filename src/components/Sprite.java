package components;
import java.io.File;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.artemis.Component;


public class Sprite extends Component{
	private Image[] sprite;
	private boolean ghost,agility,replenish,temper;
	
	public Sprite(Image[] s) throws SlickException
	{
		sprite = new Image[s.length];
		sprite = s;
	}
	
	public int getLength()
	{
		return sprite.length;
	}
	
	public Image getImage(int i)
	{
		return sprite[i];
	}

	public boolean isGhost() 
	{
		return ghost;
	}

	public void setGhost(boolean ghost) 
	{
		this.ghost = ghost;
	}

	public boolean isAgility() 
	{
		return agility;
	}

	public void setAgility(boolean agility) 
	{
		this.agility = agility;
	}

	public boolean isReplenish() 
	{
		return replenish;
	}

	public void setReplenish(boolean replenish) 
	{
		this.replenish = replenish;
	}

	public boolean isTemper() 
	{
		return temper;
	}

	public void setTemper(boolean temper) 
	{
		this.temper = temper;
	}
	
}
