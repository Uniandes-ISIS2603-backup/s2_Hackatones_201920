/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.ProximaLogic;
import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.ProximaPersistence;
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
public class ProximaLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProximaEntity.class.getPackage())
                .addPackage(ProximaLogic.class.getPackage())
                .addPackage(ProximaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private ProximaLogic proximaLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Test
    public void createProxima() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        ProximaEntity result = proximaLogic.createProxima(newEntity);
        Assert.assertNotNull(result);
        
        ProximaEntity entity = em.find(ProximaEntity.class, result.getId());
        Assert.assertEquals(entity.getReglas(), result.getReglas());
        Assert.assertEquals(entity.getRestricciones(), result.getRestricciones());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProximaRestriccionesNull() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        newEntity.setRestricciones(null);
        ProximaEntity result = proximaLogic.createProxima(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProximaReglasNull() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        newEntity.setReglas(null);
        ProximaEntity result = proximaLogic.createProxima(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProximaRestriccionesCadenaVacia() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        newEntity.setRestricciones("");
        ProximaEntity result = proximaLogic.createProxima(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createProximaReglasCadenaVacia() throws BusinessLogicException{
        
        ProximaEntity newEntity = factory.manufacturePojo(ProximaEntity.class);
        newEntity.setReglas("");
        ProximaEntity result = proximaLogic.createProxima(newEntity);
    }
}

