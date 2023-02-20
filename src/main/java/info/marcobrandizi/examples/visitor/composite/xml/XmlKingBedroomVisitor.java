package info.marcobrandizi.examples.visitor.composite.xml;

import info.marcobrandizi.examples.visitor.model.KingBedroom;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class XmlKingBedroomVisitor extends XmlBedroomVisitor<KingBedroom>
{
	public XmlKingBedroomVisitor ()
	{
		super ( KingBedroom.class );
	}
	
	@Override
	public String visit ( KingBedroom bedroom )
	{
		return visitBedroom ( true, bedroom );
	}
		
}
