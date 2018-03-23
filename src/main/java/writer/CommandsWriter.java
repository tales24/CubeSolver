package writer;

import java.io.FileWriter;
import java.io.PrintWriter;

import model.Cube;

public class CommandsWriter {

	private Cube cube;
	
	public CommandsWriter(final Cube cube) {
		this.cube = cube;
	}
	
	public void writeCommandsTo(final String file) {
		try (final PrintWriter writer = new PrintWriter(new FileWriter(file))) {
			writer.print(String.join("", cube.getCommands()));
			writer.flush();
		} catch (Exception e) {

		}
	}
}
