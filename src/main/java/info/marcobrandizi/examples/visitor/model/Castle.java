package info.marcobrandizi.examples.visitor.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import info.marcobrandizi.examples.visitor.classic.Visitor;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public class Castle extends CastlePart
{
	private final List<Room> rooms;
	
	public Castle ( String name, Room... rooms )
	{
		super ( 
			name, 
			Stream.of ( rooms ).collect ( Collectors.summingInt ( Room::getSize ) ), 
			Stream.of ( rooms ).collect ( Collectors.summingInt ( Room::getArtworks ) ) 
		);
		this.rooms = Collections.unmodifiableList ( Arrays.asList ( rooms ) ); 
	}
	
	public List<Room> getRooms ()
	{
		return rooms;
	}

	@Override
	public String toString ()
	{
		return String.format ( 
			"CastlePart{ name: \"%s\", size: %d m^2, #artworks: %d, #rooms: %d }", 
			getName (), getSize (), getArtworks (), getRooms ().size ()
		);
	}

	@Override
	public String accept ( Visitor visitor )
	{
		logAccept ();
		return visitor.visitCastle ( this, room -> room.accept ( visitor ) );
	}
}
