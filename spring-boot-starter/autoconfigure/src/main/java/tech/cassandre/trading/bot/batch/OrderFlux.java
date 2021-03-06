package tech.cassandre.trading.bot.batch;

import tech.cassandre.trading.bot.domain.Order;
import tech.cassandre.trading.bot.dto.trade.OrderDTO;
import tech.cassandre.trading.bot.repository.OrderRepository;
import tech.cassandre.trading.bot.service.TradeService;
import tech.cassandre.trading.bot.util.base.BaseExternalFlux;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Order flux - push {@link OrderDTO}.
 */
public class OrderFlux extends BaseExternalFlux<OrderDTO> {

    /** Trade service. */
    private final TradeService tradeService;

    /** Order repository. */
    private final OrderRepository orderRepository;

    /**
     * Constructor.
     *
     * @param newTradeService    trade service
     * @param newOrderRepository order repository
     */
    public OrderFlux(final TradeService newTradeService, final OrderRepository newOrderRepository) {
        this.tradeService = newTradeService;
        this.orderRepository = newOrderRepository;
    }

    @Override
    protected final Set<OrderDTO> getNewValues() {
        getLogger().debug("OrderFlux - Retrieving new values");
        Set<OrderDTO> newValues = new LinkedHashSet<>();

        // Finding which order has been updated.
        tradeService.getOrders().forEach(order -> {
            System.out.println("=> " + order);
            getLogger().debug("OrderFlux - Treating order : {}", order.getId());
            final Optional<Order> orderInDatabase = orderRepository.findById(order.getId());
            // If it does not exist or something changed, we do it.
            if (orderInDatabase.isEmpty() || !mapper.mapToOrderDTO(orderInDatabase.get()).equals(order)) {
                getLogger().debug("OrderFlux - Order {} has changed : {}", order.getId(), order);
                newValues.add(order);
            }
        });
        getLogger().debug("OrderFlux - {} order(s) updated", newValues.size());
        return newValues;
    }

    @Override
    public final void backupValue(final OrderDTO newValue) {
        final Order valueToSave = mapper.mapToOrder(newValue);
        // We retrieve value already in database.
        final Optional<Order> orderInDatabase = orderRepository.findById(newValue.getId());
        orderInDatabase.ifPresent(order -> {
            // We set the strategy.
            valueToSave.setStrategy(order.getStrategy());
            // We add the trades we already have.
            orderInDatabase.get().getTrades().forEach(trade -> valueToSave.getTrades().add(trade));
        });
        // We save.
        orderRepository.save(valueToSave);
    }

}
