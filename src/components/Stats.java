package components;
import java.util.Random;

import com.artemis.Component;

//TODO

public class Stats extends Component
{
	private float hp;
	private float attack;
	private float armour;
	private int fire, water, lightning;
	private float maxHP;
	
	//attack is base damage, spell damage is calculated by modifying it.
	
	public Stats(float hp, float max, float attack, float armour, int f, int w, int l)
	{
		this.hp = hp;
		maxHP = max;
		this.attack = attack;
		this.armour = armour;
		fire = f;
		water = w;
		lightning = l;
	}
	
	/* dunno where this goes
	public void monsterLoot()
	{
		int item, quant;
		Random r = new Random();
		item = r.nextInt(3);
		
		//quant is essentially droprate. Might need to tweak.
		quant = r.nextInt(4);
		
		if (item == 0)
			fire = quant;
		else if (item == 1)
			water = quant;
		else if (item == 2)
			lightning = quant;
		
	}*/
	
	public void updateHealth(int healthChange)
	{
		if (hp + healthChange <= maxHP)
			hp += healthChange;
		else if (hp + healthChange > maxHP)
			hp = 100;
	}
	
	public void setHP(int hp)
	{
		this.hp = hp;
	}
	
	public void setAtk(int atk)
	{
		attack = atk;
	}
	
	public void setMaxHP(int max)
	{
		maxHP = max;
	}
	
	public void setArmour(int arm)
	{
		armour = arm;
	}
	
	public void setFireScroll(int num)
	{
		fire = num;
	}
	
	public void setWaterScroll(int num)
	{
		water = num;
	}
	public void setLightningScroll(int num)
	{
		lightning = num;
	}
	
	public float getHP()
	{
		return hp;
	}
	
	public float getAttack()
	{
		return attack;
	}
	public float getArmour()
	{
		return armour;
	}

	public int getFireScroll()
	{
		return fire;
	}
	
	public int getWaterScroll()
	{
		return water;
	}
	public int getLightningScroll()
	{
		return lightning;
	}
	public void useLight(int x)
	{
		lightning -= x;
	}
	public void useFire(int x)
	{
		fire -= x;
	}
	public void useWat(int x)
	{
		water -= x;
	}
	public float getMaxHP() 
	{
		return maxHP;
	}

}
