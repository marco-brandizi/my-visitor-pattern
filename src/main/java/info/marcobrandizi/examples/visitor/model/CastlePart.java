package info.marcobrandizi.examples.visitor.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import info.marcobrandizi.examples.visitor.classic.Visitable;

/**
 * Base class for the domain model
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public abstract class CastlePart implements Visitable
{
	private final String name;
	private final int size;
	private final int artworks;
	
	private Logger log = LogManager.getLogger ();
	
	protected CastlePart ( String name, int size, int artworks )
	{
		super ();
		this.name = name;
		this.size = size;
		this.artworks = artworks;
	}

	public String getName ()
	{
		return name;
	}
	
	public int getSize ()
	{
		return size;
	}

	public int getArtworks ()
	{
		return artworks;
	}
	
	@Override
	public String toString ()
	{
		return String.format ( 
			"CastlePart{ name: \"%s\", size: %s m^2, #artworks: %s }", 
			getName (), getSize (), getArtworks () 
		);
	}
	
	protected void logAccept ()
	{
		log.debug ( "Calling accept() for {}", this.getClass ().getSimpleName () );
	}
	
}
