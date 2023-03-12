package command;

import java.util.LinkedList;

import helpers.FileHelper;
import mainclasses.Ticket;

public class InfoCmd implements Command {
	private LinkedList<Ticket> list;
	private FileHelper fh;
	
	public InfoCmd(CommandHelper ch) {
		this.list = ch.getList();
		this.fh = ch.getFileHelper();
	}
	
	@Override
	public String execute(String[] args) {
		String[] creationTime = fh.getCreationTime().toString().split("[T.]");
		String out = "��� ���������: LinkedList\n" +
				"���� �������������: " + creationTime[0] + " " + creationTime[1] + "\n" +
				"���������� ���������: " + list.size();
		return out;
	}

	@Override
	public String getName() {
		return "info";
	}

}
