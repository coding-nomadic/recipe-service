package com.recipe.server.service;

import com.recipe.server.entity.Recipe;
import com.recipe.server.entity.User;
import com.recipe.server.exceptions.ResourceNotFoundException;
import com.recipe.server.repository.UserRepository;
import com.recipe.server.utils.ClientApiUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;
import java.util.List;

@Component
@Slf4j
public class ReportGenerationScheduler {

    private final String emailText = "Your recipe has been chosen as the Recipe of the week !";
    @Value("${token.email.url}")
    private String emailUrl;

    @Autowired
    private AnalyticsService analyticsService;

    @Autowired
    private UserRepository userRepository;

    @Scheduled(cron = "0 0 0 1/14 * ?") // Runs at midnight (00:00:00) every 14 days
    public void generateReports() {
        List<Recipe> popularRecipes = analyticsService.generatePopularRecipesReport();
        for (Recipe recipe : popularRecipes) {
            User user = userRepository.findById(recipe.getId()).orElseThrow(() -> new ResourceNotFoundException("User Id not found", "102"));
            String emailTo = user.getEmail();
            String url = emailUrl + "?emailTo=" + user.getEmail() + "&emailText=" + emailText;
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
}
