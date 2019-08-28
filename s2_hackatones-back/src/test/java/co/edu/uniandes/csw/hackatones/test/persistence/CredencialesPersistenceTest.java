/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
import co.edu.uniandes.csw.hackatones.persistence.CredencialesPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author ne.cardenas
 */
@RunWith(Arquillian.class)
public class CredencialesPersistenceTest {
    
    @Inject
    private CredencialesPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    private void clearData() {
        em.createQuery("delete from CredencialesEntity").executeUpdate();
    }
    
    private List<CredencialesEntity> data = new ArrayList<CredencialesEntity>();
    
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CredencialesEntity entity = factory.manufacturePojo(CredencialesEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CredencialesEntity.class)
                .addClass(CredencialesPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createCredencialesTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CredencialesEntity newEntity = factory.manufacturePojo(CredencialesEntity.class);
        
        CredencialesEntity ce = ep.create(newEntity);
        
        Assert.assertNotNull(ce);
        
        CredencialesEntity entity = em.find(CredencialesEntity.class, ce.getId());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getContrasenha(), entity.getContrasenha());
    }
    
    @Test
    public void getAllCredencialesTest() {
        List<CredencialesEntity> list = ep.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CredencialesEntity ent: list)
        {
            boolean found = false;
            for (CredencialesEntity entity: data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getCredencialesTest() {
        CredencialesEntity entity = data.get(0);
        CredencialesEntity newEntity = ep.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getContrasenha(), entity.getContrasenha());
    }
    
    @Test
    public void deleteCredencialesTest() {
        CredencialesEntity entity = data.get(0);
        ep.delete(entity.getId());
        CredencialesEntity deleted = em.find(CredencialesEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    @Test
    public void updateCredencialesTest() {
        CredencialesEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CredencialesEntity newEntity = factory.manufacturePojo(CredencialesEntity.class);

        newEntity.setId(entity.getId());

        ep.update(newEntity);

        CredencialesEntity resp = em.find(CredencialesEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCorreo(), resp.getCorreo());
        Assert.assertEquals(newEntity.getContrasenha(), resp.getContrasenha());
    }
}
