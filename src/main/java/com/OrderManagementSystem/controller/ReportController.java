package com.OrderManagementSystem.controller;

import com.OrderManagementSystem.service.ReportService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("")
    public String showReportOptions(Model model) {
        return "reportOptions";
    }

    @GetMapping("/combined")
    public ResponseEntity<byte[]> getCombinedReport() {
        try {
            byte[] pdfReport = reportService.generateCombinedReport();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "combined_report.pdf");

            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
        } catch (DocumentException | IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<byte[]> getCustomerReport() {
        try {
            byte[] pdfReport = reportService.generateCustomerReport();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "customer_report.pdf");

            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
        } catch (DocumentException | IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers/csv")
    public ResponseEntity<byte[]> getCustomerReportCsv() {
        byte[] csvReport = reportService.generateCustomerReportCsv();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "customer_report.csv");

        return new ResponseEntity<>(csvReport, headers, HttpStatus.OK);
    }

    @GetMapping("/customers/xls")
    public ResponseEntity<byte[]> getCustomerReportXls() {
        try {
            byte[] xlsReport = reportService.generateCustomerReportXls();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "customer_report.xls");

            return new ResponseEntity<>(xlsReport, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<byte[]> getOrderReport() {
        try {
            byte[] pdfReport = reportService.generateOrderReport();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "order_report.pdf");

            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
        } catch (DocumentException | IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders/csv")
    public ResponseEntity<byte[]> getOrderReportCsv() {
        byte[] csvReport = reportService.generateOrderReportCsv();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "order_report.csv");

        return new ResponseEntity<>(csvReport, headers, HttpStatus.OK);
    }

    @GetMapping("/orders/xls")
    public ResponseEntity<byte[]> getOrderReportXls() {
        try {
            byte[] xlsReport = reportService.generateOrderReportXls();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "order_report.xls");

            return new ResponseEntity<>(xlsReport, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<byte[]> getProductReport() {
        try {
            byte[] pdfReport = reportService.generateProductReport();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "product_report.pdf");

            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
        } catch (DocumentException | IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/csv")
    public ResponseEntity<byte[]> getProductReportCsv() {
        byte[] csvReport = reportService.generateProductReportCsv();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);
        headers.setContentDispositionFormData("attachment", "product_report.csv");

        return new ResponseEntity<>(csvReport, headers, HttpStatus.OK);
    }

    @GetMapping("/products/xls")
    public ResponseEntity<byte[]> getProductReportXls() {
        try {
            byte[] xlsReport = reportService.generateProductReportXls();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "product_report.xls");

            return new ResponseEntity<>(xlsReport, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
