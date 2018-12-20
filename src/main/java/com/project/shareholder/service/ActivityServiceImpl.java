package com.project.shareholder.service;

import com.project.shareholder.dao.ActivityDao;
import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Activity;
import com.project.shareholder.model.Person;
import com.project.shareholder.request.ActivityRequest;
import com.project.shareholder.util.Constants;
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
        Activity activity = new Activity();
        try {
            // Check if person exists
            UUID personId = activityRequest.getPersonId();
            Person person = personDao.retrieveById(personId);
            if (null == person) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            // Set person's activity
            activity.setType(activityRequest.getType());
            activity.setTarget(activityRequest.getTarget());
            activity.setContent(activityRequest.getContent());
            activity.setPerson(person);
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

    // Retrieve person's activity by person id
    @Override
    public List<Activity> retrieveByPersonId(String personId) throws NotFoundException {
        Person person = personDao.retrieveById(UUID.fromString(personId));
        return activityDao.retrieveByPerson(person);
    }

    // Retrieve all activities
    @Override
    public List<Activity> list() {
        return activityDao.retrieveAll();
    }
}
