package Main;

public enum Direction {
	UP (1),
	DOWN (-1);
	
	
	private final int direction;
	
	Direction(int dir) {
		direction = dir;
	}
	
	public int getDir() {
		return direction;
	}
	
	public Direction getOppositeDir() {
		if (direction == 1) return DOWN;
		else return UP;
	}
	
}
