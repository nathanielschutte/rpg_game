package game.level;

import java.util.ArrayList;
import java.util.List;

import game.Game;
import game.gfx.Screen;
import game.gfx.SpriteSheet;

public class Level {
	
	private final ChunkGen gen;
	private final Screen screen;
	
	private List<Chunk> chunks;
	private List<SpriteSheet> sheets;
	
	private static final int SHEET_COUNT = 3;
	private static final int CHUNK_PIXELS = Chunk.getSize()*32;
	
	public Level(Screen screen) {
		gen = new ChunkGen(0);
		this.screen = screen; 
		
		chunks = new ArrayList<Chunk>();
		chunks.add(gen.createBeachChunk(0, 0));
		chunks.add(gen.createBeachChunk(-1, 0));
		chunks.add(gen.createBeachChunk(1, 0));
		
		chunks.add(gen.createBasicChunk(0, -1));
		chunks.add(gen.createBasicChunk(-1, -1));
		chunks.add(gen.createBasicChunk(1, -1));
		
		sheets = new ArrayList<SpriteSheet>();
		for(int i = 0; i < SHEET_COUNT; i++) {
			sheets.add(new SpriteSheet("tiles_sheet_" + i + ".png"));
		}
	}
	
	public void render() {
		
		// render all loaded chunks
		for(Chunk c : chunks) {
			c.render(screen, sheets);
		}
	}
	
	public List<SpriteSheet> getSheets() {
		return sheets;
	}
}
