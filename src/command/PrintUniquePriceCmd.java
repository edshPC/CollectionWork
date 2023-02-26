package command;

import java.util.LinkedList;
import java.util.TreeSet;

import mainclasses.Ticket;

public class PrintUniquePriceCmd implements Command {
	private LinkedList<Ticket> list;
	
	public PrintUniquePriceCmd(LinkedList<Ticket> list) {
		this.list = list;
	}
	
	@Override
	public String execute(String[] args) {
		if(list.size() == 0)
			return "Коллекция пуста";
		
		TreeSet<Long> prices = new TreeSet<>();
		
		for(Ticket ticket : list) {
			prices.add(ticket.getPrice());
		}
		return "Надены билеты по следующим ценам:\n" + prices.toString();
	}

	@Override
	public String getName() {
		return "print_unique_price";
	}

}
