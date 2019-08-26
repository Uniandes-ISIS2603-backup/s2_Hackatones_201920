/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.ObservadoresEntity;
import co.edu.uniandes.csw.hackatones.persistence.ObservadoresPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jc.higuera
 */
public class ObservadoresPersistenceTest 
{
    @Inject
    private ObservadoresPersistence op;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(ObservadoresEntity.class)
                .addClass(ObservadoresPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createObservadoresTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        ObservadoresEntity observadores = factory.manufacturePojo(ObservadoresEntity.class);
        
        ObservadoresEntity oe = op.create(observadores);
        
        Assert.assertNotNull(oe);
        
        ObservadoresEntity entity = em.find(ObservadoresEntity.class, oe.getId());
        Assert.assertEquals(observadores.getId(), entity.getId());
    }
    
}
