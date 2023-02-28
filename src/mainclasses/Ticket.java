package mainclasses;
import java.time.ZonedDateTime;
import java.util.LinkedList;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import enums.TicketType;
import exeptions.WrongFieldExeption;

public class Ticket implements Comparable<Ticket> {
    private long id; //�������� ���� ������ ���� ������ 0, �������� ����� ���� ������ ���� ����������, �������� ����� ���� ������ �������������� �������������
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private Coordinates coordinates; //���� �� ����� ���� null
    private ZonedDateTime creationDate; //���� �� ����� ���� null, �������� ����� ���� ������ �������������� �������������
    private long price; //�������� ���� ������ ���� ������ 0
    private String comment; //���� ����� ���� null
    private TicketType type; //���� �� ����� ���� null
    private Event event; //���� �� ����� ���� null
    
    private static long lastId = 0;
    private static LinkedList<Ticket> list;
    
    public Ticket(String name, Coordinates coordinates, long price, String comment, TicketType type, Event event) throws WrongFieldExeption {
		id = ++lastId;
		creationDate = ZonedDateTime.now();
		
		if(name == null || name.isEmpty() || coordinates == null || price <= 0 || type == null || event == null)
			throw new WrongFieldExeption("������������ �������� ����");
			//return;
		
		this.name = name;
		this.coordinates = coordinates;
		this.price = price;
		this.comment = comment;
		this.type = type;
		this.event = event;
		
	}
   
    public Ticket(JSONObject jObj) throws WrongFieldExeption {
    	try {
    		this.id = jObj.getLong("id");
        	this.name = jObj.getString("name");
    		this.coordinates = new Coordinates(jObj.getJSONObject("coordinates"));
    		this.creationDate = ZonedDateTime.parse(jObj.getString("creationDate"));
    		this.price = jObj.getLong("price");
    		this.comment = jObj.getString("comment");
    		this.type = TicketType.valueOf(jObj.getString("type"));
    		this.event = new Event(jObj.getJSONObject("event"));
		} catch (JSONException e) {
			throw new WrongFieldExeption("������ � ��������� ����");
		}
    	lastId = Math.max(lastId, this.id);
    }
    
    /**
     * ����� �������� {@link JSONObject} �� ������ �������
     * @return ��� ���� ������� � ���� {@link JSONObject}
     */
    public JSONObject toJsonObject() {
    	JSONObject jObj = new JSONObject();
    	jObj.put("id", id).put("name", name).put("coordinates", coordinates.toJsonObject()).put("creationDate", creationDate.toString())
    		.put("price", price).put("comment", comment).put("type", type.name()).put("event", event.toJsonObject());
    	return jObj;
    }
    
    public Event getEvent() {
		return this.event;
	}
    public String getComment() {
		return this.comment;
	}
    public long getPrice() {
		return this.price;
	}
    
    @Override
    public String toString() {
    	String out = "���������� � ������ #" + id + ":\n" +
    			" - ���: " + name + "\n" +
    			" - ����������: " + coordinates + "\n" +
    			" - ���� ��������: " + creationDate + "\n" +
    			" - ����: " + price + "\n" +
    			" - �����������: " + comment + "\n" +
    			" - ���: " + type + "\n" +
    			" - �������: " + event;
    	
    	return out;
    }
    
    @Override
	public int compareTo(Ticket t) {
    	int diff = this.event.compareTo(t.event);
    	if(diff != 0)
    		return diff;
    	
    	diff = this.name.compareTo(t.name);
    	if(diff != 0)
    		return diff;
    	diff = this.creationDate.compareTo(t.creationDate);
		
		return diff;
	}
    
    /**
     * ����� �������� �������� ������ � ���������, �� ��������� �������, ������������ ��� �������� id
     * @param index ������, ���� ���������
     * @param id id, ������� ����� ���������
     * @param t �����, ������� ����� ��������
     */
    public static void putWithId(int index, long id, Ticket t) {
		if(lastId == t.id)
			lastId--;
		t.id = id;
		list.set(index, t);
    	lastId = Math.max(lastId, id);
    }
    
    /**
     * ������������� ��������� ������, ��� �� ���������
     * @param list ���������
     */
    public static void setList(LinkedList<Ticket> list) {
    	Ticket.list = list;
    }
    
    /**
     * ��������� ���������� �� �����, ����� �� ���� ��������
     */
    public static void sortList() {
    	list.sort(Ticket::compareTo);
    }
    
    /**
     * ���������� ������ �������� � ��������� �� ��� id
     * @param id id
     * @return ������ / -1 ���� ������ �� ������
     */
    public static int getIndexById(long id) {
    	int index = 0;
    	for(Ticket check : Ticket.list) {
    		if(check.id == id)
    			return index;
    		index++;
    	}
    	return -1;
    }
    
    /**
     * �������� �������� ������ ������� ������, ��������� ������ ������
     * @param sc ������, ������� ����� ����������
     * @return ����� ������ ������ {@link Ticket}
     * @throws WrongFieldExeption ���� ���� � ���������� ������� ��������
     */
    public static Ticket create(Scanner sc) throws WrongFieldExeption {
    	System.out.print("����� ��� ������:\n>> ");
		String ticketName = sc.nextLine();
		
		Coordinates coords = null;
		while(true) {
			System.out.print("����� ���������� ����� ����� ������� (x - float <= 542, y - int <= 203, ������: '10.2, 20'):\n>> ");
			String[] coordsStr = sc.nextLine().split(", ");
			try {
				float x = Float.valueOf(coordsStr[0]);
				int y = Integer.valueOf(coordsStr[1]);
				coords = new Coordinates(x, y);
				break;
			} catch (NumberFormatException | IndexOutOfBoundsException | WrongFieldExeption e) {
				System.err.println("������ ��� ����� ���������: " + e.getMessage());
			}
		}
		
		long price = 0;
		while(true) {
			System.out.print("����� ���� ������ (long > 0):\n>> ");
			try {
				price = Long.valueOf(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.err.println("������ ��� ����� ����: " + e.getMessage());
			}
		}
		
		System.out.print("����� �����������:\n>> ");
		String comment = sc.nextLine();
		
		TicketType type = null;
		while(true) {
			System.out.println("����� ����� ���� ������, ��������� ����:");
			TicketType[] values = TicketType.values();
			for(int i=0; i<values.length; i++) {
				System.out.println(" " + (i+1) + ". " + values[i]);
			}
			System.out.print(">> ");
			try {
				int id = Integer.valueOf(sc.nextLine());
				type = values[id-1];
				break;
			} catch (NumberFormatException | IndexOutOfBoundsException e) {
				System.err.println("������ ��� ����� ����: " + e.getMessage());
			}
		}
		
		Event event = null;
		while(true) {
			try {
				event = Event.create(sc);
				break;
			} catch (Exception e) {
				System.err.println("������ ��� �������� �������: " + e.getMessage());
			}
		}
		
		
    	return new Ticket(ticketName, coords, price, comment, type, event);
    }
       
}