/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.HackatonLogic;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jc.higuera
 */
@RunWith(Arquillian.class)
public class HackatonLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private HackatonLogic hackatonLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<HackatonEntity> data = new ArrayList<HackatonEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HackatonEntity.class.getPackage())
                .addPackage(HackatonLogic.class.getPackage())
                .addPackage(HackatonPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    
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
    
    
    private void clearData()
    {
        em.createQuery("delete from HackatonEntity").executeUpdate();
    }
    
    private void insertData()
    {
        for(int i = 0; i<3; i++)
        {
            HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createHackatonTest() throws BusinessLogicException
    {
        HackatonEntity newEntity = factory.manufacturePojo(HackatonEntity.class);
        HackatonEntity result = hackatonLogic.createHackaton(newEntity);
        Assert.assertNotNull(result);
        
        HackatonEntity entity = em.find(HackatonEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        Assert.assertEquals(entity.getEspecificacion(), result.getEspecificacion());
        Assert.assertEquals(entity.getFechaFin(), result.getFechaFin());
        Assert.assertEquals(entity.getFechaInicio(), result.getFechaInicio());
        Assert.assertEquals(entity.getNivel(), result.getNivel());
        Assert.assertEquals(entity.getTema(), result.getTema());
        Assert.assertEquals(entity.getFinalizada(), result.getFinalizada());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createHackatonNombreNullTest() throws BusinessLogicException
    {
        HackatonEntity newEntity = factory.manufacturePojo(HackatonEntity.class);
        newEntity.setNombre(null);
        HackatonEntity result = hackatonLogic.createHackaton(newEntity);
        
    }
    
    @Test
    public void getHackatonesTest()
    {
        List<HackatonEntity> list = hackatonLogic.getHackatones();
        Assert.assertEquals(data.size(), list.size());
        for(HackatonEntity entity: list)
        {
            boolean found = false;
            for(HackatonEntity storedEntity: data)
            {
                if (entity.getId().equals(storedEntity.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getHackatonTest()
    {
        HackatonEntity entity = data.get(0);
        HackatonEntity resultEntity = hackatonLogic.getHackaton(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
    }
    
    @Test
    public void updateHackatonTest()
    {
        HackatonEntity entity = data.get(0);
        HackatonEntity pojoEntity = factory.manufacturePojo(HackatonEntity.class);
        pojoEntity.setId(entity.getId());
        hackatonLogic.updateHackaton(pojoEntity.getId(), pojoEntity);
        HackatonEntity resp = em.find(HackatonEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }
    
   @Test
    public void deleteHackatonTest()
   {
        HackatonEntity entity = data.get(0);
        hackatonLogic.deleteHackaton(entity.getId());
        HackatonEntity deleted = em.find(HackatonEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
}
