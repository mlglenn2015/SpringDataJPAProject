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

/**
 * JPA Entity for the STOCKS.ORDER_STATUS table.
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "ORDER_STATUS")   //@Table(name = "ORDER_STATUS", schema = "STOCKS", catalog = "")  TODO cleanup
public class OrderStatusEntity implements Serializable {

    private static final long serialVersionUID = 7729506380238566594L;
    private Long id;
    private String orderStatus;
    private String description;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(
            name = "SEQ_ORDER_STATUS", sequenceName = "SEQ_ORDER_STATUS", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ORDER_STATUS", strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ORDER_STATUS", nullable = false, length = 25)
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Basic
    @Column(name = "DESCRIPTION", nullable = false, length = 100)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderStatusEntity that = (OrderStatusEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderStatus != null ? !orderStatus.equals(that.orderStatus) : that.orderStatus != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderStatus != null ? orderStatus.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderStatusEntity{" +
                "id=" + id +
                ", orderStatus='" + orderStatus + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
