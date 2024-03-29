package command;

import java.util.LinkedList;

import org.json.JSONArray;

import helpers.FileHelper;
import helpers.JsonHelper;
import mainclasses.Ticket;

public class SaveCmd implements Command {
	private LinkedList<Ticket> list;
	private FileHelper fh;
	
	public SaveCmd(CommandHelper ch) {
		this.list = ch.getList();
		this.fh = ch.getFileHelper();
	}
	
	@Override
	public String execute(String[] args) {
		JSONArray arr = new JSONArray();		
		for(Ticket ticket : list) {
			arr.put(ticket.toJsonObject());
		}
		JsonHelper jh = new JsonHelper(arr);
		jh.stringifyJsonArr();
		fh.setRawJson(jh.getRawJson());
		if(fh.writeToFile())
			return "��������� ���������";
		else
			return "��������� �� ���������";
	}

	@Override
	public String getName() {
		return "save";
	}

}
