package info.marcobrandizi.examples.visitor.classic;

import info.marcobrandizi.examples.visitor.model.CastlePart;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public interface Visitable
{
	public String accept ( Visitor visitor );
}
