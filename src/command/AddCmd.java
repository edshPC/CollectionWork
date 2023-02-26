package command;

import java.util.LinkedList;
import java.util.Scanner;

import exeptions.WrongFieldExeption;
import mainclasses.Ticket;

public class AddCmd implements Command {
	private LinkedList<Ticket> list;
	private Scanner sc;
	
	public AddCmd(LinkedList<Ticket> list, Scanner sc) {
		this.list = list;
		this.sc = sc;
	}
	
	
	@Override
	public String execute(String[] args) {
		//Scanner sc = new Scanner(System.in);
		
		try {
			list.add(Ticket.create(sc));
		} catch (WrongFieldExeption e) {
			System.err.println("Ошибка в создании билета: " + e.getMessage());
			return "Билет не создан";
		}
		
		return "Билет успешно добавлен!";
	}

	@Override
	public String getName() {
		return "add";
	}

}
