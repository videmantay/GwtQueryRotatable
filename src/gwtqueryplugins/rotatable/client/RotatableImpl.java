package gwtqueryplugins.rotatable.client;

import static gwtquery.plugins.draggable.client.Draggable.Draggable;
import gwtquery.plugins.draggable.client.Draggable;
import gwtquery.plugins.draggable.client.DraggableOptions;
import gwtquery.plugins.draggable.client.DraggableOptions.DragFunction;
import gwtquery.plugins.draggable.client.DraggableOptions.HelperType;
import gwtquery.plugins.draggable.client.DraggableOptions.RevertOption;
import gwtquery.plugins.draggable.client.events.DragContext;

import com.google.gwt.dom.client.Element;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;

import static com.google.gwt.query.client.GQuery.*;

import com.google.gwt.touch.client.Point;
import com.google.gwt.user.client.Event;

public class RotatableImpl {
	
	private final DraggableOptions dragOpts = new DraggableOptions();
	private final RotatableOptions opts ;
	private final GQuery element;
	private Double angle;
	private Boolean rotating = false;
	private int[] mouseCoords = new int[2];
	private int[] centerCoords = new int[2];
	private final DragFunction start = new DragFunction(){
		@Override
		public void f(DragContext context) {
		$(context.getDraggable()).on("mousemove.rotatable", new Function(){
			@Override
			public boolean f(Event e){
				e.stopPropagation();
				e.preventDefault();
				mouseCoords[0] = e.getClientX();
				mouseCoords[1] = e.getClientY();
				Double angle  = radToDeg(getAngle(mouseCoords, centerCoords));
				rotate(angle);
				rotating = true;
				return true;
			}
		});
			
		}};// end start
	private final DragFunction stop = new DragFunction(){

		@Override
		public void f(DragContext context) {
			$(context.getDraggable()).off("mousemove.rotatable");
			
		}};
private final DragFunction drag = new DragFunction(){

	@Override
	public void f(DragContext context) {

		console.log("The x is: " + mouseCoords[0] +
					"The y is: " + mouseCoords[1] +
				    "The matrix is" + $(element).css("transform"));
		
	}};
	
	RotatableImpl(GQuery element, RotatableOptions opts){
		this.opts = opts;
		GQuery handler = $("<div class='" + opts.getRotatorClass() +"'></div>");
		this.element = element;
		this.element.append(handler);
		
		//set drag opts
		dragOpts.setHelper(HelperType.CLONE);
		dragOpts.setRevert(RevertOption.NEVER);
		dragOpts.setOnDragStart(start);
		dragOpts.setOnDrag(drag);
		dragOpts.setOnDragStop(stop);
		handler.as(Draggable).draggable(dragOpts);
		
		
		centerCoords[0] =  (int) (element.offset().left + element.width() * 0.5);
		centerCoords[1] =  (int) (element.offset().top + element.height() * 0.5);
		         
		
	}
	
	// Get Angle
    private double getAngle(int[] ms, int[] ctr) {
      int x = ms[0] - ctr[0],
        y = -ms[1] + ctr[1];
       double hyp = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
       double angle = Math.acos((-1) * x / hyp);

      if (y < 0) {
        angle = 2 * Math.PI - angle;
      }

      return angle;
    }

    // Convert from Degrees to Radians
   private double degToRad(Double d) {
      return (d * (Math.PI / 180));
    }

    // Convert from Radians to Degrees
    private double radToDeg(Double r) {
      return (r * (180 / Math.PI));
    }

    // Rotate Element to the Given Degree
    private void rotate(Double degree) {
     
      double cos = Math.cos(degToRad(degree)),
        sin = Math.sin(degToRad(degree));
        double[] mtx = {cos, sin, (-sin), cos};

      angle = degree;
      updateRotationMatrix(mtx);
    }

    // Get CSS Transform Matrix (transform: matrix)
    private double[] getRotationMatrix(){
      String matrix = this.element.css("transform") != null ? this.element.css("transform") : "matrix(1, 0, 0, 1, 0, 0)";
        String[] mtx = matrix.split(",");
       double[] m = new double[4];

      for (int i = 0; i < 4; i++) {
        m[i] = Double.parseDouble(mtx[i].replace("matrix(", ""));
      }

      return m;
    }

    // Update CSS Transform Matrix (transform: matrix)
    private void updateRotationMatrix(double[] m) {
      String matrix = "matrix(" + m[0] + "," + m[1] + "," + m[2] + "," + m[3] + ", 0, 0)";
        
      this.element.css($$("transform:" + matrix));

    }
	

	     


}
