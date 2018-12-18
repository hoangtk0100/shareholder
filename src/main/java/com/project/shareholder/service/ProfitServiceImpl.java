package com.project.shareholder.service;

import com.project.shareholder.dao.ProfitDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Profit;
import com.project.shareholder.request.ProfitRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

import static com.project.shareholder.util.Utility.convertStringToYearMonth;

@Service
@Transactional
public class ProfitServiceImpl implements ProfitService {
    @Autowired
    private ProfitDao profitDao;

    @Override
    public Profit create(ProfitRequest profitRequest) throws DatabaseException {
        Profit profit = new Profit();
        try {
            YearMonth period = convertStringToYearMonth(profitRequest.getPeriod());
            profit.setTotalProfit(profitRequest.getTotalProfit());
            profit.setPeriod(period);
            profitDao.createObj(profit);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return profit;
    }

    @Override
    public Profit update(ProfitRequest profitRequest) throws DatabaseException {
        Profit profit = new Profit();
        try {
            profit.setId(profitRequest.getId());
            profit.setTotalProfit(profitRequest.getTotalProfit());
            YearMonth period = convertStringToYearMonth(profitRequest.getPeriod());
            profit.setPeriod(period);
            profitDao.updateObj(profit);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return profit;
    }

    @Override
    public Profit deactivate(String id) throws DatabaseException {
        Profit profit;
        try {
            profit = profitDao.retrieveById(UUID.fromString(id));
            profitDao.deactivateObj(profit);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return profit;
    }

    @Override
    public String delete(String id) throws DatabaseException {
        try {
            Profit profit = profitDao.retrieveById(UUID.fromString(id));
            profitDao.deleteObj(profit);
        } catch (Exception exception) {
            throw new DatabaseException(Constants.DATABASE_MESSAGE);
        }

        return id;
    }

    @Override
    public Profit retrieveById(String id) throws NotFoundException {
        Profit profit;
        try {
            profit = profitDao.retrieveById(UUID.fromString(id));
        } catch (Exception exception) {
            throw new NotFoundException(Constants.NOT_FOUND_MESSAGE);
        }

        return profit;
    }

    @Override
    public Profit retrieveByPeriod(String period) throws NotFoundException {
        return profitDao.retrieveByPeriod(convertStringToYearMonth(period));
    }

    @Override
    public List<Profit> list() {
        return profitDao.retrieveAll();
    }
}

