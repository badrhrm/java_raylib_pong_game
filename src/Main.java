import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.core.input.Keyboard;


public class Main {
	
	final static int WINDOW_WIDTH = 900;
	final static int WINDOW_HEIGHT = 600;
	
	static int LEFT_SIDE_SCORE = 0;
	static int RIGHT_SIDE_SCORE = 0;
	
	public static void rectangleCollisionWithWallLogic(Rectangle rectangle) {
		if (rectangle.y <= 0 ) {
			rectangle.y = 0;
    	}
    	if (rectangle.y + rectangle.height >= Main.WINDOW_HEIGHT ) {
    		rectangle.y = Main.WINDOW_HEIGHT-rectangle.height;
    	}
	}

    public static void main(String[] args) {
    	Raylib rlj = new Raylib();
    	Ball ball = new Ball();
    	Rectangle rightRectangle = new Rectangle();
    	Rectangle leftRectangle = new Rectangle();
    	
    	ball.x = Main.WINDOW_WIDTH/2;
    	ball.y = Main.WINDOW_HEIGHT/2;
        
    	
        rightRectangle.x = (int) (Main.WINDOW_WIDTH - Main.WINDOW_WIDTH * 0.02);
        rightRectangle.y = (int) (Main.WINDOW_HEIGHT/2 - rightRectangle.height/2);
        leftRectangle.x = (int) (Main.WINDOW_WIDTH - Main.WINDOW_WIDTH * 0.98);
        leftRectangle.y = (int) (Main.WINDOW_HEIGHT/2 - leftRectangle.height/2);
        
        final int scoresFontSize = 20;
        final int leftSideScoreX = (int) (Main.WINDOW_WIDTH - Main.WINDOW_WIDTH * 0.95);
        final int leftSideScoreY = (int) (Main.WINDOW_HEIGHT * 0.05);
        final int rightSideScoreX = (int) (Main.WINDOW_WIDTH / 2 + Main.WINDOW_WIDTH * 0.05);
        final int rightSideScoreY = (int) (Main.WINDOW_HEIGHT * 0.05);
        
        // creating window 
        rlj.core.InitWindow(Main.WINDOW_WIDTH, Main.WINDOW_HEIGHT, "Raylib-J Test");
        rlj.core.SetTargetFPS(60);
        
        // game loop
        while (!rlj.core.WindowShouldClose()) {
        	
        	// even handling / user input 
        	if (rlj.core.IsKeyDown(Keyboard.KEY_UP)) {
        		rightRectangle.y -= 7;
        	}
        	if (rlj.core.IsKeyDown(Keyboard.KEY_DOWN)) {
        		rightRectangle.y += 7;
        	}
        	if (rlj.core.IsKeyDown(Keyboard.KEY_I)) {
        		leftRectangle.y -= 7;
        	}
        	if (rlj.core.IsKeyDown(Keyboard.KEY_K)) {
        		leftRectangle.y += 7;
        	}
        	
        	
        	// updating positions
        	ball.move();
        	
        	
        	
        	// handling collisions 
        	
			// collision of the ball with the rectangles
			// left rectangle
			if ((ball.x - ball.RADIUS <= leftRectangle.x + leftRectangle.width)
					&& (ball.y >= leftRectangle.y && ball.y <= leftRectangle.y + leftRectangle.height)) {
				ball.movementX *= -1;
			}
			// right rectangle
			else if ((ball.x + ball.RADIUS >= rightRectangle.x - rightRectangle.width)
					&& (ball.y >= rightRectangle.y && ball.y <= rightRectangle.y + rightRectangle.height)) {
				ball.movementX *= -1;
			}
			
			// handling collision of the ball with the borders and updating score 
			// vertical walls
			if( (ball.y - ball.RADIUS <= 0) || (ball.y + ball.RADIUS >= Main.WINDOW_HEIGHT) ) {
				ball.movementY *= -1;
			}
			// ball hits left side wall
			else if ( (ball.x - ball.RADIUS <= 0)) {
				Main.RIGHT_SIDE_SCORE += 1;
				ball.resetPosition();
			} 
			// ball hits right side wall
			else if ( (ball.x + ball.RADIUS >= Main.WINDOW_WIDTH) ) {
				Main.LEFT_SIDE_SCORE  += 1;
				ball.resetPosition();
			}
			
			// not allowing rectangle to cross vertical walls 
			rectangleCollisionWithWallLogic(leftRectangle);
			rectangleCollisionWithWallLogic(rightRectangle);
			
        	
            // drawing 
            rlj.core.BeginDrawing();
            rlj.core.ClearBackground(Color.BLACK); 
            rlj.shapes.DrawLine(Main.WINDOW_WIDTH/2, 0, Main.WINDOW_WIDTH/2, Main.WINDOW_HEIGHT, Color.WHITE);
            
            rlj.text.DrawText("Left Side Score: ", leftSideScoreX, leftSideScoreY, 20, Color.GRAY);
            int LeftSideScoreTextLength = rlj.text.MeasureText("Left Side Score: ", scoresFontSize);
            rlj.text.DrawText(Integer.toString(Main.LEFT_SIDE_SCORE), leftSideScoreX+LeftSideScoreTextLength, leftSideScoreY, 20, Color.ORANGE);
            
            rlj.text.DrawText("Right Side Score: ", rightSideScoreX, rightSideScoreY, 20, Color.GRAY);
            int rightSideScoreTextLength = rlj.text.MeasureText("Right Side Score: ", scoresFontSize);
            rlj.text.DrawText(Integer.toString(Main.RIGHT_SIDE_SCORE), rightSideScoreX+rightSideScoreTextLength, leftSideScoreY, 20, Color.YELLOW);
            
            ball.draw();
            rightRectangle.draw();
            leftRectangle.draw();

            rlj.core.EndDrawing();
        }

        // closing window
        rlj.core.CloseWindow();
    }
}
