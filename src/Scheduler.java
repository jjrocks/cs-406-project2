public interface Scheduler {

	public void addProcess(Process process); //when a process arrives, add it to the scheulder
	public Process getProcessToRun(); //get the Process which should run for the next timestep
	public boolean queueEmpty(); //return true if there are no waiting processes, false if there are

}