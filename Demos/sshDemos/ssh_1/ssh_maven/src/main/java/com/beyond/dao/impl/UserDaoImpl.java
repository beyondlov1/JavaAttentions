package com.beyond.dao.impl;

import com.beyond.dao.UserDao;
import com.beyond.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private HibernateTemplate hibernateTemplate;


    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void addUser(User user) {
        hibernateTemplate.save(user);
    }

    public void deleteUser(User user) {
        hibernateTemplate.delete(user);
    }

    public void updateUser(User user) {
        hibernateTemplate.update(user);
    }

    public User selectById(String id) {
        return hibernateTemplate.load(User.class, id);
    }

    public List<User> selectAll() {
        return  hibernateTemplate.findByExample(new User());
    }
}
