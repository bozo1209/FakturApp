package com.fakturapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import com.example.fakturapp.R;
import com.fakturapp.model.TemplateTestMain;
import com.itextpdf.text.DocumentException;
//import com.lowagie.text.DocumentException;

//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("***********1******************");
        Context applicationContext1 = getApplicationContext();
        String[] strings1 = applicationContext1.fileList();
        Arrays.stream(strings1).forEach(System.out::println);
        System.out.println("*****************************");
        for (String s : strings1){
            if (s.equalsIgnoreCase("template.pdf")){
                System.out.println("helllllle");
            }
        }
        System.out.println("*************1****************");

        try {


            TemplateTestMain.templateTestMain(getApplicationContext());
        } catch ( IOException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }

        System.out.println("***********2******************");
        Context applicationContext = getApplicationContext();
        String[] strings = applicationContext.fileList();
        Arrays.stream(strings).forEach(System.out::println);
        System.out.println("*****************************");
        String ret = "";
        for (String s : strings){
            if (s.equalsIgnoreCase("temp.txt")){
                System.out.println("helllllle");
                try {
                    FileInputStream fileInputStream = applicationContext.openFileInput(s);
                    if (fileInputStream != null){

                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String receiveString = "";
                        StringBuilder sb = new StringBuilder();
                        while (true){
                            try {
                                if ((receiveString = bufferedReader.readLine()) != null){
                                    sb.append("\n").append(receiveString);
                                }else {
                                    break;
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }


                    fileInputStream.close();
                    ret = sb.toString();
                    }
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        System.out.println("********");
        System.out.println(ret);
        System.out.println("*************2****************");

    }
}