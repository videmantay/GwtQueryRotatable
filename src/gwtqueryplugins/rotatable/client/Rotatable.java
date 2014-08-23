package gwtqueryplugins.rotatable.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.plugins.Plugin;
import com.google.gwt.touch.client.Point;

import static gwtquery.plugins.draggable.client.Draggable.Draggable;
import gwtquery.plugins.draggable.client.DraggableOptions;
import gwtquery.plugins.draggable.client.DraggableOptions.DragFunction;
import gwtquery.plugins.draggable.client.DraggableOptions.HelperType;
import gwtquery.plugins.draggable.client.DraggableOptions.RevertOption;
import gwtquery.plugins.draggable.client.events.DragContext;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Rotatable  extends GQuery{
private String	ROTATABLE_KEY = "rotatableKey";
	 // Register the plugin in GQuery plugin system and
	  // set a shortcut to the class 
	  public static final Class<Rotatable> Rotatable = GQuery.registerPlugin(
	    Rotatable.class, new Plugin<Rotatable>() {
	      public Rotatable init(GQuery gq) {
	        return new Rotatable(gq);
	      }
	    });

	protected Rotatable(GQuery gq) {
		super(gq);
	}
	
	public Rotatable rotatable(){
	
	return	rotatable(new RotatableOptions());
	}
	
	public Rotatable rotatable (RotatableOptions opts){
		
		for(Element e: elements()){
			GQuery $e = $(e);
		RotatableImpl impl = new RotatableImpl($e, opts);
		$(e).data(ROTATABLE_KEY, impl);
		}
		
		return this;
	}
	
}
