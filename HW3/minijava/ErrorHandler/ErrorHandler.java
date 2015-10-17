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
	    	String rowCol = s.substring(1, s.indexOf(']'));
	    	String[] coord = rowCol.split(",");
	    	Integer row = Integer.parseInt(coord[0]);
	    	Integer col = Integer.parseInt(coord[1]);

    		LineNumberReader in = new LineNumberReader(new FileReader(filename));
    		for (int i = 1; i < row; i++) {
    			in.readLine();
    		}

    		String problemLine = in.readLine();
	    	char[] problemChars = problemLine.toCharArray();
	    	StringBuilder sb = new StringBuilder();
	    	for (int i = 0; i < col-1; i++) {
	    		if (problemChars[i] == '\t') {
	    			sb.append("\t");
	    		} else {
	    			sb.append(" ");
	    		}
	    	}
            String spaces = sb.toString();

	    	sb = new StringBuilder();
    		sb.append("Error during parsing: ");
            sb.append(s);
    		sb.append("\nThe error was detected at line ");
            sb.append(row);
            sb.append(", column ");
            sb.append(col);
    		sb.append("\nHere is line ");
            sb.append(row);
            sb.append(". The carat mark (^) indicates where the error was detected.\n");
    		sb.append(problemLine);
            sb.append("\n");
    		sb.append(spaces);
            sb.append("^\n");
    		longMessage = sb.toString();
    	} catch (Exception e) {
    		throw new UnsupportedOperationException();
    	}
    	return longMessage;
    }
}
