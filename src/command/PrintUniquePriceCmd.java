package command;

import java.util.LinkedList;
import java.util.TreeSet;

import mainclasses.Ticket;

public class PrintUniquePriceCmd implements Command {
	private LinkedList<Ticket> list;
	
	public PrintUniquePriceCmd(CommandHelper ch) {
		this.list = ch.getList();
	}
	
	@Override
	public String execute(String[] args) {
		if(list.size() == 0)
			return "��������� �����";
		
		TreeSet<Long> prices = new TreeSet<>();
		
		for(Ticket ticket : list) {
			prices.add(ticket.getPrice());
		}
		return "������ ������ �� ��������� �����:\n" + prices.toString();
	}

	@Override
	public String getName() {
		return "print_unique_price";
	}

}
