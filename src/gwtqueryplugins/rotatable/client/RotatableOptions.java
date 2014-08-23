package gwtqueryplugins.rotatable.client;

public class RotatableOptions {

	private Boolean autoHide = true;
	private  String rotatorClass = "rotatable-handle";
	private Double[] matrix = {1.0,0.0,0.0,1.0};
	private Double angle;
	
	
	
	
	public Boolean getAutoHide(){return autoHide;}
	
	public void setAutoHide(Boolean autoHide){
		this.autoHide = autoHide;
	}

	public String getRotatorClass() {
		return rotatorClass;
	}

	public void setRotatorClass(String rotatorClass) {
		this.rotatorClass = rotatorClass;
	}

	public Double[] getMatrix() {
		return matrix;
	}
	
	public void setMatrix(Double[] matrix){
		this.matrix = matrix;
	}
	

	

}
