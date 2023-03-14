package uz.hh.dto;

import uz.hh.domain.Chat;

import java.util.Objects;

public  class MessageCreateDTO {
    private final Chat chat;
    private final String text;

    public MessageCreateDTO(Chat chat, String text) {
        this.chat = chat;
        this.text = text;
    }

    public Chat chat() {
        return chat;
    }

    public String text() {
        return text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (MessageCreateDTO) obj;
        return Objects.equals(this.chat, that.chat) &&
                Objects.equals(this.text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chat, text);
    }

    @Override
    public String toString() {
        return "MessageCreateDTO[" +
                "chat=" + chat + ", " +
                "text=" + text + ']';
    }

}
