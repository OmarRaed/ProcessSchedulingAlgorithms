package com.psa.algorithms;

import com.psa.model.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DeadLine {
    //initialize a final static integer that equals the max int in java
    private static final int MAX_INT_VALUE = Integer.MAX_VALUE;

    public Queue<Process> runAlgorithm(List<Process> processes) {

        //initialize empty output queue
        Queue<Process> outputQueue = new LinkedList<>();

        //initialize empty ready list
        List<Process> readyList = new ArrayList<>();
        
        //get a copy of the list
        List<Process> unarrivedProcesses = new ArrayList<>(processes);

        //sort processes by arrival time
        Collections.sort(unarrivedProcesses);

        //set the remaining time for each process equals the burst time
        for (Process p : unarrivedProcesses)
            p.setRemainingTime(p.getBurstTime());

        //initialize current time with 0
        int currentTime = 0;

        //loop until all process finishes
        while (unarrivedProcesses.size() != 0 || readyList.size() != 0) {

            //if ready queue is not empty
            if (!readyList.isEmpty()) {

                //create a new process and initialize it with maximum burst number
                Process shortestProcess = new Process();
                shortestProcess.setBurstTime(MAX_INT_VALUE);

                //initialize shortestIndex variable with 0
                int shortestIndex = 0;

                //loop for all processes in the ready list to find the list with minimum burst time
                for (int i = 0; i < readyList.size(); i++) {

                    //if this process has less burst time then choose it as the shortest process
                    if (readyList.get(i).getBurstTime() < shortestProcess.getBurstTime()) {
                        //set the shortest process equals this process
                        shortestProcess = readyList.get(i);

                        //set the shortest process index equals i
                        shortestIndex = i;
                    }
                }

                //set shortest process start time with current time
                shortestProcess.setStartTime(currentTime);

                //increase current time by shortest process vurst time
                currentTime += shortestProcess.getBurstTime();

                //set shortest process end time with current time
                shortestProcess.setEndTime(currentTime);

                //then add shortest process to theoutput queue
                outputQueue.add(shortestProcess);

                //and remove shortest process from the ready list
                readyList.remove(shortestIndex);

            } else if (currentTime != 0) {

                //get time when the next process will arrive
                int nextProcessArrivalTime = getNextArrivedProcess(unarrivedProcesses);

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

            //check if any process arrived or not
            moveToReadyList(unarrivedProcesses, readyList, currentTime);

        }

        return outputQueue;

    }

    public int getNextArrivedProcess(List<Process> processes) {

        int leastArrivalTime = MAX_INT_VALUE;

        for (Process process : processes)
            if (leastArrivalTime > process.getArriveTime())
                leastArrivalTime = process.getArriveTime();

        return leastArrivalTime;

    }

    private void moveToReadyList(List<Process> unarrivedProcesses, List<Process> readyList, int currentTime) {

        //if process arrival time less than current time add it to the ready list
        //and remove it from unarrived list
        for (int i = 0; i < unarrivedProcesses.size(); i++) {

            Process process = unarrivedProcesses.get(i); //get i process

            //if process arrival time is less than or equal current time
            //then add it to the ready list and remove it from unarrived List
            if (process.getArriveTime() <= currentTime) {

                //add process to the ready list
                readyList.add(process);

                //remove process from the unarrived list
                unarrivedProcesses.remove(i);

                //decrement i
                i--;
            }
        }
    }

}
