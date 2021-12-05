package aggregates;


import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;

import org.axonframework.spring.stereotype.Aggregate;

import event.MandateCreatedEvent;

	
	@Aggregate
	public class Mandate { //ist diese KLasse überhaupt nötig? hängt das ab von der Speicherungsentscheidung??

	    @AggregateIdentifier
	    private String mandateId;

	    public Mandate() {
	   
	    }

	    @EventSourcingHandler
	    public void on(MandateCreatedEvent event) {
	   
	    	//log MandateCreatedEvent
	    	this.mandateId = event.getId();
	    	
	    	//geht raus an Mandateprojection ->ist schon draußen!
	      
	    }

	    //Evebtsourcing!


}
