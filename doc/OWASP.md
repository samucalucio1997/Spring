# Vulnerabilidades de Segurança (OWASP 2025)

## 🔑 A07:2025 - Authentication Failures

### 📌 Descrição
Falhas de checagem de senhas fracas.

### 💥 Impacto
- Usuario malicioso pode acessar a conta de outro usuário

### 🛡️ Como mitigar
- Implementar autenticação checagem de senha forte
- Implementar autenticação multifator (MFA)
- Bloquear contas após tentativas de login falhas

### 🖼️ Exemplo / Ilustração
```java
@PostMapping("/login")// não há nenhuma checagem de validade de senha
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
                .token(TokenUtil.encodeToken(user))
                .usuarioDto(usuarioDto)
                .build();
    }
```

---

## 📉 A09:2025 - Security Logging and Alerting Failures

### 📌 Descrição
Essa vulnerabilidade ocorre quando o sistema não registra eventos importantes ou não monitora atividades suspeitas adequadamente. Devido
à falta de bibliotecas de logging.

### ⚠️ Exemplos
- Falta de logs de login e falhas de autenticação

### 💥 Impacto
- Ataques passam despercebidos
- Dificuldade em investigar incidentes

### 🛡️ Como mitigar
- Implementar logging adequado
- Monitorar eventos críticos
- Adicionar lib de logging confiável como o SLF4J

---

## ⚠️ A10:2025 - Mishandling of Exceptional Conditions

### 📌 Descrição
Ocorre quando erros e exceções não são tratados corretamente, expondo informações internas do sistema ou permitindo comportamento inesperado.

### ⚠️ Exemplos
- Exibição de stack trace para o usuário
- Mensagens de erro com detalhes técnicos
- Falhas não tratadas que derrubam o sistema
- Retorno de informações sensíveis em erros

### 💥 Impacto
- Vazamento de informações internas
- Facilitação de ataques
- Instabilidade da aplicação

### 🛡️ Como mitigar
- Tratar exceções globalmente
- Exibir mensagens genéricas para o usuário
- Registrar erros detalhados apenas no backend
- Evitar exposição de detalhes técnicos

### 🖼️ Exemplo / Ilustração
```java
@PostMapping(value = "/cadastroUser")
     public ResponseEntity<User> PostCadastro(
     @RequestParam(value="file",required = false) MultipartFile file,
     @RequestParam("username") String username,
     @RequestParam("password") String password,
     @RequestParam("first_name") String first_name,
     @RequestParam("last_name") String last_name,
     @RequestParam("email") String email,
     @RequestParam("cep") String cep,
     @RequestParam("numcasa") int numcasa,
     @RequestParam(value="is_staff",required = false,defaultValue = "0") int code
     ) throws IOException {
          User usuario = 
          new User(username, first_name, last_name,
          email, password, cep, numcasa, true);
          System.out.println("code =>"+code); 
          if (code==351622) {
              usuario.setIs_staff(true);
          }
          User usuUser = this.service.CriarUser(usuario,file);
          String msg = usuario.isIs_active()?"está ativo":"ative imedia";
          System.out.println("Bem-Vindo " + username+", "+msg);
          return ResponseEntity.ok(usuUser);
     }
```
```java
public User CriarUser(User usuario, MultipartFile file) throws IOException{
        User user =(User) Consumer.findByUsername(usuario.getUsername());
        if(user != null){
           throw new RuntimeException("Usuario já existe");
        }
        if (file!=null&&!file.isEmpty()) {
            String nomeArquivo = file.getOriginalFilename();
            Path pasta = fileStoraged.resolve(nomeArquivo).toAbsolutePath().normalize();
            file.transferTo(pasta);
            usuario.setImagem(nomeArquivo);
        }
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        this.Consumer.save(usuario);
        return usuario;
    }
```