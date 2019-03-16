package com.mano.springvaadinapp1.springvaadin.ui;

import com.mano.springvaadinapp1.springvaadin.model.Employee;
import com.mano.springvaadinapp1.springvaadin.repository.EmployeeRepository;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
public class TestUI extends HorizontalLayout {

    private TextField txtname, txtphone,
            txtemail;
    private Button btnAddNew;
    private ListBox<Employee> listBox =
            new ListBox<>();
    private EmployeeRepository repo;
    @Autowired
    public TestUI(EmployeeRepository repo){
        this.repo = repo;
        listBox.setItems(repo.findAll());
        listBox.setRenderer(new ComponentRenderer<>(emp -> {
            Label name = new Label("Name: " + emp.getName());
            Label phone = new Label("Phone: " + emp.getPhone());
            Label email = new Label("Email: " + emp.getEmail());
            NativeButton button = new NativeButton("Delete",
                    event -> {
                        repo.delete(emp);
                        clearFormData();
                        listBox.getDataProvider().refreshItem(emp);
                    });
            Div labels = new Div(name, phone,email);
            Div layout = new Div(labels, button);
            labels.getStyle().set("display", "flex")
                    .set("flexDirection", "column")
                    .set("marginRight", "10px");
            layout.getStyle().set("display", "flex")
                    .set("alignItems", "center");
            return layout;
        }));
        listBox.setItemEnabledProvider(employee -> {
            employee = repo.findByName(employee.getName());
            if (employee!=null)
                return true;
            else
                return false;
        });
        listBox.addValueChangeListener(event ->
                populateForm(listBox.getValue()));
        add(listBox, createForm());
    }
    private Component createForm(){
        FormLayout formLayout = new FormLayout();
        txtname = new TextField("Name");
        txtphone = new TextField("Phone");
        txtemail = new TextField("Email");
        btnAddNew = new Button("Add New",
                VaadinIcon.PLUS.create());
        btnAddNew.addClickListener(event->save());
        formLayout.add(txtname,txtemail,txtphone,btnAddNew);
        return formLayout;
    }
    private void save(){
        if (txtname.isEmpty() || txtphone.isEmpty()
                || txtemail.isEmpty()) {
            Notification.show("Input data cannot be empty");
            return;
        }
        Employee emp = repo.findByName(txtname
                .getValue().trim());
        if (emp==null) {
            repo.save(new Employee(txtname.getValue(),
                    txtphone.getValue(), txtemail.getValue()));
            listBox.setItems(repo.findAll());
        }else{
            Notification.show("Cannot save. Same name exists. Try different name.");
        }
        clearFormData();
    }
    public void clearFormData(){
        txtname.setValue("");
        txtphone.setValue("");
        txtemail.setValue("");
    }
    public void populateForm(Employee emp){
        txtname.setValue(emp.getName());
        txtemail.setValue(emp.getEmail());
        txtphone.setValue(emp.getPhone());
    }

}
