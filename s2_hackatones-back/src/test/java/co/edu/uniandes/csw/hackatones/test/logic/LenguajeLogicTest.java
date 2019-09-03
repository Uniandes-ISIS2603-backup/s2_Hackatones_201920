/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.LenguajeLogic;
import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LenguajePersistence;
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
 * @author Santiago Estupinan
 */
@RunWith(Arquillian.class)
public class LenguajeLogicTest 
{
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    private LenguajeLogic logic;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LenguajeEntity.class.getPackage())
                .addPackage(LenguajeLogic.class.getPackage())
                .addPackage(LenguajePersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");

    }
    
    @Test
    public void createLenguajeTest() throws BusinessLogicException
    {
        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
        LenguajeEntity result = logic.createLenguaje(newEntity);
        
        Assert.assertNotNull(result);
        LenguajeEntity entity = em.find(LenguajeEntity.class, result.getId());
        Assert.assertEquals(entity.getName(), result.getName());
        
    }
//    @Test (expected = BusinessLogicException.class)
//    public void  createLenguajeNombreNull() throws BusinessLogicException
//    {
//        LenguajeEntity newEntity = factory.manufacturePojo(LenguajeEntity.class);
//        newEntity.setName(null);
//        LenguajeEntity result = logic.createLenguaje(newEntity);
//    }
    
    
}
