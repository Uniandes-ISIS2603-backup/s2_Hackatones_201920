/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.CatalogoEntity;
import co.edu.uniandes.csw.hackatones.persistence.CatalogoPersistence;
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
 * @author ne.cardenas
 */
@RunWith(Arquillian.class)
public class CatalogoPersistenceTest {
    
    @Inject
    private CatalogoPersistence ep;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CatalogoEntity.class)
                .addClass(CatalogoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createCatalogoTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        CatalogoEntity newEntity = factory.manufacturePojo(CatalogoEntity.class);
        
        CatalogoEntity ce = ep.create(newEntity);
        
        Assert.assertNotNull(ce);
        
        CatalogoEntity entity = em.find(CatalogoEntity.class, ce.getId());
        Assert.assertEquals(newEntity.getPatrocinadores(), entity.getPatrocinadores());
        Assert.assertEquals(newEntity.getProximos(), entity.getProximos());
        Assert.assertEquals(newEntity.getActual(), entity.getActual());
    }
    
}
