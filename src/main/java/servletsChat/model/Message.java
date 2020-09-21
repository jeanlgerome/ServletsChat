package servletsChat.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "messages")
public final class Message {

    private int id;
    private Chat chat;
    @JsonProperty("username")
    private String userName;
    @JsonProperty("content")
    private String content;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", chat=" + chat +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @JsonCreator
    public Message(@JsonProperty("username") final String userName, @JsonProperty("content") final String content) {
        Objects.requireNonNull(userName);
        Objects.requireNonNull(content);
        this.userName = userName;
        this.content = content;
    }

    public Message(int id, Chat chat, String userName, String content) {
        this.id = id;
        this.chat = chat;
        this.userName = userName;
        this.content = content;
    }

    public Message(Chat chat, Message message) {
        this.chat = chat;
        this.userName = message.getUserName();
        this.content = message.getContent();
    }

    public Message() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    public int getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "chat_id")
    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Basic
    @Column(name = "username", nullable = true, insertable = true, updatable = true, length = 50)
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "message", nullable = true, insertable = true, updatable = true, length = 50)
    public String getContent() {
        return content;
    }
}
