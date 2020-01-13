package game.level;

import java.util.List;

import game.Debug;
import game.Game;
import game.gfx.Screen;
import game.gfx.SpriteSheet;

public class Chunk {

	private static final int SIZE = 8;
	
	private final List<Tile> tiles;
	private final int xPos;
	private final int yPos;
	
	public Chunk(int x, int y, List<Tile> t) {
		tiles = t;
		xPos = x;
		yPos = y;
	}
	
	public void render(Screen screen, List<SpriteSheet> sheets) {
		int max = SIZE-1;
		double x = screen.xOffset;
		double y = screen.yOffset;
		
		int xPos = this.xPos*SIZE*32;
		int yPos = this.yPos*SIZE*32;
		
		int minX = (int)Math.max((x - xPos) / 32, 0);
		int maxX = (int)Math.min((Math.abs(xPos) + screen.width + x) / 32, max);
		
		int minY = (int)Math.max((y - yPos) / 32, 0);
		int maxY = (int)Math.min((Math.abs(yPos) + screen.height + y) / 32, max);
		
		if(minY >= SIZE || maxY < 0) return;
		
//		Debug.add("x:" + xPos + " -- " + minX + " " + maxX);
//		Debug.add("y:" + yPos + " -- " + minY + " " + maxY);
		
		for(int yy = minY; yy <= maxY; yy++) {
			for(int xx = minX; xx <= maxX; xx++) {
				Tile tile = tiles.get(xx+yy*SIZE);
				screen.setTile((xx << 5) + xPos, (yy << 5) + yPos, tile.getType(), sheets.get(tile.getSheet()), 32);
			}
		}
	}
	
	public List<Tile> getTiles() {
		return tiles;
	}
	
	public int getX() {
		return xPos;
	}
	
	public int getY() {
		return yPos;
	}
	
	public int[] position() {
		int[] i = new int[2];
		i[0] = xPos;
		i[1] = yPos;
		
		return i;
	}
	
	
	public static int getSize() {
		return SIZE;
	}
	
	
}
