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
public class XMLVisitor extends Visitor
{
	@Override
	public String visitCastle ( Castle castle )
	{
		String result = "<Castle ";
		result += visitFields ( getFields ( castle ) );
		result += ">\n";
		
		String roomsXml = castle.getRooms ()
		.stream ()
		.map ( room -> "    " + room.accept ( this ) )
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
		return visitXmlTag ( room.getClass ().getSimpleName (), getFields ( room ) );
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
		List<Object> fields = new ArrayList<> ( getFields ( bedroom ) );
		if ( wrapName ) fields.set ( 1, ":" + bedroom.getName () + ":" );
		fields.add ( "bed-color" );
		fields.add ( bedroom.getBedColor () );
		return visitXmlTag ( bedroom.getClass ().getSimpleName (), fields ); 
	}

	private String visitXmlTag ( String xmlTag, List<Object> fields ) { 
		return visitXmlTag ( xmlTag, visitFields ( fields ) ); 
	}

	/**
	 * Renders a child-less XML tag, possibly with attributes definitions,
	 * eg, &lt;Foo a = "1" /&gt; 
	 */
	private String visitXmlTag ( String xmlTag, String attribDefs ) 
	{
		if ( !"".equals ( attribDefs ) ) attribDefs = " " + attribDefs; 
		return "<" + xmlTag + attribDefs + " />"; 
	}

	/**
	 * Renders the castle parts as list of XML attributes, to be wrapped on an XML tag.
	 */
	private String visitFields ( List<Object> fields )
	{
		String result = "";
		String sep = "";
		for ( int i = 0; i < fields.size () - 1; i++ )
		{
			String k = (String) fields.get ( i );
			Object v = fields.get ( ++i );
			result += String.format ( "%s%s = \"%s\"", sep, k, v );
			sep = " ";
		}
		return result;
	}
	
	/**
	 * Renders the fields of a castle part, in the format accepted by {@link #visitFields(List)}.
	 */
	private List<Object> getFields ( CastlePart part )
	{
		return List.of (
			"name", part.getName (),
			"size-sq-m", part.getSize (),
			"artworks", part.getArtworks ()
		);
	}	
}
