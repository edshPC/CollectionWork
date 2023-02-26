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
			return "����� � ����� ��������� �� ����� ���� �� ����������. ��������, ��������, ���� script.txt � �������� � ���������� � ������� 'execute_script script.txt'";
		}
		CommandHelper commandHelper = new CommandHelper(sc);
		commandHelper.registerAllCommands(list, fh);
		invokes++;
		if(invokes > 5) {
			invokes--;
			return "������ ��������� ����� 5 ��������� ��������. ���������, �� ��������� �� ������ ��� ����";
		}
		while (sc.hasNextLine()) {
			commandHelper.executeNextCommand();
		}
		invokes--;
		return "������ ��������";
	}

	@Override
	public String getName() {
		return "execute_script";
	}

}
