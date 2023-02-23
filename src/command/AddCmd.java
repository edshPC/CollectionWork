package command;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

import enums.EventType;
import enums.TicketType;
import exeptions.WrongFieldExeption;
import helpers.FileHelper;
import mainclasses.Coordinates;
import mainclasses.Event;
import mainclasses.Ticket;

public class AddCmd implements Command {
	private LinkedList<Ticket> list;
	
	private AddCmd(LinkedList<Ticket> list) {
		this.list = list;
	}
	
	
	@Override
	public String execute(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("����� ��� ������:\n>> ");
		String ticketName = sc.nextLine();
		
		boolean fin = false;
		Coordinates coords = null;
		while(!fin) {
			System.out.print("����� ���������� ����� ����� ������� (x - float <= 542, y - int <= 203, ������: '10.2, 20'):\n>> ");
			String[] coordsStr = sc.nextLine().split(", ");
			try {
				float x = Float.valueOf(coordsStr[0]);
				int y = Integer.valueOf(coordsStr[1]);
				coords = new Coordinates(x, y);
				fin = true;
			} catch (NumberFormatException | IndexOutOfBoundsException | WrongFieldExeption e) {
				System.err.println("������ ��� ����� ���������: " + e.getMessage());
			}
		}
		
		fin = false;
		long price = 0;
		while(!fin) {
			System.out.print("����� ���� ������ (long > 0):\n>> ");
			try {
				price = Long.valueOf(sc.nextLine());
				fin = true;
			} catch (NumberFormatException e) {
				System.err.println("������ ��� ����� ����: " + e.getMessage());
			}
		}
		
		System.out.print("����� �����������:\n>> ");
		String comment = sc.nextLine();
		
		
		fin = false;
		TicketType type = null;
		while(!fin) {
			System.out.println("����� ����� ���� ������, ��������� ����:");
			TicketType[] values = TicketType.values();
			for(int i=0; i<values.length; i++) {
				System.out.println(" " + (i+1) + ". " + values[i]);
			}
			System.out.print(">> ");
			try {
				int id = Integer.valueOf(sc.nextLine());
				type = values[id-1];
				fin = true;
			} catch (NumberFormatException | IndexOutOfBoundsException e) {
				System.err.println("������ ��� ����� ����: " + e.getMessage());
			}
		}
		
		fin = false;
		Event event = null;
		while(!fin) {
			System.out.println("����� ������ ������� ������:");
			System.out.print("����� ��������:\n>>> ");
			String evName = sc.nextLine();
			System.out.print("����� ���� ���������� � ������� 'YYYY-MM-DD':\n>>> ");
			String dateStr = sc.nextLine();
			System.out.print("����� ����������� �������:\n>>> ");
			String minAgeStr = sc.nextLine();
			System.out.print("����� ���������� �������:\n>>> ");
			String ticketsCountStr = sc.nextLine();
			System.out.println("����� ����� ���� �������, ��������� ����:");
			EventType[] values = EventType.values();
			for(int i=0; i<values.length; i++) {
				System.out.println(" " + (i+1) + ". " + values[i]);
			}
			System.out.print(">>> ");
			String evTypeIdStr = sc.nextLine();
			try {
				event = new Event(evName, LocalDate.parse(dateStr), Long.valueOf(minAgeStr), Long.valueOf(ticketsCountStr), values[Integer.valueOf(evTypeIdStr)-1]);
				fin = true;
			} catch (Exception e) {
				System.err.println("������ ��� �������� �������: " + e.getMessage());
			}
		}
		
		try {
			list.add(new Ticket(ticketName, coords, price, comment, type, event));
		} catch (WrongFieldExeption e) {
			System.err.println("������ � �������� ������: " + e.getMessage());
			return "����� �� ������";
		}
		
		return "����� ������� ��������!";
	}

	@Override
	public String getName() {
		return "add";
	}
	
	private static AddCmd instance;
	
	public static synchronized AddCmd get(LinkedList<Ticket> list) {
		if (instance == null) {
			instance = new AddCmd(list);
		}
		return instance;
	}

}
