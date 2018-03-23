package DreiT.Rubiks;

import newmodel.Cube;

/**
 * Hello world!
 *
 */
public class App {
	public static void main( final String[] args ) {
		final String pathToJson = args[0];

		final newbuilder.CubeBuilder cubeBuilder = new newbuilder.CubeBuilder();
		final Cube cube = cubeBuilder.buildCubeFromJson(pathToJson);

		// final JsonReader reader = new JsonReader();
		// final Map<String, Object> json = reader.createMapFrom(pathToJson);
		//
		// final CubeBuilder builder = new CubeBuilder();
		// final Cube cube = builder.createCubeFrom(json);
		// final CubeSolver solver = new CubeSolver(cube);
		// solver.solve();
		//
		// final CommandsWriter writer = new CommandsWriter(cube);
		// writer.writeCommandsTo("Testdukoment.txt");
	}
}
