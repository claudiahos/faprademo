package event;

public class MandateCreatedEvent {// +

	private final String id;

	public MandateCreatedEvent(String id) {

		this.id = id;
	}

	public String getId() {
		return id;
	}
}
