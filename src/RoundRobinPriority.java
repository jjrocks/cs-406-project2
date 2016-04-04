import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by JJ's Lappy on 4/3/2016.
 */
public class RoundRobinPriority implements Scheduler{


    static final int QUANTUM = 2;
    final double priorityQuantumMultiplier = 2;

    private Queue<Process> readyQueue;
    private Process currentlyRunningProcess;
    private int timestepsOnCurProcess; //how many timesteps in a row have we used on the current process

    public RoundRobinPriority() {
        readyQueue = new LinkedList<Process>();
    }

    public void addProcess(Process process) {
        readyQueue.add(process);
    }

    public Process getProcessToRun() {

        //decide on the appropriate process to run
        if(currentlyRunningProcess == null || currentlyRunningProcess.remainingTime() == 0) {
            //if no currently running process, take the next one from the queue
            if(readyQueue.isEmpty()) {
                currentlyRunningProcess = null;
                return null;
            }
            else {
                currentlyRunningProcess = readyQueue.remove();
                timestepsOnCurProcess = 1;
                return currentlyRunningProcess;
            }
        }
        else if(timestepsOnCurProcess >= (QUANTUM + priorityQuantumMultiplier*currentlyRunningProcess.originalPriority())
                && !readyQueue.isEmpty()) {
            //preempt if time quantum exceeded and processes are waiting
            readyQueue.add(currentlyRunningProcess);
            currentlyRunningProcess = readyQueue.remove();
            timestepsOnCurProcess = 1;
            return currentlyRunningProcess;
        }
        else {
            //otherwise just let the current process continue
            timestepsOnCurProcess++;
            return currentlyRunningProcess;
        }
    }

    public boolean noProcesses() {
        return readyQueue.isEmpty() && currentlyRunningProcess==null;
    }

}
