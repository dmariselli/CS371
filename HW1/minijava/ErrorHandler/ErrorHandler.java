package minijava.ErrorHandler;
import minijava.lexer.*;
import java.io.FileReader;
import java.io.PushbackReader;
import java.io.LineNumberReader;
import java.util.List;
import java.util.Arrays;

import minijava.node.*;

public class ErrorHandler {

    String filename;
    String longMessage;

    public ErrorHandler (String filename) {
    	this.filename = filename;
    }

    public String getLongMessage(String s) {
    	try {
	    	char[] letters = s.toCharArray();
	    	StringBuffer sb = new StringBuffer();
	    	int count = 1;
	    	while (letters[count] != ']') {
	    		sb.append(letters[count++]);
	    	}
	    	List<String> coord = Arrays.asList(sb.toString().split(","));
	    	Integer row = Integer.parseInt(coord.get(0));
	    	Integer col = Integer.parseInt(coord.get(1));
	    	
    		LineNumberReader in = new LineNumberReader(new FileReader(filename));
    		for (int i = 1; i < row; i++) {
    			in.readLine();
    		}

    		String problemLine = in.readLine();
	    	char[] problemChars = problemLine.toCharArray();
	    	StringBuffer spaces = new StringBuffer();
	    	for (int i = 0; i < col-1; i++) {
	    		if (problemChars[i] == '\t') {
	    			spaces.append("\t");
	    		} else {
	    			spaces.append(" ");
	    		}
	    	}
	    	sb = new StringBuffer();
    		sb.append("Error during parsing: " + s + "\n");
    		sb.append("The error was detected at line " + row + ", column " + col + "\n");
    		sb.append("Here is line " + row + ". The carat mark (^) indicates where the error was detected." + "\n");
    		sb.append(problemLine + "\n");
    		sb.append(spaces.toString() + "^" + "\n");
    		longMessage = sb.toString();
    	} catch (Exception e) {
    		throw new UnsupportedOperationException();
    	}
    	return longMessage;
    }
}
