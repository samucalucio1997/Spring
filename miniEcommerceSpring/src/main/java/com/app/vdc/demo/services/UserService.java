package com.app.vdc.demo.services;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.vdc.demo.Config.FilestorageProperties;
import com.app.vdc.demo.Model.User;
import com.app.vdc.demo.repository.UserRepository;


@Service
public class UserService implements UserDetailsService{


    @Autowired
    private UserRepository Consumer;
    
    private final Path fileStoraged; 
    
      

   public UserService(FilestorageProperties fileStoraged) {
        this.fileStoraged = Paths.get(fileStoraged.getUploadDir()).toAbsolutePath()
        .normalize();
    }


public User CriarUser(User usuario, MultipartFile file) throws IOException{
        User user =(User) Consumer.findByUsername(usuario.getUsername());
        if(user != null){
           throw new RuntimeException("Usuario já existe");
        }
        if (!file.isEmpty()) {
            String nomeArquivo = file.getOriginalFilename();
            Path pasta = fileStoraged.resolve(nomeArquivo).toAbsolutePath().normalize();
            file.transferTo(pasta);
            usuario.setImagem(nomeArquivo);
        }
        usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));
        this.Consumer.save(usuario);
        return usuario;
    }


@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // TODO Auto-generated method stub
    return Consumer.findByUsername(username);
}  





    // @Override
    // public boolean AdicionarCarrinho(Produto produto, User cliente) {
    //     if (cliente.getCarrinho() != null && Consumer.findById(cliente.getId()) != null) {
    //         cliente.getCarrinho().getProdutos().add(produto);
    //         System.out.println(Consumer.save(cliente));
    //         return true;
    //     } else {
    //         if(cliente.getCarrinho()!=null){
    //             cliente.setCarrinho(new Carrinho(new ArrayList<>()));
    //             System.out.println(cliente.getCarrinho().getProdutos().add(produto));
    //             return true;
    //         }
    //         return false;
    //     }
    //     // TODO Auto-generated method stub
    //     // throw new UnsupportedOperationException("Unimplemented method
    //     // 'AdicionarCarrinho'");
    // }

    // @Override
    // public boolean EfetuarPedido() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'EfetuarPedido'");
    // }

    // @Override
    // public boolean RemoverdoCarrinho(Long id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'RemoverdoCarrinho'");
    // }

    // @Override
    // public boolean EditarCarrinho(Long id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'EditarCarrinho'");
    // }

    // @Override
    // public ArrayList<Produto> ListarItensCarrinho() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'ListarItensCarrinho'");
    // }



    
    

}

