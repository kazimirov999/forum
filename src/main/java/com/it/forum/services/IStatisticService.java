package com.it.forum.services;

import java.util.Set;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IStatisticService {

    Set<String> getLoginsOfActiveUsers();

    int countActiveUsers();

    int countActiveVisitors();

    Long countAllMessages();

    Long countRegisterUsers();

    Statistic getStatistic();

    abstract class Statistic {
        private Long allMessagesNumber;
        private Long registerUsersNumber;
        private Integer activeUsersNumber;
        private Integer activeVisitorsNumber;
        private Set<String> loginsOfActiveUsers;

        public Long getAllMessagesNumber() {
            return allMessagesNumber;
        }

        public void setAllMessagesNumber(Long allMessagesNumber) {
            this.allMessagesNumber = allMessagesNumber;
        }

        public Long getRegisterUsersNumber() {
            return registerUsersNumber;
        }

        public void setRegisterUsersNumber(Long registerUsersNumber) {
            this.registerUsersNumber = registerUsersNumber;
        }

        public Integer getActiveUsersNumber() {
            return activeUsersNumber;
        }

        public void setActiveUsersNumber(Integer activeUsersNumber) {
            this.activeUsersNumber = activeUsersNumber;
        }

        public Integer getActiveVisitorsNumber() {
            return activeVisitorsNumber;
        }

        public void setActiveVisitorsNumber(Integer activeVisitorsNumber) {
            this.activeVisitorsNumber = activeVisitorsNumber;
        }

        public Set<String> getLoginsOfActiveUsers() {
            return loginsOfActiveUsers;
        }

        public void setLoginsOfActiveUsers(Set<String> loginsOfActiveUsers) {
            this.loginsOfActiveUsers = loginsOfActiveUsers;
        }
    }
}
