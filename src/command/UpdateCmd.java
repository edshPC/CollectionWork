package command;

import java.util.Scanner;

import exeptions.WrongFieldExeption;
import mainclasses.Ticket;

public class UpdateCmd implements Command {
	private Scanner sc;
	
	public UpdateCmd(Scanner sc) {
		this.sc = sc;
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
