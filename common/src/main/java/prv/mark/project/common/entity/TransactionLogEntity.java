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
import java.util.Date;

/**
 * JPA Entity for the TRANSACTION_LOG table.
 *
 * @author mlglenn.
 */
@Entity
@Table(name = "TRANSACTION_LOG")  //@Table(name = "TRANSACTION_LOG", schema = "STOCKS", catalog = "") TODO cleanup
public class TransactionLogEntity implements Serializable {

    private static final long serialVersionUID = -5939529108912508042L;
    private Long id;
    private Date logDateTime;
    private String transactionType;
    private String transactionData;

    protected TransactionLogEntity() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public TransactionLogEntity(Long id, Date logDateTime, String transactionType) {
        this.id = id;
        this.logDateTime = logDateTime;
        this.transactionType = transactionType;
    }

    public TransactionLogEntity(Long id, Date logDateTime, String transactionType, String transactionData) {
        this.id = id;
        this.logDateTime = logDateTime;
        this.transactionType = transactionType;
        this.transactionData = transactionData;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(
            name = "SEQ_TRANSACTION_LOG", sequenceName = "SEQ_TRANSACTION_LOG", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "SEQ_TRANSACTION_LOG", strategy = GenerationType.SEQUENCE)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOG_DATE_TIME", nullable = false)
    public Date getLogDateTime() {
        return logDateTime;
    }

    public void setLogDateTime(Date logDateTime) {
        this.logDateTime = logDateTime;
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
    @Column(name = "TRANSACTION_DATA", nullable = true, length = 500)
    public String getTransactionData() {
        return transactionData;
    }

    public void setTransactionData(String transactionData) {
        this.transactionData = transactionData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransactionLogEntity that = (TransactionLogEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (logDateTime != null ? !logDateTime.equals(that.logDateTime) : that.logDateTime != null) return false;
        if (transactionType != null ? !transactionType.equals(that.transactionType) : that.transactionType != null)
            return false;
        if (transactionData != null ? !transactionData.equals(that.transactionData) : that.transactionData != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (logDateTime != null ? logDateTime.hashCode() : 0);
        result = 31 * result + (transactionType != null ? transactionType.hashCode() : 0);
        result = 31 * result + (transactionData != null ? transactionData.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TransactionLogEntity{" +
                "id=" + id +
                ", logDateTime=" + logDateTime +
                ", transactionType='" + transactionType + '\'' +
                ", transactionData='" + transactionData + '\'' +
                '}';
    }
}
