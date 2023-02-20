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
public class XmlUtils
{
	public static String renderXmlTag ( String xmlTag, List<Object> fields ) { 
		return renderXmlTag ( xmlTag, renderXmlAttributes ( fields ) ); 
	}

	/**
	 * Renders a child-less XML tag, possibly with attributes definitions,
	 * eg, &lt;Foo a = "1" /&gt; 
	 */
	public static String renderXmlTag ( String xmlTag, String attribDefs ) 
	{
		if ( !"".equals ( attribDefs ) ) attribDefs = " " + attribDefs; 
		return "<" + xmlTag + attribDefs + " />"; 
	}

	/**
	 * Renders the castle parts as list of XML attributes, to be wrapped on an XML tag.
	 */
	public static String renderXmlAttributes ( List<Object> fields )
	{
		String result = "";
		String sep = "";
		for ( int i = 0; i < fields.size () - 1; i++ )
		{
			String k = (String) fields.get ( i );
			Object v = fields.get ( ++i );
			result += String.format ( "%s%s = \"%s\"", sep, k, v );
			sep = " ";
		}
		return result;
	}
	
	/**
	 * Renders the fields of a castle part, in the format accepted by {@link #renderXmlAttributes(List)}.
	 */
	public static List<Object> getFields ( CastlePart part )
	{
		return List.of (
			"name", part.getName (),
			"size-sq-m", part.getSize (),
			"artworks", part.getArtworks ()
		);
	}	
}
