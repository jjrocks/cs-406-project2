/**
 * Created by JJ's Lappy on 3/26/2016.
 */
public class Process {
    // Each process has a pid, burst_time, arrival_time, and a priority
    int pid;
    int burstTime;
    int arrivalTime;
    int priority;


    int startedRunningAt; //record the first timestep that this process was running
    int finishedRunningAt; //record the last timestep that this process was running

    public Process(int pid, int burstTime, int arrivalTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;

        startedRunningAt = -1;
        finishedRunningAt = -1;
    }

    public String toString() {
        String str = "[Process " + pid + "]: ";
        str += "burstTime=" + burstTime + ", ";
        str += "arrivalTime=" + arrivalTime + ", ";
        str += "priority=" + priority + ", ";
        str += "startedRunningAt=" + startedRunningAt + ", ";
        str += "finishedRunningAt=" + finishedRunningAt + ", ";
        str += "waitingTime=" + waitingTime() + ", ";
        str += "responseTime=" + responseTime();
        return str;
    }

    public int waitingTime() {
        //returns the total time spent in the ready queue

        if(finishedRunningAt != -1)
        {
            //waiting time is basically the number of timesteps between the arrival time and the finish time
            //minus the portion of that for which the process was actually running
            return finishedRunningAt+1 - arrivalTime - burstTime;
        }
        else
        {
            //if the process isn't done running yet, we can't return the waiting time
            return -1;
        }
    }

    public int responseTime() {
        //returns the time from when the process arrived until the process started running

        if(startedRunningAt != -1)
        {
            //response time is just the difference between starting time and arrival time
            return startedRunningAt - arrivalTime;
        }
        else
        {
            //if the process hasn't started running yet, we can't return the response time
            return -1;
        }
    }

}
