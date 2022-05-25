package com.cathay.HomeworkDemo;

import com.cathay.HomeworkDemo.model.request.CreateCurrencyInfoRequest;
import com.cathay.HomeworkDemo.model.request.UpdateCurrencyInfoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class HomeworkDemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@Order(1)
	void queryCoinDesk() throws Exception {
		mockMvc.perform(get("/queryCoinDesk")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.time").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.bpi").isNotEmpty());
	}

	@Test
	@Order(2)
	void queryByCurrency() throws Exception {
		mockMvc.perform(get("/queryByCurrency/USD")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").isNotEmpty());
	}

	@Test
	@Order(3)
	void createCurrencyInfo() throws Exception {
		CreateCurrencyInfoRequest createCurrencyInfoRequest =new CreateCurrencyInfoRequest();
		createCurrencyInfoRequest.setRate(32240.4137f);
		createCurrencyInfoRequest.setCurrencyChinese("英鎊");
		String content = objectMapper.writeValueAsString(createCurrencyInfoRequest);

		mockMvc.perform(post("/createCurrencyInfo/GPB").content(content)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(4)
	void selectCurrencyInfo() throws Exception {
		mockMvc.perform(get("/selectCurrencyInfo")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(5)
	void updateCurrencyInfo() throws Exception {
		UpdateCurrencyInfoRequest updateCurrencyInfoRequest = new UpdateCurrencyInfoRequest();
		updateCurrencyInfoRequest.setRate(32240.4137f);
		updateCurrencyInfoRequest.setCurrencyChinese("英鎊");
		String content = objectMapper.writeValueAsString(updateCurrencyInfoRequest);

		mockMvc.perform(patch("/updateCurrencyInfo/USD").content(content)
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	@Order(6)
	void deleteCurrencyInfo() throws Exception {
		mockMvc.perform(delete("/deleteCurrencyInfo/USD")
						.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
