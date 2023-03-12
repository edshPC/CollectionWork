package command;

import exeptions.WrongFieldExeption;
import helpers.MyScanner;
import mainclasses.Ticket;

public class UpdateCmd implements Command {
	private MyScanner sc;
	
	public UpdateCmd(CommandHelper ch) {
		this.sc = ch.getScanner();
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
			return "�� ������ ����� � ������ id. ����������� add ����� �������� �����";
		
		try {
			Ticket.putWithId(index, id, Ticket.create(sc));
		} catch (WrongFieldExeption e) {
			System.err.println("������ � �������� ������: " + e.getMessage());
			return "����� �� ��������";
		}
		
		return "����� ������� ��������!";
	}

	@Override
	public String getName() {
		return "update";
	}

}
