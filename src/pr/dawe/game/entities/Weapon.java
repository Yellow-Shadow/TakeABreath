package pr.dawe.game.entities;

import pr.dawe.game.gfx.Colours;
import pr.dawe.game.gfx.Screen;
import pr.dawe.game.level.Level;

public class Weapon extends Mob {

	private int colour = Colours.get(-1, 500, 440, 000);
	private int scale = 1;
	public static int shootDir;
	public static boolean moving;
	public static boolean isTakeOut = false;

	public Weapon(Level level, int x, int y, String name, int attack) {
		super(level, name, x, y, 0, 1, setforce(attack));
	}

	public static int setforce(int attack) {
		return Player.force + attack;
	}

	public void playerAttack(NPC enemy) {
		enemy.hp = enemy.hp - Weapon.force;
		if(enemy.hp<=0) {
			Level.removeMonEntity(enemy);
		}
	}
	
	public void tick() {
		int xa = 0;
		int ya = 0;

		if (shootDir == 0)
			ya--;
		if (!hasCollided(xa, ya)) {
			moving = true;
		} else {
			moving = false;
		}

		if (shootDir == 1)
			ya++;
		if (!hasCollided(xa, ya)) {
			moving = true;
		} else {
			moving = false;
		}

		if (shootDir == 2)
			xa--;
		if (!hasCollided(xa, ya)) {
			moving = true;
		} else {
			moving = false;
		}

		if (shootDir == 3)
			xa++;
		if (!hasCollided(xa, ya)) {
			moving = true;
		} else {
			moving = false;
		}

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			isMoving = true;
		} else {
			isMoving = false;
		}
		if (level.getTile(this.x >> 3, this.y >> 3).getId() == 3) {

		}
	}

	public void render(Screen screen) {
		int xTile = 10;
		int yTile = 28;
		int xOffset = x;
		int yOffset = y;

		screen.render(xOffset, yOffset, xTile + yTile * 32, colour, 1, scale);

	}

	public boolean hasCollided(int xa, int ya) {
		int xMin = 0;
		int xMax = 3;
		int yMin = 3;
		int yMax = 3;
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMin)) {
				return true;
			}
		}
		for (int x = xMin; x < xMax; x++) {
			if (isSolidTile(xa, ya, x, yMax)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMin, y)) {
				return true;
			}
		}
		for (int y = yMin; y < yMax; y++) {
			if (isSolidTile(xa, ya, xMax, y)) {
				return true;
			}
		}
		return false;
	}

}
