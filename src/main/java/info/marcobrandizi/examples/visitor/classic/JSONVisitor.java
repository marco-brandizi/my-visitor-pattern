package info.marcobrandizi.examples.visitor.classic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import info.marcobrandizi.examples.visitor.model.Bedroom;
import info.marcobrandizi.examples.visitor.model.Castle;
import info.marcobrandizi.examples.visitor.model.CastlePart;
import info.marcobrandizi.examples.visitor.model.KingBedroom;
import info.marcobrandizi.examples.visitor.model.Room;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public class JSONVisitor extends Visitor
{
	@Override
	public String visitCastle ( Castle castle )
	{
		List<Object> fields = new ArrayList<> ( getFields ( castle ) );
		
		String roomsJson = castle.getRooms ()
		.stream ()
		.map ( room -> "  " + room.accept ( this ) )
		.collect ( Collectors.joining ( ",\n", "[\n", "\n]" ) );
		
		fields.add ( "rooms" );
		fields.add ( roomsJson );
		
		return visitBlock ( fields );
	}

	@Override
	public String visitRoom ( Room room )
	{
		return visitBlock ( getFields ( room ) );
	}

	/**
	 * Uses {@link #visitBedroom(boolean, Bedroom)} with false
	 */
	@Override
	public String visitBedroom ( Bedroom bedroom )
	{
		return visitBedroom ( false, bedroom );
	}

	/**
	 * Uses {@link #visitBedroom(boolean, Bedroom)} with true
	 */
	@Override
	public String visitKingBedroom ( KingBedroom kingBedroom )
	{
		return visitBedroom ( true, kingBedroom );
	}
	
	/**
	 * Renders the JSON object for a bedroom.
	 * 
	 * @param wrapName if true, renders the name with the ':' wrapper
	 */
	private String visitBedroom ( boolean wrapName, Bedroom bedroom )
	{
		List<Object> fields = new ArrayList<> ( getFields ( bedroom ) );
		if ( wrapName ) fields.set ( 1, "\":" + bedroom.getName () + ":\"" );
		fields.add ( "bed-color" );
		fields.add ( "\"" + bedroom.getBedColor () + '"' );
		return visitBlock ( fields ); 
	}

	/**
	 * Chains {@link #visitBlock(String)} and {@link #visitFields(List)}.
	 */
	protected String visitBlock ( List<Object> fields ) { 
		return visitBlock ( visitFields ( fields ) ); 
	}

	/**
	 * Utility that wraps the string with {}
	 */
	private String visitBlock ( String blockBody ) { 
		return "{ " + blockBody + " }"; 
	}

	/**
	 * The JSON dictionary assignments (without '{}') for these fields.
	 * @param fields a list of name, value, ... values are rendered as-is, ie, you must add quotes.
	 * 
	 */
	private String visitFields ( List<Object> fields )
	{
		String result = "";
		String sep = "";
		for ( int i = 0; i < fields.size () - 1; i++ )
		{
			String k = (String) fields.get ( i );
			Object v = fields.get ( ++i );
			result += sep + "\"" + k + "\": " + v;
			sep = ", ";
		}
		return result;
	}
	
	/**
	 * Renders the fields of a castle part, in the format accepted by {@link #visitFields(List)}.
	 */
	protected List<Object> getFields ( CastlePart part )
	{
		return List.of (
			"name", "\"" + part.getName () + '"',
			"size-sq-m", part.getSize (),
			"artworks", part.getArtworks ()
		);
	}
}
