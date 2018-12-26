package com.project.shareholder.service;

import com.project.shareholder.dao.StageDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Stage;
import com.project.shareholder.request.StageRequest;
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
public class StageServiceImpl implements StageService {
    @Autowired
    private StageDao stageDao;

    @Override
    public Stage create(StageRequest stageRequest) throws DatabaseException {
        Stage stage = new Stage();
        stage.setName(stageRequest.getName());
        stage.setStockQuantity(stageRequest.getStockQuantity());
        stage.setDateStartedAt(convertStringToDate(stageRequest.getDateStartedAt()));
        stage.setDateEndedAt(convertStringToDate(stageRequest.getDateEndedAt()));
        stage.setNote(stageRequest.getNote());
        try {
            stageDao.createObj(stage);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return stage;
    }

    @Override
    public Stage update(StageRequest stageRequest) throws DatabaseException {
        Stage stage = new Stage();
        stage.setId(stageRequest.getId());
        stage.setName(stageRequest.getName());
        stage.setStockQuantity(stageRequest.getStockQuantity());
        stage.setDateStartedAt(convertStringToDate(stageRequest.getDateStartedAt()));
        stage.setDateEndedAt(convertStringToDate(stageRequest.getDateEndedAt()));
        stage.setNote(stageRequest.getNote());
        try {
            stageDao.updateObj(stage);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return stage;
    }

    @Override
    public Stage deactivate(String id) throws DatabaseException {
        Stage stage;
        try {
            stage = stageDao.retrieveById(UUID.fromString(id));
            if (null == stage) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            stageDao.deactivateObj(stage);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return stage;
    }

    @Override
    public String delete(String id) throws DatabaseException {
        Stage stage;
        try {
            stage = stageDao.retrieveById(UUID.fromString(id));
            if (null == stage) {
                throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
            }

            stageDao.deleteObj(stage);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return id;
    }

    @Override
    public Stage retrieveById(String id) throws NotFoundException {
        return stageDao.retrieveById(UUID.fromString(id));
    }

    @Override
    public Stage retrieveByPeriod(String period) throws NotFoundException {
        return stageDao.retrieveByPeriod(convertYearMonthStringToDate(period));
    }

    @Override
    public Stage retrieveByName(String name) throws NotFoundException {
        return stageDao.retrieveByName(name);
    }

    @Override
    public List<Stage> list() {
        return stageDao.retrieveAll();
    }
}
