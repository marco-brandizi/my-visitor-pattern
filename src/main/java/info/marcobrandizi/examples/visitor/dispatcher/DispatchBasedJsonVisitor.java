package info.marcobrandizi.examples.visitor.dispatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import info.marcobrandizi.examples.visitor.classic.JSONVisitor;
import info.marcobrandizi.examples.visitor.classic.Visitor;
import info.marcobrandizi.examples.visitor.model.Castle;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class DispatchBasedJsonVisitor extends JSONVisitor
{
	private VisitorDispatcher dispatcher;
	
	public DispatchBasedJsonVisitor ( VisitorDispatcher dispatcher )
	{
		super ();
		this.dispatcher = dispatcher;
	}

	
	@Override
	public String visitCastle ( Castle castle )
	{
		List<Object> fields = new ArrayList<> ( getFields ( castle ) );
		
		String roomsJson = castle.getRooms ()
		.stream ()
		.map ( room -> "  " +  dispatcher.dispatchVisit ( room, this ) )
		.collect ( Collectors.joining ( ",\n", "[\n", "\n]" ) );
		
		fields.add ( "rooms" );
		fields.add ( roomsJson );
		
		return visitBlock ( fields );
	}
}
