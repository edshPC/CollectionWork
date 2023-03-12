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
	
	/**
	 * ������� ����� �������� ������ � {@link JSONArray}
	 * @return ������� �� �������
	 */
	public boolean parseRawJson() {
		try {
			jsonArr = new JSONArray(rawJson);
		} catch (JSONException e) {
			System.err.println("������ � ��������. ���� �������� ����");
			return false;
		}
		
		return true;
	}
	
	/**
	 * ������� ��������� {@link JSONArray} � �������� ������
	 */
	public void stringifyJsonArr() {
		rawJson = jsonArr.toString(4);
	}
	
	public JSONArray getJsonArr() {
		return jsonArr;
	}
	
	/**
	 * ���� ��������� �������� �� ��������� {@link JSONArray}
	 * @return ��������� ���������
	 */
	public LinkedList<Ticket> toLinkedList() {
		LinkedList<Ticket> temp = new LinkedList<>();
		
		for(int i=0; i<jsonArr.length(); i++) {
			
			try {
				temp.add(new Ticket(jsonArr.getJSONObject(i)));
			} catch (WrongFieldExeption | JSONException e) {
				System.err.println("�������� ������������ �����: " + e.getMessage());
			}
		}
		
		return temp;
	}
	
	public String getRawJson() {
		return rawJson;
	}
}
