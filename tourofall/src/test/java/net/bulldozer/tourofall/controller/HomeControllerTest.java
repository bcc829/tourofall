package net.bulldozer.tourofall.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import net.bulldozer.tourofall.common.controller.HomeController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class HomeControllerTest {
	@Test
	public void testHomePage() throws Exception{
		HomeController c = new HomeController();
		MockMvc mockMvc = standaloneSetup(c).build();

		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}

}