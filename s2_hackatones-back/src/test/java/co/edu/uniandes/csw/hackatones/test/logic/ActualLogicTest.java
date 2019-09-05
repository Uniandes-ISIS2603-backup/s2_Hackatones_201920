/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.ActualLogic;
import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ActualPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author a.pedraza
 */
@RunWith(Arquillian.class)
public class ActualLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ActualEntity.class.getPackage())
                .addPackage(ActualLogic.class.getPackage())
                .addPackage(ActualPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private ActualLogic actualLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Test
    public void createActual() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        ActualEntity result = actualLogic.createActual(newEntity);
        Assert.assertNotNull(result);
        
        ActualEntity entity = em.find(ActualEntity.class, result.getId());
        Assert.assertEquals(entity.getReglas(), result.getReglas());
        Assert.assertEquals(entity.getRestricciones(), result.getRestricciones());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createActualRestriccionesNull() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        newEntity.setRestricciones(null);
        ActualEntity result = actualLogic.createActual(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createActualReglasNull() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        newEntity.setReglas(null);
        ActualEntity result = actualLogic.createActual(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createActualRestriccionesCadenaVacia() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        newEntity.setRestricciones("");
        ActualEntity result = actualLogic.createActual(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createActualReglasCadenaVacia() throws BusinessLogicException{
        
        ActualEntity newEntity = factory.manufacturePojo(ActualEntity.class);
        newEntity.setReglas("");
        ActualEntity result = actualLogic.createActual(newEntity);
    }
}
