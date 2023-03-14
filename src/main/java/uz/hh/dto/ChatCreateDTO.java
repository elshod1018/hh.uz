package uz.hh.dto;

import uz.hh.domain.*;

import java.util.Objects;

public class ChatCreateDTO {
    private final Vacancy vacancy;
    private final Employer employer;
    private final Message message;

    public ChatCreateDTO(Vacancy vacancy, Employer employer, Message message) {
        this.vacancy = vacancy;
        this.employer = employer;
        this.message = message;
    }

    public Vacancy vacancy() {
        return vacancy;
    }

    public Employer employer() {
        return employer;
    }

    public Message message() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ChatCreateDTO) obj;
        return Objects.equals(this.vacancy, that.vacancy) &&
                Objects.equals(this.employer, that.employer) &&
                Objects.equals(this.message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vacancy, employer, message);
    }

    @Override
    public String toString() {
        return "ChatCreateDTO[" +
                "vacancy=" + vacancy + ", " +
                "employer=" + employer + ", " +
                "message=" + message + ']';
    }

}
