package com.school.forum.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author panha
 */
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.school.forum.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class MainService implements UserDetailsService {

    @Value("${school.main.url}")
    private String mainUrl;

    @Autowired
    private RestTemplate restTemplate;

    public UserResponse getUsers(String authToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + authToken);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    mainUrl + "/users/user-detail",
                    HttpMethod.GET,
                    new HttpEntity<>(headers),
                    String.class);

            String responseBody = response.getBody();

            ObjectMapper objectMapper = new ObjectMapper();
            UserResponse userResponse = objectMapper.readValue(responseBody, UserResponse.class);

            return userResponse;
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return null;
    }
}
