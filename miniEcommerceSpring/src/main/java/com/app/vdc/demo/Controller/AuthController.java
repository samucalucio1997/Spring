package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.TokenUtil;
import com.app.vdc.demo.dto.UserLoginReturn;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public UserLoginReturn Authentica(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) throws IOException {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication auth = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var user =(User) auth.getPrincipal();

        return UserLoginReturn
                .builder()
                .user(user)
                .token(TokenUtil.encodeToken(user))
                .build();
    }

    @GetMapping("/validate")
    @PreAuthorize("permitAll()")
    public boolean validarToken(@RequestParam("token") String token) {
        try {
            TokenUtil.getSubject(token); // lança exceção se inválido/expirado
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/refresh")
    public String refreshToken(
            @RequestHeader(value = "Authorization", required = false) String authorization) {
        try {
            String token = authorization.substring(7);
            String username = TokenUtil.getSubjectAllowExpired(token);

//            JWT.require(Algorithm.HMAC256("grazinads"))
//                    .withIssuer("Auth")
//                    .build()
//                    .verify(token);

            var userDetails = this.userDetailsService.loadUserByUsername(username);
            // se sua User for a classe com.app.vdc.demo.Model.User, faça o cast:
            User user = (User) userDetails;

            return TokenUtil.encodeToken(user);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o token: " + e.getMessage());
        }
    }

}
