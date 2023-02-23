package mainclasses;
import java.time.LocalDate;

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
}