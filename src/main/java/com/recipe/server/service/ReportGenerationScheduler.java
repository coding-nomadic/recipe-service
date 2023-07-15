package com.recipe.server.service;

import com.recipe.server.utils.ClientApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;

@Component
@Slf4j
public class ReportGenerationScheduler {

    @Value("${token.service.email}")
    private String emailUrl;

    @Autowired
    private AnalyticsService analyticsService;

    @Scheduled(cron = "0 0 0 * * ?") // Runs once per day at midnight
    public void generateReports() {
        String emailTo = "";
        String emailText = "";
        String url = emailUrl + "?emailTo=" + emailTo + "&emailText=" + emailText;
        try {
            HttpResponse<String> httpResponse = ClientApiUtils.callExternalApi(url);
            if (httpResponse.statusCode() == 200) {
                log.info("successfully sent email to the users : " + emailTo);
            }
        } catch (Exception e) {
            log.error("Exception occurred while calling external API : " + e.getLocalizedMessage());
        }
    }
}
