package command;

import mainclasses.Ticket;

public class SortCmd implements Command {
	
	public SortCmd(CommandHelper ch) {}
	
	@Override
	public String execute(String[] args) {
		
		Ticket.sortList();
		
		return "��������� �������������";
	}

	@Override
	public String getName() {
		return "sort";
	}

}
