package com.app.vdc.demo.Controller;

import com.app.vdc.demo.utils.AwsService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {

    private final AwsService awsService;

    @GetMapping("/img")
    public ResponseEntity<InputStreamResource> getFile(@RequestParam(name = "nomeArquivo") String nomeArquivo){
        return ResponseEntity.ok()
                        .contentType(MediaType.valueOf(MediaType.IMAGE_PNG_VALUE))
                        .body(awsService.getFileUrl(nomeArquivo));
    }
}
