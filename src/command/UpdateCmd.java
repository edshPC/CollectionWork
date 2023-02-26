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
			return "Не указан или указан неверно id элемента. Пожалуйста укажите id как число, пример: 'update 5'";
		}
		int index = Ticket.getIndexById(id);
		if(index < 0)
			return "Не найден билет с данным id. Используйте add чтобы добавить новый";
		
		try {
			Ticket.putWithId(index, id, Ticket.create(sc));
		} catch (WrongFieldExeption e) {
			System.err.println("Ошибка в создании билета: " + e.getMessage());
			return "Билет не обновлен";
		}
		
		return "Билет успешно обновлен!";
	}

	@Override
	public String getName() {
		return "update";
	}

}
