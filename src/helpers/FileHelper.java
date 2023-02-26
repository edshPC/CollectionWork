package helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.Scanner;

public class FileHelper {
	private String filename;
	private String rawJson;
	private Path filePath;
	
	public FileHelper(String filename) {
		this.filename = filename;
		this.filePath = Paths.get(filename);
	}
	
	public boolean createFile() {
		File file = new File(filename);
		try {
			return file.createNewFile();
		} catch (Exception e) {
			System.err.println("������ � �������� �����");
		}
		return false;
	}
	
	public boolean readFile() {
		String source = "";
		try {
			Scanner sc = new Scanner(filePath);
			while (sc.hasNextLine()) {
				source += sc.nextLine();
			}
			sc.close();
		} catch (IOException e) {
			System.err.println("���� �� ������. ���� �������� ����");
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
			System.out.println("����� �� ����������. ��������...");
			createFile();
			writeToFile(rawJson);
		} catch (IOException e) {
			System.err.print("������ � ������ �����. ���������� �������� ����� �������");
			return false;
		}
		
		return true;
	}
	
	public boolean writeToFile() {
		return writeToFile(rawJson);
	}
	
	public FileTime getCreationTime() {
		try {
			return (FileTime) Files.getAttribute(Paths.get(filename), "creationTime");
		} catch (IOException e) {
			return FileTime.fromMillis(0);
		}
	}
	
}
