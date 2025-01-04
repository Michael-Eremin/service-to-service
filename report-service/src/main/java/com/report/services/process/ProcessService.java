package com.report.services.process;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProcessService {
    void createPdfFromExcel() throws InterruptedException, IOException;
}
