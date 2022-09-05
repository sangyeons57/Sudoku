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

public class sudokuSolving extends RuntimeException {
	String digits = "123456789";
	String rows = "ABCDEFGHI";
	String cols = digits;
	List<String> squares = cross(rows , cols);
	List<String> colsList = List.of("ABC","DEF","GHI");
	List<String> rowsList = List.of("123","456","789");
	
	List<List<String>> unitlist = new ArrayList<List<String>>();
	
	Map<String, List<List<String>>> units = new HashMap<String, List<List<String>>>();
	Map<String, Set<String>> peers = new HashMap<String, Set<String>>();
	

	public static void main(String[] args)
	{
		sudokuSolving ss = new sudokuSolving();
		

		ss.makeBaseVariable();
		
		String grid1 = "003020600900305001001806400008102900700000008006708200002609500800203009005010300";
		System.out.println( ss.parse_grid(grid1) );
		
	}
	
	void makeBaseVariable()
	{
		//unit list make
		//cols line unit
		for (char c : cols.toCharArray())
		{
			unitlist.add(cross(rows, "" + c));
		}
		//rows line unit
		for (char r : rows.toCharArray())
		{
			unitlist.add(cross("" + r,cols));
		}
		//box unit
		for ( int i =0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				unitlist.add( cross(colsList.get(i), rowsList.get(j) ) );
			}
		}
		
		
		//make units : 3 kind unit -> dict (main : units)
		for ( String s : squares)
		{
			List<List<String>> L = new ArrayList<List<String>>();
			for (List<String> u : unitlist)
			{
				if (u.contains(s))
				{
					L.add(u);
				}
			}
			units.put(s, L);
		}
		
		//make peers: be able to peers about main square
		for (String s : squares )
		{
			Set<String> set = new HashSet<String>();
			for (List<List<String>> i : units.values())
			{
				for (List<String> j : i)
				{
					set.addAll( j );
				}
			}
			set.remove(s);
			peers.put(s, set);
		}

	}
	
	Map<String,String> parse_grid(String grid)
	{
		Map<String,String> values = new HashMap<String,String>();
		List<String> chars = new ArrayList<String>();

		for (String s : squares)
		{
			values.put(s, digits);
		}
		for (char s : grid.toCharArray())
		{
			chars.add(""+s);
		}


		for(int i = 0; i < squares.size(); i++)
		{
			if ( digits.contains(chars.get(i)) && !(assign(values,squares.get(i),chars.get(i))))
			{
				System.out.println("exit");
				System.exit(1);
			}
			String d = squares.get(i);
		}
		return values; //return values string
	}
	
	//grid_values 생략
	

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


	boolean assign( Map<String, String> values, String s, String d)
	{


		Map<String,String> other_value = new HashMap<String,String>();
		other_value.put(s, values.get(s).replace(d, ""));
		System.out.println("other_value "+ other_value);
		for( String d2 : other_value.values())
		{
			System.out.println(s+ "===" + d);
			//하나라도 false면 false
			if(!eliminate(values,s,d2))
			{
				return false;
			}
		}
		//아니면 True
		return true;

	}
	
	/**
	 * 
	 * @param values > return value
	 * 
	 * @param s > edit position
	 * @param d > input 삭제할값
	 * @return
	 */
	
	boolean eliminate (Map<String,String> values, String s, String d)
	{
		if (!values.get(s).contains(d)) 
		{
			System.out.println("already eliminate " + s + " : " + d );
			//System.out.println(values.get(s)+" " +s+" "+ d);
			return true;
		}
		
		values.replace(s, values.get(s).replace(d, "") );
		System.out.println(values);
		
		if(values.get(s).length() == 0)
		{
			return false;
		}
		else if(values.get(s).length() == 1) 
		{
			String d2 = values.get(s);
			for(String s2 : peers.get(s))
			{
				if(!(eliminate(values, s2, d2)))
				{
					//하나라도 True 튕겨나감
					return false;
				}
			}
		}

		for (List<String> u : units.get(s))
		{
			//deplace
			List<String> deplaces = new ArrayList<String>();
			for(String i : u)
			{
				if(values.get(i).contains(d))
				{
					deplaces.add(i);
				}
			}
			
			if (deplaces.size() == 0)
			{
				return false;
			}
			else if (deplaces.size() == 1)
			{
				if(!assign(values, deplaces.get(0),d))
				{
					return false;
				}
			}
			
		}

		return true;
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
