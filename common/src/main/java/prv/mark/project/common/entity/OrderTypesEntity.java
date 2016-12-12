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
 * Created by mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "ORDER_TYPES", schema = "STOCKS", catalog = "")
public class OrderTypesEntity implements Serializable {

    private static final long serialVersionUID = 7715998937751390384L;
    private Long id;
    private String orderType;
    private String description;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(
            name = "SEQ_ORDER_TYPES", sequenceName = "SEQ_ORDER_TYPES", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SEQ_ORDER_TYPES", strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

        OrderTypesEntity that = (OrderTypesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (orderType != null ? !orderType.equals(that.orderType) : that.orderType != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (orderType != null ? orderType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderTypesEntity{" +
                "id=" + id +
                ", orderType='" + orderType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
