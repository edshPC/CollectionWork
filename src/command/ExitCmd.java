package command;

public class ExitCmd implements Command {
	private ExitCmd() {}
	
	@Override
	public String execute(String[] args) {
		System.out.println("����� �� ���������");
		System.exit(0);
		return "��������� ���������";
	}

	@Override
	public String getName() {
		return "exit";
	}
	
	private static ExitCmd instance;
	
	public static synchronized ExitCmd get() {
		if (instance == null) {
			instance = new ExitCmd();
		}
		return instance;
	}

}
