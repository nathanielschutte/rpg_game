package game.level;

import java.util.ArrayList;
import java.util.List;

import game.entity.Entity;
import game.entity.EntityHandler;

public class ChunkIO {

	public static String getChunkString(Chunk chunk, EntityHandler eh) {
		ArrayList<Entity> e = eh.getEntitiesInChunk(chunk.position());
		
		
		return "";
	}
	
	public static String getTileString(Tile t) {
		int type = t.getType();
		int struct = t.getStruct();
		int sheet = t.getSheet();
		int data = ((type << 16) & 0xFF0000) | ((struct << 8) & 0x00FF00) | (sheet & 0x0000FF);
		
		return data+"";
	}
	
	
	
	
	
	public static Tile parseTileData(String s) {
		int r = Integer.valueOf(s);
		return new Tile((r & 0xFF0000) >> 16, (r & 0x00FF00) >> 8, (r & 0x0000FF));
	}
}
