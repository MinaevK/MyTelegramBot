package com.example.vobabot;

import com.example.vobabot.services.impl.SenderServiceImpl;
import com.example.vobabot.services.impl.TextAnalyserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import com.example.vobabot.services.*;

@Slf4j
@Component
public class VobaBot extends TelegramLongPollingBot {
    @Autowired
    private SenderService senderService;

    @Autowired
    private InputAnalyserService textAnalyserService;

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getText());
        System.out.println(update.getMessage().getFrom().getFirstName());
        // We check if the update has a message and the message has text
        Message msg = update.hasMessage() ? update.getMessage() : null;
        if (msg != null && msg.hasText()) {
            Long chatId = msg.getChatId();
            log.debug("textAnalyserService {}", textAnalyserService);
            String response = textAnalyserService.getResponse(msg);
            try {
                execute(senderService.sendMessage(chatId, response)); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "voba_bot";
    }

    @Override
    public String getBotToken() {
        return "994009559:AAGfPXZUCD7QqF0DEX7OJcsZUQoUFzc0q2U";
    }
}
