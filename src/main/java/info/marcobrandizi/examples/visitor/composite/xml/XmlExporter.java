package info.marcobrandizi.examples.visitor.composite.xml;

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
public class XmlExporter
{
	private static CompositeDispatcher<CastlePart, XmlCastlePartVisitor<CastlePart>> DISPATCHER;
	
	/**
	 * We don't code this directly in static{}, just to be able to use SuppressWarnings.
	 */
	@SuppressWarnings ( "unchecked" )
	private static void init()
	{
		DISPATCHER = new CompositeDispatcher<> ();
		DISPATCHER.registerVisitors ( 
			new XmlCastleVisitor ( DISPATCHER ),
			new XmlRoomVisitor<> (),
			new XmlBedroomVisitor<> (),
			new XmlKingBedroomVisitor ()
		);
	}
	
	static {
		init ();
	}
	
	public static String xmlExport ( Castle castle ) {
		return DISPATCHER.dispatchVisit ( castle );
	}
}
