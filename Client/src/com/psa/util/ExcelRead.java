package com.psa.util;

import com.psa.model.Process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelRead {

    public List<Process> readProcesses(String filePath) throws FileNotFoundException {

        //create list of Process
        List<Process> processes = new ArrayList<>();

        //Create an object of File class to open xls file
        File src = new File(filePath);

        //Create an object of FileInputStream class to read excel file
        FileInputStream fis = new FileInputStream(src);

        try {
            //Create an object of HSSFWorkbook
            HSSFWorkbook wb = new HSSFWorkbook(fis);
            //specify first sheet in wb
            HSSFSheet Sheet1 = wb.getSheetAt(0);

            //Create a loop to set cells values in every row in
            for (int i = 1; i <= Sheet1.getLastRowNum(); i++) {

                //create object from process
                Process process = new Process();

                //set process name
                process.setProcessName(Sheet1.getRow(i)
                                             .getCell(0)
                                             .getStringCellValue());

                //set process arrival time
                process.setArriveTime((int) Sheet1.getRow(i)
                                                  .getCell(1)
                                                  .getNumericCellValue());

                //set process burst time
                process.setBurstTime((int) Sheet1.getRow(i)
                                                 .getCell(2)
                                                 .getNumericCellValue());
                //set remaining time equals to burst time
                process.setRemainingTime(process.getBurstTime());

                //set process deadline
                process.setDeadline((int) Sheet1.getRow(i)
                                                .getCell(3)
                                                .getNumericCellValue());

                processes.add(process);

            }

        } catch (IOException e) {
        }

        return processes;
    }

}
