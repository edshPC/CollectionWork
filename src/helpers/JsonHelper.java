package helpers;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;

import exeptions.WrongFieldExeption;
import mainclasses.Ticket;

public class JsonHelper {
	private String rawJson;
	private JSONArray jsonArr;
	
	public JsonHelper(String rawJson) {
		this.rawJson = rawJson;
	}
	
	public JsonHelper(JSONArray jsonArr) {
		this.jsonArr = jsonArr;
	}	
	
	public boolean parseRawJson() {
		try {
			jsonArr = new JSONArray(rawJson);
		} catch (JSONException e) {
			System.err.println("Ошибка в парсинге. Лист объектов пуст");
			return false;
		}
		
		return true;
	}
	
	public void stringifyJsonArr() {
		rawJson = jsonArr.toString(4);
	}
	
	public JSONArray getJsonArr() {
		return jsonArr;
	}
	
	public LinkedList<Ticket> toLinkedList() throws WrongFieldExeption {
		LinkedList<Ticket> temp = new LinkedList<>();
		
		for(int i=0; i<jsonArr.length(); i++) {
			temp.add(new Ticket(jsonArr.getJSONObject(i)));
		}
		
		return temp;
	}
	
	public String getRawJson() {
		return rawJson;
	}
}
