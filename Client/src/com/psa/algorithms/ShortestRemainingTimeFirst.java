package com.psa.algorithms;

import com.psa.model.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShortestRemainingTimeFirst {

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
                shortestProcess.setRemainingTime(MAX_INT_VALUE);

                //initialize shortestIndex variable with 0
                int shortestIndex = 0;

                //loop for all processes in the ready list to find the list with minimum burst time
                for (int i = 0; i < readyList.size(); i++) {

                    //if this process has less burst time then choose it as the shortest process
                    if (readyList.get(i).getRemainingTime() < shortestProcess.getRemainingTime()) {
                        //set the shortest process equals this process
                        shortestProcess = readyList.get(i).copy();

                        //set the shortest process index equals i
                        shortestIndex = i;
                    }
                }

                //set shortest process start time with current time
                shortestProcess.setStartTime(currentTime);

                //get the time that next process will arrive
                int nextProcessArrivalTime = getNextArrivedProcess(unarrivedProcesses);

                if (nextProcessArrivalTime < currentTime + shortestProcess.getRemainingTime()) {

                    //get worked job time by minus currentTime from nextProcessArrivalTime 
                    int workTime = nextProcessArrivalTime - currentTime ;

                    //set current time by next process arrival time
                    currentTime = nextProcessArrivalTime;

                    //set shortest process end time with current time
                    shortestProcess.setEndTime(currentTime);

                    //set shortest process duration
                    shortestProcess.setBurstTime(shortestProcess.getEndTime() - shortestProcess.getStartTime());

                    //then add shortest process to theoutput queue
                    outputQueue.add(shortestProcess);

                    //decrease the remaining time for this process in the ready list
                    readyList.get(shortestIndex)
                        .setRemainingTime(readyList.get(shortestIndex).getRemainingTime() - workTime);


                } else {

                    //increase current time by shortest process vurst time
                    currentTime += shortestProcess.getRemainingTime();

                    //set shortest process end time with current time
                    shortestProcess.setEndTime(currentTime);

                    //set shortest process duration
                    shortestProcess.setBurstTime(shortestProcess.getEndTime() - shortestProcess.getStartTime());

                    //then add shortest process to theoutput queue
                    outputQueue.add(shortestProcess);

                    //and remove shortest process from the ready list
                    readyList.remove(shortestIndex);

                }

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

        return optimizeOutputQueue(outputQueue);

    }

    private int getNextArrivedProcess(List<Process> processes) {

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

    /**
     * A method to optimize the output queue(decrease it's processes count if possible
     * if any to successive processes combine them into one process
     */
    private Queue<Process> optimizeOutputQueue(Queue<Process> outputQueue){
        
        //convert the output queue into list to be easier to loop 
        List<Process> outputList = new ArrayList<>(outputQueue);
        
        //loop for all output list elements
        for(int i = 0 ; i < outputList.size()-1 ; i++){
            
            //get i (current) process and i+1 (next) process
            Process currentProcess = outputList.get(i) ;            
            Process nextProcess = outputList.get(i+1) ;
            
            //if current and next processes have the same name then combine them 
            //into one process and remove the other
            if(currentProcess.getProcessName().equals(nextProcess.getProcessName())){
                
                //set current process end time equals next process end time
                currentProcess.setEndTime(nextProcess.getEndTime());
                
                //set current process duration equals next process duration + current process duration
                currentProcess.setBurstTime(currentProcess.getBurstTime() + nextProcess.getBurstTime());
                
                //remove next process
                outputList.remove(i+1) ;
                
            }
            
        }
        
        //return the optimized output list as linked list (queue)
        return new LinkedList<Process>(outputList) ;
        
    }

}


