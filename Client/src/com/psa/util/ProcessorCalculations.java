package com.psa.util;

import com.psa.model.Process;

import java.util.List;

public class ProcessorCalculations {

    /**
     *
     * a method that return the average waiting time for a list of processes
     * @param processes
     * @return
     */
    public float averageWaitingTime(List<Process> processes) {

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        int totalWaitingTime = 0; //initialize total waiting time with 0 value
        int processesNumber = processes.size(); //initialize processesNumber variable with the number of processes

        //for each process in the list
        for (Process process : processes) {
            //get the procces waiting time and add it to the total waiting time
            //waiting time = (end time - arrival time) - burst time
            totalWaitingTime += (process.getEndTime() - process.getArriveTime()) - process.getBurstTime();
        }

        //divide total waiting time by the processes number to get the average and return it
        return totalWaitingTime / processesNumber;

    }

    /**
     *
     * a method that return the average response time for a list of processes
     * @param processes
     * @return
     */
    public float averageResponseTime(List<Process> processes) {

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        int totalResponseTime = 0; //initialize total response time with 0 value
        int processesNumber = processes.size(); //initialize processesNumber variable with the number of processes

        //for each process in the list
        for (Process process : processes) {
            //get the procces response time and add it to the total response time
            //response time = start time - arrival time
            totalResponseTime += process.getStartTime() - process.getArriveTime();
        }

        //divide total response time by the processes number to get the average and return it
        return totalResponseTime / processesNumber;

    }

    /**
     *
     * a method that return the average turn around time for a list of processes
     * @param processes
     * @return
     */
    public float averageTurnAroundTime(List<Process> processes) {

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        int totalTurnAroundTime = 0; //initialize total turn around time with 0 value
        int processesNumber = processes.size(); //initialize processesNumber variable with the number of processes

        //for each process in the list
        for (Process process : processes) {
            //get the procces turn around time and add it to the total turn around time
            //turn around time = end time - arrival time
            totalTurnAroundTime += process.getEndTime() - process.getArriveTime();
        }

        //divide total turn around time by the processes number to get the average and return it
        return totalTurnAroundTime / processesNumber;

    }

    /**
     *
     * a method that return the CPU utilization for a list of processes
     * @param processes
     * @return
     */
    public float cpuUtilization(List<Process> processes) {

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        int totalBurstTime = 0; //initialize total burst time with 0 value
        int cpuTotalTime = 0;

        //for each process in the list
        for (Process process : processes) {
            //get the procces burst time and add it to the total burst time
            totalBurstTime += process.getBurstTime();

            //get the total running time by getting the end time for the last process
            if (cpuTotalTime < process.getEndTime())
                cpuTotalTime = process.getEndTime();
        }

        //divide total burst time by the cpu total running time to get the cpu utilization
        return totalBurstTime / cpuTotalTime;
    }

    /**
     *
     * a method that return the Throughput for a list of processes
     * @param processes
     * @return
     */
    public float Throughput(List<Process> processes) {

        //if process list is null or size is zero, then return 0
        if (processes == null || processes.size() == 0)
            return 0;

        int processesNumber = processes.size(); //initialize processesNumber variable with the number of processes
        int cpuTotalTime = 0;

        //for each process in the list
        for (Process process : processes) {

            //get the total running time by getting the end time for the last process
            if (cpuTotalTime < process.getEndTime())
                cpuTotalTime = process.getEndTime();
        }

        //divide processes number by the cpu total running time to get the Throughput
        return processesNumber / cpuTotalTime;
    }

}
