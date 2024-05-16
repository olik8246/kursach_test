package com.example.demo;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class Main extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        ReplyKeyboardMarkup keyboardMarkup = getMenuKeyboard();
        sendMessage.setReplyMarkup(keyboardMarkup);
        sendMessage.setText("Для виклику екстренних служб надішліть відповідний номер: \n" + "101 - служба порятунку; \n" + "102 - поліція; \n" + "103 - швидка медична допомога" );
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        if(message.getText().equals("101")){
            sendMessage.setText("Переадресація дзвінка до служби порятунку");
            sendMessage.setChatId(String.valueOf(message.getChatId()));
        }else if (message.getText().equals("102")){
            sendMessage.setText("Переадресація дзвінка до поліції");
            sendMessage.setChatId(String.valueOf(message.getChatId()));
        }else if (message.getText().equals("103")) {
            sendMessage.setText("Відбувається переадресація дзвінка на швидку медичну допомогу");
                    sendMessage.setChatId(String.valueOf(message.getChatId()));
        }
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }



    @Override
    public String getBotUsername() {
        return "java59201_bot";
    }


    @Override
    public String getBotToken() {
        return "6827524241:AAF0vDgjzN28mq6xeMczq-61EGc76efKQ2I";
    }

    private ReplyKeyboardMarkup getMenuKeyboard (){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        List <KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("101");
        keyboardRow.add("102");
        KeyboardRow keyboardRowTwo = new KeyboardRow();
        keyboardRowTwo.add("103");
        keyboardRowTwo.add("Перелік екстренних служб");
        keyboardRows.add(keyboardRow);
        keyboardRows.add(keyboardRowTwo);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

}
