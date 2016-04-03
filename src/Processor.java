import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Processor {

	public static void main(String[] args) {

		//here's where all the scheduler types are defined
		TreeMap<String, Scheduler> schedulerStrats = new TreeMap<String, Scheduler>();
		schedulerStrats.put("fcfs", new Fcfs());
		schedulerStrats.put("nonpreprior", new Nonpreprior());
		schedulerStrats.put("rr", new Rr());

		//check for correct input
		if(args.length == 1 && args[0].equals("list"))
		{
			//handle "list" command
			System.out.println("\nHere are the available commands for selecting a scheduling algorithm:\n");
			for(String str : schedulerStrats.keySet()) {
				System.out.println(str);
			}
			System.out.println("");
			return;
		}
		else if(args.length != 2) {
			//make sure there are two arguments
			System.out.println("\nThis program takes two parameters: the first parameter is " + 
				"which scheduler to use and the second parameter is the input file name.");
			System.out.println("\nAlternatively, you can enter the word \"list\" as a " + 
				"single parameter to list all available scheduling algorithms.\n");
			return;
		}
		else if(!schedulerStrats.containsKey(args[0])) {
			//make sure it's a valid scheduling algorithm
			System.out.println("\nSorry, but \"" + args[0] + "\" is not not a valid scheduling algorithm.");
			System.out.println("\nEnter the word \"list\" as a " + 
				"single parameter to list all available scheduling algorithms.\n");
			return;
		}


		//parse the input file
		ArrayList<Process> processList = TextReader.parseText(args[1]);
		if(processList == null || processList.size() == 0)
		{
			System.out.println("Unable to load processes.");
			return;
		}

		//create a queue that will automatically sort the processes by arrival time
		PriorityQueue<Process> arrivalQueue = new PriorityQueue<Process>(processList.size(), new ArrivalTimeComparator());
		for(Process process : processList) {
			process.agingCounter = 0;
			arrivalQueue.add(process);
		}

		//get the appropriate scheduler
		Scheduler sched = schedulerStrats.get(args[0]);
 
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