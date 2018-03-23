package reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import reader.model.ImportCube;

public class JsonReader {

	public Map<String, Object> createMapFrom(final String fileName) {
		try {
			final byte[] bytes = Files.readAllBytes(Paths.get(fileName));
			return new ObjectMapper().readValue(bytes, HashMap.class);
		} catch (final JsonParseException j) {

		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return java.util.Collections.emptyMap();
	}

	public ImportCube readCube(final String fileName) {
		try {
			final byte[] bytes = Files.readAllBytes(Paths.get(fileName));
			final ObjectMapper objectMapper = new ObjectMapper();

			return objectMapper.readValue(bytes, ImportCube.class);
		} catch (final IOException e) {
			System.out.println(e);
		}

		return new ImportCube();
	}
}
