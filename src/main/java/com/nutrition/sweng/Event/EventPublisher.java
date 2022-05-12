package com.nutrition.sweng.Event;

public interface EventPublisher {
    public boolean publishEvent(MealChangedEvent event);
    public boolean publishEvent(MealAddedEvent event);
}
