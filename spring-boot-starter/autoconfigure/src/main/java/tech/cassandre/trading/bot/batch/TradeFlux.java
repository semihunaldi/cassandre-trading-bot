package tech.cassandre.trading.bot.batch;

import tech.cassandre.trading.bot.domain.Trade;
import tech.cassandre.trading.bot.dto.trade.TradeDTO;
import tech.cassandre.trading.bot.repository.OrderRepository;
import tech.cassandre.trading.bot.repository.TradeRepository;
import tech.cassandre.trading.bot.service.TradeService;
import tech.cassandre.trading.bot.util.base.BaseExternalFlux;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Trade flux - push {@link TradeDTO}.
 */
public class TradeFlux extends BaseExternalFlux<TradeDTO> {

    /** Trade service. */
    private final TradeService tradeService;

    /** Order repository. */
    private final OrderRepository orderRepository;

    /** Trade repository. */
    private final TradeRepository tradeRepository;

    /**
     * Constructor.
     *
     * @param newTradeService    trade service
     * @param newOrderRepository order repository
     * @param newTradeRepository trade repository
     */
    public TradeFlux(final TradeService newTradeService,
                     final OrderRepository newOrderRepository,
                     final TradeRepository newTradeRepository) {
        this.tradeRepository = newTradeRepository;
        this.orderRepository = newOrderRepository;
        this.tradeService = newTradeService;
    }

    @Override
    protected final Set<TradeDTO> getNewValues() {
        getLogger().debug("TradeFlux - Retrieving new values");
        Set<TradeDTO> newValues = new LinkedHashSet<>();

        // Finding which trades has been updated.
        tradeService.getTrades().forEach(trade -> {
            getLogger().debug("TradeFlux - Treating trade : {}", trade.getId());
            final Optional<Trade> tradeInDatabase = tradeRepository.findById(trade.getId());
            if (tradeInDatabase.isEmpty() || !mapper.mapToTradeDTO(tradeInDatabase.get()).equals(trade)) {
                getLogger().info("TradeFlux - Trade {} has changed : {}", trade.getId(), trade);
                newValues.add(trade);
            }
        });
        getLogger().debug("TradeFlux - {} trade(s) updated", newValues.size());
        return newValues;
    }

    @Override
    public final void backupValue(final TradeDTO newValue) {
        tradeRepository.save(mapper.mapToTrade(newValue));
    }

}
