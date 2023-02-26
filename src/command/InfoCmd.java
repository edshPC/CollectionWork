package command;

import java.util.LinkedList;

import helpers.FileHelper;
import mainclasses.Ticket;

public class InfoCmd implements Command {
	private LinkedList<Ticket> list;
	private FileHelper fh;
	
	public InfoCmd(LinkedList<Ticket> list, FileHelper fh) {
		this.list = list;
		this.fh = fh;
	}
	
	@Override
	public String execute(String[] args) {
		String[] creationTime = fh.getCreationTime().toString().split("[T.]");
		String out = "Тип коллекции: LinkedList\n" +
				"Дата инициализации: " + creationTime[0] + " " + creationTime[1] + "\n" +
				"Количество элементов: " + list.size();
		return out;
	}

	@Override
	public String getName() {
		return "info";
	}

}
