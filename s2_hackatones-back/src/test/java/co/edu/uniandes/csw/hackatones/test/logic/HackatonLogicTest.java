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
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
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
    
    @Test
    public void createHackatonTest() throws BusinessLogicException
    {
        HackatonEntity newEntity = factory.manufacturePojo(HackatonEntity.class);
        HackatonEntity result = hackatonLogic.createHackaton(newEntity);
        Assert.assertNotNull(result);
        
        HackatonEntity entity = em.find(HackatonEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createHackatonNombreNullTest() throws BusinessLogicException
    {
        HackatonEntity newEntity = factory.manufacturePojo(HackatonEntity.class);
        newEntity.setNombre(null);
        HackatonEntity result = hackatonLogic.createHackaton(newEntity);
        
    }
}
