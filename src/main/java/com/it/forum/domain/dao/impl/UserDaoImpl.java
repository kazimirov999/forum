package com.it.forum.domain.dao.impl;

import com.it.forum.domain.dao.IUserDao;
import com.it.forum.domain.entities.User;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Repository;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements IUserDao {

    private static final Logger log = Logger.getLogger(UserDaoImpl.class);

    public UserDaoImpl() {
        super(User.class);
    }

    @Override
    public User findUserByLogin(String login) {
        User user = null;
        try {
            user = (User) getSession().createCriteria(getType())
                    .add(Restrictions.eq("login", login))
                    .uniqueResult();
        } catch (Exception e) {
            log.error("Find user error", e);
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = null;
        try {
            user = (User) getSession().createCriteria(getType())
                    .add(Restrictions.eq("email", email))
                    .uniqueResult();
        } catch (Exception e) {
            log.error("Find user error", e);
        }
        return user;
    }

    @Override
    public Long countAllUsers() {
        return (Long) getSession().createCriteria(getType())
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
}
