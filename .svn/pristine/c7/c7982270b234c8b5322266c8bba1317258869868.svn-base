package components;
import com.artemis.Component;

import util.Element;
import util.Spells;

public class Player extends Component
{
	private Element[] elements; 
	private Spells[] spells;
	private boolean needList;
	private boolean regAtk;
	private Spells spellCast;
	
	public Player()
	{
		needList = false;
		
		elements = new Element[3];
		spells = new Spells[3];
		for(int i = 0; i < 3; i++)
		{
			elements[i] = Element.EMPTY;
			spells[i] = Spells.EMPTY;
		}
	}
	
	public void getList()
	{
		needList = true;
	}
	public void delList()
	{
		needList = false;
	}
	public boolean yesList()
	{
		return needList;
	}
	
	public Element getElement(int index)
	{
		return elements[index];
	}
	
	public Spells getSpell(int index)
	{
		return spells[index];
	}
	
	public void spellCast(Spells s)
	{
		spellCast = s;
	}
	
	public Spells getSpellCast()
	{
		return spellCast;
	}
	
	public void pushElement(Element e)
	{
		elements[0] = elements[1];
		elements[1] = elements[2];
		elements[2] = e;
	}
	
	public void pushSpell(Spells s)
	{
		spells[2] = spells[1];
		spells[1] = spells[0];
		spells[0] = s;
	}

	public boolean isRegAtk() {
		return regAtk;
	}

	public void setRegAtk(boolean regAtk) 
	{
		this.regAtk = regAtk;
	}
	
	
}
