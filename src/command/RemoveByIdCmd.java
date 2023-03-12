package command;

import java.util.LinkedList;

import mainclasses.Ticket;

public class RemoveByIdCmd implements Command {
	private LinkedList<Ticket> list;
	
	public RemoveByIdCmd(CommandHelper ch) {
		this.list = ch.getList();
	}
	
	
	@Override
	public String execute(String[] args) {
		long id;
		try {
			id = Long.valueOf(args[1]);
		} catch (NumberFormatException | IndexOutOfBoundsException e) {
			return "�� ������ ��� ������ ������� id ��������. ���������� ������� id ��� �����, ������: 'update 5'";
		}
		int index = Ticket.getIndexById(id);
		if(index < 0)
			return "�� ������ ����� � ������ id";
		
		list.remove(index);
		
		return "����� ������� ������!";
	}

	@Override
	public String getName() {
		return "remove_by_id";
	}

}
