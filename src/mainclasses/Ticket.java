package mainclasses;
import java.time.ZonedDateTime;

import org.json.JSONException;
import org.json.JSONObject;

import enums.TicketType;
import exeptions.WrongFieldExeption;

public class Ticket {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private String comment; //Поле может быть null
    private TicketType type; //Поле не может быть null
    private Event event; //Поле не может быть null
    
    private static long lastId = 0;
    
    public Ticket(String name, Coordinates coordinates, long price, String comment, TicketType type, Event event) throws WrongFieldExeption {
		id = ++lastId;
		creationDate = ZonedDateTime.now();
		
		if(name == null || name.isEmpty() || coordinates == null || price <= 0 || type == null || event == null)
			throw new WrongFieldExeption("Недопустимое значение поля");
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
			throw new WrongFieldExeption("Ошибка в получении поля");
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
    	String out = "Информация о билете #" + id + ":\n" +
    			" - Имя: " + name + "\n" +
    			" - Координаты: " + coordinates + "\n" +
    			" - Дата создания: " + creationDate + "\n" +
    			" - Цена: " + price + "\n" +
    			" - Комментарий: " + comment + "\n" +
    			" - Тип: " + type + "\n" +
    			" - Событие: " + event;
    	
    	return out;
    }
       
}