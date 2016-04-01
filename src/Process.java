/**
 * Created by JJ's Lappy on 3/26/2016.
 */
public class Process {
    // Each process has a pid, burst_time, arrival_time, and a priority
    private int pid;
    private int burstTime;
    private int arrivalTime;
    private int priority;


    private int startedRunningAt; //record the first timestep that this process was running
    private int finishedRunningAt; //record the last timestep that this process was running
    private int runTimeSoFar; //record how much time this process has been allowed to run so far

    public Process(int pid, int burstTime, int arrivalTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.priority = priority;

        startedRunningAt = -1;
        finishedRunningAt = -1;
        runTimeSoFar = 0;
    }

    public String toString() {
        String str = "[Process " + pid + "]: ";
        str += "burstTime=" + burstTime + ", ";
        str += "arrivalTime=" + arrivalTime + ", ";
        str += "priority=" + priority + ", ";
        str += "startedRunningAt=" + startedRunningAt + ", ";
        str += "finishedRunningAt=" + finishedRunningAt + ", ";
        str += "runTimeSoFar=" + runTimeSoFar + ", ";
        str += "waitingTime=" + waitingTime() + ", ";
        str += "responseTime=" + responseTime() + ", ";
        str += "remainingTime=" + remainingTime();
        return str;
    }

    public void ageProcess() {
        //increase the priority of a process (due to aging)
        priority++;
    }

    public boolean runProcess(int curTimestep) {
        //simulate running the process for one timestep. return true if the process is now
        //finished, false if the process is no longer finished

        if(startedRunningAt == -1) {
            //if this is the first time this process was run, record the timestep
            startedRunningAt = curTimestep;
        }

        runTimeSoFar++;

        if(remainingTime() == 0) {
            //if the process is finished, record the timestep
            finishedRunningAt = curTimestep;
            return true;
        }

        return false; //process still needs more running time
    }

    //Getters

    public int pid() {
        return pid;
    }

    public int burstTime() {
        return burstTime;
    }

    public int arrivalTime() {
        return arrivalTime;
    }

    public int priority() {
        return priority;
    }

    public int startedRunningAt() {
        return startedRunningAt;
    }

    public int finishedRunningAt() {
        return finishedRunningAt;
    }

    public int runTimeSoFar() {
        return runTimeSoFar;
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

    public int remainingTime() {
        //returns how much more time this process needs to run
        return burstTime - runTimeSoFar;
    }

}
