package com.report.services.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class ProcessServiceImpl implements ProcessService {

    private Environment env;

    public ProcessServiceImpl(@Autowired Environment env) {
        this.env = env;
    }

    @Override
    public void createPdfFromExcel() throws InterruptedException, IOException {

        String pathToFileFolder = env.getProperty("spring.placement.convertpdf");
        String pathToBash = env.getProperty("spring.placement.bash");
        String fileName = "plan_3085_30.xlsx";

        ProcessBuilder processBuilder = new ProcessBuilder(
                pathToBash,
                "-c",
                "cd "
                    + pathToFileFolder
                    + " && libreoffice --headless --convert-to pdf:calc_pdf_Export --outdir . "
                    + fileName
        );


//        processBuilder.directory(new File());
        Process process = processBuilder.start();



        int exitCode = process.waitFor();
        System.out.println("exitCode " + exitCode);



    }
}
