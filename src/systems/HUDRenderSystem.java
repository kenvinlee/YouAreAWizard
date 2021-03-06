package systems;

import java.io.File;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.artemis.EntityProcessingSystem;
import com.artemis.ComponentMapper;
import com.artemis.Entity;

import components.Player;
import components.Stats;
import util.Element;
import util.Spells;


public class HUDRenderSystem extends EntityProcessingSystem
{
	private ComponentMapper<Stats> stats;
	private ComponentMapper<Player> player;
	private GameContainer gc;
	private Image top;
	private Image bot;
	private Image agility, coneoffire, disintegrate, ghost, lightningblade, lightning, melt, orbofdestruction, renew, temper;
	private Image firescroll, lightningscroll, waterscroll;
	private Image healthBar; //draw at 435, 29 lower than the y it's drawn at.
	private Image list;

	public HUDRenderSystem(GameContainer gc) 
	{
		super(Player.class, Stats.class);
		this.gc = gc;
	}

	/*
	 * 940x36 coordinate of first spell icon location
	 * 1050x36 coordinate of second spell icon location
	 * 1160x36 coordinate of third spell icon location
	 * */

	public void initialize()
	{
		stats = new ComponentMapper<Stats>(Stats.class, world);
		player = new ComponentMapper<Player>(Player.class, world);

		try {
			top = new Image("Data" + File.separator + "HUD" + File.separator + "top HUD.png");
			bot = new Image("Data" + File.separator + "HUD" + File.separator + "bottom HUD.png");

			agility = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "agility.png");	
			coneoffire = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "cone of fire.png");
			disintegrate = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "disintegrate.png");
			ghost = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "ghost.png");
			lightningblade = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "lightning blade.png");
			lightning = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "lightning.png");
			melt = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "melt.png");
			orbofdestruction = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "orb of destruction.png");
			renew = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "renew.png");
			temper = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "spell icons" + File.separator + "temper.png");
			firescroll = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "scrolls" + File.separator + "fire scroll.png");
			lightningscroll = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "scrolls" + File.separator + "lightning scroll.png");
			waterscroll = new Image("Data" + File.separator + "Monster Sprites" + File.separator + "scrolls" + File.separator + "water scroll.png");
			healthBar = new Image("Data" + File.separator + "HUD" + File.separator + "health bar.png");

			list = new Image("Data" + File.separator + "HUD" + File.separator + "spell list.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public void process(Entity e)
	{
		Graphics g = gc.getGraphics();

		top.draw(0,0);
		bot.draw(0, 600);

		for(int i = 0; i < 3; i++)
		{
			switch(player.get(e).getSpell(i))
			{
			case WWL: ghost.draw(938+110*i, 636);break;
			case WWF: temper.draw(938+110*i, 636);break;
			case WWW: renew.draw(938+110*i, 636);break;
			case LLW: agility.draw(938+110*i, 636);break;
			case LLL: lightning.draw(938+110*i, 636);break;
			case LLF: lightningblade.draw(938+110*i, 636);break;
			case FFW: melt.draw(938+110*i, 636);break;
			case FFF: coneoffire.draw(938+110*i, 636);break;
			case FFL: disintegrate.draw(938+110*i, 636);break;
			case FWL: orbofdestruction.draw(938+110*i, 636);break;
			}

			switch(player.get(e).getElement(i))
			{
			case FIRE: firescroll.draw(540 + 110*i, 570);break;
			case LIGHTNING: lightningscroll.draw(540+ 110*i, 570);break;
			case WATER: waterscroll.draw(540 + 110*i, 570);break;
			}
		}

		if (player.get(e).yesList())
		{
			list.draw(1160,151);
		}

		g.drawString(""+stats.get(e).getFireScroll(), 80, 640);
		g.drawString(""+stats.get(e).getLightningScroll() , 175, 640);
		g.drawString(""+stats.get(e).getWaterScroll(), 265, 640);

		healthBar.draw(435,629,(healthBar.getWidth()*(float)(stats.get(e).getHP()/stats.get(e).getMaxHP())), healthBar.getHeight());

	}
}
