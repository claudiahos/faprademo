package event;

public class CreateCustomerProjectionEvent {

	private String id;

	public CreateCustomerProjectionEvent(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
