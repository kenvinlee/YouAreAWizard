package systems;

import gamestates.GameplayState;
import components.Player;
import components.Spell;
import components.Stats;
import components.Velocity;
import components.Position;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import util.Element;
import util.Spells;

import com.artemis.EntityProcessingSystem;
import com.artemis.Entity;
import com.artemis.ComponentMapper;



public class InputSystem extends EntityProcessingSystem{ 	
	private ComponentMapper<Player> player;
	private ComponentMapper<Velocity> vel;
	private ComponentMapper<Position> pos;
	private ComponentMapper<Stats> stat;
	private ComponentMapper<Spell> spell;
	private GameContainer gc;
	private boolean lastQ, lastE, lastW, lastR;

	public InputSystem(GameContainer gc)
	{
		super(Player.class, Velocity.class, Position.class);
		this.gc = gc;
	}

	public void initialize()
	{
		player = new ComponentMapper<Player>(Player.class, world);
		vel = new ComponentMapper<Velocity>(Velocity.class, world);
		pos = new ComponentMapper<Position>(Position.class, world);
		stat = new ComponentMapper<Stats>(Stats.class, world);
		spell = new ComponentMapper<Spell>(Spell.class, world);
	}

	public void process(Entity e)
	{
		Input input = gc.getInput();
		vel.get(e).setMagnitude(0);

		//changed to else ifs to detect movement for animation.
		if(input.isKeyDown(Input.KEY_DOWN))
		{
			vel.get(e).setMagnitude(0.5f);
			vel.get(e).setDirection((float)Math.PI/2);
			pos.get(e).setTheta(180);
			pos.get(e).makeMove();
		}

		else if(input.isKeyDown(Input.KEY_UP))
		{
			vel.get(e).setMagnitude(0.5f);
			vel.get(e).setDirection((float)-Math.PI/2);
			pos.get(e).setTheta(0);
			pos.get(e).makeMove();
		}


		else if(input.isKeyDown(Input.KEY_LEFT))
		{
			vel.get(e).setMagnitude(0.5f);
			vel.get(e).setDirection((float)Math.PI);
			pos.get(e).setTheta(-90);
			pos.get(e).makeMove();
		}


		else if(input.isKeyDown(Input.KEY_RIGHT))
		{
			vel.get(e).setMagnitude(0.5f);
			vel.get(e).setDirection(0);
			pos.get(e).setTheta(90);
			pos.get(e).makeMove();
		}
		else
			pos.get(e).detectMove();
		
		if(input.isKeyDown(Input.KEY_Z))
		{
			player.get(e).getList();
		}
		else
			player.get(e).delList();

		if(input.isKeyDown(Input.KEY_Q) && !lastQ)
		{
			player.get(e).pushElement(Element.FIRE);
		}

		if(input.isKeyDown(Input.KEY_W) && !lastW)
		{
			player.get(e).pushElement(Element.LIGHTNING);
		}

		if(input.isKeyDown(Input.KEY_E) && !lastE)
		{
			player.get(e).pushElement(Element.WATER);
		}

		if(input.isKeyDown(Input.KEY_R) && !lastR)
		{
			Element e1 = player.get(e).getElement(0);
			Element e2 = player.get(e).getElement(1);
			Element e3 = player.get(e).getElement(2);

			if(e1 == Element.WATER && e2 == Element.WATER && stat.get(e).getWaterScroll() >= 2)
			{
				switch(e3)
				{
				case LIGHTNING:
					if (stat.get(e).getLightningScroll() >= 1)
					{	
						player.get(e).pushSpell(Spells.WWL);
						stat.get(e).useLight(1);
						stat.get(e).useWat(2);
					}

					break;
				case FIRE:
					if (stat.get(e).getFireScroll() >= 1)
					{	
						player.get(e).pushSpell(Spells.WWF);
						stat.get(e).useWat(2);
						stat.get(e).useFire(1);
					}
					break;
				case WATER:
					if (stat.get(e).getWaterScroll() >= 3)
					{	
						player.get(e).pushSpell(Spells.WWW);
						stat.get(e).useWat(3);
					}
					break;
				}

			}
			if(e1 == Element.WATER && e2 == Element.LIGHTNING && stat.get(e).getLightningScroll() >= 1 && stat.get(e).getWaterScroll() >= 1)
			{//I need to keep working from here.
				switch(e3)
				{
				case LIGHTNING:
					if(stat.get(e).getLightningScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.LLW);
						stat.get(e).useLight(2);
						stat.get(e).useWat(1);
					}
					break;
				case FIRE:
					if(stat.get(e).getFireScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.FWL);
						stat.get(e).useFire(1);
						stat.get(e).useWat(1);
						stat.get(e).useLight(1);
					}break;
				case WATER:
					if(stat.get(e).getWaterScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.WWL);
						stat.get(e).useWat(2);
						stat.get(e).useLight(1);
					}
					break;
				}

			}
			if(e1 == Element.WATER && e2 == Element.FIRE && stat.get(e).getFireScroll() >= 1 && stat.get(e).getWaterScroll() >= 1)
			{
				switch(e3)
				{
				case LIGHTNING:
					if(stat.get(e).getLightningScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.FWL);
						stat.get(e).useFire(1);
						stat.get(e).useWat(1);
						stat.get(e).useLight(1);
					}
					break;
				case FIRE:
					if(stat.get(e).getFireScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.FFW);
						stat.get(e).useFire(2);
						stat.get(e).useWat(1);
					}
					break;
				case WATER:
					if(stat.get(e).getWaterScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.WWF);
						stat.get(e).useWat(2);
						stat.get(e).useFire(1);
					}
					break;
				}

			}


			if(e1 == Element.LIGHTNING && e2 == Element.LIGHTNING && stat.get(e).getLightningScroll() >= 2)
			{
				switch(e3)
				{
				case LIGHTNING:
					if(stat.get(e).getLightningScroll() >= 3)
					{
						player.get(e).pushSpell(Spells.LLL);					
						stat.get(e).useLight(3);
					} 
					break;
				case FIRE:
					if(stat.get(e).getFireScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.LLF);
						stat.get(e).useFire(1);
						stat.get(e).useLight(2);
					}
					break;
				case WATER:
					if(stat.get(e).getWaterScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.LLW);
						stat.get(e).useWat(1);
						stat.get(e).useLight(2);
					}
					break;
				}
			}
			if(e1 == Element.LIGHTNING && e2 == Element.WATER && stat.get(e).getLightningScroll() >= 1 && stat.get(e).getWaterScroll() >= 1)
			{
				switch(e3)
				{
				case LIGHTNING:
					if(stat.get(e).getLightningScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.LLW);
						stat.get(e).useLight(2);
						stat.get(e).useWat(1);
					}
					break;
				case FIRE:
					if(stat.get(e).getFireScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.FWL);
						stat.get(e).useFire(1);
						stat.get(e).useWat(1);
						stat.get(e).useLight(1);
					}
					break;
				case WATER:
					if(stat.get(e).getWaterScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.WWL);
						stat.get(e).useWat(2);
						stat.get(e).useLight(1);
					}
					break;
				}
			}
			if(e1 == Element.LIGHTNING && e2 == Element.FIRE && stat.get(e).getLightningScroll() >= 1 && stat.get(e).getFireScroll() >= 1)
			{
				switch(e3)
				{
				case LIGHTNING:
					if(stat.get(e).getLightningScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.LLF);
						stat.get(e).useFire(1);
						stat.get(e).useLight(2);
					}
					break;
				case FIRE:
					if(stat.get(e).getFireScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.FFL);
						stat.get(e).useFire(2);
						stat.get(e).useLight(1);
					}
					break;
				case WATER:
					if(stat.get(e).getWaterScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.FWL);
						stat.get(e).useFire(1);
						stat.get(e).useWat(1);
						stat.get(e).useLight(1);
					}
					break;
				}
			}

			if(e1 == Element.FIRE && e2 == Element.FIRE && stat.get(e).getFireScroll() >= 2)
			{
				switch(e3)
				{
				case LIGHTNING:
					if(stat.get(e).getLightningScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.FFL);
						stat.get(e).useFire(2);
						stat.get(e).useLight(1);
					}
					break;
				case FIRE:
					if(stat.get(e).getFireScroll() >= 3)
					{
						player.get(e).pushSpell(Spells.FFF);
						stat.get(e).useFire(3);
					}
					break;
				case WATER:
					if(stat.get(e).getWaterScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.FFW);					
						stat.get(e).useFire(2);
						stat.get(e).useWat(1);
					}
					break;
				}
			}

			if(e1 == Element.FIRE && e2 == Element.LIGHTNING && stat.get(e).getLightningScroll() >= 1 && stat.get(e).getFireScroll() >= 1)
			{
				switch(e3)
				{
				case LIGHTNING:
					if(stat.get(e).getLightningScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.LLF);
						stat.get(e).useFire(1);
						stat.get(e).useLight(2);
					}
					break;
				case FIRE:
					if(stat.get(e).getFireScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.FFL);
						stat.get(e).useFire(2);
						stat.get(e).useLight(1);
					}
					break;
				case WATER:
					if(stat.get(e).getWaterScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.FWL);
						stat.get(e).useFire(1);
						stat.get(e).useWat(1);
						stat.get(e).useLight(1);
					}
					break;
				}
			}

			if(e1 == Element.FIRE && e2 == Element.WATER && stat.get(e).getWaterScroll() >= 1 && stat.get(e).getFireScroll() >= 1)
			{
				switch(e3)
				{
				case LIGHTNING:
					if(stat.get(e).getLightningScroll() >= 1)
					{
						player.get(e).pushSpell(Spells.FWL);
						stat.get(e).useFire(1);
						stat.get(e).useWat(1);
						stat.get(e).useLight(1);
					}
					break;
				case FIRE:
					if(stat.get(e).getFireScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.FFW);
						stat.get(e).useFire(2);
						stat.get(e).useWat(1);
					}
					break;
				case WATER:
					if(stat.get(e).getWaterScroll() >= 2)
					{
						player.get(e).pushSpell(Spells.WWF);
						stat.get(e).useWat(2);
						stat.get(e).useFire(1);
					}
					break;
				}
			}

		}



		lastQ = input.isKeyDown(Input.KEY_Q);
		lastW = input.isKeyDown(Input.KEY_W);
		lastE = input.isKeyDown(Input.KEY_E);
		lastR = input.isKeyDown(Input.KEY_R);
		
		if(input.isKeyDown(Input.KEY_A))
		{
			pos.get(e).makeCast();
			player.get(e).setRegAtk(true);
		}
		else if(input.isKeyDown(Input.KEY_S))
		{
			pos.get(e).makeCast();
			GameplayState.getInstance().makeSpell(player.get(e).getSpell(0));
		}
		else if(input.isKeyDown(Input.KEY_D))
		{
			pos.get(e).makeCast();
			GameplayState.getInstance().makeSpell(player.get(e).getSpell(1));
		}
		else if(input.isKeyDown(Input.KEY_F))
		{
			pos.get(e).makeCast();
			GameplayState.getInstance().makeSpell(player.get(e).getSpell(2));
		}
		else 
		{
			pos.get(e).stopCast();
			player.get(e).setRegAtk(false);
		}
	}
}
