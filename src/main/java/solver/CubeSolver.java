package solver;

import model.Cube;

public class CubeSolver {

	private Cube cube;
	
	
	public CubeSolver(final Cube cube) {
		this.cube = cube;
	}
	
	public void solve() {
		final TopLayerHelper topLayerHelper = new TopLayerHelper(cube);
		topLayerHelper.createCross();
		topLayerHelper.completeFirstSite();
	}
}
