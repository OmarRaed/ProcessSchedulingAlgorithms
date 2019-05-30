package com.psa.client;

import com.psa.algorithms.RoundRobin;
import com.psa.model.Process;
import com.psa.util.ExcelRead;

import com.psa.util.ExcelWrite;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.List;
import java.util.Queue;

public class MainClass {
    public static void main(String[] args) {
        MainClass mainClass = new MainClass();

        ExcelRead ER = new ExcelRead();
        
//        try {
//            List<Process> processes = ER.readProcesses("C:\\Users\\omaar\\Desktop\\RRTEST\\RR.xls");
//            System.out.println("DONE READING");
//            RoundRobin RR = new RoundRobin() ;
//            Queue processesQueue = RR.runAlgorithm(processes, 1);
//            System.out.println("DONE ALGORITHM");
//            ExcelWrite EW = new ExcelWrite() ;
//            EW.writeProcesses(processesQueue, "RoundRobin", "C:\\Users\\omaar\\Desktop\\RRTEST\\RRR.xls");
//            EW.writeProcessesInfo(processesQueue, "RoundRobin", "C:\\Users\\omaar\\Desktop\\RRTEST\\RRRINFO.xls");
//            System.out.println("DONE WRITIING");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
