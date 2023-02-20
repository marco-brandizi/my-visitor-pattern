package info.marcobrandizi.examples.visitor.composite.xml;

import java.util.ArrayList;
import java.util.List;

import info.marcobrandizi.examples.visitor.XmlUtils;
import info.marcobrandizi.examples.visitor.model.Bedroom;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class XmlBedroomVisitor<BR extends Bedroom> extends XmlRoomVisitor<BR>
{
	@SuppressWarnings ( "unchecked" )
	public XmlBedroomVisitor ()
	{
		super ( (Class<BR>) Bedroom.class );
	}
	
	public XmlBedroomVisitor ( Class<BR> domainClass )
	{
		super ( domainClass );
	}



	protected String visitBedroom ( boolean wrapName, Bedroom bedroom )
	{
		List<Object> fields = new ArrayList<> ( XmlUtils.getFields ( bedroom ) );
		if ( wrapName ) fields.set ( 1, ":" + bedroom.getName () + ":" );
		fields.add ( "bed-color" );
		fields.add ( bedroom.getBedColor () );
		return XmlUtils.renderXmlTag ( bedroom.getClass ().getSimpleName (), fields ); 
	}

	@Override
	public String visit ( BR bedroom )
	{
		return visitBedroom ( false, bedroom );
	}
	
}
