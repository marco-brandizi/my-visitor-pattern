package info.marcobrandizi.examples.visitor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import info.marcobrandizi.examples.visitor.classic.JSONVisitor;
import info.marcobrandizi.examples.visitor.classic.XMLVisitor;
import info.marcobrandizi.examples.visitor.dispatcher.DispatchBasedJsonVisitor;
import info.marcobrandizi.examples.visitor.dispatcher.VisitorDispatcher;
import info.marcobrandizi.examples.visitor.model.Ballroom;
import info.marcobrandizi.examples.visitor.model.Bedroom;
import info.marcobrandizi.examples.visitor.model.Castle;
import info.marcobrandizi.examples.visitor.model.Hall;
import info.marcobrandizi.examples.visitor.model.KingBedroom;
import info.marcobrandizi.examples.visitor.model.QueenBedroom;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public class ClassicVisitorTest
{
	private Logger log = LogManager.getLogger ();
	
	private Castle castle = new Castle ( "The Visitable Castle",  
		new Hall (),
		new Ballroom (),
		new KingBedroom (),
		new QueenBedroom (),
		new Bedroom ( "guest bedroom", 50, 10, "orange" )
	);
	
	@Test
	public void testJSON ()
	{
		String json = castle.accept ( new JSONVisitor () );
		log.info ( "JSON Visitor:\n{}", json );
	}
	
	@Test
	public void testXML ()
	{
		String xml = castle.accept ( new XMLVisitor () );
		log.info ( "XML Visitor:\n{}", xml );
	}

	@Test
	public void testDispatcher ()
	{
		VisitorDispatcher dispatcher = new VisitorDispatcher ();
		String json = dispatcher.dispatchVisit ( castle, new DispatchBasedJsonVisitor ( dispatcher ) );
		log.info ( "JSON VisitorDispatcher:\n{}", json );
	}
	
}
