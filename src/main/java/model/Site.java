package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Site {

	private Direction direction;
	private Color color;
	private List<Piece> pieces = new ArrayList<>();
	
	public Direction getDirection() {
		return direction;
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}
	
	public void addPiece(final Piece piece) {
		this.pieces.add(piece);
	}
	
	public List<Piece> getPiecesInRow(final int row) {
		return getPieces(row, Piece::getX);
	}
	
	public List<Piece> getPiecesInColumn(final int column) {
		return getPieces(column, Piece::getY);
	}
	
	private List<Piece> getPieces(final int filter, final IGetValue value) {
		return pieces.stream().filter(p -> value.getCoordinate(p) == filter).collect(Collectors.toList());
	}
	
	public Piece getPieceAt(final int row, final int column) {
		return pieces.stream().filter(p -> p.getX() == row && p.getY() == column).collect(Collectors.toList()).get(0);
	}
	
	public List<Piece> getEdgesWithColor(final Color color) {
		return pieces.stream().filter(p -> p.getColor().equals(color) && isEdge(p)).collect(Collectors.toList());
	}
	
	public List<Piece> getCornersWithColor(final Color color) {
		return pieces.stream().filter(p -> p.getColor().equals(color) && !isEdge(p)).collect(Collectors.toList());
	}
	
	public List<Piece> getEdges() {
		return pieces.stream().filter(p -> isEdge(p)).collect(Collectors.toList());
	}
	
	private boolean isEdge(final Piece piece) {
		return piece.getX() == 1 || piece.getY() == 1;
	}
	
	public boolean isCorrectEdgeAt(final int x, final int y) {
		return getPieceAt(x, y).getColor().equals(color);
	}
	
	public List<Piece> getCorners() {
		return pieces.stream().filter(p -> !isEdge(p)).collect(Collectors.toList());
	}
}
