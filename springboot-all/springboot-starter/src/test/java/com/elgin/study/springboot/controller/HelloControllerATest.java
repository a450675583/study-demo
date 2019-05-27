package com.elgin.study.springboot.controller;

import com.elgin.study.springboot.starter.SpringbootStarterApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootStarterApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerATest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/starter-web/hello");
    }

    @Test
    public void testHello() throws Exception {
        ResponseEntity<String> res = restTemplate.getForEntity(base.toString(),String.class);
        assertThat(res.getBody(),equalTo("Hello World!"));
    }
}
