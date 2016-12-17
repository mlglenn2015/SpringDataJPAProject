package prv.mark.project.common.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * JPA Entity for the STOCKS.USERS table.
 *
 * http://blog.jbaysolutions.com/2012/12/17/jpa-2-relationships-many-to-many/
 *
 * https://en.wikibooks.org/wiki/Java_Persistence/ManyToMany
 *
 * https://github.com/olivergierke/repositories-deepdive/blob/master/src/main/java/de/olivergierke/deepdive/EmailAddress.java
 *
 * @author mlglenn on 12/12/2016.
 */
@Entity
@Table(name = "USERS")
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 9157226449683413471L;

    @Id
    @Column(name = "USER_ID", nullable = false, length = 30)
    private String userId;

    @Basic
    @Column(name = "USER_NAME", nullable = false, length = 100)
    private String userName;


    // The entity you put 'mappedBy' is the one which is NOT the owner
    @ManyToMany(mappedBy = "usersCollection")  //GroupsEntity.usersCollection
    private Collection<GroupsEntity> groupsCollection;


    protected UsersEntity() {
        // no-args constructor required by JPA spec
        // this one is protected since it shouldn't be used directly
    }

    public UsersEntity(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public UsersEntity(String userId, String userName, Collection<GroupsEntity> groupsCollection) {
        this.userId = userId;
        this.userName = userName;
        this.groupsCollection = groupsCollection;
    }

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

    @XmlTransient
    public Collection<GroupsEntity> getGroupsCollection() {
        return groupsCollection;
    }

    public void setGroupsCollection(Collection<GroupsEntity> groupsCollection) {
        this.groupsCollection = groupsCollection;
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
                ", groupsCollection=" + groupsCollection +
                '}';
    }
}
