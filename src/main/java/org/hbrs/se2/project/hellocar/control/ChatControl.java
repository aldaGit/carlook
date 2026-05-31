package org.hbrs.se2.project.hellocar.control;

import com.vaadin.flow.component.UI;
import org.hbrs.se2.project.hellocar.services.ai.AIService;
import org.hbrs.se2.project.hellocar.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class ChatControl {

    @Autowired
    private PromptControl promptControl;

    @Autowired
    private AIService aiService;

    public boolean sendAndGetMessage(String userMessage){

        if(userMessage.isEmpty()){
            return false;
        }

        List<Map<String, String>> chatHistory = getCurrentChatHistory();

        while (chatHistory.size() > 21) {
            chatHistory.remove(1);
        }

        chatHistory.add(Map.of("role","user", "content", userMessage));

        String response = this.aiService.sendMessageAndGetResponse(chatHistory);

        chatHistory.add(Map.of("role", "assistant", "content", response));

        UI.getCurrent().getSession().setAttribute(Globals.CHAT_HISTORY, chatHistory);

        return true;
    }

    public List<Map<String, String>> getCurrentChatHistory(){

        List<Map<String, String>> chatHistory = (List<Map<String, String>>) UI.getCurrent().getSession().getAttribute(Globals.CHAT_HISTORY);

        if (chatHistory == null){
            chatHistory = new ArrayList<>();
            String prompt = this.promptControl.getSystemPrompt();

            chatHistory.add(Map.of("role", "system", "content", prompt));

            chatHistory.add(Map.of("role", "assistant", "content", "Hi, mein Name ist Carlito. " +
                    "Ich bin ein virtueller Assistent, der dich rund um das Thema Autos beraten kann! "+
                    "Sag mir Bescheid, wenn du etwas wissen willst :)"));

            UI.getCurrent().getSession().setAttribute(Globals.CHAT_HISTORY, chatHistory);
        }

        return chatHistory;
    }
}
