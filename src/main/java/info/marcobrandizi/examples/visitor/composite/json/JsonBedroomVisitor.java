package info.marcobrandizi.examples.visitor.composite.json;

import java.util.ArrayList;
import java.util.List;

import info.marcobrandizi.examples.visitor.JsonUtils;
import info.marcobrandizi.examples.visitor.model.Bedroom;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class JsonBedroomVisitor<BR extends Bedroom> extends JsonRoomVisitor<BR>
{
	@SuppressWarnings ( "unchecked" )
	public JsonBedroomVisitor ()
	{
		super ( (Class<BR>) Bedroom.class );
	}
	
	public JsonBedroomVisitor ( Class<BR> domainClass )
	{
		super ( domainClass );
	}



	protected String visitBedroom ( boolean wrapName, Bedroom bedroom )
	{
		List<Object> fields = new ArrayList<> ( JsonUtils.getFields ( bedroom ) );
		if ( wrapName ) fields.set ( 1, "\":" + bedroom.getName () + ":\"" );
		fields.add ( "bed-color" );
		fields.add ( "\"" + bedroom.getBedColor () + '"' );
		return JsonUtils.renderJsonDict ( fields ); 
	}

	@Override
	public String visit ( BR bedroom )
	{
		return visitBedroom ( false, bedroom );
	}
	
}
