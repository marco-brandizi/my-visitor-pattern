package info.marcobrandizi.examples.visitor.composite.json;

import info.marcobrandizi.examples.visitor.composite.ComponentVisitor;
import info.marcobrandizi.examples.visitor.model.CastlePart;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public abstract class JsonCastlePartVisitor<CP extends CastlePart> extends ComponentVisitor<CP>
{
	protected JsonCastlePartVisitor ( Class<CP> domainClass )
	{
		super ( domainClass );
	}
}
