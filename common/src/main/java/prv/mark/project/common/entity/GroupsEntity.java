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
 * http://stackoverflow.com/questions/1082095/how-to-remove-entity-with-manytomany-relationship-in-jpa-and-corresponding-join?rq=1
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "GROUPS")   //@Table(name = "GROUPS", schema = "STOCKS", catalog = "")  TODO cleanup
public class GroupsEntity implements Serializable {

    /*
    The ownership of the relation is determined by where you place the 'mappedBy' attribute to the annotation.
    The entity you put 'mappedBy' is the one which is NOT the owner. There's no chance for both sides to be
    owners. If you don't have a 'delete user' use-case you could simply move the ownership to the Group entity,
    as currently the User is the owner.
    On the other hand, you haven't been asking about it, but one thing worth to know. The groups and users are
    not combined with each other. I mean, after deleting User1 instance from Group1.users, the User1.groups
    collections is not changed automatically (which is quite surprising for me),
    All in all, I would suggest you decide who is the owner. Let say the User is the owner. Then when deleting
    a user the relation user-group will be updated automatically. But when deleting a group you have to take
    care of deleting the relation yourself like this:

    entityManager.remove(group)
    for (User user : group.users) {
        user.groups.remove(group);
    }
    ...
    // then merge() and flush()
    */

    private static final long serialVersionUID = -2769750154242294344L;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    private Long id;

    @Basic
    @Column(name = "GROUP_NAME", nullable = false, length = 100)
    private String groupName;

    //private Long userId;
    //@ManyToMany(mappedBy="groupsEntitySet")
    //Set<UsersEntity> usersEntitySet;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    /*@Basic
    @Column(name = "USER_ID", nullable = false, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }*/

    /*public Set<UsersEntity> getUsersEntitySet() {
        return usersEntitySet;
    }

    public void setUsersEntitySet(Set<UsersEntity> usersEntitySet) {
        this.usersEntitySet = usersEntitySet;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        if (!getId().equals(that.getId())) return false;
        return getGroupName().equals(that.getGroupName());

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + getGroupName().hashCode();
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
