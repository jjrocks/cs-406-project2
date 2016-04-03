import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by JJ's Lappy on 4/2/2016.
 */
public class ShortestRemainingTimeFirst implements Scheduler {

    PriorityQueue<Process> processQueue = new PriorityQueue<>(new ShortestRemainingTimeFirstComparator());

    @Override
    public void addProcess(Process process) {
        processQueue.add(process);
    }

    @Override
    public Process getProcessToRun() {
        if (processQueue.isEmpty())
            return null;
        if (processQueue.peek().remainingTime() == 0) {
            processQueue.poll();
        }
        return processQueue.peek();
    }

    @Override
    public boolean noProcesses() {
        return processQueue.isEmpty();
    }

    private class ShortestRemainingTimeFirstComparator implements Comparator<Process> {

        @Override
        public int compare(Process process1, Process process2) {
            if (process1.remainingTime() < process2.remainingTime()) {
                return -1;
            }
            else if (process1.remainingTime() > process2.remainingTime()) {
                return 1;
            }
            else
                return 0;
        }
    }
}
