package command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

import helpers.FileHelper;
import mainclasses.Ticket;

public class CommandHelper {
	private LinkedList<Ticket> list;
	private FileHelper fileHelper;
	private Scanner sc;
	private HashMap<String, Command> commands;
	
	public CommandHelper(LinkedList<Ticket> list, FileHelper fh) {
		this.list = list;
		this.fileHelper = fh;
		sc = new Scanner(System.in);
		commands = new HashMap<>();
	}
	
	public void registerCommands(Command... cmds) {
		for(Command cmd : cmds) {
			this.commands.put(cmd.getName(), cmd);
		}
	}
	
	public void executeNextCommand() {
		System.out.print("> ");
		String[] cmd = sc.nextLine().split(" ");
		
		if(commands.containsKey(cmd[0])) {
			Command command = commands.get(cmd[0]);
			System.out.println(command.execute(cmd));
			
		} else {
			System.err.println("Данной команды не существует. Введите 'help' для просмотра списка команд");
		}
		
		
		
		executeNextCommand();
	}
}
