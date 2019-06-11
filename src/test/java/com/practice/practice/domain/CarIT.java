package com.practice.practice.domain;


import com.practice.practice.dto.CarDto;
import com.practice.practice.dto.NewCarRequest;
import com.practice.practice.shared.CarType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.HttpClientErrorException;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class CarIT {

    private WebTestClient webTestClient;


    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setup(){
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port + "/api/cars")
                .responseTimeout(Duration.ofMinutes(1))
                .build();
    }

    @Test
    void getAll(){
        List<CarDto> cars;
        cars = this.webTestClient.get()
                .exchange().expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBodyList(CarDto.class)
                .returnResult()
                .getResponseBody();

        for(CarDto car: cars){
            System.out.println(car.plateNumber);
        }
    }

    @Test
    void newCar(){
        NewCarRequest newCarRequest = NewCarRequest.builder()
                .dailyCost(7000L)
                .plateNumber("HGB-546")
                .type(CarType.Sedan)
                .build();
        CarDto carDto = this.webTestClient.post()
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(newCarRequest)
                .exchange().expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(CarDto.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(carDto);
        assertEquals(carDto.isRented, false);
        assertEquals(carDto.dailyCost, newCarRequest.dailyCost);
        assertEquals(carDto.plateNumber, newCarRequest.plateNumber);
        assertEquals(carDto.type, newCarRequest.type);
    }

    @Test
    void newCarFail(){
        NewCarRequest newCarRequest = NewCarRequest.builder()
                .dailyCost(3000L)
                .plateNumber("HGB-546")
                .type(CarType.Sedan)
                .build();
        NewCarRequest newCarRequest1 = NewCarRequest.builder()
                .dailyCost(7000L)
                .plateNumber("HGBD-546")
                .type(CarType.Sedan)
                .build();

        String costError = this.webTestClient.post()
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(newCarRequest)
                .exchange().expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        String plateNumberError = this.webTestClient.post()
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .syncBody(newCarRequest)
                .exchange().expectStatus().is4xxClientError()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertNotNull(costError);
        assertNotNull(plateNumberError);

    }
}
