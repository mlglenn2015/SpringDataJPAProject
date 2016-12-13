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
@Table(name = "GROUPS", schema = "STOCKS", catalog = "")
public class mlgGroupsEntity {
    private Serializable groupId;
    private Serializable groupName;

    @Id
    @Column(name = "GROUP_ID", nullable = false, length = 30)
    public Serializable getGroupId() {
        return groupId;
    }

    public void setGroupId(Serializable groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "GROUP_NAME", nullable = false, length = 100)
    public Serializable getGroupName() {
        return groupName;
    }

    public void setGroupName(Serializable groupName) {
        this.groupName = groupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        mlgGroupsEntity that = (mlgGroupsEntity) o;

        if (groupId != null ? !groupId.equals(that.groupId) : that.groupId != null) return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = groupId != null ? groupId.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        return result;
    }
}
