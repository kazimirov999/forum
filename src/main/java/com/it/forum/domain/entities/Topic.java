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
        @NamedQuery(name = "Topic.countTopicsInBranches",
                query = "SELECT t.branch, COUNT(t) FROM Topic t GROUP BY t.branch.name")
})
@Entity
@Table(name = "topics")
public class Topic implements Comparable<Topic>, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NotEmpty(message = "Field is not empty")
    @Column(columnDefinition = "TEXT")
    private String name;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime publicationDate;

    @OneToMany(mappedBy = "topic", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @OrderBy("publicationDate DESC")
    private List<Message> messages = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    public Topic() {
        this.publicationDate = DateTime.now();
    }

    @Override
    public int compareTo(Topic o) {
        if(o.getMessages().isEmpty() || this.messages.isEmpty()){
            return 1;
        }
        return o.getMessages().get(0).getPublicationDate()
                .compareTo(this.messages.get(0).getPublicationDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;

        Topic topic = (Topic) o;

        if (id != null ? !id.equals(topic.id) : topic.id != null) return false;
        return !(name != null ? !name.equals(topic.name) : topic.name != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", branch=" + branch +
                ", user=" + user +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(DateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

}
