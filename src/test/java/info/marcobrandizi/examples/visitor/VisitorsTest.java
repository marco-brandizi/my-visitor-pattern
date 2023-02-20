package info.marcobrandizi.examples.visitor;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import info.marcobrandizi.examples.visitor.classic.JsonVisitor;
import info.marcobrandizi.examples.visitor.classic.XmlVisitor;
import info.marcobrandizi.examples.visitor.composite.json.JsonExporter;
import info.marcobrandizi.examples.visitor.composite.xml.XmlExporter;
import info.marcobrandizi.examples.visitor.dispatcher.VisitorDispatcher;
import info.marcobrandizi.examples.visitor.model.Ballroom;
import info.marcobrandizi.examples.visitor.model.Bedroom;
import info.marcobrandizi.examples.visitor.model.Castle;
import info.marcobrandizi.examples.visitor.model.Hall;
import info.marcobrandizi.examples.visitor.model.KingBedroom;
import info.marcobrandizi.examples.visitor.model.QueenBedroom;

/**
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public class VisitorsTest
{
	private Logger log = LogManager.getLogger ();
	
	private Castle castle = new Castle ( "The Fairytale Castle",  
		new Hall (),
		new Ballroom (),
		new KingBedroom (),
		new QueenBedroom (),
		new Bedroom ( "Guest Bedroom", 50, 10, "orange" )
	);
		
	@Test
	public void testJson ()
	{
		String json = castle.accept ( new JsonVisitor () );
		verifyJson ( json, "JSON Visitor, Classical Approach:\n{}" );
	}
	
	@Test
	public void testXml ()
	{
		String xml = castle.accept ( new XmlVisitor () );
		verifyXml ( xml, "XML Visitor, Classical Approach:\n{}" );
	}

	@Test
	public void testDispatcherJson ()
	{
		VisitorDispatcher dispatcher = new VisitorDispatcher ();
		String json = dispatcher.dispatchVisit ( castle, new JsonVisitor () );
		verifyJson ( json, "JSON Visitor, Dispatcher Approach:\n{}" );
	}

	@Test
	public void testDispatcherXml ()
	{
		VisitorDispatcher dispatcher = new VisitorDispatcher ();
		String xml = dispatcher.dispatchVisit ( castle, new XmlVisitor () );
		verifyXml ( xml, "XML Visitor, Dispatcher Approach:\n{}" );
	}
	

	@Test
	public void testCompositeDispatcherJson ()
	{
		String json = JsonExporter.jsonExport ( castle );
		verifyJson ( json, "JSON Visitor, Composite Dispatcher Approach:\n{}" );
	}

	@Test
	public void testCompositeDispatcherXml ()
	{
		String xml = XmlExporter.xmlExport ( castle );
		verifyXml ( xml, "XML Visitor, Composite Dispatcher Approach:\n{}" );
	}
	
	
	private void verifyJson ( String json, String logMsg )
	{
		log.debug ( logMsg, json );
		
		assertTrue ( json.startsWith ( "{ " ), "Bad document begin!" );
		assertTrue ( json.endsWith ( "] }" ), "Bad document end!" );
		
		assertTrue ( json.startsWith ( 
			"{ \"name\": \"The Fairytale Castle\", \"size-sq-m\": 850, \"artworks\": 275, \"rooms\": [" ),
			"Bad castle data!"
		);
		assertTrue ( json.contains ( 
			"{ \"name\": \":The Royal King Bedroom:\", \"size-sq-m\": 100, \"artworks\": 30, \"bed-color\": \"red\" },\n" ),
			"Bad King bedroom!"
		);
		assertTrue ( json.contains ( "The Royal Queen Bedroom" ), "No queen bedroom!" );
		assertTrue ( json.contains ( "Guest Bedroom" ), "No guest bedroom!" );
		assertTrue ( json.contains ( "Ballroom" ), "No ballroom!" );
		assertTrue ( json.contains ( "Luxury Hall" ), "No Hall!" );
	}		
	
	private void verifyXml ( String xml, String logMsg )
	{
		log.debug ( logMsg, xml );
		
		assertTrue ( xml.startsWith ( "<Castle " ), "Bad document begin!" );
		assertTrue ( xml.endsWith ( "\n  </Rooms>\n</Castle>\n" ), "Bad document end!" );
		
		assertTrue ( xml.startsWith ( 
			"<Castle name = \"The Fairytale Castle\" size-sq-m = \"850\" artworks = \"275\">\n  <Rooms>\n    <Hall" ),
			"Bad castle node!"
		);
		assertTrue ( xml.contains ( 
			"    <Hall name = \"The Luxury Hall\" size-sq-m = \"200\" artworks = \"50\" />\n" ),
			"Bad Hall!"
		);
		assertTrue ( xml.contains ( "<KingBedroom name = \":The Royal King Bedroom:\"" ), "No King bedroom!" );
		assertTrue ( xml.contains ( "<QueenBedroom name = \"The Royal Queen Bedroom\"" ), "No queen bedroom!" );
		assertTrue ( xml.contains ( "<Bedroom name = \"Guest Bedroom\"" ), "No guest bedroom!" );
		assertTrue ( xml.contains ( "Ballroom" ), "No ballroom!" );
		assertTrue ( xml.contains ( "Luxury Hall" ), "No Hall!" );
	}
}
