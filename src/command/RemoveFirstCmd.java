package command;

import java.util.LinkedList;

import mainclasses.Ticket;

public class RemoveFirstCmd implements Command {
	private LinkedList<Ticket> list;
	
	public RemoveFirstCmd(LinkedList<Ticket> list) {
		this.list = list;
	}
	
	
	@Override
	public String execute(String[] args) {
		if(list.size() == 0)
			return "Нечего удалить";
		
		list.removeFirst();
		
		return "Билет успешно удален!";
	}

	@Override
	public String getName() {
		return "remove_first";
	}

}
