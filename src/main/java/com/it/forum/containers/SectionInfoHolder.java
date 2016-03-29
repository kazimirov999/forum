package com.it.forum.containers;

import com.it.forum.domain.entities.Branch;
import com.it.forum.domain.entities.Message;
import com.it.forum.domain.entities.Section;

import java.util.*;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class SectionInfoHolder {

    private List<Section> sections = new ArrayList<>();
    private Map<Branch, Long> amountMessagesInBranches = new HashMap<>();
    private Map<Branch, Long> amountTopicsInBranches = new HashMap<>();
    private Set<Message> lastMessageInBranch = new HashSet();

    public Long getCountMessagesInBranch(Branch branch){
        return (amountMessagesInBranches.get(branch) != null) ? amountMessagesInBranches.get(branch):0;
    }

    public Long getCountTopicsInBranch(Branch branch){
        return (amountTopicsInBranches.get(branch) != null) ? amountTopicsInBranches.get(branch):0;
    }

    public Message getLastMessageInBranch(Branch branch){
        Message result = null;
        for(Message message : lastMessageInBranch){
            if(branch.equals(message.getTopic().getBranch())){
                result = message;
            }
        }

        return result;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public Map<Branch, Long> getAmountMessagesInBranches() {
        return amountMessagesInBranches;
    }

    public void setAmountMessagesInBranches(Map<Branch, Long> amountMessagesInBranches) {
        this.amountMessagesInBranches = amountMessagesInBranches;
    }

    public Map<Branch, Long> getAmountTopicsInBranches() {
        return amountTopicsInBranches;
    }

    public void setAmountTopicsInBranches(Map<Branch, Long> amountTopicsInBranches) {
        this.amountTopicsInBranches = amountTopicsInBranches;
    }

    public Set<Message> getLastMessageInBranch() {
        return lastMessageInBranch;
    }

    public void setLastMessageInBranch(Set<Message> lastMessageInBranch) {
        this.lastMessageInBranch = lastMessageInBranch;
    }
}
