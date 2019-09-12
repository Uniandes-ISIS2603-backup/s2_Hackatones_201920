/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.CredencialesLogic;
import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
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
public class CredencialesLogicTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CredencialesEntity.class.getPackage())
                .addPackage(CredencialesLogic.class.getPackage())
                .addPackage(CredencialesPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private CredencialesLogic credencialesLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Inject
    private UserTransaction utx;

    private List<CredencialesEntity> data = new ArrayList<>();
    
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
        em.createQuery("delete from CredencialesEntity").executeUpdate();
    }
    
     private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CredencialesEntity entity = factory.manufacturePojo(CredencialesEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createCredenciales() throws BusinessLogicException{
        
        CredencialesEntity newEntity = factory.manufacturePojo(CredencialesEntity.class);
        CredencialesEntity result = credencialesLogic.createCredenciales(newEntity);
        Assert.assertNotNull(result);
        
        CredencialesEntity entity = em.find(CredencialesEntity.class, result.getId());
        Assert.assertEquals(entity.getCorreo(), result.getCorreo());
        Assert.assertEquals(entity.getContrasenha(), result.getContrasenha());
//        Assert.assertEquals(entity.getUsuario(), result.getUsuario());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCredencialesCorreoNull() throws BusinessLogicException{
        
        CredencialesEntity newEntity = factory.manufacturePojo(CredencialesEntity.class);
        newEntity.setCorreo(null);
        CredencialesEntity result = credencialesLogic.createCredenciales(newEntity);
    }
    
    @Test(expected = Exception.class)
    public void createCredencialesContrasenhaNull() throws BusinessLogicException{
        
        CredencialesEntity newEntity = factory.manufacturePojo(CredencialesEntity.class);
        newEntity.setContrasenha(null);
        CredencialesEntity result = credencialesLogic.createCredenciales(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCredencialesCorreoCadenaVacia() throws BusinessLogicException{
        CredencialesEntity newEntity = factory.manufacturePojo(CredencialesEntity.class);
        newEntity.setCorreo("");
        CredencialesEntity result = credencialesLogic.createCredenciales(newEntity);
    }
    
    @Test
    public void getAllCredencialesTest() {
        List<CredencialesEntity> list = credencialesLogic.getAllCredenciales();
        Assert.assertEquals(data.size(), list.size());
        for (CredencialesEntity ent : list) {
            boolean found = false;
            for (CredencialesEntity entity : data) {
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
        CredencialesEntity newEntity = credencialesLogic.getCredenciales(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getContrasenha(), entity.getContrasenha());
//        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
    }

    @Test
    public void updateCalificacionTest() throws BusinessLogicException {
        CredencialesEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CredencialesEntity newEntity = factory.manufacturePojo(CredencialesEntity.class);

        newEntity.setId(entity.getId());

        credencialesLogic.updateCredenciales(newEntity.getId(), newEntity);

        CredencialesEntity resp = em.find(CredencialesEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getContrasenha(), resp.getContrasenha());
//        Assert.assertEquals(newEntity.getUsuario(), resp.getUsuario());
        Assert.assertEquals(newEntity.getCorreo(), resp.getCorreo());
    }
    
    @Test
    public void deleteCalificacionTest() throws BusinessLogicException {
        CredencialesEntity entity = data.get(0);
        credencialesLogic.deleteCredenciales(entity.getId());
        CredencialesEntity deleted = em.find(CredencialesEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
