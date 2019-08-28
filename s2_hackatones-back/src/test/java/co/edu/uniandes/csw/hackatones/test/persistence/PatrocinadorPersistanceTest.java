/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.persistence.PatrocinadorPersistence;
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
public class PatrocinadorPersistanceTest {
    
    @PersistenceContext()
    EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(PatrocinadorEntity.class)
                .addClass(PatrocinadorPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml" );

                }
    
    @Inject
    PatrocinadorPersistence ep;

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PatrocinadorEntity patrocinador = factory.manufacturePojo(PatrocinadorEntity.class);
        PatrocinadorEntity result = ep.create(patrocinador);
        Assert.assertNotNull(result);
        
        
        PatrocinadorEntity entity = em.find(PatrocinadorEntity.class, result.getId());
        Assert.assertEquals(patrocinador.getNombre(), entity.getNombre());
        Assert.assertEquals(patrocinador.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(patrocinador.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(patrocinador.getInfoAdicional(), entity.getInfoAdicional());

    }

}
