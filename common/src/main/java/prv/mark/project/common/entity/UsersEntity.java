package prv.mark.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * JPA Entity for the STOCKS.USERS table.
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "USERS") //@Table(name = "USERS", schema = "STOCKS", catalog = "") TODO cleanup
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 9157226449683413471L;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private Long id;

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 100)
    private String userName;

    //private Long groupId;
    @ManyToMany
    Set<GroupsEntity> groupsEntitySet;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /*@Basic
    @Column(name = "GROUP_ID", nullable = false, precision = 0)
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }*/

    public Set<GroupsEntity> getGroupsEntitySet() {
        return groupsEntitySet;
    }

    public void setGroupsEntitySet(Set<GroupsEntity> groupsEntitySet) {
        this.groupsEntitySet = groupsEntitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (!getId().equals(that.getId())) return false;
        return getUserName().equals(that.getUserName());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getUserName().hashCode();
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
