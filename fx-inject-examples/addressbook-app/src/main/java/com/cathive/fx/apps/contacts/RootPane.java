/*
 * Copyright (C) 2013,2014 The Cat Hive Developers.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cathive.fx.apps.contacts;

import com.cathive.fx.apps.contacts.model.Company;
import com.cathive.fx.apps.contacts.model.Contact;
import com.cathive.fx.apps.contacts.model.Family;
import com.cathive.fx.apps.contacts.model.Person;
import com.cathive.fx.inject.core.FXMLComponent;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Root pane
 * @author Benjamin P. Jung
 */
@Named
@Dependent
@FXMLComponent(location = "RootPane.fxml", resources = "com.cathive.fx.apps.contacts.Messages")
public class RootPane extends AnchorPane {

    @FXML private ToolBar toolBar;
    @FXML private Button addButton;
    @FXML private Button removeButton;
    @FXML private ContactListView contactListView;
    @FXML private ContactDetailsPane contactDetailsPane;

    private final ReadOnlyObjectWrapper<Contact> selectedContact = new ReadOnlyObjectWrapper<>(this, "selectedContact");

    @Inject
    public void init() {

        // TODO Remove this dummy content.
        contactListView.getItems().addAll(
                new Person("John", "Doe"),
                new Person("Max", "Mustermann"),
                new Company("Monster Inc."),
                new Family("Schmitz"));
        final Person chuckNorris = new Person("Chuck", "Norris");
        chuckNorris.setPhoto(new Image("http://www.biography.com/imported/images/Biography/Images/Profiles/N/Chuck-Norris-15720761-1-402.jpg"));
        contactListView.getItems().add(chuckNorris);

        this.selectedContact.bind(contactListView.selectedContactProperty());

        removeButton.disableProperty().bind(Bindings.isNull(selectedContactProperty()));
    }

    public ReadOnlyObjectProperty<Contact> selectedContactProperty() {
        return this.selectedContact.getReadOnlyProperty();
    }

}
