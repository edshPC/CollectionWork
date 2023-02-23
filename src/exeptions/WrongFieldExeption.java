package exeptions;

import java.io.IOException;

public class WrongFieldExeption extends IOException {
	public WrongFieldExeption(String msg) {
		super(msg);
	}
}
