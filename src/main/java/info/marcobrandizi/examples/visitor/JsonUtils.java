package info.marcobrandizi.examples.visitor;

import java.util.List;

import info.marcobrandizi.examples.visitor.model.CastlePart;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class JsonUtils
{
	/**
	 * Chains {@link #renderJsonDict(String)} and {@link #renderJsonFields(List)}.
	 */
	public static String renderJsonDict ( List<Object> fields ) { 
		return renderJsonDict ( renderJsonFields ( fields ) ); 
	}

	/**
	 * Utility that wraps the string with {}
	 */
	public static String renderJsonDict ( String fieldsDefs ) { 
		return "{ " + fieldsDefs + " }"; 
	}

	/**
	 * The JSON dictionary assignments (without '{}') for these fields.
	 * @param fields a list of name, value, ... values are rendered as-is, ie, you must add quotes.
	 * 
	 */
	public static String renderJsonFields ( List<Object> fields )
	{
		String result = "";
		String sep = "";
		for ( int i = 0; i < fields.size () - 1; i++ )
		{
			String k = (String) fields.get ( i );
			Object v = fields.get ( ++i );
			result += sep + "\"" + k + "\": " + v;
			sep = ", ";
		}
		return result;
	}
	
	/**
	 * Renders the fields of a castle part, in the format accepted by {@link #renderJsonFields(List)}.
	 */
	public static List<Object> getFields ( CastlePart part )
	{
		return List.of (
			"name", "\"" + part.getName () + '"',
			"size-sq-m", part.getSize (),
			"artworks", part.getArtworks ()
		);
	}
}
