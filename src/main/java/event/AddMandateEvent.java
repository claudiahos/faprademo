package event;

public class AddMandateEvent {
	
	private String id;
	
	public AddMandateEvent(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

}
