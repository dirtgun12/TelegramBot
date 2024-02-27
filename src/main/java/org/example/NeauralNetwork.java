package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class NeauralNetwork extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "";
    }

    @Override
    public String getBotToken() {
        return "";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
        var massage = update.getMessage();
        var chatId = massage.getChatId().toString();
        var text = massage.getText();
        boolean waitingForPassword = false;


            var next = InlineKeyboardButton.builder()
                    .text("Next").callbackData("next")
                    .build();

            var back = InlineKeyboardButton.builder()
                    .text("Back").callbackData("back")
                    .build();

            var url = InlineKeyboardButton.builder()
                    .text("Tutorial")
                    .url("https://core.telegram.org/bots/api")
                    .build();
            boolean screaming = false;
            InlineKeyboardMarkup keyboardM1;
            InlineKeyboardMarkup keyboardM2;
            ;
            keyboardM1 = InlineKeyboardMarkup.builder()
                    .keyboardRow(List.of(next)).build();

//Buttons are wrapped in lists since each keyboard is a set of button rows
            keyboardM2 = InlineKeyboardMarkup.builder()
                    .keyboardRow(List.of(back))
                    .keyboardRow(List.of(url))
                    .build();
        var txt = massage.getText();
        if(massage.isCommand()) {
            if (txt.equals("/scream"))
                screaming = true;
            else if (txt.equals("/whisper"))
                screaming = false;
            else if (txt.equals("/menu"))
                sendMenu(update.getMessage().getFrom().getId(), "<b>Menu 1</b>", keyboardM1);
            return;
        }

    }


    public void sendText(String who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what).build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
    public void sendMenu (Long who, String txt, InlineKeyboardMarkup kb){
        SendMessage sm = SendMessage.builder().chatId(who.toString())
                .parseMode("HTML").text(txt)
                .replyMarkup(kb).build();

        try {
            execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}





