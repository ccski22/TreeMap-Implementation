package assignment2_f20;



	public interface Map {
	  public Value put ( String k, Value v ); // recursive
	  public Value get (String k);            // recursive
	  public void remove (String k);          // recursive
	  public boolean hasKey(String k);        // recursive
	  public int size();
	}


