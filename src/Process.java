/**
 * Created by JJ's Lappy on 3/26/2016.
 */
public class Process {
    // Each process has a pid, burst_time, arrival_time, and a priority
    int pid;
    int burstTime;
    int arrivalTime;
    int priority;

    public Process(int pid, int burstTime, int arrivalTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
    }

}
