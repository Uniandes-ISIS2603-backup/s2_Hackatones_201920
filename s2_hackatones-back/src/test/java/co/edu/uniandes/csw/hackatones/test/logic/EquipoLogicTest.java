/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.EquipoLogic;
import co.edu.uniandes.csw.hackatones.ejb.EquipoLogic;
import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.EquipoPersistence;
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
public class EquipoLogicTest {
    
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoLogic.class.getPackage())
                .addPackage(EquipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private EquipoLogic equipoLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<EquipoEntity> data = new ArrayList<>();
    
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
        em.createQuery("delete from EquipoEntity").executeUpdate();
    }
    
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EquipoEntity entity = factory.manufacturePojo(EquipoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
     
      
    @Test
    public void createEquipo() throws BusinessLogicException{
        
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity result = equipoLogic.createEquipo(newEntity);
        Assert.assertNotNull(result);
        
        EquipoEntity entity = em.find(EquipoEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
  
    }
    
    @Test
    public void getAllEquipoTest() {
        List<EquipoEntity> list = equipoLogic.getEquipoes();
        Assert.assertEquals(data.size(), list.size());
        for (EquipoEntity ent : list) {
            boolean found = false;
            for (EquipoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    @Test
    public void getEquipoTest() {
        EquipoEntity entity = data.get(0);
        EquipoEntity newEntity = equipoLogic.getEquipo(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.isEsGanador(), entity.isEsGanador());
        
    }

    @Test
    public void updateEquipoTest() throws BusinessLogicException {
        EquipoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);

        newEntity.setId(entity.getId());

        equipoLogic.updateEquipo(newEntity.getId(), newEntity);

        EquipoEntity resp = em.find(EquipoEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    @Test
    public void deleteEquipoTest() throws BusinessLogicException {
        EquipoEntity entity = data.get(0);
        equipoLogic.deleteEquipo(entity.getId());
        EquipoEntity deleted = em.find(EquipoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
