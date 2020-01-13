package game.level;

public class Tile {

	private final int SHEET;
	private final int TILE;
	private final int STRUCT;
	
	public Tile(int tile, int struct, int sheet) {
		TILE = tile;
		STRUCT = struct;
		
		SHEET = sheet;
	}
	
	public int getSheet() {
		return SHEET;
	}
	
	public int getType() {
		return TILE;
	}
	
	public int getStruct() {
		return STRUCT;
	}
}
