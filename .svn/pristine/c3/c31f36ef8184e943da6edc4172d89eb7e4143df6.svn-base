package systems;

import org.newdawn.slick.geom.Shape;

import com.artemis.EntityProcessingSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;

import components.Position;
import components.Velocity;
import components.Collides;
import components.Map;

public class PhysicsSystem extends EntityProcessingSystem{
	
	private ComponentMapper<Position> pos;
	private ComponentMapper<Velocity> vel;
	private ComponentMapper<Collides> col;
	private ComponentMapper<Map> maps;
	
	public PhysicsSystem()
	{
		super(Position.class, Velocity.class, Collides.class);
	}
	
	public void initialize()
	{
		pos = new ComponentMapper<Position>(Position.class, world);
		vel = new ComponentMapper<Velocity>(Velocity.class, world);
		col = new ComponentMapper<Collides>(Collides.class, world);
		maps = new ComponentMapper<Map>(Map.class, world);
	}
	
	public void process(Entity e)
	{
		pos.get(e).addX(vel.get(e).getXComponent()*world.getDelta());
		pos.get(e).addY(vel.get(e).getYComponent()*world.getDelta());
		col.get(e).getShape().setX(col.get(e).getShape().getX() + vel.get(e).getXComponent() * world.getDelta());
		col.get(e).getShape().setY(col.get(e).getShape().getY() + vel.get(e).getYComponent() * world.getDelta());
		
		Map m = maps.get(world.getGroupManager().getEntities("MAP").get(0));
		
		Collides c = col.get(e);
		for(Shape p: m.getAll())
		{
			if(c.getShape().intersects(p))
			{
				pos.get(e).addX(-vel.get(e).getXComponent()*world.getDelta());
				pos.get(e).addY(-vel.get(e).getYComponent()*world.getDelta());
				col.get(e).getShape().setX(col.get(e).getShape().getX() - vel.get(e).getXComponent() * world.getDelta());
				col.get(e).getShape().setY(col.get(e).getShape().getY() - vel.get(e).getYComponent() * world.getDelta());
			}
		}
	}

}
