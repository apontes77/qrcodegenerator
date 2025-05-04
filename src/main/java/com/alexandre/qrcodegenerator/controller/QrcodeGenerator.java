package com.alexandre.qrcodegenerator.controller;

import com.alexandre.qrcodegenerator.service.QrcodeGeneratorService;
import com.google.zxing.WriterException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/qrcode")
public class QrcodeGenerator {

    private final QrcodeGeneratorService qrcodeGeneratorService;

    public QrcodeGenerator(QrcodeGeneratorService qrcodeGeneratorService) {
        this.qrcodeGeneratorService = qrcodeGeneratorService;
    }

    @PostMapping
    public ResponseEntity<QrcodeResponse> generate(@RequestBody QrcodeRequest qrcodeRequest) throws IOException, WriterException {
       try {
           QrcodeResponse qrcodeResponse = this.qrcodeGeneratorService.generateAndUploadQrCode(qrcodeRequest.text());
           return ResponseEntity.ok(qrcodeResponse);
       } catch (Exception e) {
           return ResponseEntity.internalServerError().build();
       }
    }
}
