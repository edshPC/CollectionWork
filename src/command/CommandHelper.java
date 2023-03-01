package command;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

import helpers.FileHelper;
import mainclasses.Ticket;

public class CommandHelper {
	//private LinkedList<Ticket> list;
	//private FileHelper fileHelper;
	private Scanner sc;
	private HashMap<String, Command> commands;
	
	/**
	 * ������� ������, ���������� � ��������� �� ������������� ������ � {@link Scanner}
	 * @param sc ������
	 */
	public CommandHelper(Scanner sc) {
		//this.list = list;
		//this.fileHelper = fh;
		this.sc = sc;
		commands = new HashMap<>();
	}
	
	/**
	 * ������������ ������ �������, �������� �� ��� � ������ � {@link HashMap}
	 * @param cmds �������
	 */
	public void registerCommands(Command... cmds) {
		for(Command cmd : cmds) {
			this.commands.put(cmd.getName(), cmd);
		}
	}
	
	/**
	 * ������������ ��� ��������� �������
	 * @param list ������ ��� ���������� ���������
	 * @param fileHelper ��������, ���������� � ������������ ������
	 */
	public void registerAllCommands(LinkedList<Ticket> list, FileHelper fileHelper) {
		registerCommands(new HelpCmd(), new InfoCmd(list, fileHelper), new ShowCmd(list), new AddCmd(list, sc),
				new UpdateCmd(sc), new RemoveByIdCmd(list), new ClearCmd(list), new SaveCmd(list, fileHelper),
				new ExecuteScriptCmd(list, fileHelper), new ExitCmd(sc), new RemoveFirstCmd(list),
				new RemoveGreaterCmd(list), new RemoveLowerCmd(list), new RemoveAllByEventCmd(list, sc),
				new FilterContainsCommentCmd(list), new PrintUniquePriceCmd(list), new SortCmd());
	}
	
	/**
	 * �������� �������� ����� � ���������� ������� ������������, �������� ����� ����������� {@link Scanner}
	 */
	public boolean executeNextCommand() {
		System.out.print("> ");
		try {
			String[] cmd = sc.nextLine().split(" ");
			
			if(cmd.length > 0 && commands.containsKey(cmd[0])) {
				Command command = commands.get(cmd[0]);
				System.out.println(command.execute(cmd));
			} else {
				if(cmd.length > 0 && !cmd[0].isEmpty())
					System.err.println("������ ������� �� ����������. ������� 'help' ��� ��������� ������ ������");
			}
			
		} catch (NoSuchElementException e) {
			System.err.println("���� ������ ��� �������");
			return false;
		}
		return true;
		//executeNextCommand();
	}
}
