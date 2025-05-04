package com.alexandre.qrcodegenerator.service;

import com.alexandre.qrcodegenerator.controller.QrcodeResponse;
import com.alexandre.qrcodegenerator.ports.StoragePort;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class QrcodeGeneratorService {

    private final StoragePort storagePort;

    public QrcodeGeneratorService(StoragePort storagePort) {
        this.storagePort = storagePort;
    }

    public QrcodeResponse generateAndUploadQrCode(String text) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        byte[] pngQrcodeData = pngOutputStream.toByteArray();

        String url = storagePort.uploadFile(pngQrcodeData, UUID.randomUUID().toString(), "image/png");
        return new QrcodeResponse(url);
    }
}
