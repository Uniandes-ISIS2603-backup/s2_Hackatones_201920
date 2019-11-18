/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.InteresLogic;
import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
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
 * @author ja.torresl
 */
@RunWith(Arquillian.class)
public class InteresLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InteresEntity.class.getPackage())
                .addPackage(InteresLogic.class.getPackage())
                .addPackage(InteresPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private InteresLogic interesLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<InteresEntity> data = new ArrayList<>();
    
    @Before
    public void configTest() {
        try {
            utx.begin();
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
        em.createQuery("delete from InteresEntity").executeUpdate();
    }
    
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            InteresEntity entity = factory.manufacturePojo(InteresEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
     
      
    @Test
    public void createInteres() throws BusinessLogicException{
        
        InteresEntity newEntity = factory.manufacturePojo(InteresEntity.class);
        InteresEntity result = interesLogic.createInteres(newEntity);
        Assert.assertNotNull(result);
        
        InteresEntity entity = em.find(InteresEntity.class, result.getId());
        Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
  
    }
    
    @Test
    public void getAllInteresTest() {
        List<InteresEntity> list = interesLogic.getIntereses();
        Assert.assertEquals(data.size(), list.size());
        for (InteresEntity ent : list) {
            boolean found = false;
            for (InteresEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getInteresTest() {
        InteresEntity entity = data.get(0);
        InteresEntity newEntity = interesLogic.getInteres(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }

    @Test
    public void updateInteresTest() throws BusinessLogicException {
        InteresEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        InteresEntity newEntity = factory.manufacturePojo(InteresEntity.class);

        newEntity.setId(entity.getId());

        interesLogic.updateInteres(newEntity.getId(), newEntity);

        InteresEntity resp = em.find(InteresEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), resp.getDescripcion());
    }
    
    @Test
    public void deleteInteresTest() throws BusinessLogicException {
        InteresEntity entity = data.get(0);
        interesLogic.deleteInteres(entity.getId());
        InteresEntity deleted = em.find(InteresEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
    @Test (expected = BusinessLogicException.class)
    public void createInteresNombreNull() throws BusinessLogicException{
        
        InteresEntity newEntity = factory.manufacturePojo(InteresEntity.class);
        newEntity.setNombre(null);
        InteresEntity result = interesLogic.createInteres(newEntity);
    }
    
    
   
}
