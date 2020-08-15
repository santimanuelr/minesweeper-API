package com.minesweeper.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(classes = ApiApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration
public class ApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
