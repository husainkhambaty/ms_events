package io.thebuildingblocks.services.eventing;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EventsApplication.class)
@AutoConfigureMockMvc
class EventsApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	void contextLoads() {
	}

	@Test
	public void validateIncorrectMessagePublish() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/events/publish")
					.contentType(MediaType.APPLICATION_JSON)
					.content("{ \"someattribute\":\"some value\" }")
		).andExpect(status().is(400));

	}

	@Test
	public void validateCorrectMessagePublish() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders.post("/events/publish")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{ \"message\":\"valueable event\" }")
		).andExpect(status().is(200));

	}
}
