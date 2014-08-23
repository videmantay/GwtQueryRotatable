package gwtqueryplugins.rotatable.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.RootPanel;
import static com.google.gwt.query.client.GQuery.*;
import static gwtquery.plugins.draggable.client.Draggable.Draggable;
public class GwtQueryRotatable implements EntryPoint{

	@Override
	public void onModuleLoad() {
		RootPanel root = RootPanel.get();
		HTMLPanel div = new HTMLPanel("<div id='rotateMe' style='width:100px;height:100px;border:1px solid Navy; background-color:Yellow'></div>");
		HTMLPanel div2 = new HTMLPanel("<div id='rotateMe2' style='width:100px;height:100px;border:1px solid Navy; background-color:Blue'></div>");
		root.add(div);
		root.add(div2);
		$("#rotateMe").as(Rotatable.Rotatable).rotatable();
		
		$("#rotateMe").as(Draggable).draggable();
		
$("#rotateMe2").as(Rotatable.Rotatable).rotatable();
		
		$("#rotateMe2").as(Draggable).draggable();
		
		
	}

}
