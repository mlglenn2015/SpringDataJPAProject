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
 * JPA Entity for the STOCKS.APPLICATION_MESSAGES table.
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "APPLICATION_MESSAGES")  //@Table(name = "APPLICATION_MESSAGES", schema = "STOCKS", catalog = "")  TODO cleanup
public class ApplicationMessagesEntity implements Serializable {

    private static final long serialVersionUID = 3848762733391793866L;
    private Long id;
    private String messageKey;
    private String message;

    protected ApplicationMessagesEntity() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public ApplicationMessagesEntity(Long id, String messageKey, String message) {
        this.id = id;
        this.messageKey = messageKey;
        this.message = message;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    @SequenceGenerator(name = "SEQ_APPLICATION_MESSAGES", sequenceName = "SEQ_APPLICATION_MESSAGES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLICATION_MESSAGES")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MESSAGE_KEY", nullable = false, length = 100)
    public String getMessageKey() {
        return messageKey;
    }

    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    @Basic
    @Column(name = "MESSAGE", nullable = false, length = 500)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationMessagesEntity that = (ApplicationMessagesEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (messageKey != null ? !messageKey.equals(that.messageKey) : that.messageKey != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (messageKey != null ? messageKey.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ApplicationMessagesEntity{" +
                "id=" + id +
                ", messageKey='" + messageKey + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
