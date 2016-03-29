package com.it.forum.domain.entities;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@NamedQueries({
        @NamedQuery(name = "Message.countMessagesInBranches",
                query = "SELECT m.topic.branch, COUNT(m) FROM Message m GROUP BY m.topic.branch.name"),
        @NamedQuery(name = "Message.findLastMassagesInBranches",
                query = "SELECT m FROM Message m WHERE m.publicationDate=" +
                        "(SELECT max(mm.publicationDate)FROM Message mm where mm.topic.branch.name=m.topic.branch.name)"
        )
})
@Entity
@Table(name = "messages")
public class Message implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String text;
    private Integer grade;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime publicationDate;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @OrderBy("publicationDate ASC")
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Message() {
        this.publicationDate = DateTime.now();
    }

    public Message(String text, Topic topic, Integer grade, List<Comment> comments, User user) {
        this();
        this.text = text;
        this.topic = topic;
        this.grade = grade;
        this.comments = comments;
        this.user = user;
    }

    public void gradeIncrease() {
        grade++;
    }

    public void gradeDecrease() {
        grade--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Message)) return false;

        Message message = (Message) o;

        if (id != null ? !id.equals(message.id) : message.id != null) return false;
        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        if (grade != null ? !grade.equals(message.grade) : message.grade != null) return false;
        return !(publicationDate != null ? !publicationDate.equals(message.publicationDate) : message.publicationDate != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", grade=" + grade +
                ", publicationDate=" + publicationDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public DateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(DateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
