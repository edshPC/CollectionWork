package command;

import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

import helpers.FileHelper;
import mainclasses.Ticket;

public class ExecuteScriptCmd implements Command {
	private LinkedList<Ticket> list;
	private FileHelper fh;
	private static int invokes = 0;
	
	public ExecuteScriptCmd(LinkedList<Ticket> list, FileHelper fh) {
		this.list = list;
		this.fh = fh;
	}
	
	@Override
	public String execute(String[] args) {
		Scanner sc;
		try {
			sc = new Scanner(Paths.get(args[1]));
		} catch (Exception e) {
			return "Файла с таким названием по этому пути не обнаружено. Создайте, например, файл script.txt в каталоге с программой и введите 'execute_script script.txt'";
		}
		CommandHelper commandHelper = new CommandHelper(sc);
		commandHelper.registerAllCommands(list, fh);
		invokes++;
		if(invokes > 5) {
			invokes--;
			return "Нельзя запускать более 5 вложенных скриптов. Провертье, не запускает ли скрипт сам себя";
		}
		while (sc.hasNextLine()) {
			commandHelper.executeNextCommand();
		}
		invokes--;
		return "Скрипт завершен";
	}

	@Override
	public String getName() {
		return "execute_script";
	}

}
