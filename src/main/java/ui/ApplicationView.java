package ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import io.sapl.fullstack.api.command.application.ApproveApplicationCommand;
import io.sapl.fullstack.api.command.application.RejectApplicationCommand;
import io.sapl.fullstack.api.enumeration.ApplicationFormStatus;
import io.sapl.fullstack.api.query.application.ApplicationQuery;
import io.sapl.fullstack.api.response.application.ApplicationResponse;
import io.sapl.fullstack.ui.util.DialogUtil;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;

@Route("application/:applicationFormId")
@PageTitle("Application")
@AnonymousAllowed
public class ApplicationView extends VerticalLayout implements BeforeEnterObserver {

    private String applicationFormId;

    private CommandGateway commandGateway;
    private QueryGateway queryGateway;

    private TextField firstName = new TextField("First name");
    private TextField lastName = new TextField("Last name");
    private TextField address = new TextField("Address");
    private TextField city = new TextField("City");
    private IntegerField postalCode = new IntegerField("Postal code");
    private TextField phone = new TextField("Phone");
    private EmailField email = new EmailField("Email");

    private Button approveButton = new Button("Approve", buttonClickEvent -> {
        commandGateway.send(new ApproveApplicationCommand(applicationFormId)); //Übergang ins Backend
        DialogUtil.showDialog("Application was approved");
    });

    private Button rejectButton = new Button("Reject", buttonClickEvent -> {
        commandGateway.send(new RejectApplicationCommand(applicationFormId));
        DialogUtil.showDialog("Application was rejected");
    });

    public ApplicationView(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
        add(new H1("Bank account application"));
        add(firstName, lastName, address, city, postalCode, phone, email);
        add(new HorizontalLayout(approveButton, rejectButton));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        applicationFormId = event.getRouteParameters().get("applicationFormId").orElseThrow();
        try {
            var app = queryGateway.query(new ApplicationQuery(applicationFormId),
                    ResponseTypes.instanceOf(ApplicationResponse.class)).get();
            firstName.setValue(app.getFirstName());
            lastName.setValue(app.getLastName());
            address.setValue(app.getAddress());
            city.setValue(app.getCity());
            postalCode.setValue(app.getPostalCode());
            phone.setValue(app.getPhone());
            email.setValue(app.getEmail());
            if (app.getStatus() != ApplicationFormStatus.PENDING) {
                approveButton.setEnabled(false);
                rejectButton.setEnabled(false);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
