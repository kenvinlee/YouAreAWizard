package systems;
import com.artemis.EntitySystem;
import com.artemis.Entity;
import com.artemis.utils.ImmutableBag;
import com.artemis.ComponentMapper;

import components.Collides;
import components.Position;
import components.Stats;

public class CollisionSystem extends EntitySystem{
	private ComponentMapper<Position> pos;
	private ComponentMapper<Collides> col;
	private ComponentMapper<Stats> stats;
	
	public CollisionSystem()
	{
		super(Collides.class, Stats.class);
	}

	@Override
	protected boolean checkProcessing() {
		return true;
	}

	public void initialize()
	{
		pos = new ComponentMapper<Position>(Position.class, world);
		col = new ComponentMapper<Collides>(Collides.class, world);
		stats = new ComponentMapper<Stats>(Stats.class, world);
	}
	
	@Override
	public void processEntities(ImmutableBag<Entity> e) {
		
		
	}
	
	
}
