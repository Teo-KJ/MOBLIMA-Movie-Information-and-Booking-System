package View;

public abstract class View {
	public View previous; 
	
	public View() {
		
	}
	
	protected abstract void starter();
	
	public void intent(View current, View new_v){
		new_v.previous = current;
		new_v.starter();
	}
	
	public View previous_view() {
		return previous;
	}
}
