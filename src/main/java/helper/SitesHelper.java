package helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Cube;
import model.Direction;
import model.Piece;
import model.Site;

public class SitesHelper {

	private Cube cube;
	
	public SitesHelper(final Cube cube) {
		this.cube = cube;
	}
	
	public Site getAdjacentSiteForUp(final Piece piece) {
		if (piece.getX() == 0) {
			return cube.getSite(Direction.BACK);
		} else if (piece.getX() == 1) {
			return piece.getY() == 0 ? cube.getSite(Direction.LEFT) : cube.getSite(Direction.RIGHT);
		} else {
			return cube.getSite(Direction.FRONT);
		}
	}
	
	public List<Site> getAdjacentSitesForUp(final Piece piece) {
		final List<Site> sites = new ArrayList<>();
		
		if (piece.getX() == 0) {
			sites.add(cube.getSite(Direction.BACK));
		}
		
		if (piece.getX() == 2) {
			sites.add(cube.getSite(Direction.FRONT));
		}
			
		if (piece.getY() == 0) {
			sites.add(cube.getSite(Direction.LEFT));
		}
		
		if (piece.getY() == 2) {
			sites.add(cube.getSite(Direction.RIGHT));
		}
		
		return sites;
	}
	
	public Site getAdjacentSiteForFront(final Piece piece) {
		if (piece.getX() == 0) {
			return cube.getSite(Direction.UP);
		} else if (piece.getX() == 1) {
			return piece.getY() == 0 ? cube.getSite(Direction.LEFT) : cube.getSite(Direction.RIGHT);
		} else {
			return cube.getSite(Direction.DOWN);
		}
	}
	
	public List<Site> getAdjacentSitesForFront(final Piece piece) {
		final List<Site> sites = new ArrayList<>();
		
		if (piece.getX() == 0) {
			sites.add(cube.getSite(Direction.UP));
		}
		
		if (piece.getX() == 2) {
			sites.add(cube.getSite(Direction.DOWN));
		}
			
		if (piece.getY() == 0) {
			sites.add(cube.getSite(Direction.LEFT));
		}
		
		if (piece.getY() == 2) {
			sites.add(cube.getSite(Direction.RIGHT));
		}
		
		return sites;
	}
	
	public Site getAdjacentSiteForDown(final Piece piece) {
		if (piece.getX() == 0) {
			return cube.getSite(Direction.FRONT);
		} else if (piece.getX() == 1) {
			return piece.getY() == 0 ? cube.getSite(Direction.LEFT) : cube.getSite(Direction.RIGHT);
		} else {
			return cube.getSite(Direction.BACK);
		}
	}
	
	public List<Site> getAdjacentSitesForDown(final Piece piece) {
		final List<Site> sites = new ArrayList<>();
		
		if (piece.getX() == 0) {
			sites.add(cube.getSite(Direction.FRONT));
		}
		
		if (piece.getX() == 2) {
			sites.add(cube.getSite(Direction.BACK));
		}
			
		if (piece.getY() == 0) {
			sites.add(cube.getSite(Direction.LEFT));
		}
		
		if (piece.getY() == 2) {
			sites.add(cube.getSite(Direction.RIGHT));
		}
		
		return sites;
	}
	
	public Site getAdjacentSiteForBack(final Piece piece) {
		if (piece.getX() == 0) {
			return cube.getSite(Direction.UP);
		} else if (piece.getX() == 1) {
			return piece.getY() == 0 ? cube.getSite(Direction.RIGHT) : cube.getSite(Direction.LEFT);
		} else {
			return cube.getSite(Direction.DOWN);
		}
	}
	
	public List<Site> getAdjacentSitesForBack(final Piece piece) {
		final List<Site> sites = new ArrayList<>();
		
		if (piece.getX() == 0) {
			sites.add(cube.getSite(Direction.UP));
		}
		
		if (piece.getX() == 2) {
			sites.add(cube.getSite(Direction.DOWN));
		}
			
		if (piece.getY() == 0) {
			sites.add(cube.getSite(Direction.RIGHT));
		}
		
		if (piece.getY() == 2) {
			sites.add(cube.getSite(Direction.LEFT));
		}
		
		return sites;
	}
	
	public Site getAdjacentSiteForLeft(final Piece piece) {
		if (piece.getX() == 0) {
			return cube.getSite(Direction.UP);
		} else if (piece.getX() == 1) {
			return piece.getY() == 0 ? cube.getSite(Direction.BACK) : cube.getSite(Direction.FRONT);
		} else {
			return cube.getSite(Direction.DOWN);
		}
	}
	
	public List<Site> getAdjacentSitesForLeft(final Piece piece) {
		final List<Site> sites = new ArrayList<>();
		
		if (piece.getX() == 0) {
			sites.add(cube.getSite(Direction.UP));
		}
		
		if (piece.getX() == 2) {
			sites.add(cube.getSite(Direction.DOWN));
		}
			
		if (piece.getY() == 0) {
			sites.add(cube.getSite(Direction.BACK));
		}
		
		if (piece.getY() == 2) {
			sites.add(cube.getSite(Direction.FRONT));
		}
		
		return sites;
	}
	
	public Site getAdjacentSiteForRight(final Piece piece) {
		if (piece.getX() == 0) {
			return cube.getSite(Direction.UP);
		} else if (piece.getX() == 1) {
			return piece.getY() == 0 ? cube.getSite(Direction.FRONT) : cube.getSite(Direction.BACK);
		} else {
			return cube.getSite(Direction.DOWN);
		}
	}
	
	public List<Site> getAdjacentSitesForRight(final Piece piece) {
		final List<Site> sites = new ArrayList<>();
		
		if (piece.getX() == 0) {
			sites.add(cube.getSite(Direction.UP));
		}
		
		if (piece.getX() == 2) {
			sites.add(cube.getSite(Direction.DOWN));
		}
			
		if (piece.getY() == 0) {
			sites.add(cube.getSite(Direction.FRONT));
		}
		
		if (piece.getY() == 2) {
			sites.add(cube.getSite(Direction.BACK));
		}
		
		return sites;
	}
	
	public Site getAdjacentSiteFor(final Direction direction, final Piece piece) {
		switch (direction) {
			case FRONT: return getAdjacentSiteForFront(piece);
			case BACK: return getAdjacentSiteForBack(piece);
			case UP: return getAdjacentSiteForUp(piece);
			case DOWN: return getAdjacentSiteForDown(piece);
			case LEFT: return getAdjacentSiteForLeft(piece);
			case RIGHT: return getAdjacentSiteForRight(piece);
			default: return null;
	}
	}
	
	public Site getAdjacentSiteFor(final Site site, final Piece piece) {
		return getAdjacentSiteFor(site.getDirection(), piece);
	}
	
	public List<Site> getAdjacentSitesFor(final Direction direction, final Piece piece) {
		switch (direction) {
			case FRONT: return getAdjacentSitesForFront(piece);
			case BACK: return getAdjacentSitesForBack(piece);
			case UP: return getAdjacentSitesForUp(piece);
			case DOWN: return getAdjacentSitesForDown(piece);
			case LEFT: return getAdjacentSitesForLeft(piece);
			case RIGHT: return getAdjacentSitesForRight(piece);
			default: return Collections.emptyList();
		}
	}
	
	public List<Site> getAdjacentSitesFor(final Site site, final Piece piece) {
		return getAdjacentSitesFor(site.getDirection(), piece);
	}
}
