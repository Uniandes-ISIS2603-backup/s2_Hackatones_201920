/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.ActualEntity;
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
public class ActualPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ActualEntity.class)
                .addClass(ActualPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    ActualPersistence ap;
    
    @PersistenceContext
    protected EntityManager em;
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ActualEntity actual = factory.manufacturePojo(ActualEntity.class);
        ActualEntity result = ap.create(actual);
        Assert.assertNotNull(result);      
        
        ActualEntity entity = em.find(ActualEntity.class,result.getId());
        Assert.assertEquals(actual.getId(), entity.getId());
        Assert.assertEquals(actual.getReglas(), entity.getReglas());
        Assert.assertEquals(actual.getRestricciones(), entity.getRestricciones());
    }
}
