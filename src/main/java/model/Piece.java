package model;

public class Piece {

	private Position position;
	private Color color;
	
	public Position getPosition() {
		return position;
	}
	
	public void setPosition(final Position position) {
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}
	
	public int getX() {
		return position.getX();
	}
	
	public int getY() {
		return position.getY();
	}
}
