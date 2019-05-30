package com.psa.util;

import com.psa.model.Process;

import java.io.FileOutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    public void writeProcessesInfo(Queue<Process> processes, String algorithmTitle, String filePath) throws IOException {

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

        Queue<Process> outputProcesses = new LinkedList<>(processes);
        
        ProcessorCalculations PC = new ProcessorCalculations() ;

        for (int rowNum = 2 ; rowNum < 8 ; rowNum++) {

            HSSFRow tempRow = sheet.createRow(2); //create new row
            float awt = PC.averageWaitingTime(new ArrayList(outputProcesses)); 
            tempRow.createCell(0).setCellValue("AWT");
            tempRow.createCell(1).setCellValue(awt);

            tempRow = sheet.createRow(3); //create new row
            float art = PC.averageResponseTime(new ArrayList(outputProcesses));   
            tempRow.createCell(0).setCellValue("ART");
            tempRow.createCell(1).setCellValue(art);

            tempRow = sheet.createRow(4); //create new row
            float ata = PC.averageTurnAroundTime(new ArrayList(outputProcesses));  
            tempRow.createCell(0).setCellValue("ATA");
            tempRow.createCell(1).setCellValue(ata);

            tempRow = sheet.createRow(5); //create new row
            float throughput = PC.Throughput(new ArrayList(outputProcesses));     
            tempRow.createCell(0).setCellValue("Throughput");
            tempRow.createCell(1).setCellValue(throughput);

            tempRow = sheet.createRow(6); //create new row
            float utilization = PC.cpuUtilization(new ArrayList(outputProcesses));
            tempRow.createCell(0).setCellValue("Utilization");
            tempRow.createCell(1).setCellValue(utilization);
            


        }

        //write to excel file
        FileOutputStream out = new FileOutputStream(filePath + ".xls");
        workbook.write(out);

        workbook.close(); //close workbook
        out.close(); //close file stream

    }



}
