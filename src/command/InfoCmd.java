package command;

import java.util.LinkedList;

import helpers.FileHelper;
import mainclasses.Ticket;

public class InfoCmd implements Command {
	private LinkedList<Ticket> list;
	
	private InfoCmd(LinkedList<Ticket> list) {
		this.list = list;
	}
	
	@Override
	public String execute(String[] args) {
		String out = "Тип коллекции: LinkedList\n" +
				"Дата инициализации: " + "00-00-0000" + "\n" +
				"Количество элементов: " + list.size();
		return out;
	}

	@Override
	public String getName() {
		return "info";
	}
	
	private static InfoCmd instance;
	
	public static synchronized InfoCmd get(LinkedList<Ticket> list) {
		if (instance == null) {
			instance = new InfoCmd(list);
		}
		return instance;
	}

}
