package info.marcobrandizi.examples.visitor.classic;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import info.marcobrandizi.examples.visitor.XmlUtils;
import info.marcobrandizi.examples.visitor.model.Bedroom;
import info.marcobrandizi.examples.visitor.model.Castle;
import info.marcobrandizi.examples.visitor.model.KingBedroom;
import info.marcobrandizi.examples.visitor.model.Room;

/**
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public class XmlVisitor extends Visitor
{
	@Override
	public String visitCastle ( Castle castle, Function<Room, String> roomVisitor )
	{
		String result = "<Castle ";
		result += XmlUtils.renderXmlAttributes ( XmlUtils.getFields ( castle ) );
		result += ">\n";
		
		String roomsXml = castle.getRooms ()
		.stream ()
		.map ( room -> "    " + roomVisitor.apply ( room ) )
		.collect ( Collectors.joining ( "\n" ) );
		
		result += "  <Rooms>\n"
			+ roomsXml + "\n"
			+ "  </Rooms>\n";
		
		result += "</Castle>\n";
		
		return result;
	}

	@Override
	public String visitRoom ( Room room )
	{
		return XmlUtils.renderXmlTag ( room.getClass ().getSimpleName (), XmlUtils.getFields ( room ) );
	}

	@Override
	public String visitBedroom ( Bedroom bedroom )
	{
		return visitBedroom ( false, bedroom );
	}

	@Override
	public String visitKingBedroom ( KingBedroom kingBedroom )
	{
		return visitBedroom ( true, kingBedroom );
	}
	
	private String visitBedroom ( boolean wrapName, Bedroom bedroom )
	{
		List<Object> fields = new ArrayList<> ( XmlUtils.getFields ( bedroom ) );
		if ( wrapName ) fields.set ( 1, ":" + bedroom.getName () + ":" );
		fields.add ( "bed-color" );
		fields.add ( bedroom.getBedColor () );
		return XmlUtils.renderXmlTag ( bedroom.getClass ().getSimpleName (), fields ); 
	}

}
