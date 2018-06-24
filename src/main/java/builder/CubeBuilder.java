package builder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Color;
import model.Cube;
import model.Direction;
import model.Piece;
import model.Position;
import model.Site;

public class CubeBuilder {

	public Cube createCubeFrom(final Map<String, Object> jsonMap) {
		if (null == jsonMap || jsonMap.isEmpty()) {
			return null;
		}

		final List<Object> sidesList = toList(jsonMap.get("cube"));

		final Cube cube = new Cube();

		for (final Object sidesObject : sidesList) {
			final Map<String, Object> sidesMap = toMap(sidesObject);
			cube.addSite(createSite(sidesMap));
		}

		return cube;
	}

	private Site createSite(final Map<String, Object> sidesMap) {
		final Map.Entry<String, Object> sideEntry = sidesMap.entrySet().iterator().next();

		final Site site = new Site();
		final Direction direction = Direction.valueOf(sideEntry.getKey().toUpperCase());
		site.setDirection(direction);

		final Map<String, Object> sideElements = toMap(sideEntry.getValue());
		site.setColor(toColor(sideElements.get("color")));

		final List<Object> areaList = toList(sideElements.get("area"));
		for (final Object areaObject : areaList) {
			final Map<String, Object> areaMap = toMap(areaObject);
			site.addPiece(createPiece(areaMap));
		}

		return site;
	}

	private Piece createPiece(final Map<String, Object> areaMap) {
		final Piece piece = new Piece();

		final int x = Integer.parseInt(areaMap.get("x").toString());
		final int y = Integer.parseInt(areaMap.get("y").toString());
		piece.setPosition(new Position(x, y));

		piece.setColor(toColor(areaMap.get("color")));

		return piece;
	}

	private Map<String, Object> toMap(final Object object) {
		return (HashMap<String, Object>) object;
	}

	private List<Object> toList(final Object object) {
		return (ArrayList) object;
	}

	private Color toColor(final Object object) {
		return Color.valueOf(object.toString().toUpperCase());
	}
}
