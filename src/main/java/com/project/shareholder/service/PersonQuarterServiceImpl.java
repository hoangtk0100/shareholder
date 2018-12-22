package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.PersonQuarterDao;
import com.project.shareholder.dao.QuarterDao;
import com.project.shareholder.dao.SharePeriodDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonQuarter;
import com.project.shareholder.model.Quarter;
import com.project.shareholder.model.SharePeriod;
import com.project.shareholder.request.PersonQuarterRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.project.shareholder.util.Utility.convertYearMonthStringToDate;

@Service
@Transactional
public class PersonQuarterServiceImpl implements PersonQuarterService {
    @Autowired
    private PersonQuarterDao personQuarterDao;

    @Autowired
    private PersonDao personDao;
    
    @Autowired
    private QuarterDao quarterDao;

    @Autowired
    private SharePeriodDao sharePeriodDao;

    // Create person quarter
    @Override
    public PersonQuarter create(PersonQuarterRequest personQuarterRequest) throws DatabaseException {
        PersonQuarter personQuarter = new PersonQuarter();
        try {
            Person person = personDao.retrieveById(personQuarterRequest.getPersonId());
            if (null == person) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            Quarter quarter = quarterDao.retrieveById(personQuarterRequest.getQuarterId());
            if (null == quarter) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            List<SharePeriod> sharePeriods = null;
            ArrayList<UUID> referralIds = personQuarterRequest.getReferralIds();

            personQuarter.setPerson(person);
            personQuarter.setQuarter(quarter);
            personQuarter.setBonusStock(0);
            personQuarter.setStockQuantity(personQuarterRequest.getStockQuantity());
            personQuarter.setReferralQuantity(0);
            personQuarter.setReferralIds(referralIds);
            personQuarter.setSharePeriods(sharePeriods);
            personQuarter.setNote(personQuarterRequest.getNote());
            personQuarterDao.createObj(personQuarter);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return personQuarter;
    }

    // Update specific person - quarter
    @Override
    public PersonQuarter update(PersonQuarterRequest personQuarterRequest) throws DatabaseException {
        PersonQuarter personQuarter = new PersonQuarter();
        try {
            UUID id = personQuarterRequest.getId();

            // Check if person - quarter exists
            personQuarter = personQuarterDao.retrieveById(id);
            if (null == personQuarter) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            // Add share period
            double stockQuantity = personQuarter.getStockQuantity();
            double bonusStock = personQuarter.getBonusStock();
            List<UUID> sharePeriodIds = personQuarterRequest.getSharePeriodIds();
            List<SharePeriod> sharePeriods = personQuarter.getSharePeriods();
            if (!sharePeriodIds.isEmpty()) {
                for (UUID sharePeriodIdIndex : sharePeriodIds) {
                    SharePeriod sharePeriodIndex = sharePeriodDao.retrieveById(sharePeriodIdIndex);
                    if (null == sharePeriodIndex) {
                        throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                    }

                    if (!sharePeriodIndex.isActive()) {
                        continue;
                    }

                    sharePeriods.add(sharePeriodIndex);
                    switch (sharePeriodIndex.getShareAction()) {
                        case ADD:
                            stockQuantity += sharePeriodIndex.getStockQuantity();
                            break;
                        case SUBTRACT:
                            stockQuantity -= sharePeriodIndex.getStockQuantity();
                            break;
                        case BONUS:
                            bonusStock += sharePeriodIndex.getStockQuantity();
                            break;
                        default: break;
                    }
                }

                if (sharePeriods.size() == 0) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }
            }

            ArrayList<UUID> referralIds = personQuarter.getReferralIds();
            ArrayList<UUID> newReferralIds = personQuarterRequest.getReferralIds();
            if (!newReferralIds.isEmpty()) {
                for (UUID referralIdIndex : newReferralIds) {
                    Person referralIndex = personDao.retrieveById(referralIdIndex);
                    if (!referralIndex.isActive()) {
                        continue;
                    }

                    referralIds.add(referralIdIndex);
                }
            }

            personQuarter.setReferralIds(referralIds);
            personQuarter.setReferralQuantity(referralIds.size());
            personQuarter.setSharePeriods(sharePeriods);
            personQuarter.setStockQuantity(stockQuantity);
            personQuarter.setBonusStock(bonusStock);
            personQuarter.setNote(personQuarterRequest.getNote());
            quarterDao.updateObj(personQuarter);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return personQuarter;
    }

    // Deactivate person - quarter
    @Override
    public PersonQuarter deactivate(String id) throws DatabaseException {
        PersonQuarter personQuarter;
        try {
            personQuarter = personQuarterDao.retrieveById(UUID.fromString(id));
            if (null == personQuarter) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            quarterDao.deactivateObj(personQuarter);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return personQuarter;
    }

    // Retrieve person quarter by id
    @Override
    public PersonQuarter retrieveById(String id) throws NotFoundException {
        return personQuarterDao.retrieveById(UUID.fromString(id));
    }

    // Retrieve person quarter by person id
    @Override
    public List<PersonQuarter> retrieveByPersonId(String personId) throws NotFoundException {
        Person person = personDao.retrieveById(UUID.fromString(personId));
        return personQuarterDao.retrieveByPerson(person);
    }

    // Retrieve person quarter by quarter id
    @Override
    public List<PersonQuarter> retrieveByQuarterId(String quarterId) throws NotFoundException {
        Quarter quarter = quarterDao.retrieveById(UUID.fromString(quarterId));
        return personQuarterDao.retrieveByQuarter(quarter);
    }

    // Retrieve person quarter by person quarter
    @Override
    public PersonQuarter retrieveByPersonQuarter(String personId, String quarterId) throws NotFoundException {
        Person person = personDao.retrieveById(UUID.fromString(personId));
        Quarter quarter = quarterDao.retrieveById(UUID.fromString(quarterId));
        return personQuarterDao.retrieveByPersonQuarter(person, quarter);
    }

    @Override
    public PersonQuarter retrieveByPersonPeriod(String personId, String period) throws NotFoundException {
        Person person = personDao.retrieveById(UUID.fromString(personId));
        Quarter quarter = quarterDao.retrieveByPeriod(convertYearMonthStringToDate(period));
        return personQuarterDao.retrieveByPersonQuarter(person, quarter);
    }

    @Override
    public List<PersonQuarter> list() {
        return personQuarterDao.retrieveAll();
    }
}
