package info.marcobrandizi.examples.visitor.dispatcher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import info.marcobrandizi.examples.visitor.classic.Visitor;
import info.marcobrandizi.examples.visitor.model.Bedroom;
import info.marcobrandizi.examples.visitor.model.Castle;
import info.marcobrandizi.examples.visitor.model.CastlePart;
import info.marcobrandizi.examples.visitor.model.KingBedroom;
import info.marcobrandizi.examples.visitor.model.Room;

/**
 * The dispatcher approach: the visit dispatch is no longer in the domain model, no accept()
 * pollution of it, but it's decided here, based on the component to be visited.
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class VisitorDispatcher
{
	private Logger log = LogManager.getLogger ();
	
	public String dispatchVisit ( CastlePart part, Visitor visitor )
	{
		log.debug ( "Calling dispatchVisit() for {}", part.getClass ().getSimpleName () );
		
		if ( part instanceof Castle ) 
			return visitor.visitCastle ( (Castle) part, room -> this.dispatchVisit ( room, visitor ) );
		// You need to go from the most specific to the most generic 
		if ( part instanceof KingBedroom ) return visitor.visitKingBedroom ( (KingBedroom) part );
		if ( part instanceof Bedroom ) return visitor.visitBedroom ( (Bedroom) part );
		if ( part instanceof Room ) return visitor.visitRoom ( (Room) part );
		
		throw new UnsupportedOperationException ( "Can't visit a " + part.getClass ().getSimpleName () );
	}
}
