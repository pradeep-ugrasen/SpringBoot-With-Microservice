package com.cubisoft.service;

import com.cubisoft.entity.OrderEntity;
import com.cubisoft.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final OrderRepository orderRepository;

    /**
     * Generate professional Excel report with proper styling, types, and formatting
     */
    public byte[] generateProfessionalOrderReport() throws Exception {
        List<OrderEntity> orders = orderRepository.findAll();

        // SXSSFWorkbook for low memory usage
        SXSSFWorkbook workbook = new SXSSFWorkbook(100); // keep 100 rows in memory
        Sheet sheet = workbook.createSheet("Orders Report");

        // =====================
        // Styles
        // =====================

        // Header Style
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFont(headerFont);

        // Data Style
        CellStyle dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);

        // Currency Style
        CellStyle currencyStyle = workbook.createCellStyle();
        currencyStyle.cloneStyleFrom(dataStyle);
        DataFormat df = workbook.createDataFormat();
        currencyStyle.setDataFormat(df.getFormat("₹#,##0.00"));

        // Boolean Style
        CellStyle booleanStyle = workbook.createCellStyle();
        booleanStyle.cloneStyleFrom(dataStyle);

        // Date Style (if needed)
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.cloneStyleFrom(dataStyle);
        dateStyle.setDataFormat(df.getFormat("dd-MMM-yyyy"));

        // =====================
        // Header Row
        // =====================
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Order ID", "Order Name", "Quantity", "Price", "In Stock", "Order Date"};

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }

        // =====================
        // Data Rows
        // =====================
        int rowNum = 1;
        for (OrderEntity order : orders) {
            Row row = sheet.createRow(rowNum++);

            // Alternate row coloring
            if (rowNum % 2 == 0) {
                CellStyle altStyle = workbook.createCellStyle();
                altStyle.cloneStyleFrom(dataStyle);
                altStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
                altStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                row.setRowStyle(altStyle);
            }

            // Order ID → Numeric
            Cell idCell = row.createCell(0, CellType.NUMERIC);
            idCell.setCellValue(order.getOrderId());
            idCell.setCellStyle(dataStyle);

            // Order Name → String
            Cell nameCell = row.createCell(1, CellType.STRING);
            nameCell.setCellValue(order.getOrderName());
            nameCell.setCellStyle(dataStyle);

            // Quantity → Numeric
            Cell qtyCell = row.createCell(2, CellType.NUMERIC);
            qtyCell.setCellValue(order.getQuantity());
            qtyCell.setCellStyle(dataStyle);

            // Price → Currency
            Cell priceCell = row.createCell(3, CellType.NUMERIC);
            priceCell.setCellValue(order.getPrice());
            priceCell.setCellStyle(currencyStyle);

            // In Stock → Boolean
            Cell stockCell = row.createCell(4, CellType.BOOLEAN);
            stockCell.setCellValue(order.getQuantity() > 0);
            stockCell.setCellStyle(booleanStyle);

            // Order Date → LocalDate
            Cell dateCell = row.createCell(5, CellType.STRING);
            dateCell.setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
            dateCell.setCellStyle(dateStyle);
        }

        // =====================
        // Auto-size columns
        // =====================
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // =====================
        // Optional: Freeze header
        // =====================
        sheet.createFreezePane(0, 1);

        // =====================
        // Optional: Summary Row
        // =====================
        int summaryRowNum = orders.size() + 2;
        Row summaryRow = sheet.createRow(summaryRowNum);
        Cell summaryLabel = summaryRow.createCell(0);
        summaryLabel.setCellValue("Total Orders:");
        summaryLabel.setCellStyle(headerStyle);

        Cell totalOrdersCell = summaryRow.createCell(1);
        totalOrdersCell.setCellValue(orders.size());
        totalOrdersCell.setCellStyle(dataStyle);

        // =====================
        // Write to byte array
        // =====================
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.dispose(); // clear temp files
        workbook.close();

        return outputStream.toByteArray();
    }

    /**
     * Optionally save to file
     */
    public String saveReportToFile(byte[] excelData) throws Exception {
        String fileName = "orders-report-" + LocalDate.now() + ".xlsx";
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            fileOut.write(excelData);
        }
        return fileName;
    }
}
