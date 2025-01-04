package com.report.services.process;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;


@Service
public class ProcessServiceImpl implements ProcessService {

    public ProcessServiceImpl () {};

    @Override
    public void createPdfFromExcel() throws InterruptedException, IOException {

//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "/bin/sh",
//                "-c",
//                "cd",
//                "/home/michaeleremin/encoder/",
//                "&&",
//                "libreoffice",
//                "--headless",
//                "--convert-to",
//                "'pdf:calc_pdf_Export'",
//                "--outdir",
//                "/home/michaeleremin/encoder/",
//                "plan_3085_30.xlsx"
//        );

//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "/usr/bin/bash",
//                "-c",
//                "cd",
//                "/home/michaeleremin/encoder/",
//                "&&",
//                "cp",
//                "plan_3085_30.xlsx",
//                "plan_3085_301.xlsx"
//        );

//        ProcessBuilder processBuilder = new ProcessBuilder(
//                "/usr/bin/bash",
//                "-c",
//                "cd /home/michaeleremin/encoder/ && cp plan_3085_30.xlsx plan_3085_301.xlsx"
//        );

        ProcessBuilder processBuilder = new ProcessBuilder(
                "/usr/bin/bash",
                "-c",
                "cd /home/michaeleremin/encoder/ && libreoffice --headless --convert-to pdf:calc_pdf_Export --outdir . plan_3085_30.xlsx"
        );

//        processBuilder.directory(new File());
        Process process = processBuilder.start();



        int exitCode = process.waitFor();
        System.out.println("exitCode " + exitCode);



    }
}
