package newbuilder;

import newmodel.Cube;
import reader.JsonReader;
import reader.model.ImportCube;

public class CubeBuilder {
	public Cube buildCubeFromJson(final String pathToJson) {
		final Cube cube = new Cube();

		final JsonReader jsonReader = new JsonReader();
		final ImportCube importCube = jsonReader.readCube(pathToJson);


		return cube;
	}
}
