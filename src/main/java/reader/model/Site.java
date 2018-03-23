package reader.model;

import java.util.List;

import newmodel.Color;
import newmodel.Orientation;

public class Site {
	private Color color;

	private List<Area> areas;

	private Orientation orientation;

	public Color getColor() {
		return color;
	}

	public void setColor(final Color color) {
		this.color = color;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(final List<Area> areas) {
		this.areas = areas;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(final Orientation orientation) {
		this.orientation = orientation;
	}
}
