package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.PersonQuarterDao;
import com.project.shareholder.dao.SharePeriodDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.InvalidArgumentException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonQuarter;
import com.project.shareholder.model.ShareAction;
import com.project.shareholder.model.SharePeriod;
import com.project.shareholder.request.SharePeriodRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.project.shareholder.util.Utility.isEmptyUUID;

@Service
@Transactional
public class SharePeriodServiceImpl implements SharePeriodService {
    @Autowired
    private PersonQuarterDao personQuarterDao;

    @Autowired
    private SharePeriodDao sharePeriodDao;

    @Autowired
    private PersonDao personDao;

    @Override
    public SharePeriod add(SharePeriodRequest sharePeriodRequest) throws DatabaseException {
        SharePeriod sharePeriod = new SharePeriod();
        try {
            // Check if person-quarter exists
            PersonQuarter personQuarter = new PersonQuarter();
            if(!isEmptyUUID(sharePeriodRequest.getPersonQuarterId())) {
                personQuarter = personQuarterDao.retrieveById(sharePeriodRequest.getPersonQuarterId());
                if (null == personQuarter) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }
            } else {
                if(isEmptyUUID(sharePeriodRequest.getPersonId())) {
                    throw new InvalidArgumentException(Constants.INVALID_ARGUMENT_MESSAGE);
                }

                UUID personId = sharePeriodRequest.getPersonId();
                Person person = personDao.retrieveById(personId);
                Date currentDate = new Date();
                personQuarter = personQuarterDao.retrieveByPersonPeriod(person, currentDate);
                if (null == personQuarter) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }
            }

            // Set note content default if it's empty
            double addedStockQuantity = sharePeriodRequest.getStockQuantity();
            String note = "";
            if (sharePeriodRequest.getNote().trim().isEmpty()) {
                note = String.format("Add %lf stocks", addedStockQuantity);
            } else {
                note = sharePeriodRequest.getNote();
            }

            // Create share-period content
            sharePeriod.setPersonQuarter(personQuarter);
            sharePeriod.setNote(note);
            sharePeriod.setShareAction(ShareAction.ADD);
            sharePeriod.setStockQuantity(addedStockQuantity);
            sharePeriod.setPeriod(sharePeriodRequest.getPeriod());
            sharePeriodDao.createObj(sharePeriod);

            // Update stock quantity of person-quarter
            personQuarter.setStockQuantity(personQuarter.getStockQuantity() + addedStockQuantity);
            personQuarterDao.updateObj(personQuarter);

            // Update person's total stock
            Person person = personQuarter.getPerson();
            person.setTotalStock(person.getTotalStock() + addedStockQuantity);
            personDao.updateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return sharePeriod;
    }

    @Override
    public SharePeriod subtract(SharePeriodRequest sharePeriodRequest) throws DatabaseException {
        SharePeriod sharePeriod = new SharePeriod();
        try {
            // Stock quantity left = currentStockQuantity*[100-subtractedStockPercent]/100 - 10
            // Check if person-quarter exists
            PersonQuarter personQuarter = new PersonQuarter();
            if(!isEmptyUUID(sharePeriodRequest.getPersonQuarterId())) {
                personQuarter = personQuarterDao.retrieveById(sharePeriodRequest.getPersonQuarterId());
                if (null == personQuarter) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }
            } else {
                if(isEmptyUUID(sharePeriodRequest.getPersonId())) {
                    throw new InvalidArgumentException(Constants.INVALID_ARGUMENT_MESSAGE);
                }

                UUID personId = sharePeriodRequest.getPersonId();
                Person person = personDao.retrieveById(personId);
                Date currentDate = new Date();
                personQuarter = personQuarterDao.retrieveByPersonPeriod(person, currentDate);
                if (null == personQuarter) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }
            }

            // Check if subtracted stock percent is valid
            double subtractedStockPercent = sharePeriodRequest.getStockQuantity();
            if (subtractedStockPercent < 0 || subtractedStockPercent > 30) {
                throw new InvalidArgumentException(Constants.INVALID_ARGUMENT_MESSAGE);
            }

            Person person = personQuarter.getPerson();
            double subtractedStockQuantity = (subtractedStockPercent / 100) * person.getTotalStock() + 10;
            sharePeriod.setStockQuantity(subtractedStockQuantity);

            // Set note content default if it's empty
            String note = "";
            if (sharePeriodRequest.getNote().trim().isEmpty()) {
                note = String.format("Subtract %lf stocks", subtractedStockQuantity);
            } else {
                note = sharePeriodRequest.getNote();
            }

            sharePeriod.setNote(note);
            sharePeriod.setPersonQuarter(personQuarter);
            sharePeriod.setShareAction(ShareAction.SUBTRACT);
            sharePeriod.setPeriod(sharePeriodRequest.getPeriod());
            sharePeriodDao.createObj(sharePeriod);

            // Update stock quantity of person-quarter
            double stockQuantity = personQuarter.getStockQuantity() - subtractedStockQuantity;
            personQuarter.setStockQuantity(stockQuantity);
            personQuarterDao.updateObj(personQuarter);

            // Update person's total stock
            person.setTotalStock(person.getTotalStock() - subtractedStockQuantity);
            personDao.updateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return sharePeriod;
    }

    @Override
    public SharePeriod bonus(SharePeriodRequest sharePeriodRequest) throws DatabaseException {
        SharePeriod sharePeriod = new SharePeriod();
        try {
            PersonQuarter personQuarter = new PersonQuarter();
            if(!isEmptyUUID(sharePeriodRequest.getPersonQuarterId())) {
                personQuarter = personQuarterDao.retrieveById(sharePeriodRequest.getPersonQuarterId());
                if (null == personQuarter) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }
            } else {
                if(isEmptyUUID(sharePeriodRequest.getPersonId())) {
                    throw new InvalidArgumentException(Constants.INVALID_ARGUMENT_MESSAGE);
                }

                UUID personId = sharePeriodRequest.getPersonId();
                Person person = personDao.retrieveById(personId);
                Date currentDate = new Date();
                personQuarter = personQuarterDao.retrieveByPersonPeriod(person, currentDate);
                if (null == personQuarter) {
                    throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
                }
            }

            sharePeriod.setPersonQuarter(personQuarter);
            sharePeriod.setNote(sharePeriodRequest.getNote());
            sharePeriod.setShareAction(ShareAction.BONUS);
            sharePeriod.setStockQuantity(sharePeriodRequest.getStockQuantity());
            sharePeriod.setPeriod(sharePeriodRequest.getPeriod());
            sharePeriodDao.createObj(sharePeriod);

            // Update stock quantity of person-quarter
            double bonusStock = personQuarter.getBonusStock() + sharePeriodRequest.getStockQuantity();
            personQuarter.setBonusStock(bonusStock);
            personQuarterDao.updateObj(personQuarter);

            // Update person's total stock
            Person person = personQuarter.getPerson();
            person.setTotalStock(person.getTotalStock() + sharePeriodRequest.getStockQuantity());
            personDao.updateObj(person);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return sharePeriod;
    }

    @Override
    public List<SharePeriod> list() {
        return sharePeriodDao.retrieveAll();
    }
}
