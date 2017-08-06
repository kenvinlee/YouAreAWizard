package main;

import org.newdawn.slick.Image;
import org.newdawn.slick.Animation;


/*
 *This class extends slick's native Animation class, and is designed solely for the purpose of being able to use an animation with images rotated,
 *without needing a new spritesheet for each orientation
 */
public class MyAnimation extends Animation
{	
	public MyAnimation(Image[] i, int delay, boolean update)
	{
		super(i, delay, update);
	}
	
	public void setRotation(float theta)
	{
		for(int i = 0; i < this.getFrameCount(); i++)
		{
			Image image = this.getImage(i);
			image.setRotation(theta);
		}
	}
	
	public void setCenterRotation(float x, float y)
	{
		for(int i = 0; i < this.getFrameCount(); i++)
		{
			Image image = this.getImage(i);
			image.setCenterOfRotation(x, y);
		}
	}
	
	public float getRotation(Animation a)
	{
		return a.getImage(0).getRotation();
	}

}
