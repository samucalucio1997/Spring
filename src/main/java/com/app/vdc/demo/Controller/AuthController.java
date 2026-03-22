package com.app.vdc.demo.Controller;

import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.Security.TokenUtil;
import com.app.vdc.demo.dto.UserLoginReturn;
import com.app.vdc.demo.dto.UsuarioDto;
import com.app.vdc.demo.services.GoogleTokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@PreAuthorize("permitAll()")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleTokenService googleTokenService;

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    @PreAuthorize("permitAll()")
    @PostMapping("/google")
    public UserLoginReturn authenticateWithGoogle(@RequestParam("token") String token) {
        try {
            final var user = googleTokenService.verify(token);
            final var usuarioDto = UsuarioDto.builder()
                    .email(user.getEmail())
                    .nome(user.getNome())
                    .role("ROLE_USER")
                    .build();

            String jwtToken = TokenUtil.generate(user.getEmail());
            return UserLoginReturn
                    .builder()
//                    .googleUsuario(user)
                    .token(jwtToken)
                    .usuarioDto(usuarioDto)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao autenticar com Google: " + e.getMessage());
        }
    }

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

        final var user =(User) auth.getPrincipal();
        final var authorities = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        final var usuarioDto = UsuarioDto.builder()
                .email(user.getEmail())
                .nome(user.getUsername())
                .role(authorities.get(0))
                .build();

        return UserLoginReturn
                .builder()
//                .user(user)
                .token(TokenUtil.encodeToken(user))
                .usuarioDto(usuarioDto)
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
