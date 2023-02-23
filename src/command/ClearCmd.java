package command;

import java.util.LinkedList;

import helpers.FileHelper;
import mainclasses.Ticket;

public class ClearCmd implements Command {
	private LinkedList<Ticket> list;
	
	private ClearCmd(LinkedList<Ticket> list) {
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
	
	private static ClearCmd instance;
	
	public static synchronized ClearCmd get(LinkedList<Ticket> list) {
		if (instance == null) {
			instance = new ClearCmd(list);
		}
		return instance;
	}

}
