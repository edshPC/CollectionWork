package command;

import mainclasses.Ticket;

public class SortCmd implements Command {
	
	@Override
	public String execute(String[] args) {
		
		Ticket.sortList();
		
		return "Коллекция отсортирована";
	}

	@Override
	public String getName() {
		return "sort";
	}

}
