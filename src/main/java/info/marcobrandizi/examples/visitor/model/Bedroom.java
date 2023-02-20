package info.marcobrandizi.examples.visitor.model;

import info.marcobrandizi.examples.visitor.classic.Visitor;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public class Bedroom extends Room
{
	private final String bedColor; 
	
	public Bedroom ( String name, int size, int artworks, String bedColor )
	{
		super ( name, size, artworks );
		this.bedColor = bedColor;
	}

	public String getBedColor ()
	{
		return bedColor;
	}
	
	@Override
	public String toString ()
	{
		return String.format ( 
			"CastlePart{ name: \"%s\", size: %s m^2, #artworks: %s, bedColor: %s }", 
			getName (), getSize (), getArtworks (), getBedColor () 
		);
	}

	@Override
	public String accept ( Visitor visitor )
	{
		logAccept ();
		return visitor.visitBedroom ( this );
	}
	
}
