import java.awt.Color;
import java.awt.Graphics;


public class GameSkeleton extends Game {
	
	private int ticks;
	
	public void init(GameContainer gc) {
		ticks = 0;
	}

	public void update(GameContainer gc) {
		ticks++;
	}

	public void render(GameContainer gc, Graphics g) {
		g.setColor(new Color(ticks & 255, 255 - ticks & 255, 0));
		g.fillRect(0, 0, gc.getWidth(), gc.getHeight());
		
		g.setColor(Color.white);
		g.drawString("Game Skeleton", 40, 40);
	}

}
