package event;

public class CreateAccountProjectionEvent {

	private String id;

	public CreateAccountProjectionEvent(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}
}
