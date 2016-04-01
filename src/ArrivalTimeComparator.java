import java.util.Comparator;

public class ArrivalTimeComparator implements Comparator<Process> {

	public int compare(Process p1, Process p2) {
		if(p1.arrivalTime() < p2.arrivalTime()) {

			//p1 is placed before p2
			return -1;
		}
		else if(p1.arrivalTime() > p2.arrivalTime()) {

			//p2 is placed before p1
			return 1;
		}
		else
		{
			return 0; //tie
		}
	}

}