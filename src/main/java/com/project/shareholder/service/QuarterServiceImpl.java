package com.project.shareholder.service;

import com.project.shareholder.dao.QuarterDao;
import com.project.shareholder.dao.StageDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Quarter;
import com.project.shareholder.model.Stage;
import com.project.shareholder.request.QuarterRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.project.shareholder.util.Utility.convertStringToDate;
import static com.project.shareholder.util.Utility.convertYearMonthStringToDate;

@Service
@Transactional
public class QuarterServiceImpl implements QuarterService {
    @Autowired
    private QuarterDao quarterDao;

    @Autowired
    private StageDao stageDao;

    // Create a new quarter
    @Override
    public Quarter create(QuarterRequest quarterRequest) throws DatabaseException {
        Quarter quarter = new Quarter();
        quarter.setName(quarterRequest.getName());
        quarter.setStockQuantity(quarterRequest.getStockQuantity());
        quarter.setStockQuantityLeft(quarterRequest.getStockQuantityLeft());
        quarter.setDateStartedAt(convertStringToDate(quarterRequest.getDateStartedAt()));
        quarter.setDateEndedAt(convertStringToDate(quarterRequest.getDateEndedAt()));
        quarter.setNote(quarterRequest.getNote());
        try {
            Stage stage = stageDao.retrieveById(quarterRequest.getStageId());
            quarter.setStage(stage);
            quarterDao.createObj(quarter);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return quarter;
    }

    // Update specific quarter
    @Override
    public Quarter update(QuarterRequest quarterRequest) throws DatabaseException {
        Quarter quarter = new Quarter();
        quarter.setId(quarterRequest.getId());
        quarter.setName(quarterRequest.getName());
        quarter.setStockQuantity(quarterRequest.getStockQuantity());
        quarter.setStockQuantityLeft(quarterRequest.getStockQuantityLeft());
        quarter.setDateStartedAt(convertStringToDate(quarterRequest.getDateStartedAt()));
        quarter.setDateEndedAt(convertStringToDate(quarterRequest.getDateEndedAt()));
        quarter.setNote(quarterRequest.getNote());
        try {
            quarterDao.updateObj(quarter);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return quarter;
    }

    // Deactivate quarter
    @Override
    public Quarter deactivate(String id) throws DatabaseException {
        Quarter quarter;
        try {
            quarter = quarterDao.retrieveById(UUID.fromString(id));
            if (null == quarter) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            quarterDao.deactivateObj(quarter);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return quarter;
    }

    // Delete quarter
    @Override
    public String delete(String id) throws DatabaseException {
        Quarter quarter;
        try {
            quarter = quarterDao.retrieveById(UUID.fromString(id));
            if (null == quarter) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            quarterDao.deleteObj(quarter);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return id;
    }

    // Retrieve quarter by id
    @Override
    public Quarter retrieveById(String id) throws NotFoundException {
        return quarterDao.retrieveById(UUID.fromString(id));
    }

    // Retrieve quarter by period
    @Override
    public Quarter retrieveByPeriod(String period) throws NotFoundException {
        return quarterDao.retrieveByPeriod(convertYearMonthStringToDate(period));
    }

    // Retrieve all quarters
    @Override
    public List<Quarter> list() {
        return quarterDao.retrieveAll();
    }
}
