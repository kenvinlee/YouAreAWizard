package main;

import java.util.ArrayList;

import gamestates.*;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class YouAreAWizard extends StateBasedGame
{
	public static final int MAINMENUSTATE = 1;
	public static final int GAMEPLAYSTATE = 2;
	public static final int GAMEPAUSESTATE = 3;
	public static final int CREDITSTATE = 4;
	public static final int INSTRUCTIONSTATE = 5;
	
	
	
	public YouAreAWizard() throws SlickException
	{
		super ("You Are A Wizard!");
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new CreditState(CREDITSTATE));
		this.addState(new InstructionState(INSTRUCTIONSTATE));
        this.addState(GameplayState.getInstance());
        this.enterState(MAINMENUSTATE);
	}


	//Initialize all states here
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MAINMENUSTATE).init(gc, this);
		
	}	
	
	public static void main(String[] args) throws SlickException
	{
		AppGameContainer app = new AppGameContainer(new YouAreAWizard());
		app.setDisplayMode(1280, 720, false);
		app.setTargetFrameRate(120);
		app.setAlwaysRender(true);
		app.start();
	}
	
	private ArrayList<Shape> convertMap(TiledMap tm)
	{
		ArrayList<Shape> shapes = new ArrayList<Shape>();
		for(int i = 0; i < tm.getHeight(); i++)
		{
			for(int j = 0; j < tm.getWidth(); j++)
			{
				if(tm.getTileId(i, j, 1) != 0)
				{
					shapes.add(new Rectangle(i*tm.getTileHeight(), j * tm.getTileWidth(), tm.getTileHeight(), tm.getTileWidth()));
				}
			}
		}
		
		return shapes;
	}
	
}
