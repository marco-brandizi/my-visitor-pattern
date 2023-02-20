package info.marcobrandizi.examples.visitor.composite;

import java.util.HashMap;
import java.util.Map;

import info.marcobrandizi.examples.visitor.dispatcher.ComponentVisitor;

/**
 * TODO: comment me!
 *
 * @author brandizi
 * <dl><dt>Date:</dt><dd>20 Feb 2023</dd></dl>
 *
 */
public class CompositeDispatcher<C,R>
{
	private Map<Class<C>, R> dispatchersCache = new HashMap<> ();

	private R getReceiver ( Class<C> clz )
	{
		R result = null;
		Class<? super C> resultClass = clz;
		
		while ( result == null && clz != null )
		{
			result = dispatchersCache.get ( clz );
			if ( result != null ) break;
			
			resultClass = resultClass.getSuperclass ();
		}
		
		if ( result == null ) throw new IllegalArgumentException (
			"No dispatcher found for the class " + clz.getName () 
		);
		
		if ( !clz.equals ( resultClass ) ) dispatchersCache.put ( clz, result );
		return result;
	}
}
