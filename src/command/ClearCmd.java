package command;

import java.util.LinkedList;

import mainclasses.Ticket;

public class ClearCmd implements Command {
	private LinkedList<Ticket> list;
	
	public ClearCmd(LinkedList<Ticket> list) {
		this.list = list;
	}
	
	@Override
	public String execute(String[] args) {
		list.clear();
		return "Коллекция очищена";
	}

	@Override
	public String getName() {
		return "clear";
	}

}
