import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

public class Rectangle {
	Raylib rlj = new Raylib();
	
	final int width = 7;
	final int height = 70;
	int x;
	int y;
	
	
	public void draw() {
		rlj.shapes.DrawRectangle(this.x, this.y, this.width, this.height, Color.WHITE);
	}
}
