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
			new Record(1, "Deadwing", "Porcupine Tree", null, 2005),
			new Record(2, "White Album", "The Beatles", null, 1968),
			new Record(3, "Sailing the Seas of Cheese", "Primus", null, 1991)
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
    <div id="app" data-page='{&quot;component&quot;:&quot;records/Index&quot;,&quot;props&quot;:{&quot;records&quot;:[{&quot;id&quot;:1,&quot;name&quot;:&quot;Deadwing&quot;,&quot;artist&quot;:&quot;Porcupine Tree&quot;,&quot;coverImage&quot;:null,&quot;yearOfRelease&quot;:2005},{&quot;id&quot;:2,&quot;name&quot;:&quot;White Album&quot;,&quot;artist&quot;:&quot;The Beatles&quot;,&quot;coverImage&quot;:null,&quot;yearOfRelease&quot;:1968},{&quot;id&quot;:3,&quot;name&quot;:&quot;Sailing the Seas of Cheese&quot;,&quot;artist&quot;:&quot;Primus&quot;,&quot;coverImage&quot;:null,&quot;yearOfRelease&quot;:1991}]},&quot;url&quot;:&quot;/&quot;,&quot;version&quot;:&quot;latest&quot;,&quot;encryptHistory&quot;:false,&quot;clearHistory&quot;:false}'></div>
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
{
  "component": "records/Index",
  "props": {
    "records": [
      {
        "id": 1,
        "name": "Deadwing",
        "artist": "Porcupine Tree",
        "coverImage": null,
        "yearOfRelease": 2005
      },
      {
        "id": 2,
        "name": "White Album",
        "artist": "The Beatles",
        "coverImage": null,
        "yearOfRelease": 1968
      },
      {
        "id": 3,
        "name": "Sailing the Seas of Cheese",
        "artist": "Primus",
        "coverImage": null,
        "yearOfRelease": 1991
      }
    ]
  },
  "url": "/",
  "version": "latest",
  "encryptHistory": false,
  "clearHistory": false
}
""".strip().replaceAll("\n", "").replaceAll(" +", "");

		mvc.perform(get("/").header("X-Inertia", "true"))
				.andExpect(status().isOk())
				.andExpect(result -> {
					String actualJson = result.getResponse().getContentAsString()
							.replaceAll("\n", "")
							.replaceAll(" +", "");
					assertEquals(expectedJson, actualJson);
				});
	}

	@Test
	void versionConflict() throws Exception {
		mvc.perform(get("/").header("X-Inertia-Version", "10"))
			.andExpect(status().isConflict())
			.andExpect(header().string("X-Inertia-Location", "/"));
	}

}
