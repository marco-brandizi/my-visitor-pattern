package info.marcobrandizi.examples.visitor.model;

import info.marcobrandizi.examples.visitor.classic.Visitor;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public class KingBedroom extends Bedroom
{
	public KingBedroom ()
	{
		super ( "The Royal King Bedroom", 100, 30, "red" );
	}

	@Override
	public String accept ( Visitor visitor )
	{
		logAccept ();
		return visitor.visitKingBedroom ( this );
	}
}
