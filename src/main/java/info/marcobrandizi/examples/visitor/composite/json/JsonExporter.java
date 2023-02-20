package info.marcobrandizi.examples.visitor.composite.json;

import info.marcobrandizi.examples.visitor.composite.CompositeDispatcher;
import info.marcobrandizi.examples.visitor.model.Castle;
import info.marcobrandizi.examples.visitor.model.CastlePart;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class JsonExporter
{
	private static CompositeDispatcher<CastlePart, JsonCastlePartVisitor<CastlePart>> DISPATCHER;
	
	/**
	 * We don't code this directly in static{}, just to be able to use SuppressWarnings.
	 */
	@SuppressWarnings ( "unchecked" )
	private static void init()
	{
		DISPATCHER = new CompositeDispatcher<> ();
		DISPATCHER.registerVisitors ( 
			new JsonCastleVisitor ( DISPATCHER ),
			new JsonRoomVisitor<> (),
			new JsonBedroomVisitor<> (),
			new JsonKingBedroomVisitor ()
		);
	}
	
	static {
		init ();
	}
	
	public static String jsonExport ( Castle castle ) {
		return DISPATCHER.dispatchVisit ( castle );
	}
}
