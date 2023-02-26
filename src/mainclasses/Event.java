package mainclasses;
import java.time.LocalDate;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import enums.EventType;
import exeptions.WrongFieldExeption;

public class Event {
    private Long id; //���� �� ����� ���� null, �������� ���� ������ ���� ������ 0, �������� ����� ���� ������ ���� ����������, �������� ����� ���� ������ �������������� �������������
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private LocalDate date; //���� ����� ���� null
    private long minAge;
    private long ticketsCount; //�������� ���� ������ ���� ������ 0
    private EventType eventType; //���� �� ����� ���� null
    
    private static long lastId = 0;
    
    public Event(String name, LocalDate date, long minAge, long ticketsCount, EventType eventType) throws WrongFieldExeption {
    	id = ++lastId;
    	
    	if(name == null || name.isEmpty() || ticketsCount <= 0 || eventType == null)
			throw new WrongFieldExeption("������������ �������� ����");
    	
    	this.name = name;
    	this.date = date;
    	this.minAge = minAge;
    	this.ticketsCount = ticketsCount;
    	this.eventType = eventType;
    	
	}
    
    public Event(JSONObject jObj) throws WrongFieldExeption {
		try {
			this.id = jObj.getLong("id");
	    	this.name = jObj.getString("name");
			this.date = LocalDate.parse(jObj.getString("date"));
			this.minAge = jObj.getLong("minAge");
			this.ticketsCount = jObj.getLong("ticketsCount");
			this.eventType = EventType.valueOf(jObj.getString("eventType"));
		} catch (JSONException e) {
			throw new WrongFieldExeption("������ � ��������� ����");
		}
		lastId = Math.max(lastId, this.id);
    }
    
    public JSONObject toJsonObject() {
    	JSONObject jObj = new JSONObject();
    	jObj.put("id", id).put("name", name).put("date", date.toString()).put("minAge", minAge)
    		.put("ticketsCount", ticketsCount).put("eventType", eventType.name());
    	return jObj;
    }
    
    @Override
    public String toString() {
    	String out = "������� #" + id + ":\n" +
    			"   - ���: " + name + "\n" +
    			"   - ���� ����������: " + date + "\n" +
    			"   - ����������� �������: " + minAge + "\n" +
    			"   - ���������� �������: " + ticketsCount + "\n" +
    			"   - ���: " + eventType;
    	return out;
    }
    
    @Override
    public boolean equals(Object o) {
    	if(o == this)
    		return true;
    	
    	if(!(o instanceof Event))
    		return false;
    	
    	Event ev = (Event)o;
		return name.equals(ev.name) && date.equals(ev.date) && minAge == ev.minAge &&
				ticketsCount == ev.ticketsCount && eventType.equals(ev.eventType);
	}
    
    public static Event create(Scanner sc) throws WrongFieldExeption {
    	System.out.println("����� ������ ������� ������:");
		System.out.print("����� ��������:\n>>> ");
		String evName = sc.nextLine();
		
		LocalDate ld = null;
		while (true) {
			System.out.print("����� ���� ���������� � ������� 'YYYY-MM-DD':\n>>> ");
			try {
				ld = LocalDate.parse(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("������ ��� ����� ����: " + e.getMessage());
			}
		}
		
		long minAge = 0;
		while (true) {
			System.out.print("����� ����������� ������� (�����):\n>>> ");
			try {
				minAge = Long.valueOf(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("������ ��� ����� ��������: " + e.getMessage());
			}
		}
		
		long ticketsCount = 0;
		while (true) {
			System.out.print("����� ���������� ������� (����� > 0):\n>>> ");
			try {
				ticketsCount = Long.valueOf(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("������ ��� ����� ����������: " + e.getMessage());
			}
		}
		
		EventType type = null;
		while(true) {
			System.out.println("����� ����� ���� �������, ��������� ����:");
			EventType[] values = EventType.values();
			for(int i=0; i<values.length; i++) {
				System.out.println(" " + (i+1) + ". " + values[i]);
			}
			System.out.print(">>> ");
			try {
				int id = Integer.valueOf(sc.nextLine());
				type = values[id-1];
				break;
			} catch (Exception e) {
				System.err.println("������ ��� ����� ����: " + e.getMessage());
			}
		}
		
		return new Event(evName, ld, minAge, ticketsCount, type);
    }
    
}