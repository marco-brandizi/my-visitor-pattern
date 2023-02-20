package info.marcobrandizi.examples.visitor.model;

import info.marcobrandizi.examples.visitor.classic.Visitor;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public abstract class Room extends CastlePart
{
	protected Room ( String name, int size, int artworks )
	{
		super ( name, size, artworks );
	}

	@Override
	public String accept ( Visitor visitor )
	{
		logAccept ();
		return visitor.visitRoom ( this );
	}

}
