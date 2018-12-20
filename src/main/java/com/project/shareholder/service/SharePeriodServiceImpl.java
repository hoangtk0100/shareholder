//package com.project.shareholder.service;
//
//import com.project.shareholder.dao.SharePeriodDao;
//import com.project.shareholder.exception.DatabaseException;
//import com.project.shareholder.model.Person;
//import com.project.shareholder.model.SharePeriod;
//import com.project.shareholder.model.Stage;
//import com.project.shareholder.request.SharePeriodRequest;
//import com.project.shareholder.util.Constants;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.UUID;
//
//public class SharePeriodServiceImpl implements SharePeriodService {
//    @Autowired
//    private SharePeriodDao sharePeriodDao;
//
//    @Override
//    public SharePeriod add(SharePeriodRequest personShareRequest) throws DatabaseException {
//        SharePeriod sharePeriod = new SharePeriod();
//        try {
//            sharePeriod.setNote("Add");
//            sharePeriod.setStockQuantity(personShareRequest.getStockQuantity());
//            sharePeriod.setPeriod(personShareRequest.getPeriod());
//            sharePeriodDao.createObj(sharePeriod);
//        } catch (Exception exception) {
//            throw new DatabaseException(Constants.DATABASE_MESSAGE);
//        }
//
//        return sharePeriod;
//    }
//
//    @Override
//    public SharePeriod subtract(SharePeriodRequest personShareRequest) throws DatabaseException {
//        SharePeriod sharePeriod = new SharePeriod();
//        try {
//            Person person = new Person();
//            person.setId(UUID.fromString(personShareRequest.getPerson().getId()));
//            sharePeriod.setPerson(person);
//
//            Stage stage = new Stage();
//            stage.setId(UUID.fromString(personShareRequest.getStage().getId()));
//            sharePeriod.setStage(stage);
//            sharePeriod.setNote("Subtract");
//            sharePeriod.setStockQuantity(personShareRequest.getStockQuantity());
//            sharePeriod.setPeriod(personShareRequest.getPeriod());
//            sharePeriodDao.createObj(sharePeriod);
//        } catch (Exception exception) {
//            throw new DatabaseException(Constants.DATABASE_MESSAGE);
//        }
//
//        return sharePeriod;
//    }
//}
