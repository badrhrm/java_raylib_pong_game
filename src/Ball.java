import com.raylib.java.Raylib;
import com.raylib.java.core.Color;

class Ball {
		Raylib rlj = new Raylib();
		
		final int RADIUS = 7;
		int x;
		int y;
		
		// to generate either 1 or -1
        int randomValue = Math.random() < 0.5 ? 1 : -1;
        
		int movementX = 5 * generateNumber();
		int movementY = 5 * generateNumber();
		
		void draw() {
			rlj.shapes.DrawCircle(this.x, this.y, this.RADIUS, Color.WHITE);
		}
		
		void move() {
			this.x += movementX;
			this.y += movementY;
		}
		
		void resetPosition() {
			this.x = Main.WINDOW_WIDTH/2;
			this.y = Main.WINDOW_HEIGHT/2;
			movementX *= generateNumber();
			movementY *= generateNumber();
		}
		
		private int generateNumber() {
			// to generate either 1 or -1
	        return Math.random() < 0.5 ? 1 : -1;
		}
	}
