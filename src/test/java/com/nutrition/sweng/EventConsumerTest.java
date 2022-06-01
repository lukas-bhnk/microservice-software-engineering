package com.nutrition.sweng;

import com.nutrition.sweng.Event.UserRegisteredEvent;
import com.nutrition.sweng.Model.MealCategory;
import com.nutrition.sweng.Service.MealService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.context.annotation.Import;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Import(TestChannelBinderConfiguration.class)
public class EventConsumerTest {

    @Autowired
    private InputDestination inputDestination;

    @MockBean
    private MealService spyMealService;

    @Test
    void testConsumeMessage() {
        this.inputDestination.send(
                MessageBuilder
                .withPayload(new UserRegisteredEvent(1, "peter@test.com", "peter"))
                .build()
        );
        //checks if the EventConsumer class triggers the right service method on consumption of an event:
        verify(this.spyMealService, times(1)).createMeal(new Date(), MealCategory.BREAKFAST, "peter@test.com");
    }

}