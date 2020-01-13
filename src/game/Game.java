package game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import game.InputHandler;
import game.entity.EntityHandler;
import game.entity.EntityPlayer;
import game.gfx.Screen;
import game.level.ChunkIO;
import game.level.Level;
import game.level.Tile;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final boolean DEBUG = true;

	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static final String NAME = "RPG Game";
	
	public static final int COLOR_COUNT = 8;
	
	private JFrame frame;
	
	private boolean running = false;
	public int tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	private Screen screen;
	private Level level;
	public InputHandler input;
	
	private EntityHandler entity;
	
	
	
	public Game() {
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void init() {
		
		screen = new Screen(WIDTH, HEIGHT);
		input = new InputHandler(this);
		
		level = new Level(screen);
		entity = new EntityHandler();
		
		entity.add(new EntityPlayer(WIDTH/2-8, HEIGHT/2-8, 0, 0, 2, 0, 16, 16, screen));
	}
	
	private synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	private synchronized void stop() {
		running = false;
	}

	
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			boolean shouldRender = true;
			
			while(delta >= 1) {
				ticks++;
				update();
				delta -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(shouldRender) {
				frames++;
				render();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				
				Debug.add(ticks + " ticks,  " + frames + " frames");
				Debug.print(DEBUG);
				
				frames = 0;
				ticks = 0;
			}
		}
	}

	
	public void update() {
		tickCount++;
		
		entity.update(input);
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		
		// setup screen for drawing
		screen.reset();
		
		// do actual tiling and other rendering
		level.render();
		
		// entity rendering
		entity.render(screen, level);
		
		
		// set screen pixels to image (will then be drawn)
		for(int y = 0; y < screen.height; y++) {
			for(int x = 0; x < screen.width; x++) {
				int cc = screen.getPixels()[x+y*screen.width];
				pixels[x + y*WIDTH] = cc;
			}
		}
		
		Graphics gg = bs.getDrawGraphics();
		Graphics2D g = (Graphics2D) gg;
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		
		g.clearRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}
	
	
	public static void main(String[] args) {
		new Game().start();
		
//		Tile t = new Tile(1, 2, 3);
//		String s = ChunkIO.getTileString(t);
//		System.out.println(s);
//		
//		t = ChunkIO.parseTileData(s);
//		System.out.println(t.getType() + " " + t.getStruct() + " " + t.getSheet());
	}
}
