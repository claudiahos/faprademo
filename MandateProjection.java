package components;

//import org.axonframework.eventsourcing.EventSourcingHandler;

import org.springframework.stereotype.Component;

//import event.MandateCreatedEvent;

//import java.util.ArrayList;


@Component
public class MandateProjection { // speichert Vollmacht

	private String id;
//	private final ArrayList<String> storage = new ArrayList<>();

	public MandateProjection() {

	}

	public MandateProjection(String id) {

	}

	public String getId() {
		return id;
	};

/*	@EventSourcingHandler
	public void on(MandateCreatedEvent event) { // unnötig, wenn das über log gespeichert wird??
		storage.add(event.getId());
	} // Daten können abgefragt werden und Daten können geändert werden von der UI

*/
}
