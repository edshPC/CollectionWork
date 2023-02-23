package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHelper {
	private String filename;
	private String rawJson;
	
	public FileHelper(String filename) {
		this.filename = filename;
	}
	
	public boolean createFile() {
		File file = new File(filename);
		try {
			return file.createNewFile();
		} catch (Exception e) {
			System.err.println("Ошибка в создании файла");
		}
		return false;
	}
	
	public boolean readFile() {
		String source = "";
		try {
			Scanner sc = new Scanner(new FileReader(filename));
			while (sc.hasNextLine()) {
				source += sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.err.println("Файл не найден. Лист объектов пуст");
			return false;
		}
	
		this.rawJson = source;
		return true;
	}
	
	public String getRawJson() {
		return rawJson;
	}
	
	public void setRawJson(String rawJson) {
		this.rawJson = rawJson;
	}
	
	public boolean writeToFile(String rawJson) {
		try {
			FileWriter fw = new FileWriter(filename);
			fw.write(rawJson);
			fw.close();
		} catch (FileNotFoundException e) {
			System.out.println("Файла не существует. Создание...");
			createFile();
			writeToFile(rawJson);
		} catch (IOException e) {
			System.err.print("Ошибка в записи файла. Попробуйте изменить права доступа");
			return false;
		}
		
		return true;
	}
	
	public boolean writeToFile() {
		return writeToFile(rawJson);
	}
	
}
