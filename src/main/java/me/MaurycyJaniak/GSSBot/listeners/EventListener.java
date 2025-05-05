package me.MaurycyJaniak.GSSBot.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

//Unused
public class EventListener extends ListenerAdapter {

    int count = 0;
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
    }
}

