import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameContainer implements Runnable {

	private int gameWidth;
	private int gameHeight;
	
	private Game game;
	
	private Thread thread;
	private boolean running;
	
	private JFrame frame;
	
	private final String title;
	private Dimension canvasSize;
	
	private BufferedImage image;
	private BufferStrategy canvasBuffer;
	
	public GameContainer(Game game, String title, int width, int height, int scale) {
		this.game = game;
		this.title = title;
		this.gameWidth = width;
		this.gameHeight = height;
		this.canvasSize = new Dimension(width * scale, height * scale);
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void init() {
		
		image = new BufferedImage(gameWidth, gameHeight, BufferedImage.TYPE_INT_RGB);
		
		frame = new JFrame(title);
		
		JPanel panel = (JPanel) frame.getContentPane();
		panel.setPreferredSize(canvasSize);
		panel.setLayout(null);
		
		Canvas canvas = new Canvas();
		canvas.setBounds(0, 0, canvasSize.width, canvasSize.height);
		canvas.setIgnoreRepaint(true);
		
		panel.add(canvas);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		
		canvas.createBufferStrategy(3);
		
		canvasBuffer = canvas.getBufferStrategy();
		
		canvas.requestFocus();
	}
	
	public void run() {
		init();
		game.init(this);
		
		while (running) {
			game.update(this);
			
			game.render(this, image.getGraphics());
			
			updateCanvas();
		}
	}
	
	public void updateCanvas() {
		Graphics g = canvasBuffer.getDrawGraphics();
		g.drawImage(image, 0, 0, canvasSize.width, canvasSize.height, null);
		g.dispose();
		canvasBuffer.show();
	}
	
	public int getWidth() {
		return gameWidth;
	}
	
	public int getHeight() {
		return gameHeight;
	}
	
}
