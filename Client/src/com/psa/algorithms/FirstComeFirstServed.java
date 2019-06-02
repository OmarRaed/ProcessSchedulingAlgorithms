package com.psa.algorithms;

import com.psa.model.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FirstComeFirstServed {

    public Queue<Process> runAlgorithm(List<Process> processes) {

        Queue<Process> outputQueue = new LinkedList<>();
        Queue<Process> readyQueue = new LinkedList<>();

        Collections.sort(processes);

        int currentTime = 0;
        
        //get a copy of the list
        List<Process> unarrivedProcesses = new ArrayList<>(processes);
        
        while (unarrivedProcesses.size() != 0 || !readyQueue.isEmpty()) {

            if (!readyQueue.isEmpty()) {

                Process currentProcess = readyQueue.poll();

                currentProcess.setStartTime(currentTime);

                currentTime += currentProcess.getBurstTime();

                currentProcess.setEndTime(currentTime);

                outputQueue.add(currentProcess);
            }else if(currentTime != 0){
                
                //get time when the next process will arrive
                int nextProcessArrivalTime = getNextArrivedProcess(unarrivedProcesses) ;
                
                //create idle process
                Process idleProcess = new Process();
                //set process data
                idleProcess.setProcessName("PIDLE");
                idleProcess.setStartTime(currentTime);
                idleProcess.setArriveTime(currentTime);
                idleProcess.setEndTime(nextProcessArrivalTime);
                idleProcess.setBurstTime(nextProcessArrivalTime - currentTime);
                idleProcess.setPriority(0);
                idleProcess.setDeadline(99999);
                idleProcess.setRemainingTime(0);

                //add idle Process to the ready queue
                outputQueue.add(idleProcess);

                //set current time equal the next arrive process
                currentTime = nextProcessArrivalTime;
            }


            for (int i = 0; i < unarrivedProcesses.size(); i++) {

                Process process = unarrivedProcesses.get(i);

                if (process.getArriveTime() <= currentTime) {

                    readyQueue.add(process);

                    unarrivedProcesses.remove(i);

                    i--;

                }

            }
        }
        return outputQueue;

    }

    public int getNextArrivedProcess(List<Process> processes) {

        int leastArrivalTime = 999999;

        for (Process process : processes)
            if (leastArrivalTime > process.getArriveTime())
                leastArrivalTime = process.getArriveTime();

        return leastArrivalTime;

    }

}


