package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.InputHandler;
import game.gfx.Screen;
import game.level.Level;

public class EntityHandler {

	private List<Entity> e;
	
	
	public EntityHandler() {
		e = new ArrayList<Entity>();
	}
	
	
	public void add(Entity e) {
		this.e.add(e);
	}
	
	public void add(Entity e, int i) {
		this.e.add(i, e);
	}
	
	public void move(Entity e, int i) {
		this.e.remove(e);
		this.add(e, i);
	}
	
	public void render(Screen screen, Level level) {
		for(Entity i : e) {
			i.render(screen, level.getSheets());
		}
	}
	
	public void update(InputHandler i) {
		for(Entity k : e) {
			k.update(i);
		}
	}
	
	public ArrayList<Entity> getEntitiesInChunk(int[] pos) {
		List<Entity> p = new ArrayList<Entity>();
		for(Entity k : e) {
			if(k.getCurrentChunk() == pos)
				p.add(k);
		}
		
		return (ArrayList<Entity>) p;
	}
	
}
