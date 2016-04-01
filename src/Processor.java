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
			arrivalQueue.add(process);
		}

		//main program loop
		int curTimestep=0;
		while(arrivalQueue.size() != 0 /*and other stuff to be added*/) {
			System.out.println("TIMESTEP="+curTimestep);

			//while there are processes in the queue that arrive this timestep
			while(arrivalQueue.size() != 0 && arrivalQueue.peek().arrivalTime() == curTimestep) {
				System.out.println("Process " + arrivalQueue.poll().pid() + " has arrived.");
			}

			curTimestep++;
		}
	}

}