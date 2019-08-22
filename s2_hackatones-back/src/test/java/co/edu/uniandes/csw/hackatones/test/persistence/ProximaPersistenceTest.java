/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.ProximaEntity;
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
public class ProximaPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ProximaEntity.class)
                .addClass(ProximaPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    ProximaPersistence pp;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ProximaEntity proxima = factory.manufacturePojo(ProximaEntity.class);
        ProximaEntity result = pp.create(proxima);
        Assert.assertNotNull(result);      
        
        ProximaEntity entity = em.find(ProximaEntity.class,result.getId());
        Assert.assertEquals(proxima.getId(), entity.getId());
    }
}
