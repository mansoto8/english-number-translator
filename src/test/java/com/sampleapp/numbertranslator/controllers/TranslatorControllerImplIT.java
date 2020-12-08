package com.sampleapp.numbertranslator.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sampleapp.numbertranslator.NumberTranslatorApplication;
import com.sampleapp.numbertranslator.dtos.OutputDTO;
import com.sampleapp.numbertranslator.dtos.TranslationDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = NumberTranslatorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TranslatorControllerImplIT {

    @LocalServerPort
    private int port;

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void translateNumbers_success() throws JsonProcessingException {
        ResponseEntity<OutputDTO> response =
                restTemplate.postForEntity(createURLWithPort("/api/numbers/999"), null,
                        OutputDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nine hundred and ninety nine", response.getBody().getOutput());

        response =
                restTemplate.postForEntity(createURLWithPort("/api/numbers/-999"), null,
                        OutputDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Negative nine hundred and ninety nine", response.getBody().getOutput());
    }

    @Test
    public void getTranslationHistory_success() throws JsonProcessingException {
        ResponseEntity<OutputDTO> resp = restTemplate.postForEntity(createURLWithPort("/api/numbers/999"), null,
                OutputDTO.class);

        List<String> cookies = resp.getHeaders().get("Set-Cookie");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        restTemplate.postForEntity(createURLWithPort("/api/numbers/-999"), entity,
                OutputDTO.class);

        ResponseEntity<Set> response =
                restTemplate.exchange(createURLWithPort("/api/numbers"), HttpMethod.GET, entity, Set.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        Iterator<TranslationDTO> results = response.getBody().iterator();
        Map dto1 = (Map) results.next();
        Map dto2 = (Map) results.next();
        assertEquals(-999, (Integer) dto1.get("number"));
        assertEquals("Negative nine hundred and ninety nine", (String) dto1.get("translation"));
        assertEquals(999, (Integer) dto2.get("number"));
        assertEquals("Nine hundred and ninety nine", (String) dto2.get("translation"));
    }

    @Test
    public void translateNumbers_error_numberTooLarge() throws JsonProcessingException {
        String expected = "400 : [{\"error\":\"error-001\",\"message\":\"Number is too large.\"," +
                "\"detail\":\"Maximum is positive or negative 9,999,999,999.\"}]";

        try {
            restTemplate.postForEntity(createURLWithPort("/api/numbers/999999999999"), null,
                    OutputDTO.class);
        } catch (HttpClientErrorException e) {
            assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode());
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void translateNumbers_cached() throws JsonProcessingException {
        ResponseEntity<OutputDTO> firstResponse =
                restTemplate.postForEntity(createURLWithPort("/api/numbers/999"), null,
                        OutputDTO.class);

        List<String> cookies = firstResponse.getHeaders().get("Set-Cookie");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", cookies.stream().collect(Collectors.joining(";")));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<OutputDTO> response =
                restTemplate.postForEntity(createURLWithPort("/api/numbers/999"), entity,
                        OutputDTO.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Nine hundred and ninety nine", response.getBody().getOutput());
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
