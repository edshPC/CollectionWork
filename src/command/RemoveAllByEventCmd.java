package command;

import java.util.LinkedList;

import exeptions.WrongFieldExeption;
import helpers.MyScanner;
import mainclasses.Event;
import mainclasses.Ticket;

public class RemoveAllByEventCmd implements Command {
	private LinkedList<Ticket> list;
	private MyScanner sc;
	
	public RemoveAllByEventCmd(CommandHelper ch) {
		this.list = ch.getList();
		this.sc = ch.getScanner();
	}
	
	
	@Override
	public String execute(String[] args) {
		
		System.out.println("����� �������, �� �������� ������� �����:");
		Event ev = null;
		try {
			ev = Event.create(sc);
		} catch (WrongFieldExeption e) {
			System.err.println("������ � �������� �������: " + e.getMessage());
			return "������� �� ������, ������ �� �������";
		}
		
		int count = 0;
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getEvent().equals(ev)) {
				list.remove(i);
				count++;
			}
		}
		
		if(count == 0)
			return "�� ������� ������� � ����� ��������";
		
		return "������� " + count + " �������";
	}

	@Override
	public String getName() {
		return "remove_all_by_event";
	}

}
