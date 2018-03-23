package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Cube {

	private List<Site> sites = new ArrayList<>();
	private List<String> commands = new ArrayList<>();
	
	public List<String> getCommands() {
		return commands;
	}

	public List<Site> getSites() {
		return sites;
	}

	public void setSites(List<Site> sites) {
		this.sites = sites;
	}
	
	public void addSite(Site site) {
		this.sites.add(site);
	}
	
	public Site getSite(final Direction direction) {
		return sites.stream().filter(s -> s.getDirection().equals(direction)).collect(Collectors.toList()).get(0);
	}
	
	public void rotateSideClockwise(final Direction direction) {
		final Site site = getSite(direction);
		moveColors(site.getPieceAt(0, 0), site.getPieceAt(0, 2), site.getPieceAt(2, 2), site.getPieceAt(2, 0));
		moveColors(site.getPieceAt(0, 1), site.getPieceAt(1, 2), site.getPieceAt(2, 1), site.getPieceAt(1, 0));
		
		commands.add(updateAdjacentSidesOnClockwiseRotation(direction));
	}
	
	public void rotateSideAntiClockwise(final Direction direction) {
		final Site site = getSite(direction);
		moveColors(site.getPieceAt(0, 0), site.getPieceAt(2, 0), site.getPieceAt(2, 2), site.getPieceAt(0, 2));
		moveColors(site.getPieceAt(0, 1), site.getPieceAt(1, 0), site.getPieceAt(2, 1), site.getPieceAt(1, 2));
		
		commands.add(updateAdjacentSidesOnAntiClockwiseRotation(direction));
	}
	
	private String updateAdjacentSidesOnClockwiseRotation(final Direction direction) {		
		switch (direction) {
			case FRONT: 	updateSidesNextToFront(true); return "F";
			case BACK: updateSidesNextToBack(true); return "B";
			case UP: updateSidesNextToTop(true); return "U";
			case DOWN: updateSidesNextToDown(true); return "D";
			case LEFT: updateSidesNextToLeft(true); return "L";
			case RIGHT: updateSidesNextToRight(true); return "R";
			default: return "";
		}
	}

	private String updateAdjacentSidesOnAntiClockwiseRotation(final Direction direction) {		
		switch (direction) {
			case FRONT: updateSidesNextToFront(false); return "f";
			case BACK: updateSidesNextToBack(false); return "b";
			case UP: updateSidesNextToTop(false); return "u";
			case DOWN: updateSidesNextToDown(false); return "d";
			case LEFT: updateSidesNextToLeft(false); return "l";
			case RIGHT: updateSidesNextToRight(false); return "r";
			default: return "";
		}
	}

	private void updateSidesNextToFront(final boolean clockwise) {
		final Site top = getSite(Direction.UP);
		final Site right = getSite(Direction.RIGHT);
		final Site down = getSite(Direction.DOWN);
		final Site left = getSite(Direction.LEFT);
		
		if (clockwise) {
			moveColors(top.getPieceAt(2, 0), right.getPieceAt(0, 0), down.getPieceAt(0, 2), left.getPieceAt(2, 2));
			moveColors(top.getPieceAt(2, 1), right.getPieceAt(1, 0), down.getPieceAt(0, 1), left.getPieceAt(1, 2));
			moveColors(top.getPieceAt(2, 2), right.getPieceAt(2, 0), down.getPieceAt(0, 0), left.getPieceAt(0, 2));
		} else {
			moveColors(top.getPieceAt(2, 0), left.getPieceAt(2, 2), down.getPieceAt(0, 2), right.getPieceAt(0, 0));
			moveColors(top.getPieceAt(2, 1), left.getPieceAt(1, 2), down.getPieceAt(0, 1), right.getPieceAt(1, 0));
			moveColors(top.getPieceAt(2, 2), left.getPieceAt(0, 2), down.getPieceAt(0, 0), right.getPieceAt(2, 0));
		}
	}
	
	private void updateSidesNextToBack(final boolean clockwise) {
		final Site top = getSite(Direction.UP);
		final Site left = getSite(Direction.LEFT);
		final Site down = getSite(Direction.DOWN);
		final Site right = getSite(Direction.RIGHT);
		
		if (clockwise) {
			moveColors(top.getPieceAt(0, 2), left.getPieceAt(0, 0), down.getPieceAt(2, 0), right.getPieceAt(2, 2));
			moveColors(top.getPieceAt(0, 1), left.getPieceAt(1, 0), down.getPieceAt(2, 1), right.getPieceAt(1, 2));
			moveColors(top.getPieceAt(0, 0), left.getPieceAt(2, 0), down.getPieceAt(2, 2), right.getPieceAt(0, 2));
		} else {
			moveColors(top.getPieceAt(0, 2), right.getPieceAt(2, 2), down.getPieceAt(2, 0), left.getPieceAt(0, 0));
			moveColors(top.getPieceAt(0, 1), right.getPieceAt(1, 2), down.getPieceAt(2, 1), left.getPieceAt(1, 0));
			moveColors(top.getPieceAt(0, 0), right.getPieceAt(0, 2), down.getPieceAt(2, 2), left.getPieceAt(2, 0));
		}
	}
	
	private void updateSidesNextToTop(final boolean clockwise) {
		final Site back = getSite(Direction.BACK);
		final Site right = getSite(Direction.RIGHT);
		final Site front = getSite(Direction.FRONT);
		final Site left = getSite(Direction.LEFT);
		
		if (clockwise) {
			moveColors(back.getPieceAt(0, 2), right.getPieceAt(0, 2), front.getPieceAt(0, 2), left.getPieceAt(0, 2));
			moveColors(back.getPieceAt(0, 1), right.getPieceAt(0, 1), front.getPieceAt(0, 1), left.getPieceAt(0, 1));
			moveColors(back.getPieceAt(0, 0), right.getPieceAt(0, 0), front.getPieceAt(0, 0), left.getPieceAt(0, 0));
		} else {
			moveColors(back.getPieceAt(0, 2), left.getPieceAt(0, 2), front.getPieceAt(0, 2), right.getPieceAt(0, 2));
			moveColors(back.getPieceAt(0, 1), left.getPieceAt(0, 1), front.getPieceAt(0, 1), right.getPieceAt(0, 1));
			moveColors(back.getPieceAt(0, 0), left.getPieceAt(0, 0), front.getPieceAt(0, 0), right.getPieceAt(0, 0));
		}
	}
	
	private void updateSidesNextToDown(final boolean clockwise) {
		final Site front = getSite(Direction.FRONT);
		final Site right = getSite(Direction.RIGHT);
		final Site back = getSite(Direction.BACK);
		final Site left = getSite(Direction.LEFT);
		
		if (clockwise) {
			moveColors(front.getPieceAt(2, 0), right.getPieceAt(2, 0), back.getPieceAt(2, 0), left.getPieceAt(2, 0));
			moveColors(front.getPieceAt(2, 1), right.getPieceAt(2, 1), back.getPieceAt(2, 1), left.getPieceAt(2, 1));
			moveColors(front.getPieceAt(2, 2), right.getPieceAt(2, 2), back.getPieceAt(2, 2), left.getPieceAt(2, 2));
		} else {
			moveColors(back.getPieceAt(2, 0), left.getPieceAt(2, 0), front.getPieceAt(2, 0), right.getPieceAt(2, 0));
			moveColors(back.getPieceAt(2, 1), left.getPieceAt(2, 1), front.getPieceAt(2, 1), right.getPieceAt(2, 1));
			moveColors(back.getPieceAt(2, 2), left.getPieceAt(2, 2), front.getPieceAt(2, 2), right.getPieceAt(2, 2));
		}
	}
	
	private void updateSidesNextToLeft(final boolean clockwise) {
		final Site top = getSite(Direction.UP);
		final Site front = getSite(Direction.FRONT);
		final Site down = getSite(Direction.DOWN);
		final Site back = getSite(Direction.BACK);
		
		if (clockwise) {
			moveColors(top.getPieceAt(0, 0), front.getPieceAt(0, 0), down.getPieceAt(0, 0), back.getPieceAt(2, 2));
			moveColors(top.getPieceAt(1, 0), front.getPieceAt(1, 0), down.getPieceAt(1, 0), back.getPieceAt(1, 2));
			moveColors(top.getPieceAt(2, 0), front.getPieceAt(2, 0), down.getPieceAt(2, 0), back.getPieceAt(0, 2));
		} else {
			moveColors(top.getPieceAt(0, 0), back.getPieceAt(2, 2), down.getPieceAt(0, 0), front.getPieceAt(0, 0));
			moveColors(top.getPieceAt(1, 0), back.getPieceAt(1, 2), down.getPieceAt(1, 0), front.getPieceAt(1, 0));
			moveColors(top.getPieceAt(2, 0), back.getPieceAt(0, 2), down.getPieceAt(2, 0), front.getPieceAt(2, 0));
		}
	}
	
	private void updateSidesNextToRight(final boolean clockwise) {
		final Site top = getSite(Direction.UP);
		final Site back = getSite(Direction.BACK);
		final Site down = getSite(Direction.DOWN);
		final Site front = getSite(Direction.FRONT);
		
		if (clockwise) {
			moveColors(top.getPieceAt(2, 2), back.getPieceAt(0, 0), down.getPieceAt(2, 2), front.getPieceAt(2, 2));
			moveColors(top.getPieceAt(1, 2), back.getPieceAt(1, 0), down.getPieceAt(1, 2), front.getPieceAt(1, 2));
			moveColors(top.getPieceAt(0, 2), back.getPieceAt(2, 0), down.getPieceAt(0, 2), front.getPieceAt(0, 2));
		} else {
			moveColors(top.getPieceAt(2, 2), front.getPieceAt(2, 2), down.getPieceAt(2, 2), back.getPieceAt(0, 0));
			moveColors(top.getPieceAt(1, 2), front.getPieceAt(1, 2), down.getPieceAt(1, 2), back.getPieceAt(1, 0));
			moveColors(top.getPieceAt(0, 2), front.getPieceAt(0, 2), down.getPieceAt(0, 2), back.getPieceAt(2, 0));			
		}
	}
	
	private void moveColors(final Piece first, final Piece second, final Piece third, final Piece fourth) {
		final Color newFirstColor = fourth.getColor();
		fourth.setColor(third.getColor());
		third.setColor(second.getColor());
		second.setColor(first.getColor());		
		first.setColor(newFirstColor);
	}
	
	public Map<Direction, List<Piece>> getAllEdgesFor(final Color color) {
		final Map<Direction, List<Piece>> piecesForColor = new HashMap<>();
		
		for (final Site site : sites) {
			final List<Piece> edges = site.getEdgesWithColor(color);
			if (edges.isEmpty()) {
				continue;
			}
			
			piecesForColor.put(site.getDirection(), edges);
		}
		
		return piecesForColor;
	}
	
	public Map<Direction, List<Piece>> getAllCornersFor(final Color color) {
		final Map<Direction, List<Piece>> cornersForColor = new HashMap<>();
		
		for (final Site site : sites) {
			final List<Piece> corners = site.getCornersWithColor(color);
			if (corners.isEmpty()) {
				continue;
			}
			
			cornersForColor.put(site.getDirection(), corners);
		}
		
		return cornersForColor;
	}
	
	public boolean hasCrossOnTopSide() {
		final Site top = getSite(Direction.UP);
		final List<Piece> edges = top.getEdges();
		
		boolean isCrossCorrect = edges.stream().allMatch(e -> e.getColor().equals(top.getColor())); 
		isCrossCorrect &= isEdgeCorrectBetweenTopAnd(Direction.BACK, new Position(0, 1));
		isCrossCorrect &= isEdgeCorrectBetweenTopAnd(Direction.RIGHT, new Position(0, 1));
		isCrossCorrect &= isEdgeCorrectBetweenTopAnd(Direction.FRONT, new Position(0, 1));
		isCrossCorrect &= isEdgeCorrectBetweenTopAnd(Direction.LEFT, new Position(0, 1));

		return isCrossCorrect;
	}
	
	private boolean isEdgeCorrectBetweenTopAnd(final Direction direction, final Position position) {
		return getSite(direction).isCorrectEdgeAt(position.getX(), position.getY());
	}
	
	public boolean hasUpSiteCompleted() {
		final Site up = getSite(Direction.UP);
		
		if (hasCrossOnTopSide()) {
			final List<Piece> corners = up.getCorners();
			
			boolean areCornersCorrect = corners.stream().allMatch(c -> c.getColor().equals(up.getColor()));
			areCornersCorrect &= isCornerInCorrectPosition(Direction.BACK, new Position(0, 2), Direction.LEFT, new Position(0, 0));
			areCornersCorrect &= isCornerInCorrectPosition(Direction.BACK, new Position(0, 0), Direction.RIGHT, new Position(0, 2));
			areCornersCorrect &= isCornerInCorrectPosition(Direction.FRONT, new Position(0, 0), Direction.LEFT, new Position(0, 2));
			areCornersCorrect &= isCornerInCorrectPosition(Direction.FRONT, new Position(2, 2), Direction.RIGHT, new Position(0, 0));
			
			return areCornersCorrect;
		}
		
		return false;
	}
	
	private boolean isCornerInCorrectPosition(final Direction ad, final Position ap, final Direction bd, final Position bp) {
		return isEdgeCorrectBetweenTopAnd(ad, ap) && isEdgeCorrectBetweenTopAnd(bd, bp);
	}
}
