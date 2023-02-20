package info.marcobrandizi.examples.visitor.composite;

/**
 * A component visitor. Capture the way in which a visit should happen.
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public abstract class ComponentVisitor<C>
{
	protected final Class<C> componentClass;
	
	protected ComponentVisitor ( Class<C> domainClass )
	{
		super ();
		this.componentClass = domainClass;
	}
	
	public abstract String visit ( C visitable );
	
	/**
	 * The class or subclass that this visitor is able to visit.
	 * 
	 * This allows {@link CompositeDispatcher} to select a visitor based on the object it needs
	 * to be visited. 
	 */
	public Class<C> getComponentClass () {
		return componentClass;
	}
}
