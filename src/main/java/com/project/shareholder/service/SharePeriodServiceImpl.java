package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.PersonQuarterDao;
import com.project.shareholder.dao.SharePeriodDao;
import com.project.shareholder.exception.DatabaseException;
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

import java.util.List;

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
            PersonQuarter personQuarter = personQuarterDao.retrieveById(sharePeriodRequest.getPersonQuarterId());
            if (null == personQuarter) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            sharePeriod.setPersonQuarter(personQuarter);
            sharePeriod.setNote(sharePeriodRequest.getNote());
            sharePeriod.setShareAction(ShareAction.ADD);
            sharePeriod.setStockQuantity(sharePeriodRequest.getStockQuantity());
            sharePeriod.setPeriod(sharePeriodRequest.getPeriod());
            sharePeriodDao.createObj(sharePeriod);

            // Update stock quantity of person-quarter
            double stockQuantity = personQuarter.getStockQuantity() + sharePeriodRequest.getStockQuantity();
            personQuarter.setStockQuantity(stockQuantity);
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
    public SharePeriod subtract(SharePeriodRequest sharePeriodRequest) throws DatabaseException {
        SharePeriod sharePeriod = new SharePeriod();
        try {
            PersonQuarter personQuarter = personQuarterDao.retrieveById(sharePeriodRequest.getPersonQuarterId());
            if (null == personQuarter) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            sharePeriod.setPersonQuarter(personQuarter);
            sharePeriod.setNote(sharePeriodRequest.getNote());
            sharePeriod.setShareAction(ShareAction.SUBTRACT);
            sharePeriod.setStockQuantity(sharePeriodRequest.getStockQuantity());
            sharePeriod.setPeriod(sharePeriodRequest.getPeriod());
            sharePeriodDao.createObj(sharePeriod);

            // Update stock quantity of person-quarter
            double stockQuantity = personQuarter.getStockQuantity() - sharePeriodRequest.getStockQuantity();
            personQuarter.setStockQuantity(stockQuantity);
            personQuarterDao.updateObj(personQuarter);

            // Update person's total stock
            Person person = personQuarter.getPerson();
            person.setTotalStock(person.getTotalStock() - sharePeriodRequest.getStockQuantity());
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
            PersonQuarter personQuarter = personQuarterDao.retrieveById(sharePeriodRequest.getPersonQuarterId());
            if (null == personQuarter) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
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
