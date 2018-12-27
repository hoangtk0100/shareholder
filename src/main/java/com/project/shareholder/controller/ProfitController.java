package com.project.shareholder.controller;

import com.project.shareholder.exception.DatabaseException;
import com.project.shareholder.exception.NetworkException;
import com.project.shareholder.exception.NotFoundException;
import com.project.shareholder.model.*;
import com.project.shareholder.request.ProfitRequest;
import com.project.shareholder.request.SharePeriodRequest;
import com.project.shareholder.service.*;
import com.project.shareholder.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.*;

import static com.project.shareholder.util.Utility.convertStringToYearMonth;

@RestController
@RequestMapping("/profits")
public class ProfitController {
    @Autowired
    private ProfitService profitService;

    @Autowired
    private QuarterService quarterService;

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonQuarterService personQuarterService;

    @Autowired
    private SharePeriodService sharePeriodService;

    // Create new profit
    @PostMapping("/create")
    public Profit create(@Valid @RequestBody ProfitRequest profitRequest, Errors errors) throws DatabaseException {
        try {
            Utility.validateErrorsRequest(errors);
        } catch (NetworkException exception) {
            exception.printStackTrace();
        }
//
//        // Check current quarter
//        YearMonth currentYearMonth = YearMonth.now();
//        Quarter quarter = new Quarter();
//        try {
//            quarter = quarterService.retrieveByPeriod(currentYearMonth.toString());
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("----truoc quarter yearmonth: " );
//        YearMonth quarterStartedYearMonth = YearMonth.from(Instant.ofEpochMilli(quarter.getDateEndedAt().getTime())
//                .atZone(ZoneId.systemDefault())
//                .toLocalDate());
//        System.out.println("----quarter yearmonth: " + quarterStartedYearMonth);
//
//        // Check if current year month is startMonth of current quarter
//        if(quarterStartedYearMonth.equals(currentYearMonth)) {
//            // Set quarterly bonus | 15% current quarter's stock quantity
//            List<Person> persons = personService.list();
//            double percentBonusStock = 15;
//            Timestamp currentTime;
//            for(Person person : persons) {
//                System.out.println("----trc khi tao shareperiod ");
//                PersonQuarter personQuarter = new PersonQuarter();
//                try {
//                    personQuarter = personQuarterService.retrieveByPersonPeriod(person.getId().toString(), currentYearMonth.toString());
//                } catch (NotFoundException e) {
//                    e.printStackTrace();
//                }
//
//                currentTime = new Timestamp(new Date().getTime());
//                SharePeriodRequest sharePeriodRequest = new SharePeriodRequest();
//                sharePeriodRequest.setNote("Quarterly bonus");
//                sharePeriodRequest.setPeriod(currentTime);
//                sharePeriodRequest.setShareAction(ShareAction.BONUS);
//                sharePeriodRequest.setPersonQuarterId(personQuarter.getId());
//                sharePeriodRequest.setStockQuantity((percentBonusStock / 100) * quarter.getStockQuantity());
//                System.out.println("----trc khi bonus shareperiod ");
//                sharePeriodService.bonus(sharePeriodRequest);
//                System.out.println("----sau khi bonus shareperiod ");
//            }
//
//            // Set referring bonus | 10% referral's shares last quarter
//            Calendar now = Calendar.getInstance();
//            now.add(Calendar.MONTH, -1);
//            try {
//                Quarter lastQuarter = quarterService.retrieveByPeriod(now.get(Calendar.YEAR)
//                                                                    + "-" + now.get(Calendar.MONTH));
//                List<PersonQuarter> personQuarters = personQuarterService.retrieveByQuarterId(lastQuarter.getId().toString());
//                for(PersonQuarter personQuarter : personQuarters) {
//                    double bonusStock = 0;
//                    ArrayList<UUID> referralIds = personQuarter.getReferralIds();
//                    if(null != referralIds) {
//                        for (UUID referralId : referralIds) {
//                            Person person = personService.retrieveById(referralId.toString());
//                            bonusStock += (0.1 * person.getTotalStock());
//                        }
//                    }
//
//                    currentTime = new Timestamp(new Date().getTime());
//                    SharePeriodRequest sharePeriodRequest = new SharePeriodRequest();
//                    sharePeriodRequest.setNote("Referring bonus");
//                    sharePeriodRequest.setPeriod(currentTime);
//                    sharePeriodRequest.setShareAction(ShareAction.BONUS);
//                    sharePeriodRequest.setPersonQuarterId(personQuarter.getId());
//                    sharePeriodRequest.setStockQuantity(bonusStock);
//                    System.out.println("----trc khi referring bonus shareperiod ");
//                    sharePeriodService.bonus(sharePeriodRequest);
//                    System.out.println("----sau khi referring bonus shareperiod ");
//                }
//            } catch (NotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        System.out.println("----trc khi tao profit ");
        return profitService.create(profitRequest);
    }

    // Retrieve all activities
    @GetMapping("/all")
    public List<Profit> retrieveAll() {
        return profitService.list();
    }

    // Retrieve profit by id
    @GetMapping("/showById/{id}")
    public Profit retrieveById(@PathVariable String id) throws NotFoundException {
        return profitService.retrieveById(id);
    }

    // Retrieve profit by period
    @GetMapping("/showByPeriod/{period}")
    public Profit retrieveByPeriod(@PathVariable String period) throws NotFoundException {
        return profitService.retrieveByPeriod(period);
    }
}
