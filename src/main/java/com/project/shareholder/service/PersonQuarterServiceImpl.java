package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.PersonQuarterDao;
import com.project.shareholder.dao.QuarterDao;
import com.project.shareholder.dao.SharePeriodDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.InvalidArgumentException;
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

import java.util.List;
import java.util.UUID;

import static com.project.shareholder.util.Utility.convertStringToDate;

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

            List<UUID> sharePeriodIds = personQuarterRequest.getSharePeriodIds();
            List<SharePeriod> sharePeriods = null;
            for (int index = 0; index < sharePeriodIds.size(); ++index) {
                SharePeriod sharePeriodIndex = sharePeriodDao.retrieveById(sharePeriodIds.get(index));
                if (null == sharePeriodIndex) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }

                if (!sharePeriodIndex.isActive()) {
                    continue;
                }

                sharePeriods.add(sharePeriodIndex);
            }

            if (sharePeriods.size() == 0) {
                throw new InvalidArgumentException(Constants.INVALID_ARGUMENT_MESSAGE);
            }

            personQuarter.setPerson(person);
            personQuarter.setQuarter(quarter);
            personQuarter.setStockQuantity(personQuarterRequest.getStockQuantity());
            personQuarter.setReferralQuantity(personQuarterRequest.getReferralIds().size());
            personQuarter.setReferralIds(personQuarterRequest.getReferralIds());
            personQuarter.setSharePeriods(sharePeriods);
            personQuarter.setNote(personQuarterRequest.getNote());
            personQuarterDao.createObj(personQuarter);
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
        Quarter quarter = quarterDao.retrieveByPeriod(convertStringToDate(period));
        return personQuarterDao.retrieveByPersonQuarter(person, quarter);
    }

    @Override
    public List<PersonQuarter> list() {
        return personQuarterDao.retrieveAll();
    }
}
