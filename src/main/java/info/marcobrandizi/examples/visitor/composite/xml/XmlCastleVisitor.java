package info.marcobrandizi.examples.visitor.composite.xml;

import java.util.stream.Collectors;

import info.marcobrandizi.examples.visitor.XmlUtils;
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
public class XmlCastleVisitor extends XmlCastlePartVisitor<Castle>
{
	private CompositeDispatcher<CastlePart, XmlCastlePartVisitor<CastlePart>> dispatcher;
	
	public XmlCastleVisitor ( CompositeDispatcher<CastlePart, XmlCastlePartVisitor<CastlePart>> dispatcher )
	{
		super ( Castle.class );
		this.dispatcher = dispatcher;
	}

	@Override
	public String visit ( Castle castle )
	{
		String result = "<Castle ";
		result += XmlUtils.renderXmlAttributes ( XmlUtils.getFields ( castle ) );
		result += ">\n";
		
		String roomsXml = castle.getRooms ()
		.stream ()
		.map ( room -> "    " + dispatcher.dispatchVisit ( room ) )
		.collect ( Collectors.joining ( "\n" ) );
		
		result += "  <Rooms>\n"
			+ roomsXml + "\n"
			+ "  </Rooms>\n";
		
		result += "</Castle>\n";
		
		return result;	
	}
}
