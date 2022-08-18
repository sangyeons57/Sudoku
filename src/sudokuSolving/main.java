package sudokuSolving;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class main {
	
	public static void main(String[] args)
	{
		String digits = "123456789";
		String rows = "ABCDEFGHI";
		String cols = digits;
		List<String> squares = cross(rows , cols);
		List<String> colsList = List.of("ABC","DEF","GHI");
		List<String> rowsList = List.of("123","456","789");
		
		List<List<String>> unitlist = new ArrayList<List<String>>();
		
		Map<String, List<String>> units = new HashMap<String, List<String>>();
		Map<String, Set<String>> peers = new HashMap<String, Set<String>>();
		
		//unit list make
		//cols line unit
		for (char c : cols.toCharArray())
		{
			unitlist.add(cross(rows, "" + c));
		}
		//rows line unit
		for (char r : rows.toCharArray())
		{
			unitlist.add(cross(rows, "" + r));
		}
		//box unit
		for ( int i =0; i < 3; i++)
		{
			unitlist.add( cross(colsList.get(i), rowsList.get(i) ) );
		}
		
		
		//make units : 3 kind unit -> dict (main : units)
		for ( String s : squares)
		{
			for (List<String> u : unitlist)
			{
				if (u.contains(s))
				{
					units.put(s, u);
				}
			}
		}
		
		//make peers: be able to peers about main square
		for (String s : squares )
		{
			Set<String> set = new HashSet<String>();
			set.addAll( units.get(s));
			set.remove(s);
			peers.put(s, set);
		}
		System.out.println(peers);

	}

	public Map<String,List<String>> parse_grid(Map<String,List<String>> grid, List<String> squares, String digits) 
	{
		Map<String, String> values = new HashMap<String, List<String>>();
		for(String s : squares)
		{
			values.put(s, digits);
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
