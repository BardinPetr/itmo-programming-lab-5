package ru.bardinpetr.itmo.lab5.events.client;

import ru.bardinpetr.itmo.lab5.events.models.EventSet;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * EventSource's implementation which pools specified method for new events
 */
public class PoolingEventSource extends AbstractEventSource {

    private final Function<Instant, EventSet> poolFunction;
    private final ScheduledExecutorService executor;
    private Instant lastUpdateTime;

    /**
     * @param poolFunction function for retrieving events that happened before instant passed as param
     * @param poolDuration period for pooling
     */
    public PoolingEventSource(Function<Instant, EventSet> poolFunction, Duration poolDuration) {
        this.poolFunction = poolFunction;

        executor = Executors.newSingleThreadScheduledExecutor();
        executor.scheduleAtFixedRate(this::update, 0, poolDuration.toMillis(), TimeUnit.MILLISECONDS);

        lastUpdateTime = Instant.now();
    }

    private void update() {
        var beginTime = Instant.now();

        EventSet res = null;
        try {
            res = this.poolFunction.apply(lastUpdateTime);
        } catch (Exception ignored) {
        }

        if (res == null || res.getEvents().isEmpty())
            return;

        notifyListeners(res);

        lastUpdateTime = beginTime;
    }

    @Override
    public void stop() {
        executor.shutdownNow();
    }
}
