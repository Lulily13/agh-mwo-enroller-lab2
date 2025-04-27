package com.company.enroller.persistence;

import com.company.enroller.model.Meeting;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component("meetingService")
public class MeetingService {

    Session session;

    public MeetingService() {
        session = DatabaseConnector.getInstance().getSession();
    }

    public Collection<Meeting> getAll() {
        String hql = "FROM Meeting";
        Query query = this.session.createQuery(hql);
        return query.list();
    }

    public Meeting findById(Long id) {
        return session.get(Meeting.class, id);
    }

    public Meeting add(Meeting meeting) {
        Transaction transaction = session.beginTransaction();
        session.save(meeting);
        transaction.commit();
        return meeting;
    }

    public void delete(Long id) {
        Meeting meeting = session.get(Meeting.class, id);
        if (meeting != null) {
            Transaction transaction = session.beginTransaction();
            session.delete(meeting);
            transaction.commit();
        }
    }

    public void update(Meeting meeting) {
        Transaction transaction = session.beginTransaction();
        session.merge(meeting);
        transaction.commit();
    }

}
