package command;

import java.util.LinkedList;

import mainclasses.Ticket;

public class ShowCmd implements Command {
	private LinkedList<Ticket> list;
	
	private ShowCmd(LinkedList<Ticket> list) {
		this.list = list;
	}
	
	@Override
	public String execute(String[] args) {
		String out = "";
		for(Ticket ticket : list) {
			out += ticket + "\n";
		}
		return out.substring(0, out.length()-1);
	}

	@Override
	public String getName() {
		return "show";
	}
	
	private static ShowCmd instance;
	
	public static synchronized ShowCmd get(LinkedList<Ticket> list) {
		if (instance == null) {
			instance = new ShowCmd(list);
		}
		return instance;
	}

}
