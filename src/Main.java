import java.util.LinkedList;
import java.util.Scanner;

import command.*;
import exeptions.*;
import helpers.*;
import mainclasses.Ticket;

public class Main {

	public static void main(String[] args) {
		String filename = "source.json";
		if(args.length > 0)
			filename = args[0];
		else
			System.out.println("Вы не ввели название файла в аргументах. Попытка загрузки из файла по умолчанию: 'source.json'");
		
		FileHelper fileHelper = new FileHelper(filename);
		LinkedList<Ticket> tickets = new LinkedList<>();
		
		if(fileHelper.readFile()) {
			JsonHelper jHelper = new JsonHelper(fileHelper.getRawJson());
			if(jHelper.parseRawJson()) {
				try {
					tickets = jHelper.toLinkedList();
					System.out.println("Загружено " + tickets.size() + " элементов в коллекцию");
				} catch (WrongFieldExeption e) {
					System.err.println(e.getMessage());
				}
			}
		}
		Ticket.setList(tickets);
		//Ticket.sortList();
		
		Scanner sc = new Scanner(System.in);
		CommandHelper commandHelper = new CommandHelper(sc);
		commandHelper.registerAllCommands(tickets, fileHelper);
		
		while(commandHelper.executeNextCommand());

	}
	
}
