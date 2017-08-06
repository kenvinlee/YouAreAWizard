package systems;
import gamestates.GameplayState;
import main.MyAnimation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import components.Sprite;
import components.Animations;
import components.Velocity;
import components.Position;

import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.artemis.ComponentMapper;

public class AnimationRenderSystem extends EntityProcessingSystem{
	private ComponentMapper<Sprite> sprites;
	private ComponentMapper<Position> pos;
	private ComponentMapper<Velocity> vel;
	private ComponentMapper<Animations> animation;
	
	MyAnimation a;
	Image[] images;

	public AnimationRenderSystem()
	{
		super(Animations.class);
	}

	public void initialize()
	{
		pos = new ComponentMapper<Position>(Position.class, world);
		vel = new ComponentMapper<Velocity>(Velocity.class, world);
		animation = new ComponentMapper<Animations>(Animations.class, world);
		
	}

	public boolean hasFinished()
	{
		return (a.getFrame() == images.length);
	}
	
	@Override
	protected void process(Entity e) 
	{	
		animation.get(e).getAnimation().setRotation(pos.get(e).getTheta());
		
		if(pos.get(e).isMoving() && !pos.get(e).isCasting())
		{
			animation.get(e).getAnimation().draw(pos.get(e).getX(), pos.get(e).getY() + GameplayState.getInstance().getYOffset());
		}
		
	}

}
