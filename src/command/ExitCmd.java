package command;

import java.util.Scanner;

public class ExitCmd implements Command {
	
	private Scanner sc;
		
	public ExitCmd(Scanner sc) {
		this.sc = sc;
	}
	
	@Override
	public String execute(String[] args) {
		System.out.println("Выход из программы");
		sc.close();
		System.exit(0);
		return "Программа завершена";
	}

	@Override
	public String getName() {
		return "exit";
	}

}
