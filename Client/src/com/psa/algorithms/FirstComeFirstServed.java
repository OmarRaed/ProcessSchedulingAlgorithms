package com.psa.algorithms;

import com.psa.model.Process;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FirstComeFirstServed {

    public Queue<Process> runAlgorithm(List<Process> processes, int q) {

        Queue<Process> outputQueue = new LinkedList<>();
        Queue<Process> readyQueue = new LinkedList<>();

        Collections.sort(processes);

        int currentTime = 0;

        while (processes.size() != 0 || !readyQueue.isEmpty()) {

            if (!readyQueue.isEmpty()) {

                Process currentProcess = readyQueue.poll();


                Process finishedProcess = currentProcess.copy();

                finishedProcess.setStartTime(currentTime);

                currentTime += currentProcess.getBurstTime();

                finishedProcess.setEndTime(currentTime);

                outputQueue.add(finishedProcess);
            }


            for (int i = 0; i < processes.size(); i++) {

                Process process = processes.get(i);

                if (process.getArriveTime() <= currentTime) {

                    process.setStartTime(currentTime);

                    readyQueue.add(process);

                    processes.remove(i);

                    i--;

                }

            }
        }
        return outputQueue;

    }

}


