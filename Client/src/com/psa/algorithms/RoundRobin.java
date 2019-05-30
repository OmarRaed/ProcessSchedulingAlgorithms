package com.psa.algorithms;

import com.psa.model.Process;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin {

    public Queue<Process> runAlgorithm(List<Process> processes, int q) {

        //initialize empty process queue
        Queue<Process> processesQueue = new LinkedList<>();
        Queue<Process> readyQueue = new LinkedList<>();

        //sort processes by arrival time
        Collections.sort(processes);

        //initialize currentTime
        int currentTime = 0;

        //loop until all process finishes
        while (processes.size() != 0 || readyQueue.size() != 0) {

            //if ready queue is not empty
            if (!readyQueue.isEmpty()) {
                
                //get process at the head of the ready queue
                Process process = readyQueue.poll();

                //if process remaining time is less than the quantum
                if (process.getRemainingTime() <= q) {

                    //get a copy of the process and set it's new start time
                    Process finishedProcess = process.copy();
                    finishedProcess.setStartTime(currentTime);
                    
                    //the current time will increase for only the remaining time of the running process
                    currentTime += process.getRemainingTime();

                    //set copied process end time and remaining time
                    finishedProcess.setEndTime(currentTime);
                    finishedProcess.setRemainingTime(0);

                    //add the process to the tail of the process queue
                    processesQueue.add(finishedProcess);
                } else {

                    //get a copy of the process and set it's new start time
                    Process finishedProcess = process.copy();
                    finishedProcess.setStartTime(currentTime);
                    
                    //the current time will increase by q
                    currentTime += q;

                    //set copied process end time and remaining time
                    finishedProcess.setEndTime(currentTime);
                    finishedProcess.setRemainingTime(process.getRemainingTime() - q);

                    //process remaining time will be equal to 1
                    process.setRemainingTime(process.getRemainingTime() - q);

                    //add the process to the tail of the ready queue
                    readyQueue.add(process);

                    //add the process to the tail of the process queue
                    processesQueue.add(finishedProcess);
                }
            } else {
                //if ready queue is empty just increase current time by 1
                currentTime++;
            }


            //loop for all unarrived processes, if any one of them arrive add it to the ready queue
            for (int i = 0; i < processes.size(); i++) {

                Process process = processes.get(i); //get i process

                //if process arrival time less than or equal current time add it to the ready queue
                //and remove it from unarrived queue
                if (process.getArriveTime() <= currentTime) {
                    //set process start time
                    process.setStartTime(currentTime);
                    
                    //add process to the ready queue
                    readyQueue.add(process);
                    
                    //remove process from the unarrived processes
                    processes.remove(i);
                    
                    //decrement i
                    i--;
                }

            }

        }

        return processesQueue;

    }

}
