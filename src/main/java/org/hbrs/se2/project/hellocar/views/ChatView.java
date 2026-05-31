package org.hbrs.se2.project.hellocar.views;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import com.vaadin.flow.component.textfield.TextField;
import org.hbrs.se2.project.hellocar.control.ChatControl;
import org.hbrs.se2.project.hellocar.control.LoginControl;
import org.hbrs.se2.project.hellocar.dtos.UserDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ChatView extends Dialog {

    private ChatControl chatControl;
    private LoginControl loginControl;

    private TextField textField = new TextField();
    private Button sendButton =  new Button("Abfahrt \uD83D\uDE97");
    private Div chatInterface = new Div();
    private MessageList messageList = new MessageList();
    private Div bottomAnchor = new Div();

    public ChatView(ChatControl chatControl, LoginControl loginControl) {

        this.loginControl = loginControl;
        this.chatControl = chatControl;

        this.bottomAnchor.getStyle().set("height", "1px").set("opacity", "0");

        setWidth("800px");
        setMaxWidth("90vw");
        setMaxHeight("90vh");

        setSizeFull();
        setCloseOnEsc(true);
        setCloseOnOutsideClick(true);

        add(buildChatLayout());
    }

    private UserDTO getCurrentUser(){
        return this.loginControl.getCurrentUser();
    }


    private MessageList buildMessageList(){
        List<Map<String,String>> chatHistory = this.chatControl.getCurrentChatHistory();
        List<MessageListItem> items = new ArrayList<>();

        for (Map<String, String> message : chatHistory) {
           MessageListItem messageItem = new MessageListItem();

           String role = message.get("role");
           String content = message.get("content");

           if(role.equals("user")){
               messageItem.setUserName(getCurrentUser().getFirstName());
               messageItem.setUserColorIndex(2);

           } else if(role.equals("assistant")){
               messageItem.setUserName("Carlito");
               messageItem.setUserColorIndex(5);
               messageItem.setUserImage("images/logo.png");

           } else {
               continue;
           }
           messageItem.setText(content);
           items.add(messageItem);
        }

        this.messageList.setItems(items);
        return this.messageList;
    }

    private void refreshMessages(){
        this.chatInterface.removeAll();
        this.chatInterface.add(buildMessageList());
        this.chatInterface.add(bottomAnchor);
        scrollToBottom();
    }

    private VerticalLayout buildChatLayout(){
        VerticalLayout chatLayout = new VerticalLayout();
        chatLayout.setSizeFull();
        chatLayout.setPadding(false);
        chatLayout.setSpacing(false);

        this.chatInterface.add(buildMessageList());
        this.chatInterface.add(bottomAnchor);

        this.chatInterface.setSizeFull();
        this.chatInterface.getStyle().set("overflow", "auto");
        this.chatInterface.getStyle().set("min-height", "0");

        chatLayout.add(this.chatInterface, buildInputBar());
        chatLayout.expand(this.chatInterface);

        return chatLayout;
    }

    private HorizontalLayout buildInputBar(){

        HorizontalLayout inputBar = new HorizontalLayout();

        inputBar.setWidthFull();
        inputBar.setPadding(true);
        inputBar.setSpacing(true);
        inputBar.getStyle()
                .set("background", "white")
                .set("border", "1px solid #ddd")
                .set("border-radius", "12px")
                .set("padding", "8px");

        inputBar.setAlignItems(FlexComponent.Alignment.CENTER);

        this.textField.setWidthFull();
        this.textField.setPlaceholder("BMW, Audi, andere Marken oder Preisbewertung? Frag Carlito");
        this.textField.addKeyDownListener(Key.ENTER, e -> {
            sendMessage();
        });

        inputBar.add(this.textField, buildSendButon());

        return inputBar;
    }

    private Button buildSendButon(){
        this.sendButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        this.sendButton.getStyle().set("align-self", "stretch");
        this.sendButton.getStyle().set("border-radius", "20px");

        this.sendButton.addClickListener(e -> {
            this.sendMessage();
        });
        return this.sendButton;
    }

    private void sendMessage() {
        String value = textField.getValue().trim();

        if (value.isEmpty()) {
            Notification.show("Please enter a message!").addThemeVariants(NotificationVariant.LUMO_ERROR);
            return;
        }
        try {

            boolean success = this.chatControl.sendAndGetMessage(value);
            if (success) {
                refreshMessages();
                this.textField.clear();
            }

        } catch(RuntimeException e) {
            Notification.show("Nachricht konnte nicht gesendet werden. Bitte versuche es später erneut oder überprüfe deine Einstellungen").addThemeVariants(NotificationVariant.LUMO_ERROR);
            System.out.println("KIConnect API Request failed! "+ e.getMessage());
        }
    }

    private void scrollToBottom() {
        bottomAnchor.getElement().scrollIntoView();
    }
}