import java.awt.Graphics;

public abstract class Game {
	
	public abstract void init(GameContainer gc);
	
	public abstract void update(GameContainer gc);
	
	public abstract void render(GameContainer gc, Graphics g);
	
}
