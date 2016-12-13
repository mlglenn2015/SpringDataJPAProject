package prv.mark.project.common.entity;

import java.io.Serializable;

/**
 * JPA Entity for the STOCKS.USERS_GROUPS table.
 *
 * @author mlglenn on 12/13/2016.
 */
//@Entity
//@Table(name = "USERS_GROUPS")
public class UsersGroupsEntity implements Serializable {

    private static final long serialVersionUID = -6392980640985087941L;

    //@Basic
    //@Column(name = "USER_ID", nullable = false, length = 30)
    private String userId;

    //@Basic
    //@Column(name = "GROUP_ID", nullable = false, length = 30)
    private String groupId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersGroupsEntity that = (UsersGroupsEntity) o;

        if (!getUserId().equals(that.getUserId())) return false;
        return getGroupId().equals(that.getGroupId());

    }

    @Override
    public int hashCode() {
        int result = getUserId().hashCode();
        result = 31 * result + getGroupId().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UsersGroupsEntity{" +
                "userId='" + userId + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
