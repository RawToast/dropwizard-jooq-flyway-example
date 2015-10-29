package coop.poc.services;

import com.google.common.base.Optional;
import coop.poc.PocConfiguration;
import coop.poc.api.Saying;

import java.util.concurrent.atomic.AtomicLong;

public class IncrementingSayingService implements SayingService {

    private final PocConfiguration configuration;
    private final AtomicLong counter;

    public IncrementingSayingService(PocConfiguration configuration) {
        this.configuration = configuration;
        this.counter = configuration.getSayingCounter();
    }

    @Override
    public Saying generateNewSaying(Optional<String> name) {

        final String value = String.format(configuration.getTemplate(),
                name.or(configuration.getDefaultName()));

        return new Saying(counter.incrementAndGet(), value);
    }
}
