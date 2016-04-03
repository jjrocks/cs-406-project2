import java.util.ArrayList;
import java.util.PriorityQueue;

public class Processor {

	public static void main(String[] args) {

		//check for correct input
		if(args.length == 1 && args[0].equals("list"))
		{
			System.out.println("\nNo schedulers are implemented yet!\n");
			return;
		}
		else if(args.length != 2) {
			System.out.println("\nThis program takes two parameters: the first parameter is " + 
				"which scheduler to use and the second parameter is the input file name.");
			System.out.println("\nAlternatively, you can enter the world \"list\" as a " + 
				"single parameter to list all available scheduling algorithms.\n");
			return;
		}

		//parse the input file
		ArrayList<Process> processList = TextReader.parseText(args[1]);

		//create a queue that will automatically sort the processes by arrival time
		PriorityQueue<Process> arrivalQueue = new PriorityQueue<Process>(processList.size(), new ArrivalTimeComparator());
		for(Process process : processList) {
			process.agingCounter = 0;
			arrivalQueue.add(process);
		}

		//get the appropriate scheduler
		Scheduler sched = new PremptivePriority(); //for now it's hardcoded as fcfs
 
		//main program loop
		int curTimestep=0;
		while(!arrivalQueue.isEmpty() || !sched.noProcesses()){

			//while there are processes in the queue that arrive this timestep, add them to the scheduler
			while(!arrivalQueue.isEmpty() && arrivalQueue.peek().arrivalTime() == curTimestep) {
				sched.addProcess(arrivalQueue.poll());
			}

			//find out which process to run
			Process processToRun = sched.getProcessToRun();
			if(processToRun != null) {
				processToRun.runProcess(curTimestep);
				System.out.println("Time: " + curTimestep + ", process " + processToRun.pid() + " running");
			}

			curTimestep++;
		}

		//print analysis
		System.out.println("Average waiting time is: " + Analyzer.averageWaitingTime(processList));
		System.out.println("Average weighted waiting time is: " + Analyzer.weightedAverageWaitingTime(processList));
		System.out.println("Average response time is: " + Analyzer.averageResponseTime(processList));
		System.out.println("Average weighted response time is: " + Analyzer.weightedAverageResponseTime(processList));
	}

}