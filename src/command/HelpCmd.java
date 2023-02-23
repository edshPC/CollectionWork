package command;

public class HelpCmd implements Command {
	private HelpCmd() {}
	
	@Override
	public String execute(String[] args) {
		String out = """
				- help : ������� ������� �� ��������� ��������
				- info : ������� � ����������� ����� ������ ���������� � ��������� (���, ���� �������������, ���������� ��������� � �.�.)
				- show : ������� � ����������� ����� ������ ��� �������� ��������� � ��������� �������������
				- add {element} : �������� ����� ������� � ���������
				- update id {element} : �������� �������� �������� ���������, id �������� ����� ���������
				- remove_by_id id : ������� ������� �� ��������� �� ��� id
				- clear : �������� ���������
				- save : ��������� ��������� � ����
				- execute_script file_name : ������� � ��������� ������ �� ���������� �����. � ������� ���������� ������� � ����� �� ����, � ������� �� ������ ������������ � ������������� ������.
				- exit : ��������� ��������� (��� ���������� � ����)
				- remove_first : ������� ������ ������� �� ���������
				- remove_greater {element} : ������� �� ��������� ��� ��������, ����������� ��������
				- remove_lower {element} : ������� �� ��������� ��� ��������, �������, ��� ��������
				- remove_all_by_event event : ������� �� ��������� ��� ��������, �������� ���� event �������� ������������ ���������
				- filter_contains_comment comment : ������� ��������, �������� ���� comment ������� �������� �������� ���������
				- print_unique_price : ������� ���������� �������� ���� price ���� ��������� � ���������""";
		return out;
	}

	@Override
	public String getName() {
		return "help";
	}
	
	private static HelpCmd instance;
	
	public static synchronized HelpCmd get() {
		if (instance == null) {
			instance = new HelpCmd();
		}
		return instance;
	}

}
