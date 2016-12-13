package prv.mark.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * JPA Entity for the STOCKS.STOCK_ORDER table.
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "STOCK_ORDER")  //@Table(name = "STOCK_ORDER", schema = "STOCKS", catalog = "")  TODO cleanup
public class StockOrderEntity implements Serializable {

    private static final long serialVersionUID = 4120673366782790956L;
    private Long id;
    private String stockSymbol;
    private String action;
    private Long quantity;
    private BigDecimal price;
    private Date orderDate;
    private String orderType;
    private String orderStatus;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(
            name = "SEQ_STOCK_ORDER", sequenceName = "SEQ_STOCK_ORDER", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SEQ_STOCK_ORDER", strategy = GenerationType.SEQUENCE)
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
    @Column(name = "ACTION", nullable = false, length = 4)
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Basic
    @Column(name = "QUANTITY", nullable = false, columnDefinition = "NUMBER(5,0) DEFAULT 1")
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Basic
    //@Column(name = "PRICE", nullable = false, precision = 2)
    @Column(name = "PRICE", nullable = false, columnDefinition = "NUMBER(10,2) DEFAULT 1")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ORDER_DATE", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "ORDER_TYPE", nullable = false, length = 25)
    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    @Basic
    @Column(name = "ORDER_STATUS", nullable = false, length = 25)
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockOrderEntity that = (StockOrderEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (stockSymbol != null ? !stockSymbol.equals(that.stockSymbol) : that.stockSymbol != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (orderType != null ? !orderType.equals(that.orderType) : that.orderType != null) return false;
        if (orderStatus != null ? !orderStatus.equals(that.orderStatus) : that.orderStatus != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (stockSymbol != null ? stockSymbol.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "StockOrderEntity{" +
                "id=" + id +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", action='" + action + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", orderType='" + orderType + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }
}
