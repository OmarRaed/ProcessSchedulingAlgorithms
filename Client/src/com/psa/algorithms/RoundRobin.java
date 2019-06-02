package com.psa.algorithms;

import com.psa.model.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RoundRobin {

    public Queue<Process> runAlgorithm(List<Process> processes, int q) {

        //initialize empty process queue
        Queue<Process> processesQueue = new LinkedList<>();
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
                    finishedProcess.setBurstTime(currentTime - finishedProcess.getStartTime());
                    finishedProcess.setRemainingTime(0);

                    //add the process to the tail of the process queue
                    processesQueue.add(finishedProcess);

                    //check if any process arrived while this process was running
                    moveToReadyQueueLessThanOrEqualArrivalTime(readyQueue, unarrivedProcesses, currentTime);

                } else {

                    //get a copy of the process and set it's new start time
                    Process finishedProcess = process.copy();
                    finishedProcess.setStartTime(currentTime);

                    //the current time will increase by q
                    currentTime += q;

                    //set copied process end time and remaining time
                    finishedProcess.setEndTime(currentTime);
                    finishedProcess.setBurstTime(currentTime - finishedProcess.getStartTime());
                    finishedProcess.setRemainingTime(process.getRemainingTime() - q);

                    //process remaining time will be equal to 1
                    process.setRemainingTime(process.getRemainingTime() - q);

                    //check if any process arrived while this process was running
                    moveToReadyQueueLessThanOrEqualArrivalTime(readyQueue, unarrivedProcesses, currentTime);

                    //add the process to the tail of the ready queue
                    readyQueue.add(process);

                    //add the process to the tail of the process queue
                    processesQueue.add(finishedProcess);
                }

            }

            if (currentTime != 0 && readyQueue.isEmpty()) {

                if (unarrivedProcesses.size() == 0)
                    continue;

                int nextProcessArrivalTime = getNextArrivedProcess(unarrivedProcesses);

                if (nextProcessArrivalTime != currentTime) {

                    Process idleProcess = new Process();
                    idleProcess.setProcessName("PIDLE");
                    idleProcess.setStartTime(currentTime);
                    idleProcess.setArriveTime(currentTime);
                    idleProcess.setEndTime(nextProcessArrivalTime);
                    idleProcess.setBurstTime(nextProcessArrivalTime - currentTime);
                    idleProcess.setPriority(0);
                    idleProcess.setDeadline(99999);
                    idleProcess.setRemainingTime(0);

                    //add idle Process to the ready queue
                    processesQueue.add(idleProcess);

                    //if not first round and ready queue is empty just increase current time by 1
                    currentTime = nextProcessArrivalTime;
                }
            }

            //check if any process arrived while this process was running
            moveToReadyQueueLessThanOrEqualArrivalTime(readyQueue, unarrivedProcesses, currentTime);

        }

        return processesQueue;

    }

    public int getNextArrivedProcess(List<Process> processes) {

        int leastArrivalTime = 999999;

        for (Process process : processes)
            if (leastArrivalTime > process.getArriveTime())
                leastArrivalTime = process.getArriveTime();

        return leastArrivalTime;

    }

    public void moveToReadyQueueLessThanOrEqualArrivalTime(Queue<Process> readyQueue, List<Process> processes,
                                                           int currentTime) {

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

    public void moveToReadyQueueLessThanArrivalTime(Queue<Process> readyQueue, List<Process> processes,
                                                    int currentTime) {

        //loop for all unarrived processes, if any one of them arrive add it to the ready queue
        for (int i = 0; i < processes.size(); i++) {

            Process process = processes.get(i); //get i process

            //if process arrival time less than current time add it to the ready queue
            //and remove it from unarrived queue
            if (process.getArriveTime() < currentTime) {
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

}
