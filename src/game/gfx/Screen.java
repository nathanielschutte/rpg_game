package game.gfx;


import game.Debug;
import game.Game;

public class Screen {

	public static final int MAP_WIDTH = 64;
	public static final int MAP_WIDTH_MASK = MAP_WIDTH - 1;

	private int[] pixels;

	public double xOffset = 0;
	public double yOffset = 0;

	public int width;
	public int height;

	private int layer;

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;

		pixels = new int[width * height];
	}

	public void reset() {
		layer = 0;
	}

	// for basic square tile grid render
	public void setTile(int xPos, int yPos, int tile, SpriteSheet sheet, int tileSize) {
		int xTile = tile % 8;
		int yTile = tile / 8;
		int tileOffset = (xTile << 5) + (yTile << 5) * sheet.width;

		setPixels(xPos, yPos, tileSize, tileOffset, sheet);
	}

	// for frame specific animation reel render
	public void setAnimatedTile(int xPos, int yPos, int tile, SpriteSheet sheet, int tileSize) {
		int tileOffset = (tile << 5) * sheet.width;
		setPixels(xPos, yPos, tileSize, tileOffset, sheet);
	}

	// actually set pixel data
	private void setPixels(int xPos, int yPos, int tileSize, int tileOffset, SpriteSheet sheet) {
		xPos -= (int) xOffset;
		yPos -= (int) yOffset;

		for (int y = 0; y < tileSize; y++) {
			if (y + yPos < 0 || y + yPos >= height)
				continue;
			int ySheet = y;
			for (int x = 0; x < tileSize; x++) {
				if (x + xPos < 0 || x + xPos >= width)
					continue;
				int xSheet = x;
				int col = sheet.pixels[xSheet + ySheet * sheet.width + tileOffset];
				if (col != -16777216 || (col == -16777216 && layer == 0))
					pixels[(x + xPos) + (y + yPos) * width] = col;
			}
		}

		layer++;
	}
	
	public int[] getPixels() {
		return pixels;
	}
}