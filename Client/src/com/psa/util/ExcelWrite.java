package com.psa.util;

import com.psa.model.Process;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.LinkedList;
import java.util.Queue;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelWrite {

    public void writeProcesses(Queue<Process> processes, String algorithmTitle, String filePath) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook(); //create new workbook
        HSSFSheet sheet = workbook.createSheet("ProcessSheet"); //create new sheet
        HSSFRow row = sheet.createRow(0); //create new row for algorithm name

        row.createCell(0).setCellValue("Policy : " + algorithmTitle);

        //write columns titles
        row = sheet.createRow(1); //create new row for titles
        row.createCell(0).setCellValue("TASK");
        row.createCell(1).setCellValue("START TIME");
        row.createCell(2).setCellValue("END TIME");
        row.createCell(3).setCellValue("DURATION");
        row.createCell(4).setCellValue("STATUS");

        //initialize row number
        int rowNum = 2;

        Queue<Process> outputProcesses = new LinkedList<>(processes);

        while (!outputProcesses.isEmpty()) {

            HSSFRow processRow = sheet.createRow(rowNum); //create new row
            rowNum++;

            Process process = outputProcesses.poll();

            processRow.createCell(0).setCellValue(process.getProcessName());
            processRow.createCell(1).setCellValue(process.getStartTime());
            processRow.createCell(2).setCellValue(process.getEndTime());
            processRow.createCell(3).setCellValue(process.getBurstTime());

            if (process.getDeadline() > process.getEndTime())
                processRow.createCell(4).setCellValue("S");
            else
                processRow.createCell(4).setCellValue("F");

        }

        //write to excel file
        FileOutputStream out = new FileOutputStream(filePath + ".xls");
        workbook.write(out);

        workbook.close(); //close workbook
        out.close(); //close file stream

    }

}
