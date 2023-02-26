package command;

import java.util.LinkedList;
import java.util.Scanner;

import exeptions.WrongFieldExeption;
import mainclasses.Event;
import mainclasses.Ticket;

public class RemoveAllByEventCmd implements Command {
	private LinkedList<Ticket> list;
	private Scanner sc;
	
	public RemoveAllByEventCmd(LinkedList<Ticket> list, Scanner sc) {
		this.list = list;
		this.sc = sc;
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
