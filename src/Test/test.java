package Test;

import java.util.ArrayList;

public class test {
	public String reverseWords(String s) {

		String tmpString = "";
        ArrayList<String> strings = new ArrayList<String>();
        
        for (int i = 0; i < s.length(); i++) {
        	char a_char = s.charAt(i);
            String a_letter = Character.toString(s.charAt(i));
            if (!a_letter.equals(" "))
                tmpString = tmpString + a_letter;
            else if (tmpString.length() >0) {
                strings.add(tmpString);
                tmpString = "";
            }
        }
        
        if (tmpString.length() >0) {
            strings.add(tmpString);
            tmpString = "";
        }
        
        if (strings.size() == 0)
            return null;
        else {
            String result = "";
            for (String string : strings)
                result = string + " " + result;
            result = result.substring(0, result.length() - 1);
            return result;
        }
    }
	
	public static void main(String[] args) {
		String ttt = "   32   1";
		System.out.println((new test()).reverseWords(ttt));
	}
}