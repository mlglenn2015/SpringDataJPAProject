package prv.mark.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * JPA Entity for the STOCKS.USERS table.
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "USERS") //@Table(name = "USERS", schema = "STOCKS", catalog = "") TODO cleanup
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 9157226449683413471L;
    private Long id;
    private String userName;
    private Long groupId;


    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 100)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "GROUP_ID", nullable = false, precision = 0)
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (!getId().equals(that.getId())) return false;
        if (!getUserName().equals(that.getUserName())) return false;
        return getGroupId().equals(that.getGroupId());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUserName().hashCode();
        result = 31 * result + getGroupId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                '}';
    }
}
