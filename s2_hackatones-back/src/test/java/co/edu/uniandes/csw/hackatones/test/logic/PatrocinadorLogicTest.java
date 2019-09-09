/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.PatrocinadorLogic;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class PatrocinadorLogicTest {
        
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    private PatrocinadorLogic logic;
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PatrocinadorEntity.class.getPackage())
                .addPackage(PatrocinadorLogic.class.getPackage())
                .addPackage(PatrocinadorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");

    }
    
    @Test
    public void createUsuarioTest() throws BusinessLogicException
    {
        PatrocinadorEntity newEntity = factory.manufacturePojo(PatrocinadorEntity.class);
        PatrocinadorEntity result = logic.createPatrocinador(newEntity);
        
        Assert.assertNotNull(result);
        PatrocinadorEntity entity = em.find(PatrocinadorEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        
    }
    
    @Test (expected = Exception.class)
    public void  createUsuarioNombreNull() throws BusinessLogicException
    {
        PatrocinadorEntity newEntity = factory.manufacturePojo(PatrocinadorEntity.class);
        newEntity.setNombre(null);
        PatrocinadorEntity result = logic.createPatrocinador(newEntity);
    }
    
}
