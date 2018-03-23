package solver;

import java.util.List;
import java.util.Map;

import helper.SitesHelper;
import model.Color;
import model.Cube;
import model.Direction;
import model.Piece;
import model.Position;
import model.Site;

public class TopLayerHelper {

	private final Cube cube;
	private SitesHelper sitesHelper;
	
	public TopLayerHelper(final Cube cube) {
		this.cube = cube;
		sitesHelper = new SitesHelper(cube);
	}
	
	public void createCross() {
		final Color color = cube.getSite(Direction.UP).getColor();
		while (!cube.hasCrossOnTopSide()) {
			Map<Direction, List<Piece>> edges = cube.getAllEdgesFor(color);
			
			if (edges.containsKey(Direction.UP)) {
				handleEdgesOnTopSite(edges.get(Direction.UP));
			}
			
			edges = cube.getAllEdgesFor(color);
			
			if (edges.containsKey(Direction.DOWN)) {
				handleEdgesOnDownSite(edges.get(Direction.DOWN));
			}
			
			edges = cube.getAllEdgesFor(color);
			
			handleEdgesOnOtherSites(edges);
		}
	}
	
	private void handleEdgesOnTopSite(List<Piece> edges) {
		for (final Piece piece : edges) {
			final Site adjacentSite = sitesHelper.getAdjacentSiteForUp(piece);
			if (adjacentSite.getColor().equals(adjacentSite.getPieceAt(0, 1).getColor())) {
				continue;
			}
			
			cube.rotateSideClockwise(adjacentSite.getDirection());
			cube.rotateSideClockwise(adjacentSite.getDirection());
			
			moveEdgeFromDownToCorrectPosition(adjacentSite);
			
			break;
		}
	}
	
	private Site getNextSite(final Site site) {
		switch (site.getDirection()) {
			case FRONT: return cube.getSite(Direction.RIGHT);
			case RIGHT: return cube.getSite(Direction.BACK);
			case BACK: return cube.getSite(Direction.LEFT);
			case LEFT: return cube.getSite(Direction.FRONT);
			default: return site;
		}
	}
	
	private Site getPreviousSite(final Site site) {
		switch (site.getDirection()) {
			case FRONT: return cube.getSite(Direction.LEFT);
			case RIGHT: return cube.getSite(Direction.FRONT);
			case BACK: return cube.getSite(Direction.RIGHT);
			case LEFT: return cube.getSite(Direction.BACK);
			default: return site;
		}
	}
	
	private void moveEdgeFromDownToCorrectPosition(final Site startSite) {
		cube.rotateSideClockwise(Direction.DOWN);
		
		Site adjacentSite = getNextSite(startSite);
		int i = 0;
		while (!adjacentSite.getPieceAt(2, 1).getColor().equals(adjacentSite.getColor()) && i++ < 4) {
			cube.rotateSideClockwise(Direction.DOWN);
			adjacentSite = getNextSite(adjacentSite);
		}
		
		cube.rotateSideClockwise(adjacentSite.getDirection());
		cube.rotateSideClockwise(adjacentSite.getDirection());
	}
	
	private void handleEdgesOnDownSite(final List<Piece> pieces) {
		for (final Piece piece : pieces) {
			Site adjacentSite = sitesHelper.getAdjacentSiteForDown(piece);
			if (adjacentSite.getColor().equals(adjacentSite.getPieceAt(2, 1).getColor())) {
				continue;
			}
			
			moveEdgeFromDownToCorrectPosition(adjacentSite);
			
			break;
		}
	}
	
	private void handleEdgesOnOtherSites(final Map<Direction, List<Piece>> edges) {
		for (final Map.Entry<Direction, List<Piece>> entry : edges.entrySet()) {
			if (!entry.getKey().equals(Direction.UP) && !entry.getKey().equals(Direction.DOWN)) {
				handleRemainingEdges(entry.getKey(), entry.getValue());
				break;
			}
		}
	}
	
	private void handleRemainingEdges(final Direction direction, final List<Piece> pieces) {
		for (final Piece piece : pieces) {
			final Site site = cube.getSite(direction);
			
			switch (sitesHelper.getAdjacentSiteFor(direction, piece).getDirection()) {
				case UP: moveEdgeFromUpToDown(site);
				case DOWN: rotateEdgeFromDown(site);
				default: moveEdgeDown(site, piece);
			}
			
			break;
		}
	}
	
	private void moveEdgeFromUpToDown(final Site site) {
		cube.rotateSideClockwise(site.getDirection());
		
		final Site adjacentSite = sitesHelper.getAdjacentSiteFor(site, site.getPieceAt(1, 2));
		cube.rotateSideAntiClockwise(adjacentSite.getDirection());
		cube.rotateSideClockwise(Direction.DOWN);
		cube.rotateSideClockwise(adjacentSite.getDirection());
		
		moveEdgeFromDownToCorrectPosition(getNextSite(adjacentSite));
	}
	
	private void rotateEdgeFromDown(final Site site) {
		cube.rotateSideClockwise(site.getDirection());
		
		final Site adjacentSite = sitesHelper.getAdjacentSiteFor(site, site.getPieceAt(1, 0));
		cube.rotateSideClockwise(adjacentSite.getDirection());
		cube.rotateSideAntiClockwise(site.getDirection());
		
		moveEdgeFromDownToCorrectPosition(adjacentSite);
	}
	
	private void moveEdgeDown(final Site site, final Piece piece) {
		final Site adjacentSite = sitesHelper.getAdjacentSiteFor(site, piece);
		
		if (piece.getY() == 0) {
			cube.rotateSideClockwise(adjacentSite.getDirection());
			cube.rotateSideClockwise(Direction.DOWN);
			cube.rotateSideAntiClockwise(adjacentSite.getDirection());
		} else {
			cube.rotateSideAntiClockwise(adjacentSite.getDirection());
			cube.rotateSideClockwise(Direction.DOWN);
			cube.rotateSideClockwise(adjacentSite.getDirection());
		}
		
		moveEdgeFromDownToCorrectPosition(getNextSite(adjacentSite));
	}
	
	public void completeFirstSite() {
		final Color color = cube.getSite(Direction.UP).getColor();
		while(!cube.hasUpSiteCompleted()) {
			Map<Direction, List<Piece>> corners = cube.getAllCornersFor(color);
			
			if (corners.containsKey(Direction.UP)) {
				handleCornersOnUpSite(corners.get(Direction.UP));
			}
			
			corners = cube.getAllCornersFor(color);
			
			if (corners.containsKey(Direction.DOWN)) {
//				handleCornersOnDownSite(corners.get(Direction.DOWN));
			}
			
			corners = cube.getAllCornersFor(color);
			break;
//			handleCornersOnOtherSites(corners);
		}
	}
	
	private void handleCornersOnUpSite(final List<Piece> corners) {
		for (final Piece corner : corners) {
			final List<Site> adjacentSites = sitesHelper.getAdjacentSitesForUp(corner);
			if (isCornerAtCorrectPosition(adjacentSites, corner)) {
				continue;
			}
			
			moveCornerFromUpToDown(adjacentSites);
			break;
		}
	}
	
	private boolean isCornerAtCorrectPosition(List<Site> sites, final Piece corner) {
		final Site aSite = sites.get(0);
		final Position aPos = getCornerPostionForAdjacentSiteToUp(aSite.getDirection(), corner);
		
		final Site bSite = sites.get(1);
		final Position bPos = getCornerPostionForAdjacentSiteToUp(bSite.getDirection(), corner);
		
		return aSite.getColor().equals(aSite.getPieceAt(aPos.getX(), aPos.getY()).getColor()) 
				&& bSite.getColor().equals(bSite.getPieceAt(bPos.getX(), bPos.getY()).getColor());
	}
	
	private Position getCornerPostionForAdjacentSiteToUp(final Direction direction, final Piece piece) {
		switch (direction) {
			case BACK: return piece.getY() == 0 ? new Position(0, 2) : new Position(0, 0);
			case RIGHT: return piece.getX() == 0 ? new Position(0, 2) : new Position(0, 0);
			case FRONT: return piece.getY() == 0 ? new Position(0	, 0) : new Position(0, 2);
			case LEFT: return piece.getX() == 0 ? new Position(0, 0) : new Position(0, 2);
			default: return null;
		}
	}
	
	private void moveCornerFromUpToDown(final List<Site> sites) {
		if (sites.stream().allMatch(s -> s.getDirection().equals(Direction.FRONT) || s.getDirection().equals(Direction.RIGHT))) {
			cube.rotateSideClockwise(Direction.FRONT);
			cube.rotateSideClockwise(Direction.DOWN);
			cube.rotateSideAntiClockwise(Direction.FRONT);
			moveCornerFromDownToCorrectPosition(cube.getSite(Direction.RIGHT), new Position(2, 2));
		} else if (sites.stream().allMatch(s -> s.getDirection().equals(Direction.FRONT) || s.getDirection().equals(Direction.LEFT))) {
			cube.rotateSideAntiClockwise(Direction.FRONT);
			cube.rotateSideAntiClockwise(Direction.DOWN);
			cube.rotateSideClockwise(Direction.FRONT);
		} else if (sites.stream().allMatch(s -> s.getDirection().equals(Direction.BACK) || s.getDirection().equals(Direction.RIGHT))) {
			cube.rotateSideClockwise(Direction.RIGHT);
			cube.rotateSideClockwise(Direction.DOWN);
			cube.rotateSideAntiClockwise(Direction.RIGHT);
		} else if (sites.stream().allMatch(s -> s.getDirection().equals(Direction.BACK) || s.getDirection().equals(Direction.LEFT))) {
			cube.rotateSideAntiClockwise(Direction.LEFT);
			cube.rotateSideAntiClockwise(Direction.DOWN);
			cube.rotateSideClockwise(Direction.LEFT);
		}
	}
	
	private void moveCornerFromDownToCorrectPosition(final Site site, final Position position) {
		cube.rotateSideClockwise(Direction.DOWN);
		
		Site adjacentSite = getNextSite(site);
		int i = 0;
		while (!adjacentSite.getPieceAt(position.getX(), position.getY()).getColor().equals(adjacentSite.getColor()) && i++ < 4) {
			cube.rotateSideClockwise(Direction.DOWN);
			adjacentSite = getNextSite(adjacentSite);
		}
		
		if (position.getY() == 0) {
			adjacentSite = getPreviousSite(adjacentSite);
			cube.rotateSideClockwise(adjacentSite.getDirection());
			cube.rotateSideClockwise(Direction.DOWN);
			cube.rotateSideAntiClockwise(adjacentSite.getDirection());
		} else {
			adjacentSite = getNextSite(adjacentSite);
			cube.rotateSideAntiClockwise(adjacentSite.getDirection());
			cube.rotateSideAntiClockwise(Direction.DOWN);
			cube.rotateSideClockwise(adjacentSite.getDirection());
		}
	}
}
