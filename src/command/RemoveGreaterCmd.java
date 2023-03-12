package command;

import java.util.LinkedList;

import mainclasses.Ticket;

public class RemoveGreaterCmd implements Command {
	private LinkedList<Ticket> list;
	
	public RemoveGreaterCmd(CommandHelper ch) {
		this.list = ch.getList();
	}
	
	
	@Override
	public String execute(String[] args) {
		long id;
		try {
			id = Long.valueOf(args[1]);
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			return "�� ������ ��� ������ ������� id ��������. ���������� ������� id ��� �����, ������: 'remove_greater 5'";
		}
		
		Ticket.sortList();
		int index = Ticket.getIndexById(id);
		if(index < 0)
			return "�� ������ ����� � ������ id";
		else if(index == 0)
			return "�� ������� ������� ���� �������";
		
		for (int i = 0; i < index; i++) {
			list.removeFirst();
		}
		
		return "���� ������� " + index + " �������";
	}

	@Override
	public String getName() {
		return "remove_greater";
	}

}
