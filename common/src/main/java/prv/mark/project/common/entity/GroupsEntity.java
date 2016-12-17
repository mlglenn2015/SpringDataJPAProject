package prv.mark.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * JPA Entity for the STOCKS.GROUPS table.
 *
 * http://blog.jbaysolutions.com/2012/12/17/jpa-2-relationships-many-to-many/
 *
 * http://stackoverflow.com/questions/1082095/how-to-remove-entity-with-manytomany-relationship-in-jpa-and-corresponding-join?rq=1
 *
 * https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany
 *
 * https://github.com/olivergierke/repositories-deepdive/blob/master/src/main/java/de/olivergierke/deepdive/EmailAddress.java
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "GROUPS")
public class GroupsEntity implements Serializable {

    /*
    The ownership of the relation is determined by where you place the 'mappedBy' attribute to the annotation.
    The entity you put 'mappedBy' is the one which is NOT the owner.

    There's no chance for both sides to be owners. If you don't have a 'delete user' use-case you could simply
    move the ownership to the Group entity, as currently the User is the owner.

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
    @Column(name = "GROUP_ID", nullable = false, length = 30)
    private String groupId;

    @Basic
    @Column(name = "GROUP_NAME", nullable = false, length = 100)
    private String groupName;

    //@ManyToMany(mappedBy="groups") TODO remove
    // The entity you put 'mappedBy' is the one which is NOT the owner
    @ManyToMany
    @JoinTable(
            name="USERS_GROUPS",
            joinColumns=@JoinColumn(name="GROUP_ID", referencedColumnName="GROUP_ID"),
            inverseJoinColumns=@JoinColumn(name="USER_ID", referencedColumnName="USER_ID"))
    private Collection<UsersEntity> usersCollection;


    protected GroupsEntity() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public GroupsEntity(String groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }

    public GroupsEntity(String groupId, String groupName, Collection<UsersEntity> usersCollection) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.usersCollection = usersCollection;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @XmlTransient
    public Collection<UsersEntity> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<UsersEntity> usersCollection) {
        this.usersCollection = usersCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupsEntity that = (GroupsEntity) o;

        return getGroupId().equals(that.getGroupId());

    }

    @Override
    public int hashCode() {
        return getGroupId().hashCode();
    }

    @Override
    public String toString() {
        return "GroupsEntity{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", usersCollection=" + usersCollection +
                '}';
    }
}
