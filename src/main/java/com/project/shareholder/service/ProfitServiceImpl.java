package com.project.shareholder.service;

import com.project.shareholder.dao.PersonDao;
import com.project.shareholder.dao.PersonProfitDao;
import com.project.shareholder.dao.ProfitDao;
import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.Person;
import com.project.shareholder.model.PersonProfit;
import com.project.shareholder.model.Profit;
import com.project.shareholder.request.ProfitRequest;
import com.project.shareholder.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.*;

import static com.project.shareholder.util.Utility.convertStringToYearMonth;

@Service
@Transactional
public class ProfitServiceImpl implements ProfitService {
    @Autowired
    private ProfitDao profitDao;

    @Autowired
    private PersonDao personDao;

    @Autowired
    private PersonProfitDao personProfitDao;

    @Override
    public Profit create(ProfitRequest profitRequest) throws DatabaseException {
        Profit profit = new Profit();
        try {
            // Create new profit
            double totalProfit = profitRequest.getTotalProfit();
            profit.setTotalProfit(totalProfit);
            if (!profitRequest.getPeriod().trim().isEmpty()) {
                profit.setPeriod(convertStringToYearMonth(profitRequest.getPeriod()));
            } else {
                profit.setPeriod(YearMonth.now());
            }

            profitDao.createObj(profit);
            double totalStock = personDao.retrieveAllTotalStock();
            double percentProfit = totalProfit / totalStock;
            List<Person> persons = personDao.retrieveActivePersons();
            List<PersonProfit> personProfitsIndex = new List<PersonProfit>() {
                @Override
                public int size() {
                    return 0;
                }

                @Override
                public boolean isEmpty() {
                    return false;
                }

                @Override
                public boolean contains(Object o) {
                    return false;
                }

                @Override
                public Iterator<PersonProfit> iterator() {
                    return null;
                }

                @Override
                public Object[] toArray() {
                    return new Object[0];
                }

                @Override
                public <T> T[] toArray(T[] ts) {
                    return null;
                }

                @Override
                public boolean add(PersonProfit personProfit) {
                    return false;
                }

                @Override
                public boolean remove(Object o) {
                    return false;
                }

                @Override
                public boolean containsAll(Collection<?> collection) {
                    return false;
                }

                @Override
                public boolean addAll(Collection<? extends PersonProfit> collection) {
                    return false;
                }

                @Override
                public boolean addAll(int i, Collection<? extends PersonProfit> collection) {
                    return false;
                }

                @Override
                public boolean removeAll(Collection<?> collection) {
                    return false;
                }

                @Override
                public boolean retainAll(Collection<?> collection) {
                    return false;
                }

                @Override
                public void clear() {

                }

                @Override
                public PersonProfit get(int i) {
                    return null;
                }

                @Override
                public PersonProfit set(int i, PersonProfit personProfit) {
                    return null;
                }

                @Override
                public void add(int i, PersonProfit personProfit) {

                }

                @Override
                public PersonProfit remove(int i) {
                    return null;
                }

                @Override
                public int indexOf(Object o) {
                    return 0;
                }

                @Override
                public int lastIndexOf(Object o) {
                    return 0;
                }

                @Override
                public ListIterator<PersonProfit> listIterator() {
                    return null;
                }

                @Override
                public ListIterator<PersonProfit> listIterator(int i) {
                    return null;
                }

                @Override
                public List<PersonProfit> subList(int i, int i1) {
                    return null;
                }
            };
            int counter = 0;
            for (Person person : persons) {
                // Create person-profits {personProfit = [totalProfit / totalStock] * personTotalStock}
                PersonProfit personProfit = new PersonProfit();
                personProfit.setProfit(profit);
                double periodProfit = percentProfit * person.getTotalStock();
                personProfit.setPeriodProfit(periodProfit);
                personProfit.setPerson(person);
                personProfitDao.createObj(personProfit);

                // Update person's total profit
                if (null != person.getPersonProfits()) {
                    personProfitsIndex = person.getPersonProfits();
                }

                personProfitsIndex.add(personProfit);
                person.setPersonProfits(personProfitsIndex);
                person.setTotalProfit(person.getTotalProfit() + periodProfit);
                personDao.updateObj(person);

                // Remove personProfitsIndex data
                personProfitsIndex.clear();
            }
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

