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
 * JPA Entity for the STOCKS.TRANSACTION_TYPES table.
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "TRANSACTION_TYPES")  //@Table(name = "TRANSACTION_TYPES", schema = "STOCKS", catalog = "") TODO cleanup
public class TransactionTypesEntity implements Serializable {

    private static final long serialVersionUID = 3197409016625788732L;
    private Long id;
    private String transactionType;
    private String description;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(
            name = "SEQ_TRANSACTION_TYPES", sequenceName = "SEQ_TRANSACTION_TYPES", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SEQ_TRANSACTION_TYPES", strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TRANSACTION_TYPE", nullable = false, length = 25)
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
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

        TransactionTypesEntity that = (TransactionTypesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (transactionType != null ? !transactionType.equals(that.transactionType) : that.transactionType != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionTypesEntity{" +
                "id=" + id +
                ", transactionType='" + transactionType + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
