package com.report.services.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.lang.Nullable;

public final class ExcelReportStyleUtils {

    public static CellStyle createDefaultCs(XSSFWorkbook wb){
        CellStyle cs = wb.createCellStyle();
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        cs.setAlignment(HorizontalAlignment.RIGHT);
        cs.setWrapText(true);
        XSSFFont font = null;
        boolean boldValue = false;
        try {
            font = createFont(wb, (short) 11, IndexedColors.BLACK.getIndex(), null, boldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cs.setFont(font);
        BorderStyle bs = BorderStyle.THIN;
        cs.setBorderRight(bs);
        cs.setBorderLeft(bs);
        cs.setBorderBottom(bs);
        return cs;
    }


    public static CellStyle createTotalHeaderTopCs(XSSFWorkbook wb){
        CellStyle cs = wb.createCellStyle();
        cs.setVerticalAlignment(VerticalAlignment.CENTER);
        cs.setAlignment(HorizontalAlignment.RIGHT);
        cs.setWrapText(true);
        XSSFFont font = null;
        boolean boldValue = false;
        try {
            font = createFont(wb, (short) 11, IndexedColors.BLACK.getIndex(), null, boldValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        cs.setFont(font);
        BorderStyle bs = BorderStyle.THICK;
        cs.setBorderLeft(bs);
        cs.setBorderTop(bs);
        return cs;
    }

    public static XSSFFont createFont(final XSSFWorkbook wb, @Nullable short fontHeight, short colorIndex,
                                      @Nullable XSSFColor colorForName, boolean boldValue) {
        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints(fontHeight);
        font.setFontName("Arial");
        if (colorForName == null) {
            font.setColor(colorIndex);
        } else {
            font.setColor(colorForName);
        }

        font.setBold(boldValue);
        font.setItalic(false);
        return font;
    }


}
