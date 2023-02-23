import java.util.LinkedList;

import command.*;
import exeptions.*;
import helpers.*;
import mainclasses.Ticket;

public class Main {

	public static void main(String[] args) {
		String filename = "source.json";
		if(args.length > 0)
			filename = args[0];
		
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
		
		CommandHelper commandHelper = new CommandHelper(tickets, fileHelper);
		commandHelper.registerCommands(HelpCmd.get(), InfoCmd.get(tickets), ShowCmd.get(tickets), AddCmd.get(tickets),
				ClearCmd.get(tickets), ExitCmd.get(), SaveCmd.get(tickets, fileHelper));
		commandHelper.executeNextCommand();		

	}
	
}
