package game.entity;

import game.Debug;
import game.InputHandler;
import game.gfx.Screen;

public class EntityPlayer extends Entity {

	private final Screen screen;

	private static final double CAM_TRACK_WIDTH = 64;
	private static final double CAM_TRACK_HEIGHT = 64;
	private static final double CAM_TRACK_DELAY = 24;

	private static final double NORMAL_ACCEL = 0.35;
	private static final double NORMAL_DAMP = 0.7;

	private double accelMult = 1.0;
	private double dampMult = 1.0;

	public double centerX;
	public double centerY;

	private boolean playerLock = true;

	public EntityPlayer(int sheet, int img, Screen screen) {
		super(sheet, img);
		this.screen = screen;
		updateCenter();
	}

	public EntityPlayer(double x, double y, double xv, double yv, int sheet,
			int image, int w, int h, Screen screen) {
		super(x, y, xv, yv, sheet, image, w, h);
		this.screen = screen;
		updateCenter();
	}

	public void update(InputHandler e) {
		if (e.up.isPressed())
			yVel += NORMAL_ACCEL * accelMult;
		if (e.down.isPressed())
			yVel -= NORMAL_ACCEL * accelMult;
		if (e.left.isPressed())
			xVel -= NORMAL_ACCEL * accelMult;
		if (e.right.isPressed())
			xVel += NORMAL_ACCEL * accelMult;

		xPos += xVel;
		yPos -= yVel;
		updateCenter();

		xVel *= NORMAL_DAMP * dampMult;
		yVel *= NORMAL_DAMP * dampMult;

		// camera movement
		if (playerLock) {
			screen.xOffset += ((centerX + width/2 - screen.xOffset) / CAM_TRACK_DELAY);
			screen.yOffset += ((centerY + height/2 - screen.yOffset) / CAM_TRACK_DELAY);
		}
	}

	private void updateCenter() {
		centerX = xPos - screen.width / 2;
		centerY = yPos - screen.height / 2;
	}

	public void togglePlayerLock(boolean t) {
		playerLock = t;
	}
}
