package cn.burningbright.poc.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @Author: chenguang.lin
 * @Date: 2024-02-21 16:14
 */
@Slf4j
@Component
public class EventListenerService {

    @EventListener
    public void handleArcherEvent(BaseEvent<Archer> archerEvent) {
        log.info("监听到 archerEvent:{}", archerEvent);
    }

    @EventListener
    public void handleKnightEvent(BaseEvent<Knight> knightEvent) {
        log.info("监听到 knightEvent:{}", knightEvent);
    }

    @EventListener
    public void handleInfantryEvent(BaseEvent<Infantry> infantryEvent) {
        log.info("监听到 infantryEvent:{}", infantryEvent);
    }

}