package command;

import java.util.LinkedList;

import exeptions.WrongFieldExeption;
import helpers.MyScanner;
import mainclasses.Ticket;

public class AddCmd implements Command {
	//private CommandHelper ch;
	private LinkedList<Ticket> list;
	private MyScanner sc;
	
	public AddCmd(CommandHelper ch) {
		//this.ch = ch;
		this.list = ch.getList();
		this.sc = ch.getScanner();
	}
	
	@Override
	public String execute(String[] args) {
		
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
