package com.psa.algorithms;

import com.psa.model.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FirstComeFirstServed {

    public Queue<Process> runAlgorithm(List<Process> processes) {

        //initialize empty process queue
        Queue<Process> outputQueue = new LinkedList<>();
        Queue<Process> readyQueue = new LinkedList<>();
        
        //get a copy of the list
        List<Process> unarrivedProcesses = new ArrayList<>(processes);

        //sort processes by arrival time
        Collections.sort(unarrivedProcesses);
        
        //set the remaining time for each process equals the burst time
        for(Process p : unarrivedProcesses)
            p.setRemainingTime(p.getBurstTime());
        
        //initialize currentTime
        int currentTime = 0;

        //loop until all process finishes
        while (unarrivedProcesses.size() != 0 || !readyQueue.isEmpty()) {

            //if ready queue is not empty
            if (!readyQueue.isEmpty()) {

                //get process at the head of the ready queue
                Process currentProcess = readyQueue.poll();

                //set process start time equals current time
                currentProcess.setStartTime(currentTime);

                //increment current time by process burst time
                currentTime += currentProcess.getBurstTime();

                //set process end time equals current time
                currentProcess.setEndTime(currentTime);

                //add this process to the output queue
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

            //loop for all unarrived processes
            for (int i = 0; i < unarrivedProcesses.size(); i++) {

                //get process i
                Process process = unarrivedProcesses.get(i);
    
                //if process arrived
                if (process.getArriveTime() <= currentTime) {

                    //then add it to the ready queue
                    readyQueue.add(process);

                    //and remove it from the unarrived queue
                    unarrivedProcesses.remove(i);

                    //and decrement i
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


