package game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.Game;

public class SpriteSheet {

	public String path;
	public int width;
	public int height;
	
	public int[] pixels;
	
	public boolean animation;
	
	public SpriteSheet(String path) {
		animation = false;
		BufferedImage image = null;
		
		try {
			System.out.println(path);
			
			image = ImageIO.read(this.getClass().getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(image == null) {
			return;
		}
		
		this.path = path;
		this.width = image.getWidth();
		this.height = image.getHeight();
		
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		
		if(width == 16)
			animation = true;
	}
}
