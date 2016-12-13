package prv.mark.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by mlglenn on 12/13/2016.
 */
@Entity
@Table(name = "USERS", schema = "STOCKS", catalog = "")
public class mlgUsersEntity {
    private Serializable userId;
    private Serializable userName;

    @Id
    @Column(name = "USER_ID", nullable = false, length = 30)
    public Serializable getUserId() {
        return userId;
    }

    public void setUserId(Serializable userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 100)
    public Serializable getUserName() {
        return userName;
    }

    public void setUserName(Serializable userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        mlgUsersEntity that = (mlgUsersEntity) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        return result;
    }
}
