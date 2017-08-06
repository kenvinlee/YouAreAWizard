package systems;
import gamestates.GameplayState;
import main.MyAnimation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import components.Spell;
import components.Sprite;
import components.Animations;
import components.Velocity;
import components.Position;

import com.artemis.Entity;
import com.artemis.EntityProcessingSystem;
import com.artemis.ComponentMapper;

public class SpellRenderSystem extends EntityProcessingSystem
{
	private ComponentMapper<Spell> spells;
	private ComponentMapper<Position> pos;
	private ComponentMapper<Animations> animation;
	
	private MyAnimation a;
	private Image[] images;

	public SpellRenderSystem()
	{
		super(Spell.class);
	}

	public void initialize()
	{
		spells = new ComponentMapper<Spell>(Spell.class, world);
		pos = new ComponentMapper<Position>(Position.class, world);
		animation = new ComponentMapper<Animations>(Animations.class, world);
		
	}
	
	@Override
	protected void process(Entity e) 
	{	
		if(spells.get(e).getSpellCast() != null)
		{
			a = spells.get(e).getSpellCast();
			
			if (a.getFrame() < a.getFrameCount()-1)
			{ 
			
				a.draw(pos.get(world.getGroupManager().getEntities("PLAYER").get(0)).getX(),pos.get(world.getGroupManager().getEntities("PLAYER").get(0)).getY() 
						+ GameplayState.getInstance().getYOffset() - a.getHeight()); 
			}
					
			
		}
		
	}

}
