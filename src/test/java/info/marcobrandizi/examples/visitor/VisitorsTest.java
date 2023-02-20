package info.marcobrandizi.examples.visitor;

import java.util.stream.Stream;

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
 * TODO: proper JUnit assertions.
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
		new Bedroom ( "guest bedroom", 50, 10, "orange" )
	);
	
	@Test
	public void testJson ()
	{
		String json = castle.accept ( new JsonVisitor () );
		log.info ( "JSON Visitor, Classical Approach:\n{}", json );
	}
	
	@Test
	public void testXml ()
	{
		String xml = castle.accept ( new XmlVisitor () );
		log.info ( "XML Visitor, Classical Approach:\n{}", xml );
	}

	@Test
	public void testDispatcherJson ()
	{
		VisitorDispatcher dispatcher = new VisitorDispatcher ();
		String json = dispatcher.dispatchVisit ( castle, new JsonVisitor () );
		log.info ( "JSON Visitor, Dispatcher Approach:\n{}", json );
	}

	@Test
	public void testDispatcherXml ()
	{
		VisitorDispatcher dispatcher = new VisitorDispatcher ();
		String json = dispatcher.dispatchVisit ( castle, new XmlVisitor () );
		log.info ( "XML Visitor, Dispatcher Approach:\n{}", json );
	}
	

	@Test
	public void testCompositeDispatcherJson ()
	{
		String json = JsonExporter.jsonExport ( castle );
		log.info ( "JSON Visitor, Composite Dispatcher Approach:\n{}", json );
	}

	@Test
	public void testCompositeDispatcherXml ()
	{
		String xml = XmlExporter.xmlExport ( castle );
		log.info ( "XML Visitor, Composite Dispatcher Approach:\n{}", xml );
	}
}
