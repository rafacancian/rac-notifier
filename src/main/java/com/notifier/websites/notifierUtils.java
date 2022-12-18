package com.notifier.websites;

import java.time.Duration;
import java.time.LocalDateTime;

public class notifierUtils {

    public static void sleepForAWhile(String description) {
        System.out.println(LocalDateTime.now() + " | " + description);
        try {
            Thread.sleep(Duration.ofMinutes(1).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
