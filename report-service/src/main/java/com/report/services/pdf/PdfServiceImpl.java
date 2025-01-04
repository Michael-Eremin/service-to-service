package com.report.services.pdf;



import com.report.services.excel.ExcelService;
import com.report.services.libreoffice.LibreOfficeService;
import com.report.services.process.ProcessService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Paths;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.TIMES_ROMAN;

@Service
public class PdfServiceImpl implements PdfService {

    ProcessService processService;

    public PdfServiceImpl(
            @Autowired ProcessService processService

            ) {
        this.processService = processService;

    }

    @Override
    public void createPdfFromExcel() throws Exception {
        processService.createPdfFromExcel();

    }

}



