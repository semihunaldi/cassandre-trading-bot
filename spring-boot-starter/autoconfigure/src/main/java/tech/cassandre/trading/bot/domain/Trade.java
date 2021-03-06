package tech.cassandre.trading.bot.domain;

import tech.cassandre.trading.bot.dto.trade.OrderTypeDTO;
import tech.cassandre.trading.bot.util.base.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

import static javax.persistence.EnumType.STRING;
import static tech.cassandre.trading.bot.configuration.DatabaseAutoConfiguration.PRECISION;
import static tech.cassandre.trading.bot.configuration.DatabaseAutoConfiguration.SCALE;

/**
 * Trade (used to save data between restarts).
 */
@Entity
@Table(name = "TRADES")
public class Trade extends BaseDomain {

    /** An identifier set by the exchange that uniquely identifies the trade. */
    @Id
    @Column(name = "ID")
    private String id;

    /** The id of the order responsible for execution of this trade. */
    @Column(name = "ORDER_ID", updatable = false)
    private String orderId;

    /** A bid or a ask. */
    @Enumerated(STRING)
    @Column(name = "TYPE")
    private OrderTypeDTO type;

    /** Amount to be ordered / amount that was ordered. */
    @Column(name = "ORIGINAL_AMOUNT", precision = PRECISION, scale = SCALE)
    private BigDecimal originalAmount;

    /** The currency-pair. */
    @Column(name = "CURRENCY_PAIR")
    private String currencyPair;

    /** The price. */
    @Column(name = "PRICE", precision = PRECISION, scale = SCALE)
    private BigDecimal price;

    /** The timestamp of the trade. */
    @Column(name = "TIMESTAMP")
    private ZonedDateTime timestamp;

    /** The fee that was charged by the exchange for this trade. */
    @Column(name = "FEE_AMOUNT", precision = PRECISION, scale = SCALE)
    private BigDecimal feeAmount;

    /** The fee that was charged by the exchange for this trade. */
    @Column(name = "FEE_CURRENCY")
    private String feeCurrency;

    /**
     * Getter id.
     *
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter id.
     *
     * @param newId the id to set
     */
    public void setId(final String newId) {
        id = newId;
    }

    /**
     * Getter orderId.
     *
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Setter orderId.
     *
     * @param newOrderId the orderId to set
     */
    public void setOrderId(final String newOrderId) {
        orderId = newOrderId;
    }

    /**
     * Getter type.
     *
     * @return type
     */
    public OrderTypeDTO getType() {
        return type;
    }

    /**
     * Setter type.
     *
     * @param newType the type to set
     */
    public void setType(final OrderTypeDTO newType) {
        type = newType;
    }

    /**
     * Getter originalAmount.
     *
     * @return originalAmount
     */
    public BigDecimal getOriginalAmount() {
        return originalAmount;
    }

    /**
     * Setter originalAmount.
     *
     * @param newOriginalAmount the originalAmount to set
     */
    public void setOriginalAmount(final BigDecimal newOriginalAmount) {
        originalAmount = newOriginalAmount;
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
     * Getter price.
     *
     * @return price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Setter price.
     *
     * @param newPrice the price to set
     */
    public void setPrice(final BigDecimal newPrice) {
        price = newPrice;
    }

    /**
     * Getter timestamp.
     *
     * @return timestamp
     */
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Setter timestamp.
     *
     * @param newTimestamp the timestamp to set
     */
    public void setTimestamp(final ZonedDateTime newTimestamp) {
        timestamp = newTimestamp;
    }

    /**
     * Getter feeAmount.
     *
     * @return feeAmount
     */
    public BigDecimal getFeeAmount() {
        return feeAmount;
    }

    /**
     * Setter feeAmount.
     *
     * @param newFeeAmount the feeAmount to set
     */
    public void setFeeAmount(final BigDecimal newFeeAmount) {
        feeAmount = newFeeAmount;
    }

    /**
     * Getter feeCurrency.
     *
     * @return feeCurrency
     */
    public String getFeeCurrency() {
        return feeCurrency;
    }

    /**
     * Setter feeCurrency.
     *
     * @param newFeeCurrency the feeCurrency to set
     */
    public void setFeeCurrency(final String newFeeCurrency) {
        feeCurrency = newFeeCurrency;
    }

    @Override
    public final String toString() {
        return "Trade{"
                + " id='" + id + '\''
                + ", orderId='" + orderId + '\''
                + ", type=" + type
                + ", originalAmount=" + originalAmount
                + ", currencyPair='" + currencyPair + '\''
                + ", price=" + price
                + ", timestamp=" + timestamp
                + ", feeAmount=" + feeAmount
                + ", feeCurrency='" + feeCurrency + '\''
                + "} " + super.toString();
    }

}
