import java.util.List;

public class Analyzer {

	//returns the average waiting time for a group of processes
	public static double averageWaitingTime(List<Process> processList){
		double sum = 0;
		for(Process process : processList) {
			sum += process.waitingTime();
		}
		return sum / processList.size();
	}

	//returns the weighted average waiting time for a group of processes
	public static double weightedAverageWaitingTime(List<Process> processList){
		double numerator = 0;
		double denominator = 0;
		for(Process process : processList) {
			numerator += process.waitingTime()*process.priority;
			denominator += process.priority;
		}
		return numerator / denominator;
	}

	//returns the average response time for a group of processes
	public static double averageResponseTime(List<Process> processList){
		double sum = 0;
		for(Process process : processList) {
			sum += process.responseTime();
		}
		return sum / processList.size();
	}

	//returns the weighted average response time for a group of processes
	public static double weightedAverageResponseTime(List<Process> processList){
		double numerator = 0;
		double denominator = 0;
		for(Process process : processList) {
			numerator += process.responseTime()*process.priority;
			denominator += process.priority;
		}
		return numerator / denominator;
	}

}