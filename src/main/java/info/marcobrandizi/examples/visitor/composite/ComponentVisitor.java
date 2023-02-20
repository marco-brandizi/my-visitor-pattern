package info.marcobrandizi.examples.visitor.composite;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public abstract class ComponentVisitor<D>
{
	protected final Class<D> domainClass;
	
	protected ComponentVisitor ( Class<D> domainClass )
	{
		super ();
		this.domainClass = domainClass;
	}
	
	public abstract String visit ( D visitable );
	
	public Class<D> getDomainClass () {
		return domainClass;
	}
}
