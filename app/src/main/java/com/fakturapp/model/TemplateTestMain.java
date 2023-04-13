package com.fakturapp.model;

//import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

//import org.jsoup.Jsoup;
//import org.jsoup.helper.W3CDom;
//import org.jsoup.nodes.Document;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class TemplateTestMain {
    public static void templateTestMain(android.content.Context context) throws IOException, DocumentException {
        TemplateTestMain templateTestMain = new TemplateTestMain();
        templateTestMain.generatePdfFromHtml4(templateTestMain.parseThymeleafTemplate(), context);
        templateTestMain.readPdf();

        String s = templateTestMain.parseThymeleafTemplate();
        System.out.println("***************html******************");
        System.out.println(s);
        System.out.println("***************html******************");
    }

    public void simpleFile(android.content.Context context){

    }

//    public void generatePdfFromHtml(String html) throws IOException, DocumentException {
//        String path = "C:\\Users\\mateu\\Desktop\\pdf-template\\template.pdf";
//        OutputStream outputStream = new FileOutputStream(path);
//
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(html);
//        renderer.layout();
//        renderer.createPDF(outputStream);
//
//        outputStream.close();
//    }
//
//    public void generatePdfFromHtml2(String html, android.content.Context context) throws IOException, DocumentException {
//        File filesDir = context.getFilesDir();
//        File file = new File(filesDir, "template.pdf");
//        OutputStream outputStream = new FileOutputStream(file);
//
//        ITextRenderer renderer = new ITextRenderer();
//        renderer.setDocumentFromString(html);
//        renderer.layout();
//        renderer.createPDF(outputStream);
//
//        outputStream.close();
//    }

//    public void generatePdfFromHtml3(String html, android.content.Context context) throws IOException {
//        File filesDir = context.getFilesDir();
//        File file = new File(filesDir, "template.pdf");
//        System.out.println("*****************");
//        System.out.println(file.getPath());
//        System.out.println("*****************");
//        Document document = Jsoup.parse(html, "UTF-8");
//        document.outputSettings()
//                .syntax(Document.OutputSettings.Syntax.xml);
//
//        OutputStream outputStream = new FileOutputStream(file);
//        PdfRendererBuilder builder = new PdfRendererBuilder();
//        builder.withUri(file.getPath());
//        builder.toStream(outputStream);
//        builder.withW3cDocument(new W3CDom().fromJsoup(document), filesDir.getPath());
//        builder.run();
//
////        OutputStream outputStream = new FileOutputStream(file);
////
////        PdfRendererBuilder builder = new PdfRendererBuilder();
////        builder.withUri(file.getPath());
////        builder.toStream(outputStream);
////        builder.withW3cDocument(new W3CDom().fromJsoup(doc))
//    }

    public void generatePdfFromHtml4(String html, android.content.Context context) throws IOException, DocumentException {

        String s = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"></meta><title>test</title></head><body><h1>test</h1><h1>moj test</h1></body></html>";


        File filesDir = context.getFilesDir();
        File file = new File(filesDir, "template.pdf");
        String path = file.getPath();
        PdfWriter pdfWriter = null;
        Document document = new Document();
        pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(path));
        document.addAuthor("ja");
        document.setPageSize(PageSize.LETTER);
        document.open();
//        FileInputStream fileInputStream = new FileInputStream(html);
//        File htmlFile = new File(context.getFilesDir(), "t.html");
//        htmlFil
        InputStream fileInputStream = new ByteArrayInputStream(html.getBytes());
        XMLWorkerHelper workerHelper = XMLWorkerHelper.getInstance();
        workerHelper.parseXHtml(pdfWriter, document, fileInputStream);

        document.close();
        pdfWriter.close();
        System.out.println("*********ddddd***********");

        System.out.println(document.getPageSize());
        System.out.println(file.getPath());
        System.out.println("************d***********");



        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("temp.txt", android.content.Context.MODE_PRIVATE));
        outputStreamWriter.write(s);
        outputStreamWriter.close();
    }

    public void readPdf(){

    }

    public String parseThymeleafTemplate(){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setCharacterEncoding("UTF-8");
        System.out.println("**********************");
        System.out.println(templateResolver.getPrefix());
        System.out.println("**********************");
//        templateResolver.setPrefix("file:///android_asset/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("myText", "moj test");

        return templateEngine.process("faktura-template.html", context);
    }
}
