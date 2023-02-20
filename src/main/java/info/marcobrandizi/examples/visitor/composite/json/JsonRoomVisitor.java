package info.marcobrandizi.examples.visitor.composite.json;

import info.marcobrandizi.examples.visitor.JsonUtils;
import info.marcobrandizi.examples.visitor.model.Room;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class JsonRoomVisitor<R extends Room> extends JsonCastlePartVisitor<R>
{
	@SuppressWarnings ( "unchecked" )
	public JsonRoomVisitor ()
	{
		this ( (Class<R>) Room.class );
	}
	
	public JsonRoomVisitor ( Class<R> domainClass )
	{
		super ( domainClass );
	}

	@Override
	public String visit ( R room )
	{
		return JsonUtils.renderJsonDict ( JsonUtils.getFields ( room ) );
	}
}
