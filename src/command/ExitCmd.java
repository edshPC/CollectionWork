package command;

import helpers.MyScanner;

public class ExitCmd implements Command {
	
	private MyScanner sc;
		
	public ExitCmd(CommandHelper ch) {
		this.sc = ch.getScanner();
	}
	
	@Override
	public String execute(String[] args) {
		System.out.println("����� �� ���������");
		sc.close();
		System.exit(0);
		return "��������� ���������";
	}

	@Override
	public String getName() {
		return "exit";
	}

}
