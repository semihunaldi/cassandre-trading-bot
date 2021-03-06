package tech.cassandre.trading.bot.domain;

import tech.cassandre.trading.bot.dto.position.PositionStatusDTO;
import tech.cassandre.trading.bot.util.base.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static tech.cassandre.trading.bot.configuration.DatabaseAutoConfiguration.PRECISION;
import static tech.cassandre.trading.bot.configuration.DatabaseAutoConfiguration.SCALE;

/**
 * Position (used to save data between restarts).
 */
@Entity
@Table(name = "POSITIONS")
public class Position extends BaseDomain {

    /** An identifier that uniquely identifies the position. */
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /** Position . */
    @Enumerated(STRING)
    @Column(name = "STATUS")
    private PositionStatusDTO status;

    /** The currency-pair. */
    @Column(name = "CURRENCY_PAIR")
    private String currencyPair;

    /** Amount to be ordered / amount that was ordered. */
    @Column(name = "AMOUNT", precision = PRECISION, scale = SCALE)
    private BigDecimal amount;

    /** Position rules - stop gain percentage. */
    @Column(name = "RULES_STOP_GAIN_PERCENTAGE")
    private Float stopGainPercentageRule;

    /** Position rules - stop loss percentage. */
    @Column(name = "RULES_STOP_LOSS_PERCENTAGE")
    private Float stopLossPercentageRule;

    /** The order that opened the position. */
    @OneToOne(fetch = EAGER, cascade = ALL)
    @JoinColumn(name = "OPENING_ORDER_ID")
    private Order openingOrder;

    /** The order that closed the position. */
    @OneToOne(fetch = EAGER, cascade = ALL)
    @JoinColumn(name = "CLOSING_ORDER_ID")
    private Order closingOrder;

    /** Lowest price. */
    @Column(name = "LOWEST_PRICE", precision = PRECISION, scale = SCALE)
    private BigDecimal lowestPrice;

    /** Highest price. */
    @Column(name = "HIGHEST_PRICE", precision = PRECISION, scale = SCALE)
    private BigDecimal highestPrice;

    /** Latest price. */
    @Column(name = "LATEST_PRICE", precision = PRECISION, scale = SCALE)
    private BigDecimal latestPrice;

    /** Strategy. */
    @ManyToOne(fetch = EAGER)
    @JoinColumn(name = "STRATEGY_ID", updatable = false)
    private Strategy strategy;

    /**
     * Getter id.
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter id.
     *
     * @param newId the id to set
     */
    public void setId(final long newId) {
        id = newId;
    }

    /**
     * Getter status.
     *
     * @return status
     */
    public PositionStatusDTO getStatus() {
        return status;
    }

    /**
     * Setter status.
     *
     * @param newStatus the status to set
     */
    public void setStatus(final PositionStatusDTO newStatus) {
        status = newStatus;
    }

    /**
     * Getter currencyPair.
     *
     * @return currencyPair
     */
    public String getCurrencyPair() {
        return currencyPair;
    }

    /**
     * Setter currencyPair.
     *
     * @param newCurrencyPair the currencyPair to set
     */
    public void setCurrencyPair(final String newCurrencyPair) {
        currencyPair = newCurrencyPair;
    }

    /**
     * Getter amount.
     *
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Setter amount.
     *
     * @param newAmount the amount to set
     */
    public void setAmount(final BigDecimal newAmount) {
        amount = newAmount;
    }

    /**
     * Getter rulesStopGainPercentage.
     *
     * @return rulesStopGainPercentage
     */
    public Float getStopGainPercentageRule() {
        return stopGainPercentageRule;
    }

    /**
     * Setter rulesStopGainPercentage.
     *
     * @param newRulesStopGainPercentage the rulesStopGainPercentage to set
     */
    public void setStopGainPercentageRule(final Float newRulesStopGainPercentage) {
        stopGainPercentageRule = newRulesStopGainPercentage;
    }

    /**
     * Getter rulesStopLossPercentage.
     *
     * @return rulesStopLossPercentage
     */
    public Float getStopLossPercentageRule() {
        return stopLossPercentageRule;
    }

    /**
     * Setter rulesStopLossPercentage.
     *
     * @param newRulesStopLossPercentage the rulesStopLossPercentage to set
     */
    public void setStopLossPercentageRule(final Float newRulesStopLossPercentage) {
        stopLossPercentageRule = newRulesStopLossPercentage;
    }

    /**
     * Getter openingOrder.
     *
     * @return openOrder
     */
    public Order getOpeningOrder() {
        return openingOrder;
    }

    /**
     * Setter openingOrder.
     *
     * @param newOpenOrder the openOrder to set
     */
    public void setOpeningOrder(final Order newOpenOrder) {
        openingOrder = newOpenOrder;
    }

    /**
     * Getter closingOrder.
     *
     * @return closeOrder
     */
    public Order getClosingOrder() {
        return closingOrder;
    }

    /**
     * Setter closingOrder.
     *
     * @param newCloseOrder the closeOrder to set
     */
    public void setClosingOrder(final Order newCloseOrder) {
        closingOrder = newCloseOrder;
    }

    /**
     * Getter lowestPrice.
     *
     * @return lowestPrice
     */
    public BigDecimal getLowestPrice() {
        return lowestPrice;
    }

    /**
     * Setter lowestPrice.
     *
     * @param newLowestPrice the lowestPrice to set
     */
    public void setLowestPrice(final BigDecimal newLowestPrice) {
        lowestPrice = newLowestPrice;
    }

    /**
     * Getter highestPrice.
     *
     * @return highestPrice
     */
    public BigDecimal getHighestPrice() {
        return highestPrice;
    }

    /**
     * Setter highestPrice.
     *
     * @param newHighestPrice the highestPrice to set
     */
    public void setHighestPrice(final BigDecimal newHighestPrice) {
        highestPrice = newHighestPrice;
    }

    /**
     * Getter latestPrice.
     *
     * @return latestPrice
     */
    public BigDecimal getLatestPrice() {
        return latestPrice;
    }

    /**
     * Setter latestPrice.
     *
     * @param newLatestPrice the latestPrice to set
     */
    public void setLatestPrice(final BigDecimal newLatestPrice) {
        latestPrice = newLatestPrice;
    }

    /**
     * Getter strategy.
     *
     * @return strategy
     */
    public Strategy getStrategy() {
        return strategy;
    }

    /**
     * Setter strategy.
     *
     * @param newStrategy the strategy to set
     */
    public void setStrategy(final Strategy newStrategy) {
        strategy = newStrategy;
    }

    @Override
    public final String toString() {
        return "Position{"
                + " id=" + id
                + ", status=" + status
                + ", currencyPair='" + currencyPair + '\''
                + ", amount=" + amount
                + ", stopGainPercentageRule=" + stopGainPercentageRule
                + ", stopLossPercentageRule=" + stopLossPercentageRule
                + ", openingOrder=" + openingOrder
                + ", closingOrder=" + closingOrder
                + ", lowestPrice=" + lowestPrice
                + ", highestPrice=" + highestPrice
                + ", latestPrice=" + latestPrice
                + ", strategy=" + strategy
                + "}";
    }

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return id == position.id;
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id);
    }

}
