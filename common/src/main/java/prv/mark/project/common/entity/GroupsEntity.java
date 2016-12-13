package prv.mark.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * JPA Entity for the STOCKS.GROUPS table.
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "GROUPS")   //@Table(name = "GROUPS", schema = "STOCKS", catalog = "")  TODO cleanup
public class GroupsEntity implements Serializable {

    private static final long serialVersionUID = -2769750154242294344L;
    private Long id;
    private String groupName;
    private Long userId;


    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "GROUP_NAME", nullable = false, length = 100)
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "USER_ID", nullable = false, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (!getId().equals(that.getId())) return false;
        if (!getGroupName().equals(that.getGroupName())) return false;
        return getUserId().equals(that.getUserId());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getGroupName().hashCode();
        result = 31 * result + getUserId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GroupsEntity{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
