package com.psa.util;

import com.psa.model.Process;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProcessorCalculations {

    /**
     *
     * a method that return the average waiting time for a list of processes
     * @param processes
     * @return
     */
    public float averageWaitingTime(List<Process> outputProcesses) {

        List<Process> processes = copyProcessList(outputProcesses);

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        float totalWaitingTime = 0; //initialize total waiting time with 0 value

        //remove any duplications or PIDLE processes
        processes = optimizeList(processes);

        //for each process in the list
        for (Process process : processes) {
            //get the procces waiting time and add it to the total waiting time
            //waiting time = (end time - arrival time) - burst time
            totalWaitingTime += (process.getEndTime() - process.getArriveTime()) - process.getBurstTime();
        }

        //divide total waiting time by the processes number to get the average and return it
        return totalWaitingTime / processes.size();

    }

    /**
     *
     * a method that return the average response time for a list of processes
     * @param processes
     * @return
     */
    public float averageResponseTime(List<Process> outputProcesses) {

        List<Process> processes = copyProcessList(outputProcesses);

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        float totalResponseTime = 0; //initialize total response time with 0 value

        //remove any duplications or PIDLE processes
        processes = optimizeList(processes);

        //for each process in the list
        for (Process process : processes) {
            //get the procces response time and add it to the total response time
            //response time = start time - arrival time
            totalResponseTime += process.getStartTime() - process.getArriveTime();
        }

        //divide total response time by the processes number to get the average and return it
        return totalResponseTime / processes.size();

    }

    /**
     *
     * a method that return the average turn around time for a list of processes
     * @param processes
     * @return
     */
    public float averageTurnAroundTime(List<Process> outputProcesses) {

        List<Process> processes = copyProcessList(outputProcesses);

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        float totalTurnAroundTime = 0; //initialize total turn around time with 0 value

        //remove any duplications or PIDLE processes
        processes = optimizeList(processes);

        //for each process in the list
        for (Process process : processes) {
            //get the procces turn around time and add it to the total turn around time
            //turn around time = end time - arrival time
            totalTurnAroundTime += process.getEndTime() - process.getArriveTime();
        }

        //divide total turn around time by the processes number to get the average and return it
        return totalTurnAroundTime / processes.size();

    }

    /**
     *
     * a method that return the CPU utilization for a list of processes
     * @param processes
     * @return
     */
    public float cpuUtilization(List<Process> outputProcesses) {

        List<Process> processes = copyProcessList(outputProcesses);

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        float totalBurstTime = 0; //initialize total burst time with 0 value
        float cpuTotalTime = 0;

        //remove any duplications or PIDLE processes
        processes = optimizeList(processes);

        //for each process in the list
        for (Process process : processes) {
            //get the procces burst time and add it to the total burst time
            totalBurstTime += process.getBurstTime();

            //get the total running time by getting the end time for the last process
            if (cpuTotalTime < process.getEndTime())
                cpuTotalTime = process.getEndTime();
        }

        //divide total burst time by the cpu total running time to get the cpu utilization
        return (totalBurstTime / cpuTotalTime) * 100;
    }

    /**
     *
     * a method that return the Throughput for a list of processes
     * @param processes
     * @return
     */
    public float Throughput(List<Process> outputProcesses) {

        List<Process> processes = copyProcessList(outputProcesses);

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        float processesNumber = processes.size(); //initialize processesNumber variable with the number of processes
        float cpuTotalTime = 0;

        //remove any duplications or PIDLE processes
        processes = optimizeList(processes);

        //for each process in the list
        for (Process process : processes) {

            //get the total running time by getting the end time for the last process
            if (cpuTotalTime < process.getEndTime())
                cpuTotalTime = process.getEndTime();
        }

        //divide processes number by the cpu total running time to get the Throughput
        return processesNumber / cpuTotalTime;
    }

    /**
     *A method that takes a list of processes and removes any duplicated processes and any PIDLE process
     */
    private List<Process> optimizeList(List<Process> outputProcesses) {

        List<Process> processes = new ArrayList<>(outputProcesses);

        //loop for all processes
        for (int i = 0; i < processes.size() - 1; i++) {

            //get i process
            Process p1 = processes.get(i);

            //if process is PIDLE process then remove it
            if (p1.getProcessName().equals("PIDLE")) {
                processes.remove(i);
                i--;
                continue;
            }

            //loop for all other processes
            for (int j = i + 1; j < processes.size(); j++) {

                //get j process
                Process p2 = processes.get(j);

                //if process i equals process j then remove one of them
                if (p1.getProcessName().equals(p2.getProcessName())) {

                    //set p1 end time equals to p2 end time
                    p1.setEndTime(p2.getEndTime());

                    //set p1 duration equals p1 duration plus p2 duration
                    p1.setBurstTime(p1.getBurstTime() + p2.getBurstTime());

                    //remove p2
                    processes.remove(j);

                }

            }

        }

        //return optimized processes
        return new ArrayList<>(processes);
    }
    
    private List<Process> copyProcessList(List<Process> source){
        
        List<Process> copiedProcesses = new ArrayList<>() ;
        
        for(Process p : source){
            Process c = p.copy() ;
            copiedProcesses.add(c);
        }
        
        return copiedProcesses ;
        
    }

}
