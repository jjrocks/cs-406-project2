import java.util.Queue;
import java.util.LinkedList;

public class Fcfs implements Scheduler {
	
	private Queue<Process> readyQueue;

	public Fcfs() {
		readyQueue = new LinkedList<Process>();
	}

	public void addProcess(Process process) {
		readyQueue.add(process);
	}

	public Process getProcessToRun() {
		Process processToRun = readyQueue.peek();

		//decide if the process needs to be removed from the queue or not
		if(processToRun != null && processToRun.remainingTime() == 1) {
			//if the process only needs to be run one more timestep, then we can remove it from the queue
			readyQueue.remove();
		}

		return processToRun;
	} 

	public boolean noProcesses) {
		return readyQueue.isEmpty();
	}
}