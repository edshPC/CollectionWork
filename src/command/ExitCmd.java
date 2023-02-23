package command;

public class ExitCmd implements Command {
	private ExitCmd() {}
	
	@Override
	public String execute(String[] args) {
		System.out.println("Выход из программы");
		System.exit(0);
		return "Программа завершена";
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
