package newmodel;

import java.util.ArrayList;
import java.util.List;

public class Site {
	private List<Piece> pieces;

	private Orientation orientation;

	public Site(final int numberOfPieces) {
		pieces = new ArrayList<>(numberOfPieces);
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(final List<Piece> pieces) {
		this.pieces = pieces;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(final Orientation orientation) {
		this.orientation = orientation;
	}
}
