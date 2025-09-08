package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.TokenUtil;
import com.app.vdc.demo.dto.UserLoginReturn;
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
    public ResponseEntity<UserLoginReturn> Authentica(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ) throws IOException {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication auth = this.authenticationManager
                .authenticate(usernamePasswordAuthenticationToken);

        var user =(User) auth.getPrincipal();

        return ResponseEntity.ok(new UserLoginReturn(user, TokenUtil.encodeToken(user)));
    }

    @GetMapping("/validate")
    @PreAuthorize("PermitAll()")
    public String validarToken(String token) {
        try {
            String subject = TokenUtil.getSubject(token);
            User user = (User) userDetailsService.loadUserByUsername(subject);
            return "Token válido para o usuário: " + user.getUsername();
        } catch (Exception e) {
            return "Token inválido: " + e.getMessage();
        }
    }

    @GetMapping("/refresh")
    @PreAuthorize("PermitAll()")
    public String refreshToken(
            @RequestParam String nome) {
        try {
            final var user = (User) userDetailsService.loadUserByUsername(nome);
            return TokenUtil.encodeToken(user);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar o token: " + e.getMessage());
        }
    }

}
