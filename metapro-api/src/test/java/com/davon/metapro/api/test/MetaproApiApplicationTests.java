package com.davon.metapro.api.test;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.davon.dvnfrm.util.SessionIdentifierGenerator;
import com.davon.metapro.api.MetaproApiApplication;
import com.davon.metapro.api.domain.MessageDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MetaproApiApplication.class)
@WebAppConfiguration
@ActiveProfiles("scratch")
public class MetaproApiApplicationTests {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MetaproApiApplicationTests.class);

	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testHome() throws Exception {

		SessionIdentifierGenerator generator = new SessionIdentifierGenerator();

		ResultActions result = this.mvc.perform(get("/message/getAll"))
				.andExpect(status().isOk());

		LOGGER.debug(result.andReturn().getResponse().getContentAsString());

		MessageDTO testMessage = new MessageDTO();
		String messageContent = generator.nextSessionId();

		testMessage.setMessage(messageContent);

		ResultActions result2 = mvc.perform(post("/message/add")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(testMessage)));
		//
		// .andExpect(status().isOk()));

		LOGGER.debug(result2.andReturn().getResponse().getContentAsString());

		ResultActions result3 = this.mvc.perform(get("/message/getAll"))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString(messageContent)))
				.andExpect(status().isOk());

		LOGGER.debug(result3.andReturn().getResponse().getContentAsString());

		testMessage.setMessage(null);

		ResultActions result4 = mvc.perform(post("/message/add")
				.contentType(TestUtil.APPLICATION_JSON_UTF8)
				.content(TestUtil.convertObjectToJsonBytes(testMessage)));

	}

}
