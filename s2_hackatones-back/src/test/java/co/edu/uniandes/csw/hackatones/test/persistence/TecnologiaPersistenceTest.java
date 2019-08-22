/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
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
 * @author ja.torresl
 */
@RunWith(Arquillian.class)
public class TecnologiaPersistenceTest {
       
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(InteresEntity.class)
                .addClass(InteresPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    TecnologiaPersistence cp;

    
    @PersistenceContext
    EntityManager em;
        
    @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        TecnologiaEntity tecnologia = factory.manufacturePojo(TecnologiaEntity.class);
        TecnologiaEntity result = cp.create(tecnologia);
        Assert.assertNotNull(result);
        
        TecnologiaEntity entity = em.find(TecnologiaEntity.class, result.getNombre());
        Assert.assertEquals(tecnologia.getId(), entity.getId());
        Assert.assertEquals(tecnologia.getInteresados(), result.getInteresados());
    }
}
