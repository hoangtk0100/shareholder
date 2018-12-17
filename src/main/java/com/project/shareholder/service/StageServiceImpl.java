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

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class StageServiceImpl implements StageService {
    @Autowired
    private StageDao stageDao;

    @Override
    public Stage create(StageRequest stageRequest) throws DatabaseException {
        Stage stage = new Stage();
        stage.setName(stageRequest.getName());
        stage.setQuantity(stageRequest.getQuantity());
        stage.setDateStartedAt(stageRequest.getDateStartedAt());
        stage.setDateEndedAt(stageRequest.getDateEndedAt());
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
        stage.setId(UUID.fromString(stageRequest.getId()));
        stage.setName(stageRequest.getName());
        stage.setQuantity(stageRequest.getQuantity());
        stage.setDateStartedAt(stageRequest.getDateStartedAt());
        stage.setDateEndedAt(stageRequest.getDateEndedAt());
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
    public Stage retrieveByPeriod(YearMonth period) throws NotFoundException {
        return stageDao.retrieveByPeriod(period);
    }

    @Override
    public List<Stage> list() {
        return stageDao.retrieveAll();
    }
}
