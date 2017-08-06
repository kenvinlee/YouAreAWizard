package gamestates;

import java.io.File;
import java.util.ArrayList;

import main.YouAreAWizard;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class CreditState extends BasicGameState
{
	private int stateID = 5;
	private Image background;
	private Image credits;
	
	private ArrayList<Image> options;
	private Image option;
	private int startHeight, startWidth;
	private int numOptions;
	private float scaleStep = 0.0001f;
	private float[] scales;
	private Input input;
	private boolean mousePressed;
	private int mouseX, mouseY;

	public CreditState(int stateID)
	{
		this.stateID = stateID;
	}
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException 
	{
		// TODO Auto-generated method stub
		background = new Image("Data" + File.separator + "HUD" + File.separator + "menu art.png");
		credits = new Image("Data" + File.separator + "HUD" + File.separator + "credits book.png");
		option = new Image("Data" + File.separator + "HUD" + File.separator + "return button.png");
		numOptions = 1;
		scales = new float[numOptions];
		startHeight = 600;
		startWidth = 1050;
		options = new ArrayList<Image>();
		
		for(int i = 0; i < numOptions; i++)
		{
			options.add(option.getSubImage(0, i * option.getHeight()/numOptions, option.getWidth(), option.getHeight()/numOptions));
			scales[i] = 1f;
		}
		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics g)throws SlickException 
	{
		// TODO Auto-generated method stub
		background.draw(0,0);
		credits.draw(175, 150);
		
		for(int i = 0; i < numOptions; i++)
		{
			options.get(i).draw(startWidth, i * options.get(0).getHeight() + startHeight, scales[i]);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)throws SlickException 
	{
		// TODO Auto-generated method stub
		input = gc.getInput();
		mousePressed = input.isMousePressed(input.MOUSE_LEFT_BUTTON);
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		
		for(int i = 0; i < numOptions; i++)
		{
			boolean isSelected = (mouseY > i * options.get(0).getHeight() + startHeight && mouseY < (i+1) * options.get(0).getHeight()
								+ startHeight && mouseX > startWidth && mouseX < startWidth + option.getWidth());
			
			if(isSelected && scales[i] < 1.05f)
			{
				scales[i] += scaleStep * delta;
			}
			else if(!isSelected && scales[i] > 1.00f)
			{
				scales[i] -= scaleStep * delta;
			}
			else;
	
		}
		
		if (isClicked (gc, 0))
		{
			sbg.enterState(YouAreAWizard.MAINMENUSTATE);
		}
		
	}

	protected boolean isClicked(GameContainer gc, int index)
	{
		return mousePressed &&(mouseY > index * options.get(0).getHeight() + startHeight && mouseY < (index+1) * options.get(0).getHeight()
				+ startHeight && mouseX > startWidth && mouseX < startWidth + option.getWidth());
	}
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return stateID;
	}

}
