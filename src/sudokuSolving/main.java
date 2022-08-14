package sudokuSolving;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class main {
	
	public static void main(String[] args)
	{
		String digits = "123456789";
		String rows = "ABCDEFGHI";
		String cols = digits;
		List<String> squares = cross(digits , rows);
		List<String> colsList = List.of("ABC","DEF","GHI");
		List<String> rowsList = List.of("123","456","789");
		
		List<List<String>> unitlist = new ArrayList<List<String>>();
		
		Map<String, List<String>> units = new HashMap<>();
		Map<String, List<String>> peers = new HashMap<>();
		
		for (char c : cols.toCharArray())
		{
			unitlist.add(cross(rows, "" + c));
		}
		for (char r : rows.toCharArray())
		{
			unitlist.add(cross(rows, "" + r));
		}
		for ( int i =0; i < 3; i++)
		{
			unitlist.add( cross(colsList.get(i), rowsList.get(i) ) );
		}
		System.out.println(unitlist);
		
		for ( String s : squares)
		{
			for (List<String> u : unitlist)
			{
				if (u.contains(s))
				{
					
				}
			}
		}
	}
	
	
	static public List<String> cross(String _a, String _b) 
	{
		char[] charA = _a.toCharArray();
		char[] charB = _b.toCharArray();
		List<String> value = new ArrayList<String>();
		for (char b : charA)
		{
			for (char a : charB)
			{
				value.add(""+ b + a);
			}
		}
		return value;
	}

}
