package components;

import com.artemis.Component;
import java.util.*;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import main.MyAnimation;


public class Animations extends Component
{
	private Image[] images;
	private MyAnimation animation;
	
	//Fill in all of the variables and access methods a spritesheet would need
	public Animations(Image[] i, int duration, boolean update) throws SlickException
	{
		images = new Image[i.length];
		images = i;
		
		animation = new MyAnimation(images, duration, update);
	}
	
	public MyAnimation getAnimation()
	{
		return animation;
	}
}
