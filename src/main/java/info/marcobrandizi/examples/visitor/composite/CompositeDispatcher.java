package info.marcobrandizi.examples.visitor.composite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class CompositeDispatcher<D,V extends ComponentVisitor<D>>
{
	private Set<V> visitors = new HashSet<> ();
	private Map<Class<D>, V> dispatchersCache = new HashMap<> ();
	
	private Logger log = LogManager.getLogger ();
	
	
	public CompositeDispatcher ( @SuppressWarnings ( "unchecked" ) V... visitors )
	{
		super ();
		if ( visitors != null ) this.registerVisitors ( visitors );
	}

	public String dispatchVisit ( D domainObject ) 
	{
		V visitor = getVisitor ( domainObject );
		log.info (
			"Visiting instance of {} with the visitor: {}",
			domainObject.getClass ().getSimpleName (),
			visitor.getClass ().getSimpleName ()
		);
		return visitor.visit ( domainObject );
	}
	

	@SuppressWarnings ( "unchecked" )
	private V getVisitor ( D visitable )
	{
		return getVisitor ( (Class<D>) visitable.getClass () );
	}
	
	
	private V getVisitor ( Class<D> clazz )
	{
		if ( clazz == null ) throw new IllegalArgumentException (
			"Can't get a visitor for a null domain class"
		);
		V result = null;
		Class<? super D> resultClass = clazz;
		
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
	
	@SuppressWarnings ( "unchecked" )
	public void registerVisitors ( ComponentVisitor<?>... visitors )
	{
		this.visitors.addAll ( (List<V>) List.of ( visitors ) );
		initCache ();
	}
	
	private void initCache ()
	{
		dispatchersCache = new HashMap<> ();
		visitors.forEach ( v -> dispatchersCache.put ( v.getDomainClass (), v ) );
	}
}
