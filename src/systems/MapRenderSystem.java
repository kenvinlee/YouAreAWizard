package systems;

import gamestates.GameplayState;

import java.io.File;

import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.SlickException;

import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import components.*;

public class MapRenderSystem extends EntitySystem{
	private float y;
	private Image tileSet;
	private Image tree;
	private Image rock;
	float yShift;
	private ComponentMapper<Position> pos;
	private ComponentMapper<Velocity> vel;
	private ComponentMapper<Map> maps;
	
	public MapRenderSystem()
	{
		super();
	}
	
	public void initialize()
	{
		y = 0;
		yShift = 0;
		pos = new ComponentMapper<Position>(Position.class, world);
		vel = new ComponentMapper<Velocity>(Velocity.class, world);
		maps = new ComponentMapper<Map>(Map.class, world);
		
		try{
		tileSet = new Image("Data" + File.separator + "Maps" + File.separator + "tileset.png");
		}catch(SlickException e){}
		
		tree = tileSet.getSubImage(120, 0, 80, 80);
		rock = tileSet.getSubImage(200, 0, 80, 80);
	}
	
	protected boolean checkProcessing() {
		return true;
	}	
	
	protected void processEntities(ImmutableBag<Entity> e)
	{
		Map m = maps.get(world.getGroupManager().getEntities("MAP").get(0));
		
		m.getTiledMap().render(0, (int)GameplayState.getInstance().getYOffset(), 0);
		
		for(int i = 0; i < m.getTrees().size(); i++)
		{
			tree.draw(m.getTrees().get(i).getX(), m.getTrees().get(i).getY() + GameplayState.getInstance().getYOffset());
		}
		
		for(int i = 0; i < m.getRocks().size(); i++)
		{
			rock.draw(m.getRocks().get(i).getX(), m.getRocks().get(i).getY() + GameplayState.getInstance().getYOffset());
		}
		
		if (pos.get(world.getGroupManager().getEntities("PLAYER").get(0)).getY() < 309 || pos.get(world.getGroupManager().getEntities("PLAYER").get(0)).getY() > 309)
			GameplayState.getInstance().moveY(-vel.get(world.getGroupManager().getEntities("PLAYER").get(0)).getYComponent()*world.getDelta());
		
		if(GameplayState.getInstance().getYOffset() <= -120)
		{
			GameplayState.getInstance().setYOffset(-120f);
		}
		
		if(GameplayState.getInstance().getYOffset() >= 150)
		{
			GameplayState.getInstance().setYOffset(150);
		}
		
	}

}
