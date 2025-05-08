package io.github.inertia4j.spring.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class Inertia4jSpringApplicationTests {

	@Autowired
	MockMvc mvc;

	@MockBean
	private RecordRepository recordRepository;

	private final List<Record> mockRecords = List.of(
			new Record(1, "John Doe"),
			new Record(2, "Jane Smith"),
			new Record(3, "Alice Johnson")
	);

	@BeforeEach
	void setup() {
		Mockito.when(recordRepository.findAll()).thenReturn(mockRecords);
	}

	@Test
	void indexPageHtmlRendering() throws Exception {
		String expectedHtml = """
<!doctype html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <link rel="icon" type="image/svg+xml" href="/vite.svg" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>App</title>
  </head>
  <body>
    <div id="app" data-page='{"component":"records/Index","props":{"records":[{"id":1,"name":"John Doe"},{"id":2,"name":"Jane Smith"},{"id":3,"name":"Alice Johnson"}]},"url":"/","version":"latest","encryptHistory":false,"clearHistory":false}'></div>
    <script type="module" src="/src/main.tsx"></script>
  </body>
</html>
""";

		mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(result -> {
				String actual = result.getResponse().getContentAsString()
					.replaceAll("\\r\\n", "\n")
					.trim();
				String expected = expectedHtml
					.replaceAll("\\r\\n", "\n")
					.trim();
				assertEquals(expected, actual);
			});
	}

	@Test
	void indexPageJsonRendering() throws Exception {
		String expectedJson = """
{"component":"records/Index","props":{"records":[{"id":1,"name":"John Doe"},{"id":2,"name":"Jane Smith"},{"id":3,"name":"Alice Johnson"}]},"url":"/","version":"latest","encryptHistory":false,"clearHistory":false}
		""".stripTrailing();

		mvc.perform(get("/").header("X-Inertia", "true"))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedJson));
	}

	@Test
	void versionConflict() throws Exception {
		mvc.perform(get("/").header("X-Inertia-Version", "10"))
			.andExpect(status().isConflict())
			.andExpect(header().string("X-Inertia-Location", "/"));
	}

}
