package sudokuSolving;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

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
	
	//List<Iterator<String>,Iterator<String>> 
	public void grid_values(List<String> grid, String digits, List<String> squares)
	{
		String secondCheck = "0.";
		List<String> chars = new ArrayList<String>();
		for (String c : grid)
		{
			if(digits.contains(c) || secondCheck.contains(c))
			{
				chars.add(c);
			}
		}
		
		assert chars.size() == 81;
		/*
		//List<Pair<List<String>, List<String>>> = new ArrayList<Pair<List<String>, List<String>>>();
		Map<List<String>,List<String>> rmap = new HashMap<List<String>,List<String>>();
		rmap.put(squares, chars);
		*/
		Iterator<String> i1 = chars.listIterator();
		Iterator<String> i2 = squares.listIterator();
		//List<Iterator<String>,Iterator<String>> rvalue = new ArrayList<Iterator<String>,Iterator<String>>();
		
	}

	/*
	public Map<String,List<String>> parse_grid(Map<String,List<String>> grid, List<String> squares, String digits) 
	{
		Map<String, String> values = new HashMap<String, String>();
		for(String s : squares)
		{
			values.put(s, digits);
		}
		

	}
	*/
	
	
	
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

class PairList <T> 
{
	private Queue<T> list1 = new LinkedList<T>();
	private Queue<T> list2 = new LinkedList<T>();
	
	public PairList ()
	{
		return;
	}

	public void push (T e1, T e2)
	{
		list1.offer(e1);
		list2.offer(e2);
	}
	
	public List<T> pop()
	{
		List<T> rv = new ArrayList<T>();
		rv.add(list1.poll());
		rv.add(list1.poll());

		return rv;
	}

	
}
