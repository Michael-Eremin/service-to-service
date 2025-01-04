package com.report.services.excel;

import com.report.domain.entities.PlanDataEntity;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    List<PlanDataEntity> getPlanData() throws IOException;
    byte[] getExcelReport() throws IOException;

}

