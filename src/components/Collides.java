package components;
import com.artemis.Component;
import org.newdawn.slick.geom.*;

import java.util.ArrayList;

public class Collides extends Component{
	private Shape shapes;
	
	public Collides(Shape occupies)
	{
		shapes = occupies;
	}
	
	public Shape getShape()
	{
		return shapes;
	}
	
	
}
