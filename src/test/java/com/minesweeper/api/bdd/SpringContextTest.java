package com.minesweeper.api.bdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.minesweeper.api.ApiApplication;

import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiApplication.class)
@CucumberContextConfiguration
public class SpringContextTest {
	
	@Test
    public void whenSpringContextIsBootstrapped_thenNoExceptions() {
    }

}
