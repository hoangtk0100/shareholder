package com.project.shareholder.service;

import com.project.shareholder.dao.*;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.*;
import com.project.shareholder.request.PersonRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.project.shareholder.util.Utility.convertStringToDate;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDao personDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private QuarterDao quarterDao;

    @Autowired
    private PersonQuarterDao personQuarterDao;

    @Autowired
    private SharePeriodDao sharePeriodDao;

    @Override
    public Person create(PersonRequest personRequest) throws DatabaseException {
        Person person = new Person();
        person.setFullName(personRequest.getFullName());
        person.setUsername(personRequest.getUsername());
        person.setPassword(personRequest.getPassword());
        person.setEmail(personRequest.getEmail());
        person.setAddress(personRequest.getAddress());
        person.setBirthday(convertStringToDate(personRequest.getBirthday()));
        person.setAvatar(personRequest.getAvatar());
        person.setGender(personRequest.isGender());
        person.setPersonalId(personRequest.getPersonalId());
        person.setPhoneNumber(personRequest.getPhoneNumber());
        person.setTotalProfit(0);
        person.setTotalStock(0);
        try {
            // Set role
            Role role;
            role = roleDao.retrieveByName(personRequest.getRoleName());
            person.setRole(role);

            // Add referral
            Date currentDate = new Date();
            Quarter quarter = quarterDao.retrieveByPeriod(currentDate);
            String referrerUsername = personRequest.getReferrerUsername();
            double stockQuantity = quarter.getStockQuantity();
            Person referrer = new Person();
            boolean hasReferrer = false;
            if (!referrerUsername.trim().isEmpty()) {
                referrer = personDao.retrieveByUsername(referrerUsername);
                if (null == referrer) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }

                hasReferrer = true;
                person.setRefererId(referrer.getId());
            }

            // Create new person
            personDao.createObj(person);

            // Update referrer-quarter if referrer exists
            if (hasReferrer) {
                // Update stock quantity of referrer-quarter
                PersonQuarter referrerQuarter = personQuarterDao.retrieveByPersonQuarter(referrer, quarter);
                referrerQuarter.setBonusStock(referrerQuarter.getBonusStock() + stockQuantity/2);
                ArrayList<UUID> referralIds = new ArrayList<>();
                if (null != referrerQuarter.getReferralIds()) {
                    referralIds = referrerQuarter.getReferralIds();
                }

                referralIds.add(person.getId());
                referrerQuarter.setReferralIds(referralIds);
                personQuarterDao.updateObj(referrerQuarter);

                // Create share-period
                Timestamp currentTime = new Timestamp(new Date().getTime());
                SharePeriod sharePeriod = new SharePeriod();
                sharePeriod.setPersonQuarter(referrerQuarter);
                sharePeriod.setShareAction(ShareAction.BONUS);
                sharePeriod.setPeriod(currentTime);
                sharePeriod.setStockQuantity(stockQuantity/2);
                sharePeriod.setNote("Bonus for referring");
                sharePeriodDao.createObj(sharePeriod);

                // Update referrer's total stock
                referrer.setTotalStock(referrer.getTotalStock() + stockQuantity/2);
                personDao.updateObj(referrer);
            }

            // Create person-quarter
            PersonQuarter personQuarter = new PersonQuarter();
            personQuarter.setPerson(person);
            personQuarter.setQuarter(quarter);
            personQuarter.setBonusStock(0);
            personQuarter.setStockQuantity(0);
            personQuarter.setReferralIds(null);
            personQuarter.setSharePeriods(null);
            personQuarter.setNote(null);
            personQuarterDao.createObj(personQuarter);

            // Create share-period
            Thread.sleep(50);
            Timestamp currentTime = new Timestamp(new Date().getTime());
            SharePeriod sharePeriod = new SharePeriod();
            sharePeriod.setPersonQuarter(personQuarter);
            sharePeriod.setShareAction(ShareAction.ADD);
            sharePeriod.setPeriod(currentTime);
            sharePeriod.setStockQuantity(stockQuantity);
            sharePeriod.setNote("Initialize share");
            sharePeriodDao.createObj(sharePeriod);

            // Update stock quantity of person-quarter
            personQuarter.setStockQuantity(personQuarter.getStockQuantity() + stockQuantity);
            personQuarterDao.updateObj(personQuarter);

            // Update person's total stock
            person.setTotalStock(person.getTotalStock() + stockQuantity);
            personDao.updateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(exception.getCause().toString());
        }

        return person;
    }

    @Override
    public Person update(PersonRequest personRequest) throws DatabaseException {
        Person person = new Person();
        person.setId(personRequest.getId());
        person.setFullName(personRequest.getFullName());
        person.setUsername(personRequest.getUsername());
        person.setPassword(personRequest.getPassword());
        person.setEmail(personRequest.getEmail());
        person.setAddress(personRequest.getAddress());
        person.setBirthday(convertStringToDate(personRequest.getBirthday()));
        person.setAvatar(personRequest.getAvatar());
        person.setGender(personRequest.isGender());
        person.setPersonalId(personRequest.getPersonalId());
        person.setPhoneNumber(personRequest.getPhoneNumber());
        try {
            personDao.updateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return person;
    }

    @Override
    public Person deactivate(String id) throws DatabaseException {
        Person person;
        try {
            person = personDao.retrieveById(UUID.fromString(id));
            if (null == person) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            personDao.deactivateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return person;
    }

    @Override
    public String delete(String id) throws DatabaseException {
        Person person;
        try {
            person = personDao.retrieveById(UUID.fromString(id));
            if (null == person) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            personDao.deleteObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return id;
    }

    @Override
    public Person retrieveById(String id) throws NotFoundException {
        return personDao.retrieveById(UUID.fromString(id));
    }

    @Override
    public Person retrieveByUsername(String username) throws NotFoundException {
        return personDao.retrieveByUsername(username);
    }

    @Override
    public Person retrieveByPersonalId(String personalId) throws NotFoundException {
        return personDao.retrieveByPersonalId(personalId);
    }

    @Override
    public Person retrieveByPhoneNumber(String phoneNumber) throws NotFoundException {
        return personDao.retrieveByPhoneNumber(phoneNumber);
    }

    @Override
    public List<Person> list() {
        return (personDao.retrieveAll());
    }

    @Override
    public Person updateTotalStock(String id, double stockQuantity) throws DatabaseException {
        Person person = new Person();
        try {
            person = personDao.retrieveById(UUID.fromString(id));
            person.setTotalStock(stockQuantity);
            personDao.updateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return person;
    }

    @Override
    public Person addReferral(PersonRequest personRequest) throws DatabaseException {
        Person referer = new Person();
//        try {
//            referer = personDao.retrieveByUsername(personRequest.getReferrerUsername());
//            Person referral = personDao.retrieveById(UUID.fromString(personRequest.getId()));
//
//            List<Person> persons = referer.getReferrals();
//            persons.add(referral);
//            referer.setReferrals(persons);
//            personDao.updateObj(referer);
//        } catch (Exception exception) {
//            throw new DatabaseException(Constants.DATABASE_MESSAGE);
//        }

        return referer;
    }
}
