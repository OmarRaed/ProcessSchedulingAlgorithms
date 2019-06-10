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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;

public class ExcelWrite {

    public void writeProcesses(Queue<Process> processes, String algorithmTitle, String filePath) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook(); //create new workbook
        HSSFSheet sheet = workbook.createSheet("ProcessSheet"); //create new sheet
        HSSFRow row = sheet.createRow(0); //create new row for algorithm name

        row.createCell(0).setCellValue("Policy : " + algorithmTitle);
        setCellCellsStyleLightBlue(workbook, row.getCell(0)) ;

        //write columns titles
        row = sheet.createRow(1); //create new row for titles
        row.createCell(0).setCellValue("TASK");
        setCellCellsStyleLightBlue(workbook, row.getCell(0)) ;
        row.createCell(1).setCellValue("START TIME");
        setCellCellsStyleLightBlue(workbook, row.getCell(1)) ;
        row.createCell(2).setCellValue("END TIME");
        setCellCellsStyleLightBlue(workbook, row.getCell(2)) ;
        row.createCell(3).setCellValue("DURATION");
        setCellCellsStyleLightBlue(workbook, row.getCell(3)) ;
        row.createCell(4).setCellValue("STATUS");
        setCellCellsStyleLightBlue(workbook, row.getCell(4)) ;

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
            setCellCellsStyleLightGreen(workbook, processRow.getCell(0)) ;
            setCellCellsStyleLightGreen(workbook, processRow.getCell(1)) ;
            setCellCellsStyleLightGreen(workbook, processRow.getCell(2)) ;
            setCellCellsStyleLightGreen(workbook, processRow.getCell(3)) ;

            if (process.getDeadline() > process.getEndTime())
                processRow.createCell(4).setCellValue("S");
            else
                processRow.createCell(4).setCellValue("F");
            
            setCellCellsStyleLightGreen(workbook, processRow.getCell(4)) ;

        }

        //create a ProcessorCalculations class for the calculations
        ProcessorCalculations PC = new ProcessorCalculations();

        //convert queue to list
        List<Process> outputProcess = new ArrayList<>(processes);
        
        //calculate 
        float edf_averageWaitingTime = PC.averageWaitingTime(outputProcess);
        float edf_averageResponseTime = PC.averageResponseTime(outputProcess);
        float edf_averageTurnAroundTime = PC.averageTurnAroundTime(outputProcess);
        float edf_cpuUtilization = PC.cpuUtilization(outputProcess);
        float edf_throughput = PC.Throughput(outputProcess);
        
        writeProcessesInfo(workbook, sheet, edf_averageWaitingTime, edf_averageResponseTime,
                           edf_averageTurnAroundTime, edf_throughput, edf_cpuUtilization) ;

        //write to excel file
        FileOutputStream out = new FileOutputStream(filePath + ".xls");
        workbook.write(out);

        workbook.close(); //close workbook
        out.close(); //close file stream

    }

    public HSSFSheet writeProcessesInfo(HSSFWorkbook workbook, HSSFSheet sheet, float awt, float art, float ata, float throughput,
                                        float utilization) {

        if (sheet.getRow(2) == null) {
            HSSFRow tempRow = sheet.createRow(2); //create new row
            tempRow.createCell(7).setCellValue("AWT");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(awt);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }else{
            HSSFRow tempRow = sheet.getRow(2); //create new row
            tempRow.createCell(7).setCellValue("AWT");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(awt);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }

        if (sheet.getRow(3) == null) {
            HSSFRow tempRow = sheet.createRow(3); //create new row
            tempRow.createCell(7).setCellValue("ART");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(art);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }else{
            HSSFRow tempRow = sheet.getRow(3); //create new row
            tempRow.createCell(7).setCellValue("ART");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(art);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }

        if (sheet.getRow(4) == null) {
            HSSFRow tempRow = sheet.createRow(4); //create new row
            tempRow.createCell(7).setCellValue("ATA");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(ata);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }else{
            HSSFRow tempRow = sheet.getRow(4); //create new row
            tempRow.createCell(7).setCellValue("ATA");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(ata);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }

        if (sheet.getRow(5) == null) {
            HSSFRow tempRow = sheet.createRow(5); //create new row
            tempRow.createCell(7).setCellValue("Throughput");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(throughput);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }else{
            HSSFRow tempRow = sheet.getRow(5); //create new row
            tempRow.createCell(7).setCellValue("Throughput");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(throughput);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }

        if (sheet.getRow(6) == null) {
            HSSFRow tempRow = sheet.createRow(6); //create new row
            tempRow.createCell(7).setCellValue("Utilization");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(utilization);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }else{
            HSSFRow tempRow = sheet.getRow(6); //create new row
            tempRow.createCell(7).setCellValue("Utilization");
            setCellCellsStyleLightBlue(workbook, tempRow.getCell(7)) ;
            tempRow.createCell(8).setCellValue(utilization);
            setCellCellsStyleLightGreen(workbook, tempRow.getCell(8)) ;
        }
        
        return sheet;

    }

    public HSSFSheet writeProcessesToSheet(HSSFWorkbook workbook, Queue<Process> processes, String algorithmTitle, HSSFSheet sheet) {

        HSSFRow row = sheet.createRow(0); //create new row for algorithm name

        row.createCell(0).setCellValue("Policy : " + algorithmTitle);
        setCellCellsStyleLightBlue(workbook, row.getCell(0)) ;

        //write columns titles
        row = sheet.createRow(1); //create new row for titles
        row.createCell(0).setCellValue("TASK");
        setCellCellsStyleLightBlue(workbook, row.getCell(0)) ;
        row.createCell(1).setCellValue("START TIME");
        setCellCellsStyleLightBlue(workbook, row.getCell(1)) ;
        row.createCell(2).setCellValue("END TIME");
        setCellCellsStyleLightBlue(workbook, row.getCell(2)) ;
        row.createCell(3).setCellValue("DURATION");
        setCellCellsStyleLightBlue(workbook, row.getCell(3)) ;
        row.createCell(4).setCellValue("STATUS");
        setCellCellsStyleLightBlue(workbook, row.getCell(4)) ;

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
            setCellCellsStyleLightGreen(workbook, processRow.getCell(0)) ;
            setCellCellsStyleLightGreen(workbook, processRow.getCell(1)) ;
            setCellCellsStyleLightGreen(workbook, processRow.getCell(2)) ;
            setCellCellsStyleLightGreen(workbook, processRow.getCell(3)) ;

            if (process.getDeadline() > process.getEndTime())
                processRow.createCell(4).setCellValue("S");
            else
                processRow.createCell(4).setCellValue("F");

            setCellCellsStyleLightGreen(workbook, processRow.getCell(4)) ;

        }

        return sheet;

    }

    private void setCellCellsStyleLightBlue(HSSFWorkbook workbook, Cell cell) {

        CellStyle styleHeading = workbook.createCellStyle();
        styleHeading.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        Font font = workbook.createFont();
        font.setFontName("CALIBRI");
        font.setFontHeightInPoints((short) 11);
        styleHeading.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeading.setAlignment(HorizontalAlignment.CENTER);
        styleHeading.setFont(font);
        styleHeading.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        styleHeading.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(styleHeading);

    }

    private void setCellCellsStyleLightGreen(HSSFWorkbook workbook, Cell cell) {

        CellStyle styleHeading = workbook.createCellStyle();
        styleHeading.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        Font font = workbook.createFont();
        font.setFontName("CALIBRI");
        font.setFontHeightInPoints((short) 11);
        styleHeading.setVerticalAlignment(VerticalAlignment.CENTER);
        styleHeading.setAlignment(HorizontalAlignment.CENTER);
        styleHeading.setFont(font);
        styleHeading.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        styleHeading.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cell.setCellStyle(styleHeading);

    }

}
