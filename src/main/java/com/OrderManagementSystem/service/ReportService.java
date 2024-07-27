package com.OrderManagementSystem.service;

import com.OrderManagementSystem.model.Customer;
import com.OrderManagementSystem.model.Order;
import com.OrderManagementSystem.model.Product;
import com.OrderManagementSystem.repository.CustomerRepository;
import com.OrderManagementSystem.repository.OrderRepository;
import com.OrderManagementSystem.repository.ProductRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ReportService(CustomerRepository customerRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public byte[] generateCombinedReport() throws DocumentException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);
        document.open();

        // Add title
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Group 4 Members", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add subtitle
        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Paragraph subtitle = new Paragraph("Combined Report", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        document.add(subtitle);

        document.add(Chunk.NEWLINE);

        // Add customer table
        addCustomerTable(document);

        document.add(Chunk.NEWLINE);

        // Add order table
        addOrderTable(document);

        document.add(Chunk.NEWLINE);

        // Add product table
        addProductTable(document);

        document.close();
        return out.toByteArray();
    }

    public byte[] generateCustomerReport() throws DocumentException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);
        document.open();

        // Add title
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Group 4 Members", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add subtitle
        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Paragraph subtitle = new Paragraph("Customer Report", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        document.add(subtitle);

        document.add(Chunk.NEWLINE);

        // Add customer table
        addCustomerTable(document);

        document.close();
        return out.toByteArray();
    }

    public byte[] generateOrderReport() throws DocumentException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);
        document.open();

        // Add title
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Group 4 Members", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add subtitle
        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Paragraph subtitle = new Paragraph("Order Report", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        document.add(subtitle);

        document.add(Chunk.NEWLINE);

        // Add order table
        addOrderTable(document);

        document.close();
        return out.toByteArray();
    }

    public byte[] generateProductReport() throws DocumentException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, out);
        document.open();

        // Add title
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        Paragraph title = new Paragraph("Group 4 Members", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Add subtitle
        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Paragraph subtitle = new Paragraph("Product Report", subtitleFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);
        document.add(subtitle);

        document.add(Chunk.NEWLINE);

        // Add product table
        addProductTable(document);

        document.close();
        return out.toByteArray();
    }

    public byte[] generateCustomerReportCsv() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID,Name,Phone,Address\n");

        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            builder.append(customer.getId()).append(",");
            builder.append(customer.getName()).append(",");
            builder.append(customer.getPhone()).append(",");
            builder.append(customer.getAddress()).append("\n");
        }

        return builder.toString().getBytes();
    }

    public byte[] generateOrderReportCsv() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID,Customer Name,Order Date,Quantity,Total,Product ID\n");

        List<Order> orders = orderRepository.findAll();
        for (Order order : orders) {
            builder.append(order.getId()).append(",");
            builder.append(order.getCustomerName()).append(",");
            builder.append(order.getOrderDate()).append(",");
            builder.append(order.getQuantity()).append(",");
            builder.append(order.getTotal()).append(",");
            builder.append(order.getProduct().getId()).append("\n");
        }

        return builder.toString().getBytes();
    }

    public byte[] generateProductReportCsv() {
        StringBuilder builder = new StringBuilder();
        builder.append("ID,Name,Price,Stock\n");

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            builder.append(product.getId()).append(",");
            builder.append(product.getName()).append(",");
            builder.append(product.getPrice()).append(",");
            builder.append(product.getStock()).append("\n");
        }

        return builder.toString().getBytes();
    }

    public byte[] generateCustomerReportXls() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Customer Report");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Phone");
        header.createCell(3).setCellValue("Address");

        List<Customer> customers = customerRepository.findAll();
        int rowIndex = 1;
        for (Customer customer : customers) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(customer.getId());
            row.createCell(1).setCellValue(customer.getName());
            row.createCell(2).setCellValue(customer.getPhone());
            row.createCell(3).setCellValue(customer.getAddress());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }

    public byte[] generateOrderReportXls() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Order Report");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Customer Name");
        header.createCell(2).setCellValue("Order Date");
        header.createCell(3).setCellValue("Quantity");
        header.createCell(4).setCellValue("Total");
        header.createCell(5).setCellValue("Product ID");

        List<Order> orders = orderRepository.findAll();
        int rowIndex = 1;
        for (Order order : orders) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(order.getId());
            row.createCell(1).setCellValue(order.getCustomerName());
            row.createCell(2).setCellValue(order.getOrderDate().toString());
            row.createCell(3).setCellValue(order.getQuantity());
            row.createCell(4).setCellValue(order.getTotal());
            row.createCell(5).setCellValue(order.getProduct().getId());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }

    public byte[] generateProductReportXls() throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Product Report");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Price");
        header.createCell(3).setCellValue("Stock");

        List<Product> products = productRepository.findAll();
        int rowIndex = 1;
        for (Product product : products) {
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(product.getId());
            row.createCell(1).setCellValue(product.getName());
            row.createCell(2).setCellValue(product.getPrice());
            row.createCell(3).setCellValue(product.getStock());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }

    private void addCustomerTable(Document document) throws DocumentException {
        List<Customer> customers = customerRepository.findAll();
        document.add(new Paragraph("Customer Report"));
        PdfPTable customerTable = new PdfPTable(4); // 4 columns
        addTableHeader(customerTable, new String[]{"ID", "Name", "Phone", "Address"});
        addCustomerRows(customerTable, customers);
        document.add(customerTable);
    }

    private void addOrderTable(Document document) throws DocumentException {
        List<Order> orders = orderRepository.findAll();
        document.add(new Paragraph("Order Report"));
        PdfPTable orderTable = new PdfPTable(6); // 6 columns
        addTableHeader(orderTable, new String[]{"ID", "Customer Name", "Order Date", "Quantity", "Total", "Product ID"});
        addOrderRows(orderTable, orders);
        document.add(orderTable);
    }

    private void addProductTable(Document document) throws DocumentException {
        List<Product> products = productRepository.findAll();
        document.add(new Paragraph("Product Report"));
        PdfPTable productTable = new PdfPTable(4); // 4 columns
        addTableHeader(productTable, new String[]{"ID", "Name", "Price", "Stock"});
        addProductRows(productTable, products);
        document.add(productTable);
    }

    private void addTableHeader(PdfPTable table, String[] headers) {
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
        for (String header : headers) {
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Phrase(header, font));
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            table.addCell(cell);
        }
    }

    private void addCustomerRows(PdfPTable table, List<Customer> customers) {
        for (Customer customer : customers) {
            table.addCell(String.valueOf(customer.getId()));
            table.addCell(customer.getName());
            table.addCell(customer.getPhone());
            table.addCell(customer.getAddress());
        }
    }

    private void addOrderRows(PdfPTable table, List<Order> orders) {
        for (Order order : orders) {
            table.addCell(String.valueOf(order.getId()));
            table.addCell(order.getCustomerName());  // Using getCustomerName method
            table.addCell(order.getOrderDate().toString());
            table.addCell(String.valueOf(order.getQuantity()));
            table.addCell(String.valueOf(order.getTotal()));
            table.addCell(String.valueOf(order.getProduct().getId()));
        }
    }

    private void addProductRows(PdfPTable table, List<Product> products) {
        for (Product product : products) {
            table.addCell(String.valueOf(product.getId()));
            table.addCell(product.getName());
            table.addCell(String.valueOf(product.getPrice()));
            table.addCell(String.valueOf(product.getStock()));
        }
    }

    public long getCustomerCount() {
        return customerRepository.count();
    }

    public long getOrderCount() {
        return orderRepository.count();
    }

    public long getProductCount() {
        return productRepository.count();
    }
}
