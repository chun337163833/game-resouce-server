package org.shovelgame.engine.io;

import java.io.IOException;
import java.io.InputStream;

public class LineReader {

	private InputStream in;

	public LineReader(InputStream in) {
		super();
		this.in = in;
	}

	public String readLine() throws IOException {
		return this.readLine('\n');
	}
	
	public String readLine(String endOfLineCharacter) throws IOException {
		return this.readLine(endOfLineCharacter.toCharArray());
	}
	public String readLine(char endOfLineCharacter) throws IOException {
		return this.readLine(new char[]{endOfLineCharacter});
	}
	public String readLine(char[] endOfLineCharacters) throws IOException {
		int content;
		StringBuilder builder = new StringBuilder();
		char[] lastchars = new char[endOfLineCharacters.length];
		while ((content = in.read()) != -1) {
			char c = (char) content;
			builder.append(c);
			this.moveChar(lastchars, c);
			if(isEndOfLine(lastchars, endOfLineCharacters)) {
				return builder.toString();
			}
		}
		return null;
	}
	private void moveChar(char[] lastchars, char c) {
		for(int i = lastchars.length-1; i > 0; i--) {
			lastchars[i-1] = lastchars[i];
		}
		lastchars[lastchars.length-1] = c;
	}
	
	private boolean isEndOfLine(char[] lastchars, char[] endofline) {
		if(lastchars.length != endofline.length) {
			throw new RuntimeException("End of line must have same length as buffer of last characters for comparison.");
		}
		for(int i = 0; i < lastchars.length; i++) {
			if(lastchars[i]!=endofline[i]) {
				return false;
			}
		}
		return true;
	}
	
}
