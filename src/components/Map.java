package components;

import java.io.File;
import java.util.ArrayList;

import com.artemis.Component;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.*;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;

public class Map extends Component {
	private TiledMap tm;
	private final int TILE_SIZE = 40;
	private ArrayList<Shape> trees;
	private ArrayList<Shape> rocks;
	private ArrayList<Shape> total;
	private ArrayList<Shape> walls;
	
	public Map(String s) throws SlickException
	{
		tm = new TiledMap("Data" + File.separator + "Maps" + File.separator + s);
		trees = new ArrayList<Shape>();
		rocks = new ArrayList<Shape>();
		total = new ArrayList<Shape>();
		walls = new ArrayList<Shape>();
		loadMap();
	}
	
	public TiledMap getTiledMap()
	{
		return tm;
	}
	
	public ArrayList<Shape> getTrees()
	{
		return trees;
	}
	
	public ArrayList<Shape> getRocks()
	{
		return rocks;
	}
	
	public ArrayList<Shape> getAll()
	{
		return total;
	}
	
	private void loadMap()
	{
		for(int i = 0; i < tm.getWidth(); i++)
		{
			for(int j = 0; j < tm.getHeight(); j++)
			{
				String s = tm.getTileProperty(tm.getTileId(i, j, 1), "type", "durr");

				if(s.equals("tree1"))
				{
					trees.add(new Rectangle(i*TILE_SIZE, j*TILE_SIZE, 2*TILE_SIZE, 2*TILE_SIZE));
					total.add(new Rectangle(i*TILE_SIZE, j*TILE_SIZE, 2*TILE_SIZE, 2*TILE_SIZE));
				}
				if(s.equals("rock1"))
				{
					rocks.add(new Rectangle(i*TILE_SIZE, j*TILE_SIZE, 2*TILE_SIZE, 2*TILE_SIZE));
					total.add(new Rectangle(i*TILE_SIZE, j*TILE_SIZE, 2*TILE_SIZE, 2*TILE_SIZE));
				}
				
				String layer1 = tm.getTileProperty(tm.getTileId(i,j,0), "type", "durr");
				if(s.equals("wall"))
				{
					walls.add(new Rectangle(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE));
					total.add(new Rectangle(i*TILE_SIZE, j*TILE_SIZE, TILE_SIZE, TILE_SIZE));
				}
					
			}
		}
	}
}
