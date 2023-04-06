package ru.taksebe.telegram.telegrambot.telegram;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.taksebe.telegram.telegrambot.telegram.Bot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@AllArgsConstructor
public class WebhookController {
    @Autowired
    private JmsTemplate jmsTemplate;
    private final Bot bot;

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        jmsTemplate.convertAndSend("queue",update.getMessage());
        return bot.onWebhookUpdateReceived(update);
    }
}
