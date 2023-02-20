package info.marcobrandizi.examples.visitor.composite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import info.marcobrandizi.examples.visitor.dispatcher.VisitorDispatcher;

/**
 * The composite dispatcher.
 * 
 * This uses the {@link VisitorDispatcher dispatcher approach} but exploits a composition
 * of {@link ComponentVisitor component visitors}, which can be set up, eg, via configuration,
 * <a href = "https://www.baeldung.com/java-spi">SPI</a> or Spring IoC.
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class CompositeDispatcher<C,V extends ComponentVisitor<C>>
{
	private Set<V> visitors = new HashSet<> ();
	private Map<Class<C>, V> dispatchersCache = new HashMap<> ();
	
	private Logger log = LogManager.getLogger ();
	
	public CompositeDispatcher ( @SuppressWarnings ( "unchecked" ) V... visitors )
	{
		super ();
		if ( visitors != null ) this.registerVisitors ( visitors );
	}

	/**
	 * Dispatch a visit based on {@link #getVisitor(Class)}.
	 */
	public String dispatchVisit ( C domainObject ) 
	{
		V visitor = getVisitor ( domainObject );
		log.debug (
			"Visiting instance of {} with the visitor: {}",
			domainObject.getClass ().getSimpleName (),
			visitor.getClass ().getSimpleName ()
		);
		return visitor.visit ( domainObject );
	}
	
	/**
	 * Wrapper of {@link #getVisitor(Class)} that simply pass the visitable's class.
	 */
	@SuppressWarnings ( "unchecked" )
	private V getVisitor ( C visitable )
	{
		return getVisitor ( (Class<C>) visitable.getClass () );
	}
	
	/**
	 * This is the core of the visitor selector based on {@link ComponentVisitor#getComponentClass()}.
	 * 
	 * For a given class clazz, it selects the registered visitor having {@link ComponentVisitor#getComponentClass()}
	 * that manages clazz. If a visitor for the class isn't registered, selects a visitor that manages the most
	 * specific super-class of clazz. If no such visitor exists, throws an {@link IllegalArgumentException}.
	 * 
	 * <b>WARNING</b>: for simplicity, this implementation ignores the interfaces that clazz implements or
	 * extends. A more complete version of this methods should walk all the upward hierarchy and have a criterion
	 * to decide which interface or class has priority in case of multiple inheritance.
	 */
	private V getVisitor ( Class<C> clazz ) throws IllegalArgumentException
	{
		if ( clazz == null ) throw new IllegalArgumentException (
			"Can't get a visitor for a null domain class"
		);
		V result = null;
		Class<? super C> resultClass = clazz;
		
		while ( resultClass != null )
		{
			result = dispatchersCache.get ( resultClass );
			if ( result != null ) break;
			
			resultClass = resultClass.getSuperclass ();
		}
		
		if ( result == null ) throw new IllegalArgumentException (
			"No dispatcher found for the class " + clazz.getName () 
		);
		
		if ( !clazz.equals ( resultClass ) ) dispatchersCache.put ( clazz, result );
		return result;
	}
	
	/**
	 * Registers visitors that this dispatcher manages.
	 * 
	 * Uses {@link ComponentVisitor#getComponentClass()} to register a mapping between component
	 * classes and the visitors that have to manage them. 
	 * 
	 * @see #getVisitor(Class)
	 * 
	 */
	@SuppressWarnings ( "unchecked" )
	public void registerVisitors ( ComponentVisitor<?>... visitors )
	{
		this.visitors.addAll ( (List<V>) List.of ( visitors ) );
		initCache ();
	}
	
	private void initCache ()
	{
		dispatchersCache = new HashMap<> ();
		visitors.forEach ( v -> dispatchersCache.put ( v.getComponentClass (), v ) );
	}
}
