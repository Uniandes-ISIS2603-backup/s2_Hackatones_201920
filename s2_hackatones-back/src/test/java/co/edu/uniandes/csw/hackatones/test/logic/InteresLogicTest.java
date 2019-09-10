/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.InteresLogic;
import co.edu.uniandes.csw.hackatones.entities.InteresEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.InteresPersistence;
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
public class InteresLogicTest {
    
        
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(InteresEntity.class.getPackage())
                .addPackage(InteresLogic.class.getPackage())
                .addPackage(InteresPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }

    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private InteresLogic interesLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Test
    public void createInteres() throws BusinessLogicException{
        
        InteresEntity newEntity = factory.manufacturePojo(InteresEntity.class);
        InteresEntity result = interesLogic.createInteres(newEntity);
        Assert.assertNotNull(result);
        
        InteresEntity entity = em.find(InteresEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        Assert.assertEquals(entity.getDescripcion(), result.getDescripcion());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createInteresNombreNull() throws BusinessLogicException{
        
        InteresEntity newEntity = factory.manufacturePojo(InteresEntity.class);
        newEntity.setNombre(null);
        InteresEntity result = interesLogic.createInteres(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createInteresDescripcionNull() throws BusinessLogicException{
        
        InteresEntity newEntity = factory.manufacturePojo(InteresEntity.class);
        newEntity.setDescripcion(null);
        InteresEntity result = interesLogic.createInteres(newEntity);
    }

}
