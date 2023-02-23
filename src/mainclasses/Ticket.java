package mainclasses;
import java.time.ZonedDateTime;

import org.json.JSONException;
import org.json.JSONObject;

import enums.TicketType;
import exeptions.WrongFieldExeption;

public class Ticket {
    private long id; //�������� ���� ������ ���� ������ 0, �������� ����� ���� ������ ���� ����������, �������� ����� ���� ������ �������������� �������������
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private Coordinates coordinates; //���� �� ����� ���� null
    private ZonedDateTime creationDate; //���� �� ����� ���� null, �������� ����� ���� ������ �������������� �������������
    private long price; //�������� ���� ������ ���� ������ 0
    private String comment; //���� ����� ���� null
    private TicketType type; //���� �� ����� ���� null
    private Event event; //���� �� ����� ���� null
    
    private static long lastId = 0;
    
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
    
    public JSONObject toJsonObject() {
    	JSONObject jObj = new JSONObject();
    	jObj.put("id", id).put("name", name).put("coordinates", coordinates.toJsonObject()).put("creationDate", creationDate.toString())
    		.put("price", price).put("comment", comment).put("type", type.name()).put("event", event.toJsonObject());
    	return jObj;
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
       
}