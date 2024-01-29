package com.app.vdc.demo.services;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.vdc.demo.Model.File;
import com.app.vdc.demo.repository.FileRepository;

@Component
@PropertySource("classpath:static/app.properties")
public class FileService {
        @Value("${path-file}")
	    private String path;
		
		@Value("${tamanho-arquivo}")
	    private Integer tamanhoArquivos;

        @Autowired
		private FileRepository fileRepository;

        public File get(String path) throws IOException {
			File file = new File(path);
		    return file;
		}
        
        public File upload(MultipartFile uploadfile) throws IOException {
			String filename = uploadfile.getOriginalFilename();
			UUID id = UUID.randomUUID();
			if (!fileRepository.findById(id).isEmpty()) {
				throw new RuntimeException("Já existe essa imagem");
			}
		    String fileuuid[] = id.toString().split("/");
		    String uuid = fileuuid[fileuuid.length-1];
		    String filepath = Paths.get(ResourceUtils.getFile("classpath:static").getPath(),id.toString()).toString();
		    File arquivo = new File();
		    arquivo.setNome(uuid);
		    arquivo.setNomeOriginal(filename);
		    arquivo.setPath(filepath);
		    fileRepository.save(arquivo);

			try  {
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new java.io.File(filepath)));
				stream.write(uploadfile.getBytes());
			}catch(Exception e){
				throw e;
			}
		    return arquivo;
		}
}
