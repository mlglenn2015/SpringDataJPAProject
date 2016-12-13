package prv.mark.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * JPA Entity for the STOCKS.USERS table.
 *
 * https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "USERS") //@Table(name = "USERS", schema = "STOCKS", catalog = "") TODO cleanup
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 9157226449683413471L;

    @Id
    @Column(name = "USER_ID", nullable = false, length = 30)
    private String userId;

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 100)
    private String userName;

    @ManyToMany
    @JoinTable(
            name="USERS_GROUPS",
            joinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"),
            inverseJoinColumns=@JoinColumn(name="GROUP_ID", referencedColumnName="GROUP_ID"))
    private Set<GroupsEntity> groups;


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<GroupsEntity> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupsEntity> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        return getUserId().equals(that.getUserId());

    }

    @Override
    public int hashCode() {
        return getUserId().hashCode();
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", groups=" + groups +
                '}';
    }
}
