package com.project.shareholder.service;

import com.project.shareholder.dao.ActivityDao;
import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Activity;
import com.project.shareholder.model.Person;
import com.project.shareholder.request.ActivityRequest;
import com.project.shareholder.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private PersonDao personDao;

    // Create new activity
    @Override
    public Activity create(ActivityRequest activityRequest) throws DatabaseException {
        Person person = new Person();
        UUID personId = UUID.fromString(activityRequest.getPerson().getId());
        person.setId(personId);

        // Set person's activity
        Activity activity = new Activity();
        activity.setPerson(person);
        activity.setType(activityRequest.getType());
        activity.setTarget(activityRequest.getTarget());
        activity.setContent(activityRequest.getContent());
        try {
            activityDao.createObj(activity);
        } catch (Exception exception) {
            throw new DatabaseException(exception.getMessage());
        }

        return activity;
    }

    // Retrieve activity by id
    @Override
    public Activity retrieveById(String id) throws NotFoundException {
        return activityDao.retrieveById(UUID.fromString(id));
    }

    // Retrieve person's activity
    @Override
    public List<Activity> retrieveByPersonId(String personId) throws NotFoundException {
        return (List<Activity>) activityDao.retrieveByPersonId(UUID.fromString(personId));
    }

    // Retrieve all activities
    @Override
    public List<Activity> list() {
        return activityDao.retrieveAll();
    }
}
