package com.report.services.excel;

import com.report.domain.entities.PlanDataEntity;
import com.report.infrastructure.repositories.PlanDataRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static com.report.services.utils.ExcelReportStyleUtils.createDefaultCs;
import static com.report.services.utils.ExcelReportStyleUtils.createTotalHeaderTopCs;

@Service
public class ExcelServiceImpl implements ExcelService {
    private PlanDataRepository planDataRepository;

    public ExcelServiceImpl(PlanDataRepository planDataRepository) {
        this.planDataRepository = planDataRepository;
    }

    @Override
    public List<PlanDataEntity> getPlanData() {
//        return planDataRepository.findAll();
        return planDataRepository.getPartPlanData();
    }


    @Override
    public byte[] getExcelReport() throws IOException {
        List<PlanDataEntity> partPlanData = planDataRepository.getPartPlanData();

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("2024 plan");

        CellStyle csDefault = createDefaultCs(wb);
        CellStyle csTotalHeaderTop = createTotalHeaderTopCs(wb);


        String[] refineryName = {
                "Volgograd",
                "Perm",
                "Nizhny",
                "Ukhta"
        };


        createHeaders(
                wb,
                sheet,
                csTotalHeaderTop,
                refineryName
        );

        createPlanPart(
                wb,
                sheet,
                csDefault,
                partPlanData
        );

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        wb.write(baos);
        byte[] result = baos.toByteArray();
        baos.close();
        wb.close();
        return result;
    }


    private void createHeaders(
            XSSFWorkbook wb,
            XSSFSheet sheet,
            CellStyle csTHTop,
            String[] refineryName
        ) {

        Row row = sheet.createRow(0);
        for (int i = 0; i < refineryName.length; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(csTHTop);
            cell.setCellValue(refineryName[i]);
        }

    }


    private void createPlanPart(
            XSSFWorkbook wb,
            XSSFSheet sheet,
            CellStyle csDefault,
            List<PlanDataEntity> partPlanData
    ) {

        for (int i = 0; i < partPlanData.size(); i++) {
            Row row = sheet.createRow(i+1);
            Cell cell1 = row.createCell(0);
            cell1.setCellStyle(csDefault);
            cell1.setCellValue(partPlanData.get(i).getVariant());

            Cell cell2 = row.createCell(1);
            cell2.setCellStyle(csDefault);
            cell2.setCellValue(partPlanData.get(i).getPlan());

            Cell cell3 = row.createCell(2);
            cell3.setCellStyle(csDefault);
            cell3.setCellValue(partPlanData.get(i).getOblName());

            Cell cell4 = row.createCell(3);
            cell4.setCellStyle(csDefault);
            cell4.setCellValue(partPlanData.get(i).getPlant());



        }


    }
}
