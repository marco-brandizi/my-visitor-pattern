package info.marcobrandizi.examples.visitor.composite.xml;

import info.marcobrandizi.examples.visitor.XmlUtils;
import info.marcobrandizi.examples.visitor.model.Room;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class XmlRoomVisitor<R extends Room> extends XmlCastlePartVisitor<R>
{
	@SuppressWarnings ( "unchecked" )
	public XmlRoomVisitor ()
	{
		this ( (Class<R>) Room.class );
	}
	
	public XmlRoomVisitor ( Class<R> roomClass )
	{
		super ( roomClass );
	}

	@Override
	public String visit ( R room )
	{
		return XmlUtils.renderXmlTag ( room.getClass ().getSimpleName (), XmlUtils.getFields ( room ) );
	}
}
