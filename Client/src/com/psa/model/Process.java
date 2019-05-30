package com.psa.model;

public class Process implements Comparable<Process> {

    private String processName;
    private Integer arriveTime;
    private int burstTime, priority, deadline;
    private int startTime, endTime, remainingTime;

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setArriveTime(Integer arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Integer getArriveTime() {
        return arriveTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setDeadline(int deadline) {
        this.deadline = deadline;
    }

    public int getDeadline() {
        return deadline;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    @Override
    public int compareTo(Process process) {
        return this.getArriveTime().compareTo(process.getArriveTime());
    }
    
    public Process copy(){
        
        Process processCopy = new Process() ;
        
        processCopy.setProcessName(this.processName) ;
        processCopy.setArriveTime(this.arriveTime) ;
        processCopy.setBurstTime(this.burstTime) ;
        processCopy.setPriority(this.priority) ;
        processCopy.setStartTime(this.startTime) ;
        processCopy.setEndTime(this.endTime) ;
        processCopy.setDeadline(this.deadline) ;
        processCopy.setRemainingTime(this.remainingTime) ;
        
        return processCopy ;
    }
    
}
