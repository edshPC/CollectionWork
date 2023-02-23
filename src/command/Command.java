package command;

public interface Command {
	String execute(String[] args);
	String getName();
}
