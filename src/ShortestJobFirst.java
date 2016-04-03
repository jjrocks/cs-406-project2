import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by JJ's Lappy on 4/2/2016.
 */
public class ShortestJobFirst implements Scheduler {

    PriorityQueue<Process> processQueue;
    Process currentProcess;

    public ShortestJobFirst() {
        processQueue = new PriorityQueue<Process>(new ShortestJobFirstComparotor());
    }

    @Override
    public void addProcess(Process process) {
        processQueue.add(process);
    }

    @Override
    public Process getProcessToRun() {
        if (currentProcess == null) {
            currentProcess = processQueue.poll();
        } else {
            if (currentProcess.remainingTime() != 0) {
                return currentProcess;
            } else {
                currentProcess = processQueue.poll();
            }
        }
        return currentProcess;
    }

    @Override
    public boolean noProcesses() {
        return currentProcess == null && processQueue.isEmpty();
    }

    private class ShortestJobFirstComparotor implements Comparator<Process> {

        @Override
        public int compare(Process process1, Process process2) {
            if (process1.burstTime() < process2.burstTime()) {
                return -1;
            }
            else if (process1.burstTime() > process2.burstTime()) {
                return 1;
            }
            else
                return 0;
        }
    }
}
