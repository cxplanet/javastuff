/*
 * What this compiles to:
 * Enclosure$Enclosed.class	 and Enclosure.class
 * 
 * And I totally mistaken, as I confused a class field that is a static object, 
 * to a nested class within an object. In Java, I can create instances of nested classes 
 * all night long, although as I think we saw, you can't do that in CLR.
 * 
 * What I was really spacing about was the notion of a static field
 * that is a class. That critter requires that you assign an instance of the 
 * object to it.
 */

public class Enclosure {
	
	public static Enclosed theEnclosed;
	
	public static class Enclosed
	{
		static String name;
		static String value;
	}

	public static Enclosed createEnclosed()
	{
        	return new Enclosed();
    	}
	
	public static void instanceTest()
	{
		Enclosure.Enclosed one = new Enclosed();
		Enclosure.Enclosed two = new Enclosed();
		
		System.out.println( "Same memory location for nested class instances: " + (one == two) );
	}
	
	public static void staticFieldTest()
	{
		System.out.println( "static instance memory location prior to class construction: " + Enclosure.theEnclosed.toString() );
		Enclosure one = new Enclosure();
		Enclosure two = new Enclosure();
		System.out.println( "Same memory location for two enclosures: " + (one.theEnclosed == two.theEnclosed) );
	}
	
	/*
	 * Last, here's what is printed out when main is run:
	 * 
	 * Same memory location for nested class instances: false
	 * static instance exists prior to class construction: Enclosure$Enclosed@5e8fce95
	 * Same memory location for two enclosures: true
	 * 
	 */
	 
	public static void main(String [] args)
	{
		instanceTest();
		staticFieldTest();
	}
	
	// static initalizer for the class. This is called before the constructor. 
	static
	{
		theEnclosed = createEnclosed();
	}
