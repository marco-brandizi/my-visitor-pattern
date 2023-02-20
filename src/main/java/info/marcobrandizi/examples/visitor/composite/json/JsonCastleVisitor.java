package info.marcobrandizi.examples.visitor.composite.json;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import info.marcobrandizi.examples.visitor.JsonUtils;
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
public class JsonCastleVisitor extends JsonCastlePartVisitor<Castle>
{
	private CompositeDispatcher<CastlePart, JsonCastlePartVisitor<CastlePart>> dispatcher;
	
	public JsonCastleVisitor ( CompositeDispatcher<CastlePart, JsonCastlePartVisitor<CastlePart>> dispatcher )
	{
		super ( Castle.class );
		this.dispatcher = dispatcher;
	}

	@Override
	public String visit ( Castle castle )
	{
		List<Object> fields = new ArrayList<> ( JsonUtils.getFields ( castle ) );
		
		String roomsJson = castle.getRooms ()
		.stream ()
		.map ( room -> "  " + dispatcher.dispatchVisit ( room ) )
		.collect ( Collectors.joining ( ",\n", "[\n", "\n]" ) );
		
		fields.add ( "rooms" );
		fields.add ( roomsJson );
		
		return JsonUtils.renderJsonDict ( fields );	}
}
