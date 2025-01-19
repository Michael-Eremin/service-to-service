package com.report.interfaces.rest;


import com.report.domain.entities.PlanDataEntity;
import com.report.services.excel.ExcelService;
import com.report.services.pdf.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report/api/v1/")
public class ReportController {
    private final ExcelService excelService;
    private final PdfService pdfService;

    public ReportController(
            @Autowired ExcelService excelService,
            @Autowired PdfService pdfService
    ) {
        this.excelService = excelService;
        this.pdfService = pdfService;
    }

    @GetMapping("/planData")
    List<PlanDataEntity> getPlanDataList() throws IOException {
        return excelService.getPlanData();
    }


    @GetMapping(value="/excel", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    byte[] getDemoReport() {
        try {
            return excelService.getExcelReport();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

//    @GetMapping(value="/excelToPdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//    byte[] getExcelToPdf() {
//        try {
//            return excelService.getPdfFromExcel();
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

    @PostMapping(value="/excelToPdf")
    void createExcelToPdf() throws Exception {
        System.out.println("excelToPdf");
        pdfService.createPdfFromExcel();
    }

}
