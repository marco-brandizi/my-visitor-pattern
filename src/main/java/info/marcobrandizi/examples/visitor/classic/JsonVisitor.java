package info.marcobrandizi.examples.visitor.classic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import info.marcobrandizi.examples.visitor.JsonUtils;
import info.marcobrandizi.examples.visitor.model.Bedroom;
import info.marcobrandizi.examples.visitor.model.Castle;
import info.marcobrandizi.examples.visitor.model.KingBedroom;
import info.marcobrandizi.examples.visitor.model.Room;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public class JsonVisitor extends Visitor
{
	@Override
	public String visitCastle ( Castle castle, Function<Room, String> roomVisitor )
	{
		List<Object> fields = new ArrayList<> ( JsonUtils.getFields ( castle ) );
		
		String roomsJson = castle.getRooms ()
		.stream ()
		.map ( room -> "  " + roomVisitor.apply ( room ) )
		.collect ( Collectors.joining ( ",\n", "[\n", "\n]" ) );
		
		fields.add ( "rooms" );
		fields.add ( roomsJson );
		
		return JsonUtils.renderJsonDict ( fields );
	}

	@Override
	public String visitRoom ( Room room )
	{
		return JsonUtils.renderJsonDict ( JsonUtils.getFields ( room ) );
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
		List<Object> fields = new ArrayList<> ( JsonUtils.getFields ( bedroom ) );
		if ( wrapName ) fields.set ( 1, "\":" + bedroom.getName () + ":\"" );
		fields.add ( "bed-color" );
		fields.add ( "\"" + bedroom.getBedColor () + '"' );
		return JsonUtils.renderJsonDict ( fields ); 
	}

}
