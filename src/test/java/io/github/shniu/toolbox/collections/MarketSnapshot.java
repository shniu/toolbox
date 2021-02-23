package io.github.shniu.toolbox.collections;

/**
 * @author niushaohan
 * @date 2021/2/7 13
 */
public final class MarketSnapshot {
    private long instrumentId;
    // buy, The bid price refers to the highest price a buyer will pay for a security.
    private long bestBid;
    // sell, The ask price refers to the lowest price a seller will accept for a security
    private long bestAsk;

    public static MarketSnapshot create(long instrumentId, long bestBid, long bestAsk) {
        MarketSnapshot marketSnapshot = new MarketSnapshot();
        marketSnapshot.setInstrumentId(instrumentId);
        marketSnapshot.setBestBid(bestBid);
        marketSnapshot.setBestAsk(bestAsk);
        return marketSnapshot;
    }

    public long getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(long instrumentId) {
        this.instrumentId = instrumentId;
    }

    public long getBestBid() {
        return bestBid;
    }

    public void setBestBid(long bestBid) {
        this.bestBid = bestBid;
    }

    public long getBestAsk() {
        return bestAsk;
    }

    public void setBestAsk(long bestAsk) {
        this.bestAsk = bestAsk;
    }

    @Override
    public String toString() {
        return "MarketSnapshot{" +
                "instrumentId=" + instrumentId +
                ", bestBid=" + bestBid +
                ", bestAsk=" + bestAsk +
                '}';
    }
}
