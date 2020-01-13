package game.entity;

import java.util.List;

import game.InputHandler;
import game.gfx.Screen;
import game.gfx.SpriteSheet;
import game.level.Chunk;

public class Entity {

	protected double xPos, yPos, xVel, yVel;
	protected int width, height;
	
	protected final int SHEET;
	protected final int IMG;
	
	public Entity(int sheet, int img) {
		SHEET = sheet;
		IMG = img;
		xPos = 0;
		yPos = 0;
		xVel = 0;
		yVel = 0;
		width = 16;
		height = 16;
	}
	
	public Entity(double x, double y, double xv, double yv, int sheet, int image, int w, int h) {
		SHEET = sheet;
		IMG = image;
		xPos = x;
		yPos = y;
		xVel = xv;
		yVel = yv;
		width = w;
		height = h;
	}
	
	
	public void render(Screen screen, List<SpriteSheet> sheets) {
		screen.setTile((int)xPos, (int)yPos, IMG, sheets.get(SHEET), 16);
	}
	
	public void update(InputHandler e) {}
	
	public int[] getCurrentChunk() {
		int[] i = new int[2];
		i[0] = (int) (xPos / Chunk.getSize()*32);
		i[1] = (int) (yPos / Chunk.getSize()*32);
		
		return i;
	}
	
	
	public double getX() { return xPos; }
	public double getY() { return yPos; }
	public double getXV() { return xVel; }
	public double getYV() { return yVel; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getSheet() { return SHEET; }
	public int getImage() { return IMG; }
}
