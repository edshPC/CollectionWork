package command;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import helpers.FileHelper;
import mainclasses.Ticket;

public class ExecuteScriptCmd implements Command {
	private LinkedList<Ticket> list;
	private FileHelper fh;
	private static ArrayList<Path> invokes = new ArrayList<>();
	
	public ExecuteScriptCmd(LinkedList<Ticket> list, FileHelper fh) {
		this.list = list;
		this.fh = fh;
	}
	
	@Override
	public String execute(String[] args) {
		Scanner sc;
		Path path;
		try {
			path = Paths.get(args[1]);
			sc = new Scanner(path);
		} catch (Exception e) {
			return "����� � ����� ��������� �� ����� ���� �� ����������. ��������, ��������, ���� script.txt � �������� � ���������� � ������� 'execute_script script.txt'";
		}
		CommandHelper commandHelper = new CommandHelper(sc);
		commandHelper.registerAllCommands(list, fh);
		
		if(invokes.contains(path)) {
			invokes.clear();
			sc.close();
			return "���������� ��������. ���������, �� ��������� �� ������ ��� ����";
		}
		invokes.add(path);
		while (sc.hasNextLine()) {
			commandHelper.executeNextCommand();
		}
		invokes.clear();
		sc.close();
		return "������ ��������";
	}

	@Override
	public String getName() {
		return "execute_script";
	}

}
