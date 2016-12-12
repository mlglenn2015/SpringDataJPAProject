package prv.mark.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "STOCK_PRICE", schema = "STOCKS", catalog = "")
public class StockPriceEntity implements Serializable {

    private static final long serialVersionUID = 450747588229003896L;
    private Long id;
    private String stockSymbol;
    private BigDecimal currentPrice;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(
            name = "SEQ_STOCK_PRICE", sequenceName = "SEQ_STOCK_PRICE", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SEQ_STOCK_PRICE", strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "STOCK_SYMBOL", nullable = false, length = 10)
    public String getStockSymbol() {
        return stockSymbol;
    }

    public void setStockSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    @Basic
    @Column(name = "CURRENT_PRICE", nullable = false, precision = 2)
    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockPriceEntity that = (StockPriceEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (stockSymbol != null ? !stockSymbol.equals(that.stockSymbol) : that.stockSymbol != null) return false;
        if (currentPrice != null ? !currentPrice.equals(that.currentPrice) : that.currentPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (stockSymbol != null ? stockSymbol.hashCode() : 0);
        result = 31 * result + (currentPrice != null ? currentPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StockPriceEntity{" +
                "id=" + id +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", currentPrice=" + currentPrice +
                '}';
    }
}
