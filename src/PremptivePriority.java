import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by JJ's Lappy on 4/2/2016.
 */
public class PremptivePriority implements Scheduler, Comparator<Process> {
    //aging constants
    static final int AMOUNT_TO_AGE = 1;
    static final int TIME_TO_WAIT_BEFORE_AGING = 2;

    private List<Process> readyQueue;

    public PremptivePriority() {
        readyQueue = new ArrayList<Process>();
    }

    public void addProcess(Process process) {
        insertSort(process);
    }

    public Process getProcessToRun() {

        //decide on the appropriate process to run (defaults to same process as last time)
        if (readyQueue.isEmpty()) {
            return null;
        }
        if (readyQueue.get(0).remainingTime() == 0) {
            readyQueue.remove(0);
        }

        //now we need to age all the processes that don't get to run
        for(int i=0; i<readyQueue.size(); i++) {
            Process p = readyQueue.get(i);
            p.agingCounter++;
            if(p.agingCounter == TIME_TO_WAIT_BEFORE_AGING) {
                p.ageProcess(AMOUNT_TO_AGE);
                p.agingCounter = 0;
            }
        }
        Collections.sort(readyQueue, this);

        //return the process to run
        if(readyQueue.isEmpty()) {
            return null;
        }
        return readyQueue.get(0);
    }

    public boolean noProcesses() {
        return readyQueue.isEmpty();
    }

    private boolean insertSort(Process process)
    {
        //this is to insert the process at its appropriate place (returns true)
        if(readyQueue.isEmpty())
        {
            readyQueue.add(process);
            return true;
        }
        return insertSort(process, 0, readyQueue.size()-1);
    }

    private boolean insertSort(Process processToInsert, int leftBound, int rightBound)
    {
        //recursive binary search to find appropriate position for the process

        int midpoint = (rightBound+leftBound)/2;
        Process processAtMidpoint = readyQueue.get(midpoint);

        int compareResult = compare(processToInsert, processAtMidpoint);
        if(compareResult < 0)
        {
            if(leftBound == rightBound) {
                readyQueue.add(leftBound, processToInsert);
                return true;
            }
            return insertSort(processToInsert, leftBound, midpoint);
        }
        else if(compareResult > 0)
        {
            if(leftBound == rightBound) {
                readyQueue.add(leftBound+1, processToInsert);
                return true;
            }
            return insertSort(processToInsert, midpoint+1, rightBound);
        }
        else
        {
            readyQueue.add(midpoint+1, processToInsert);
            return true;
        }

    }

    public int compare(Process p1, Process p2) {
        if (p1.priority() > p2.priority()) {

            //p1 is placed before p2
            return -1;
        } else if (p1.priority() < p2.priority()) {

            //p2 is placed before p1
            return 1;
        }

        //on tie, sort by arrival time
        if (p1.arrivalTime() < p2.arrivalTime()) {

            //p1 is placed before p2
            return -1;
        } else if (p1.arrivalTime() > p2.arrivalTime()) {

            //p2 is placed before p1
            return 1;
        } else {
            return 0; //tie
        }
    }
}
