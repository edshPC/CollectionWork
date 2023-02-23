package mainclasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import exeptions.WrongFieldExeption;

public class Coordinates {
    private float x; //������������ �������� ����: 542
    private Integer y; //������������ �������� ����: 203, ���� �� ����� ���� null
    
    public Coordinates(float x, int y) throws WrongFieldExeption {
    	if(x > 542 || y > 203)
    		throw new WrongFieldExeption("������������ �������� ����");
    	this.x = x;
    	this.y = y;
    }
    
    public Coordinates(JSONObject jObj) throws WrongFieldExeption {
    	
    	try {
    		this.x = jObj.getFloat("x");
        	this.y = jObj.getInt("y");
		} catch (JSONException e) {
			throw new WrongFieldExeption("������ � ��������� ����");
		}
    }
    
    public JSONObject toJsonObject() {
    	JSONObject jObj = new JSONObject();
    	jObj.put("x", x).put("y", y);
    	return jObj;
    }
    
    @Override
    public String toString() {
    	String out = "x: " + x + ", y: " + y;
    	return out;
    }
}