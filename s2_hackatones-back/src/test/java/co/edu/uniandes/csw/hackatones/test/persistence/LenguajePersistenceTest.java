/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.LenguajeEntity;
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
 * @author Santiago Estupiñan
 */
@RunWith(Arquillian.class)
public class LenguajePersistenceTest {
    @PersistenceContext(unitName = "hackatones")
    EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(LenguajeEntity.class)
                .addClass(LenguajePersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml" );

                }
    
    @Inject
    LenguajePersistence ep;

    @Test
    public void createTest() {
        PodamFactory factory = new PodamFactoryImpl();
        LenguajeEntity patrocinador = factory.manufacturePojo(LenguajeEntity.class);
        LenguajeEntity result = ep.create(patrocinador);
        Assert.assertNotNull(result);
        
        
        LenguajeEntity entity = em.find(LenguajeEntity.class, result.getId());

        Assert.assertEquals(patrocinador.getName(), entity.getName());

    }

}
