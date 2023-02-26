package mainclasses;
import java.time.LocalDate;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

import enums.EventType;
import exeptions.WrongFieldExeption;

public class Event {
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDate date; //Поле может быть null
    private long minAge;
    private long ticketsCount; //Значение поля должно быть больше 0
    private EventType eventType; //Поле не может быть null
    
    private static long lastId = 0;
    
    public Event(String name, LocalDate date, long minAge, long ticketsCount, EventType eventType) throws WrongFieldExeption {
    	id = ++lastId;
    	
    	if(name == null || name.isEmpty() || ticketsCount <= 0 || eventType == null)
			throw new WrongFieldExeption("Недопустимое значение поля");
    	
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
			throw new WrongFieldExeption("Ошибка в получении поля");
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
    	String out = "Событие #" + id + ":\n" +
    			"   - Имя: " + name + "\n" +
    			"   - Дата проведения: " + date + "\n" +
    			"   - Минимальный возраст: " + minAge + "\n" +
    			"   - Количество билетов: " + ticketsCount + "\n" +
    			"   - Тип: " + eventType;
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
    	System.out.println("Введи данные события билета:");
		System.out.print("Введи название:\n>>> ");
		String evName = sc.nextLine();
		
		LocalDate ld = null;
		while (true) {
			System.out.print("Введи дату проведения в формате 'YYYY-MM-DD':\n>>> ");
			try {
				ld = LocalDate.parse(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("Ошибка при вводе даты: " + e.getMessage());
			}
		}
		
		long minAge = 0;
		while (true) {
			System.out.print("Введи минимальный возраст (число):\n>>> ");
			try {
				minAge = Long.valueOf(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("Ошибка при вводе возраста: " + e.getMessage());
			}
		}
		
		long ticketsCount = 0;
		while (true) {
			System.out.print("Введи количество билетов (число > 0):\n>>> ");
			try {
				ticketsCount = Long.valueOf(sc.nextLine());
				break;
			} catch (Exception e) {
				System.err.println("Ошибка при вводе количества: " + e.getMessage());
			}
		}
		
		EventType type = null;
		while(true) {
			System.out.println("Введи номер типа события, доступные типы:");
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
				System.err.println("Ошибка при вводе типа: " + e.getMessage());
			}
		}
		
		return new Event(evName, ld, minAge, ticketsCount, type);
    }
    
}