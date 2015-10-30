package coop.poc.services;

import com.google.common.base.Optional;
import coop.poc.api.Saying;

/**
 * Demo service interface
 */
public interface SayingService {
    Saying generateNewSaying(Optional<String> name);
}
