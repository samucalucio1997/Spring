package com.app.vdc.demo.services;

import com.app.vdc.demo.dto.GoogleUsuario;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class GoogleTokenService {

    @Value("${client.google.id}")
    private String clientId;

    public GoogleUsuario verify(String token) {

        try {

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                    .Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(clientId))
                    .build();

            GoogleIdToken idToken = verifier.verify(token);

            if (idToken == null) {
                throw new RuntimeException("Token inválido");
            }

            final var payload = idToken.getPayload();

            return GoogleUsuario.builder()
                    .email(payload.getEmail())
                    .nome((String) payload.get("name"))
                    .build();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
