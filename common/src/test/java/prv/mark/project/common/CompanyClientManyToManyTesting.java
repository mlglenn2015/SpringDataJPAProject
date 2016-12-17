package prv.mark.project.common;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import prv.mark.project.common.entity.ClientEntity;
import prv.mark.project.common.entity.CompanyEntity;

/**
 * https://github.com/jbaysolutions/jpa2tut-many2many/blob/master/src/test/java/com/jbaysolutions/blog/ManyToManyTesting.java
 *
 * @author mlglenn on 12/16/2016.
 */
public class CompanyClientManyToManyTesting {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    /*private static EntityManager em = null;

    @BeforeClass
    public static void setUpClass() throws Exception {
        if (em == null) {
            em = (EntityManager) Persistence.createEntityManagerFactory("JPATutorial3PU").createEntityManager();
        }
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }*/

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCompanyWithClient1(){
        // Start a transaction
        //em.getTransaction().begin();
        // ------------

        CompanyEntity company = createTestCompany();
        ClientEntity client = createTestClient();
        //em.persist(company);
        //em.persist(client);
        //em.flush();

        //company.getClientCollection().add(client);

        //em.merge(company);
        //em.flush();

        // Commit the Transaction
        //em.getTransaction().commit();

    }



    private CompanyEntity createTestCompany(){
        CompanyEntity c = new CompanyEntity();
        c.setName("JBay Solutions");
        c.setAddress("Av. 5 October, Lisbon");
        return c;
    }

    private ClientEntity createTestClient(){
        ClientEntity c = new ClientEntity();
        c.setName("Rui");
        c.setAddress("Home address");
        return c;
    }

}
