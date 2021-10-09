package com.binance.client;

import com.alibaba.fastjson.JSONObject;
import com.binance.client.impl.BinanceApiInternalFactory;
import com.binance.client.model.ResponseResult;
import com.binance.client.model.market.*;
import com.binance.client.model.enums.*;
import com.binance.client.model.trade.*;

import java.util.List;

/**
 * Synchronous request interface, invoking Binance RestAPI via synchronous
 * method.<br>
 * All methods in this interface will be blocked until the RestAPI response.
 * <p>
 * If the invoking failed or timeout, the
 * {@link com.binance.client.exception.BinanceApiException} will be thrown.
 */
public interface SyncRequestClient {

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create() {
        return create("", "", new RequestOptions());
    }

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @param apiKey    The public key applied from binance.
     * @param secretKey The private key applied from binance.
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create(String apiKey, String secretKey) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, new RequestOptions());
    }

    /**
     * Create the synchronous client. All interfaces defined in synchronous client
     * are implemented by synchronous mode.
     *
     * @param apiKey    The public key applied from binance.
     * @param secretKey The private key applied from binance.
     * @param options   The request option.
     * @return The instance of synchronous client.
     */
    static SyncRequestClient create(String apiKey, String secretKey, RequestOptions options) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, options);
    }


    /**
     * Fetch current exchange trading rules and symbol information.
     *
     * @return Current exchange trading rules and symbol information.
     */
    ExchangeInformation getExchangeInformation();

    /**
     * Fetch order book.
     *
     * @return Order book.
     */
    OrderBook getOrderBook(String symbol, Integer limit);

    /**
     * Get recent trades.
     *
     * @return Recent trades.
     */
    List<Trade> getRecentTrades(String symbol, Integer limit);

    /**
     * Get old Trade.
     *
     * @return Old trades.
     */
    List<Trade> getOldTrades(String symbol, Integer limit, Long fromId);

    /**
     * Get compressed, aggregate trades.
     *
     * @return Aggregate trades.
     */
    List<AggregateTrade> getAggregateTrades(String symbol, Long fromId, Long startTime, Long endTime, Integer limit);

    /**
     * Get kline/candlestick bars for a symbol.
     *
     * @return Kline/candlestick bars for a symbol.
     */
    List<Candlestick> getCandlestick(String symbol, CandlestickInterval interval, Long startTime, Long endTime, Integer limit);

    /**
     * Get mark price for a symbol.
     *
     * @return Mark price for a symbol.
     */
    List<MarkPrice> getMarkPrice(String symbol);

    /**
     * Get funding rate history.
     *
     * @return funding rate history.
     */
    List<FundingRate> getFundingRate(String symbol, Long startTime, Long endTime, Integer limit);

    /**
     * Get 24 hour rolling window price change statistics.
     *
     * @return 24 hour rolling window price change statistics.
     */
    List<PriceChangeTicker> get24hrTickerPriceChange(String symbol);

    /**
     * Get latest price for a symbol or symbols.
     *
     * @return Latest price for a symbol or symbols.
     */
    List<SymbolPrice> getSymbolPriceTicker(String symbol);

    /**
     * Get best price/qty on the order book for a symbol or symbols.
     *
     * @return Best price/qty on the order book for a symbol or symbols.
     */
    List<SymbolOrderBook> getSymbolOrderBookTicker(String symbol);

    /**
     * Get all liquidation orders.
     *
     * @return All liquidation orders.
     */
    List<LiquidationOrder> getLiquidationOrders(String symbol, Long startTime, Long endTime, Integer limit);

    /**
     * Place new orders
     * @param batchOrders
     * @return
     */
    List<Object> postBatchOrders(String batchOrders, Long timestamp);
    
    /**
     * Send in a new order.
     *
     * @return Order.
     */
    Order postOrder(String symbol, OrderSide side, PositionSide positionSide, OrderType orderType,
            TimeInForce timeInForce, String quantity, String price, String reduceOnly,
            String newClientOrderId, String stopPrice, WorkingType workingType, NewOrderRespType newOrderRespType,
            String closePosition, Long timestamp);

    /**
     * Cancel an active order.
     *
     * @return Order.
     */
    Order cancelOrder(String symbol, Long orderId, String origClientOrderId, Long timestamp);

    /**
     * Cancel all open orders.
     *
     * @return ResponseResult.
     */
    ResponseResult cancelAllOpenOrder(String symbol, Long timestamp);

    /**
     * Batch cancel orders.
     *
     * @return Order.
     */
    List<Object> batchCancelOrders(String symbol, String orderIdList, String origClientOrderIdList, Long timestamp);

    /**
     * Switch position side. (true == dual, false == both)
     *
     * @return ResponseResult.
     */
    ResponseResult changePositionSide(boolean dual, Long timestamp);

    /**
     * Change margin type (ISOLATED, CROSSED)
     * @param symbolName
     * @param marginType
     * @return
     */
    ResponseResult changeMarginType(String symbolName, String marginType, Long timestamp);

    /**
     * add isolated position margin
     * @param symbolName
     * @param type
     * @param amount
     * @param positionSide SHORT, LONG, BOTH
     * @return
     */
    JSONObject addIsolatedPositionMargin(String symbolName, int type, String amount, PositionSide positionSide, Long timestamp);

    /**
     *  get position margin history
     * @param symbolName
     * @param type
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    List<WalletDeltaLog> getPositionMarginHistory(String symbolName, int type, long startTime, long endTime, int limit);

    /**
     * Get if changed to HEDGE mode. (true == hedge mode, false == one-way mode)
     *
     * @return ResponseResult.
     */
    JSONObject getPositionSide(Long timestamp);

    /**
     * Check an order's status.
     *
     * @return Order status.
     */
    Order getOrder(String symbol, Long orderId, String origClientOrderId, Long timestamp);

    /**
     * Get all open orders on a symbol. Careful when accessing this with no symbol.
     *
     * @return Open orders.
     */
    List<Order> getOpenOrders(String symbol, Long timestamp);

    /**
     * Get all account orders; active, canceled, or filled.
     *
     * @return All orders.
     */
    List<Order> getAllOrders(String symbol, Long orderId, Long startTime, Long endTime, Integer limit, Long timestamp);
  
    /**
     * Get account balances.
     *
     * @return Balances.
     */
    List<AccountBalance> getBalance(Long timestamp);
  
    /**
     * Get current account information.
     *
     * @return Current account information.
     */
    AccountInformation getAccountInformation(Long timestamp);
  
    /**
     * Change initial leverage.
     *
     * @return Leverage.
     */
    Leverage changeInitialLeverage(String symbol, Integer leverage, Long timestamp);

    /**
     * Get position.
     *
     * @return Position.
     */
    List<PositionRisk> getPositionRisk(String symbol, Long timestamp);

    /**
     * Get trades for a specific account and symbol.
     *
     * @return Trades.
     */
    List<MyTrade> getAccountTrades(String symbol, Long startTime, Long endTime, Long fromId, Integer limit, Long timestamp);

    /**
     * Get income history.
     *
     * @return Income history.
     */
    List<Income> getIncomeHistory(String symbol, IncomeType incomeType, Long startTime, Long endTime, Integer limit, Long timestamp);

    /**
     * Start user data stream.
     *
     * @return listenKey.
     */
    String startUserDataStream(Long timestamp);

    /**
     * Keep user data stream.
     *
     * @return null.
     */
    String keepUserDataStream(String listenKey, Long timestamp);

    /**
     * Close user data stream.
     *
     * @return null.
     */
    String closeUserDataStream(String listenKey, Long timestamp);

    /**
     * Open Interest Stat (MARKET DATA)
     *
     * @return Open Interest Stat.
     */
    List<OpenInterestStat> getOpenInterestStat(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit, Long timestamp);

    /**
     * Top Trader Long/Short Ratio (Accounts) (MARKET DATA)
     *
     * @return Top Trader Long/Short Ratio (Accounts).
     */
    List<CommonLongShortRatio> getTopTraderAccountRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit, Long timestamp);

    /**
     * Top Trader Long/Short Ratio (Positions) (MARKET DATA)
     *
     * @return Top Trader Long/Short Ratio (Positions).
     */
    List<CommonLongShortRatio> getTopTraderPositionRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit, Long timestamp);

    /**
     * Long/Short Ratio (MARKET DATA)
     *
     * @return global Long/Short Ratio. 
     */
    List<CommonLongShortRatio> getGlobalAccountRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit, Long timestamp);

    /**
     * Taker Long/Short Ratio (MARKET DATA)
     *
     * @return Taker Long/Short Ratio. 
     */
    List<TakerLongShortStat> getTakerLongShortRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit, Long timestamp);

}