package components;
import java.io.File;

import main.MyAnimation;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import util.Spells;

import com.artemis.Component;
import components.*;

//TODO

public class Spell extends Component
{
	private final int GHOST = 1;
	private final int TEMPER = 2;
	private final int REPLENISH = 3;
	private final int AGILITY = 4;
	private final int LIGHTNING = 5;
	private final int LIGHTNINGSLASH = 6;
	private final int MELT = 7;
	private final int CONEOFFIRE = 8; //ANNIERIPOFFSPELL
	private final int DISINTEGRATE = 9;
	private final int ORBOFDESTRUCTION = 10;

	private Image[] disintegrate;
	private Image[] lightning;
	private Image[] lightningBlade;
	private Image[] coneOfFire;
	private Image[] deathOrb;
	private Image[] melt;

	private MyAnimation spellCast;
	
	private int spellID;
	private int damage;
	private int buff;
	private int timer;
	private boolean used;


	/*
	 *1. Ghost – WWL 
	 *2. Temper - WWF
	 *3. Replenish – WWW		
	 *4. Agility – LLW 
	 *5. Lightning – LLL
	 *6. Lightning Slash – LLF
	 *7. Melt - FFW
	 *8. Cone of Fire – FFF
	 *9. Disintegrate – FFL
	 *10. Orb of Destruction - FWL					
	 */

	public Spell(Spells s)
	{
		used = false;

		//spell arrays
		try {
			disintegrate = new Image[4];
			disintegrate[0] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Disintegrate 1.png");
			disintegrate[1] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Disintegrate 2.png");
			disintegrate[2] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Disintegrate 3.png");
			disintegrate[3] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Disintegrate 4.png");

			lightning = new Image[3];
			lightning[0] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Lightning Bolt.png");
			lightning[1] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Lightning Bolt 1.png");
			lightning[2] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Lightning Bolt 2.png");

			lightningBlade = new Image[2];
			lightningBlade[0] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Lightning Blade 1.png");
			lightningBlade[1] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Lightning Blade 2.png");

			coneOfFire = new Image[8];
			coneOfFire[0] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Cone of Fire 1 (1).png");
			coneOfFire[1] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Cone of Fire 1 (2).png");
			coneOfFire[2] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Cone of Fire 1 (3).png");
			coneOfFire[3] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Cone of Fire 1 (4).png");
			coneOfFire[4] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Cone of Fire 1 (5).png");
			coneOfFire[5] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Cone of Fire 1 (6).png");
			coneOfFire[6] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Cone of Fire 1 (7).png");
			coneOfFire[7] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Cone of Fire 1 (8).png");

			deathOrb = new Image[3];
			deathOrb[0] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Death Orb 1.png");
			deathOrb[1] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Death Orb 2.png");
			deathOrb[2] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "Death Orb 3.png");
			
			melt = new Image[2];
			melt[0] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "melt 1.png");
			melt[1] = new Image("Data" + File.separator + "Spell Sprites" + File.separator + "melt 2.png");
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		switch(s)
		{
		case WWL: 
			break;
		case WWF: buff = 3;
		break;
		case WWW: buff = 4;
		break;
		case LLW: buff = 5;
		break;
		case LLL: damage = 30;
		spellCast = new MyAnimation(lightning, 100, true);
		break;
		case LLF: damage = 50;
		spellCast = new MyAnimation(lightningBlade, 200, true);
		break;
		case FFW: /*de*/buff = 10;
		spellCast = new MyAnimation(melt, 200, true);
		break;
		case FFF: damage = 20;
		spellCast = new MyAnimation(coneOfFire, 50, true);
		break;
		case FFL: damage/*over time*/ = 10;
		spellCast = new MyAnimation(disintegrate, 10, true);
		break;
		case FWL: damage = 40;
		spellCast = new MyAnimation(deathOrb, 1000, true);
		break;
		case EMPTY:
			
		}

	}


	public MyAnimation getSpellCast()
	{
		return spellCast;
	}


	public int getDamage()
	{
		return damage;
	}

	public int getBuff()
	{
		return buff;
	}

	public void setCD(int CD)
	{
		CD = timer;
	}

	public void beenUsed()
	{
		used = true;
	}

}
