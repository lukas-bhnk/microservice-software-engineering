package com.nutrition.sweng;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutrition.sweng.Event.EventPublisher;
import com.nutrition.sweng.Event.MealChangedEvent;
import com.nutrition.sweng.Model.MealCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Import(TestChannelBinderConfiguration.class)
public class EventPublisherTests {

    @Autowired
    private OutputDestination outputDestination;

    @Autowired
    private EventPublisher eventPublisher;


    @Test
    void testPublishEvent() throws Exception {
        var event = new MealChangedEvent(20L, new Date(), MealCategory.BREAKFAST, 4, 1.0, 10.0, 12.0, 121L, Collections.emptySet());
        this.eventPublisher.publishEvent(event);
        var receivedMessage = this.outputDestination.receive();
        assertNotNull(receivedMessage);
        assertTrue(receivedMessage.getPayload().length > 0);
        var payloadObject = (new ObjectMapper()).readValue(receivedMessage.getPayload(), MealChangedEvent.class);
        assertEquals(event, payloadObject);
    }
}
