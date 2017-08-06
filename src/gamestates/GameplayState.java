package gamestates;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import main.MyAnimation;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.geom.*;

import systems.*;
import util.Spells;
import components.*;

import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.SystemManager;
import com.artemis.World;


public class GameplayState extends BasicGameState
{
	private int stateID = 2;
	private static GameplayState gp;
	private TiledMap tm;
	private World world;
	
	private float yOffset;
	
	private SystemManager systemManager;
	private EntitySystem spellRenderSystem;
	private	EntitySystem collisionSystem;
	private EntitySystem cooldownSystem;
	private EntitySystem hudRenderSystem;
	private EntitySystem inputSystem;
	private EntitySystem mapRenderSystem;
	private EntitySystem physicsSystem;
	private EntitySystem renderingSystem;
	private EntitySystem animationRenderSystem;
	private EntitySystem HUDRenderSystem;

	private Image[] wizWalk;
	private Image[] wizStill;
	
	private Image[] troll;
	private Image[] dragon;
	private Image[] golem;
	private Image[] gateKeep;
	private Image[] skeleBow;
	private Image[] skeleSword;
	
	Entity spell;

	public GameplayState(int stateID)
	{
		this.stateID = stateID;
	}

	public static GameplayState getInstance()
	{
		if(gp == null)
		{
			gp = new GameplayState(2);
		}
		return gp;
	}

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException 
	{ 
		tm = new TiledMap("Data" + File.separator + "Maps" + File.separator + "start map.tmx");
		world = new World();

		//wizard image arrays
		wizWalk = new Image[3];
		wizWalk[0] = new Image("Data" + File.separator + "Character Sprites" + File.separator + "Walking 1.png");
		wizWalk[1] = new Image("Data" + File.separator + "Character Sprites" + File.separator + "Walking 1.5.png");
		wizWalk[2] = new Image("Data" + File.separator + "Character Sprites" + File.separator + "Walking 2.png");
		
		wizStill = new Image[2];
		wizStill[0] = new Image("Data" + File.separator + "Character Sprites" + File.separator + "wizard.png");
		wizStill[1] = new Image("Data" + File.separator + "Character Sprites" + File.separator + "Casting Spell.png");
		
		//monster image arrays
		
		dragon = new Image[5];
		dragon[0] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "dragon.png");
		dragon[1] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "dragon-02.png");
		dragon[2] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "dragon-03.png");
		dragon[3] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "dragon-04.png");
		dragon[4] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "dragon-05.png");
		
		golem = new Image[5];
		golem[0] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "golem-01.png");
		golem[1] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "golem-02.png");
		golem[2] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "golem-03.png");
		golem[3] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "golem-04.png");
		golem[4] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "golem-05.png");
		
		troll = new Image[3];
		troll[0] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "ogre-01.png");
		troll[1] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "ogre-02.png");
		troll[2] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "ogre-03.png");
		
		gateKeep = new Image[3];
		gateKeep[0] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "gatekeep.png");
		gateKeep[1] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "gatekeep-02.png");
		gateKeep[2] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "gatekeep-03.png");
		
//		skeleBow = new Image[5];
//		skeleBow[0] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skelebow-01.png");
//		skeleBow[1] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skelebow-02.png");
//		skeleBow[2] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skelebow-03.png");
//		skeleBow[3] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skeleshoot-01.png");
//		skeleBow[4] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skeleshoot-02.png");
//		
//		skeleSword = new Image[5];
//		skeleSword[0] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skelesword-01.png");
//		skeleSword[1] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skelesword-02.png");
//		skeleSword[2] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skelesword-03.png");
//		skeleSword[3] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skeleslash-01.png");
//		skeleSword[4] = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "skeleslash-02.png");
//		
		systemManager = world.getSystemManager();
		animationRenderSystem = systemManager.setSystem(new AnimationRenderSystem());
		inputSystem = systemManager.setSystem(new InputSystem(gc));
		physicsSystem = systemManager.setSystem(new PhysicsSystem());
		renderingSystem = systemManager.setSystem(new RenderingSystem());
		mapRenderSystem = systemManager.setSystem(new MapRenderSystem());
		spellRenderSystem = systemManager.setSystem(new SpellRenderSystem());
		HUDRenderSystem = systemManager.setSystem(new HUDRenderSystem(gc));
		systemManager.initializeAll();

		Entity player = world.createEntity();
		Entity drag = world.createEntity();
		Entity ogre = world.createEntity();
		Entity gateKeeper = world.createEntity();
		Entity gol = world.createEntity();
		Entity map = world.createEntity();
		
		player.addComponent(new Stats(100,100,5,5,999,999,999));
		player.addComponent(new Velocity(0,0,false));
		player.addComponent(new Position(594,309));
		player.addComponent(new Player());
		player.addComponent(new Animations(wizWalk, 200, true));
		player.addComponent(new Collides(new Rectangle(594,309,90,95)));
		player.addComponent(new Sprite(wizStill));
		player.setGroup("PLAYER");
		player.refresh();

		map.addComponent(new Map("start map.tmx"));
		map.setGroup("MAP");
		map.refresh();
		
		drag.addComponent(new Stats(500,500,200,100,0,0,0));
		drag.addComponent(new Position(540,400));
		drag.addComponent(new Sprite(dragon));
		drag.addComponent(new Animations(dragon, 200, true));
		drag.addComponent(new Collides(null));
		//drag.refresh();
		
		
		

	}
	public Entity makeSpell(Spells s)
	{
		spell = world.createEntity();
		
		spell.addComponent(new Spell(s));
		spell.addComponent(new Velocity(0,0,false));
		spell.refresh();
		
		return spell;
	}
	

	public float getYOffset()
	{
		return yOffset;
	}
	
	public void setYOffset(float newy)
	{
		yOffset = newy;
	}
	
	public void moveY(float change)
	{
		yOffset += change;
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
	{	// TODO Auto-generated method stub
		mapRenderSystem.process();
		renderingSystem.process();
		animationRenderSystem.process();		
		spellRenderSystem.process();
		HUDRenderSystem.process();

	}


	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)	throws SlickException 
	{
		world.loopStart();
		inputSystem.process();
		physicsSystem.process();
		world.setDelta(delta);

	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}
