package info.marcobrandizi.examples.visitor.classic;

/**
 * The visitable (ie, component that can accept a visitor), modelled after the classical
 * visitor pattern.
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>19 Feb 2023</dd></dl>
 *
 */
public interface Visitable
{
	public String accept ( Visitor visitor );
}
