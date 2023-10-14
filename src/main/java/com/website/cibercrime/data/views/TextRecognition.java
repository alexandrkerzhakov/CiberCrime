package com.website.cibercrime.data.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.Route;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Route("textRecognition")
public class TextRecognition extends VerticalLayout {
    private final Button button = new Button("Загрузить файл");

    private final TextArea text = new TextArea("Полученный текст");
    private final MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
    private final Upload upload = new Upload(buffer);

    public TextRecognition() {
        addClassName("textRecognition-view");
        add(button, upload, text);
        uploadFile();
    }

    private void uploadFile() {
        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream inputStream = buffer.getInputStream(fileName);

            BufferedImage image = null;
            try {
                image = ImageIO.read(inputStream);
                System.out.println("ImageIO.read");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Tesseract tesseract = new Tesseract();
            tesseract.setLanguage("eng");
            tesseract.setDatapath("D:\\Tesseract-OCR\\tessdata");
            String result = null;
            try {
                result = tesseract.doOCR(image);
                System.out.println(result);
            } catch (TesseractException e) {
                throw new RuntimeException(e);
            }

            text.setValue(result);
            System.out.println(result);


//                InputStream inputStream = buffer.getInputStream(fileName);
//                System.out.println(fileName);
//
//                String text = new BufferedReader(
//                        new InputStreamReader(inputStream, StandardCharsets.UTF_8))
//                        .lines()
//                        .collect(Collectors.joining("\n"));
//
//                System.out.println(text);

            // Do something with the file data
            // processFile(inputStream, fileName);
        });
    }

}
