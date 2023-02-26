package command;

import java.util.LinkedList;

import mainclasses.Ticket;

public class RemoveLowerCmd implements Command {
	private LinkedList<Ticket> list;
	
	public RemoveLowerCmd(LinkedList<Ticket> list) {
		this.list = list;
	}
	
	
	@Override
	public String execute(String[] args) {
		long id;
		try {
			id = Long.valueOf(args[1]);
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			return "�� ������ ��� ������ ������� id ��������. ���������� ������� id ��� �����, ������: 'remove_lower 5'";
		}
		
		Ticket.sortList();
		int index = Ticket.getIndexById(id);
		if(index < 0)
			return "�� ������ ����� � ������ id";
		else if(index == list.size()-1)
			return "�� ������� ������� ���� �������";
		
		for (int i = index; i < list.size()-1; i++) {
			list.removeLast();
		}
		
		return "���� ������� " + index + " �������";
	}

	@Override
	public String getName() {
		return "remove_lower";
	}

}
