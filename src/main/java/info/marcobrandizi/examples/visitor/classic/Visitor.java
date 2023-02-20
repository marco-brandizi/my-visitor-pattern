package info.marcobrandizi.examples.visitor.classic;

import java.util.function.Function;

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
public abstract class Visitor
{
	/**
	 * Contrary to the many examples around, we work out the visit of the whole castle (its own
	 * data + rooms) here, not in Castle.accept().
	 * 
	 * This is due to the fact that the visitor knows how to combine (eg, nest) the different parts.
	 * 
	 * We still detach the {@link Visitor} from the {@link Visitable} using the lambda
	 * trick (ie, the visitor's acceptance is still managed by the caller, by passing the
	 * roomVisitor parameter).
	 * 
	 */
	public abstract String visitCastle ( Castle castle, Function<Room, String> roomVisitor );
	
	public abstract String visitRoom ( Room room );
	
	/**
	 * Wrt a regular room, {@link Bedroom} has {@link Bedroom#getBedColor()}. 
	 */
	public abstract String visitBedroom ( Bedroom bedroom );
	
	/**
	 * Assume the King's bedroom name is wrapped by ':' or alike
	 */
	public abstract String visitKingBedroom ( KingBedroom kingBedroom );
}
