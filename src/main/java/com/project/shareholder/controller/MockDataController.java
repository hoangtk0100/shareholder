package com.project.shareholder.controller;

import com.project.shareholder.model.PersonQuarter;
import com.project.shareholder.model.ShareAction;
import com.project.shareholder.model.Stage;
import com.project.shareholder.request.*;
import com.project.shareholder.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/data")
public class MockDataController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private PersonService personService;

    @Autowired
    private StageService stageService;

    @Autowired
    private QuarterService quarterService;

    @Autowired
    private PersonQuarterService personQuarterService;

    @Autowired
    private PersonProfitService personProfitService;

    @Autowired
    private ProfitService profitService;

    @Autowired
    private SharePeriodService sharePeriodService;

    @Autowired
    private ActivityService activityService;

    @PostMapping("/mock")
    public boolean mock() {
        try {
            // Mock roles
            RoleRequest roleRequest = new RoleRequest();
            roleRequest.setName("USER");
            roleService.create(roleRequest);

            roleRequest.setName("ADMIN");
            roleService.create(roleRequest);
            // Mock stages
            for (int index = 0; index < 3; ++index) {
                StageRequest stageRequest= new StageRequest();
                stageRequest.setName("stage0" + index);
                stageRequest.setStockQuantity(100);
                stageRequest.setDateStartedAt("201" + index + 7 + "-01-01");
                stageRequest.setDateEndedAt("201" + index + 7 + "-12-30");
                stageService.create(stageRequest);
            }

            // Mock quarters
            String months[] = new String[]{"01-01", "03-31", "04-01", "06-30", "07-01", "09-30", "10-01", "12-31"};
            int monthIndex = 0;
            Stage stage01 = stageService.retrieveByName("stage01");
            for (int index = 0; index < 4; ++index) {
                QuarterRequest quarterRequest = new QuarterRequest();
                quarterRequest.setStageId(stage01.getId());
                quarterRequest.setStockQuantity(50);
                quarterRequest.setName("quarter0" + index);
                quarterRequest.setDateStartedAt("2018-" + months[monthIndex++]);
                quarterRequest.setDateEndedAt("2018-" + months[monthIndex++]);
                quarterService.create(quarterRequest);
            }

            // Mock person
            for (int index = 0; index < 4; ++index) {
                PersonRequest personRequest = new PersonRequest();
                personRequest.setFullName("ahihi");
                personRequest.setUsername("10" + index);
                personRequest.setPassword("10" + index);
                personRequest.setEmail("email0" + index);
                personRequest.setPhoneNumber("09090910" + index);
                personRequest.setBirthday("2000-10-20");
                personRequest.setAddress("address");
                personRequest.setPersonalId("10" + index);
                personRequest.setAvatar("");
                personRequest.setGender(true);
                personRequest.setTotalStock(0);
                personRequest.setTotalProfit(0);
                if (index % 2 != 0) {
                    personRequest.setRoleName("ADMIN");
                    personRequest.setReferrerUsername("10" + (index - 1));
                } else {
                    personRequest.setRoleName("USER");
                    personRequest.setReferrerUsername("");
                }
                personService.create(personRequest);
            }

            // Mock share period (substract)
            PersonQuarter personQuarter01 = personQuarterService.list().get(0);
            Timestamp currentTime = new Timestamp(new Date().getTime());
            SharePeriodRequest sharePeriodRequest = new SharePeriodRequest();
            sharePeriodRequest.setPersonQuarterId(personQuarter01.getId());
            sharePeriodRequest.setPeriod(currentTime);
            sharePeriodRequest.setShareAction(ShareAction.ADD);
            sharePeriodRequest.setStockQuantity(15);
            sharePeriodRequest.setNote("Subtracted");
            sharePeriodService.subtract(sharePeriodRequest);

            // Mock profit with specific year month
            ProfitRequest profitRequest = new ProfitRequest();
            profitRequest.setTotalProfit(200);
            profitRequest.setPeriod(("2018-02"));
            profitService.create(profitRequest);

            // Mock profit with current year month
            ProfitRequest secondProfitRequest = new ProfitRequest();
            secondProfitRequest.setTotalProfit(1000);
            secondProfitRequest.setPeriod((""));
            profitService.create(secondProfitRequest);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        return true;
    }
}
