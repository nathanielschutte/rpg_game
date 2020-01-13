package game.level;

import java.util.ArrayList;
import java.util.List;

public class ChunkGen {

	private final long seed;

	private final int COUNT;
	private final int WIDTH;

	public ChunkGen(long seed) {
		this.seed = seed;
		COUNT = Chunk.getSize() * Chunk.getSize();
		WIDTH = Chunk.getSize();
	}

	// test
	public Chunk createBasicChunk(int x, int y) {
		List<Tile> tiles = new ArrayList<Tile>();

		for (int i = 0; i < COUNT; i++) {
			tiles.add(new Tile(randInt(0, 5), -1, 0));
			if (randInt(0, 8) == 0)
				tiles.set(i, new Tile(8, -1, 0));
			
			// buildings
			if (randInt(0, 5) == 0)
				tiles.set(i, new Tile(randInt(0, 5), -1, 1));
		}

		return new Chunk(x, y, tiles);
	}

	// on the beach
	public Chunk createBeachChunk(int x, int y) {
		List<Tile> tiles = new ArrayList<Tile>();

		for (int i = 0; i < COUNT; i++) {
			tiles.add(new Tile((i < COUNT/2-WIDTH*randInt(0,2) ? 17 : 16), -1, 0));
		}

		return new Chunk(x, y, tiles);
	}

	private static int randInt(int min, int max) {
		int r = (int) (Math.random() * (max - min)) + min;
		if(r == max) r = max-1;
		return r;
	}
}
