package systems;
import java.io.File;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import components.Sprite;
import components.Velocity;
import components.Position;
import gamestates.GameplayState;

import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.artemis.ComponentMapper;

public class RenderingSystem extends EntityProcessingSystem{
	private ComponentMapper<Sprite> sprites;
	private ComponentMapper<Position> pos;
	private ComponentMapper<Velocity> vel;
	private Image agility;
	private Image ghost;
	private Image replenish;
	private Image temper;

	public RenderingSystem()
	{
		super(Sprite.class);
	}

	public void initialize()
	{
		sprites = new ComponentMapper<Sprite>(Sprite.class, world);
		pos = new ComponentMapper<Position>(Position.class, world);
		vel = new ComponentMapper<Velocity>(Velocity.class, world);

		try {
			agility = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Agility.png");
			ghost = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Ghost.png");
			replenish = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Replenish.png");
			temper = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Temper.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}	
	}

	@Override
	protected void process(Entity e) 
	{	
		if (!pos.get(e).isMoving() && !pos.get(e).isCasting())
			sprites.get(e).getImage(0).draw(pos.get(e).getX(), pos.get(e).getY() + GameplayState.getInstance().getYOffset());

		for (int i = 0; i < sprites.get(e).getLength(); i++)
		{
			if (sprites.get(e).getImage(i).getRotation() != pos.get(e).getTheta())
			{
				sprites.get(e).getImage(i).setRotation(pos.get(e).getTheta());
			}
		}	
		agility.setRotation(pos.get(e).getTheta());
		ghost.setRotation(pos.get(e).getTheta());
		temper.setRotation(pos.get(e).getTheta());
		replenish.setRotation(pos.get(e).getTheta());

		if(pos.get(e).isCasting())
		{
			sprites.get(e).getImage(1).draw(pos.get(e).getX(), pos.get(e).getY() + GameplayState.getInstance().getYOffset());
		}

		/*TODO fix this so it draws behind the character
		if(sprites.get(e).isAgility())
		{
			agility.draw(pos.get(e).getX(), pos.get(e).getY() + GameplayState.getInstance().getYOffset());
		}
		*/
		if(sprites.get(e).isGhost())
		{
			ghost.draw(pos.get(e).getX(), pos.get(e).getY() + GameplayState.getInstance().getYOffset());
		}
		if(sprites.get(e).isReplenish())
		{
			replenish.draw(pos.get(e).getX(), pos.get(e).getY() + GameplayState.getInstance().getYOffset());
		}
		if(sprites.get(e).isTemper())
		{
			temper.draw(pos.get(e).getX(), pos.get(e).getY() + GameplayState.getInstance().getYOffset());
		}
	}

}
