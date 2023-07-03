package com.martygo.feshow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class WishTest {
	private static final String API_ROOT = "http://localhost:8080/api/v1/wish";

	@Test
	public void getAllWishes() {
		get(API_ROOT)
        .then()
        .assertThat()
        .statusCode(200);
	}

}
