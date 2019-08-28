/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.persistence.EquipoPersistence;
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
public class EquipoPersistenceTest {
    
    
       
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(EquipoEntity.class)
                .addClass(EquipoPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    EquipoPersistence cp;

    
    @PersistenceContext
    EntityManager em;
        
    @Test
    public void createTest(){
        
        PodamFactory factory = new PodamFactoryImpl();
        EquipoEntity equipo = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity result = cp.create(equipo);
        Assert.assertNotNull(result);
        
        EquipoEntity entity = em.find(EquipoEntity.class, result.getNombre());
        Assert.assertEquals(equipo.getId(), entity.getId());
        Assert.assertEquals(equipo.getNombre(), entity.getNombre());
        
    }
}
