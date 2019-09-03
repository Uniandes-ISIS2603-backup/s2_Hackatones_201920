/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.CalificacionLogic;
import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.CalificacionPersistence;
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
public class CalificacionLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private CalificacionLogic calificacionLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Test
    public void createCalificacion() throws BusinessLogicException{
        
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
        Assert.assertNotNull(result);
        
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        Assert.assertEquals(entity.getCalificacion(), result.getCalificacion());
        Assert.assertEquals(entity.getComentario(), result.getComentario());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionCalificacionNull() throws BusinessLogicException{
        
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setCalificacion(null);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionComentarioNull() throws BusinessLogicException{
        
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setComentario(null);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionComentarioCadenaVacia() throws BusinessLogicException{
        
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setComentario("");
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionCalificacionNegativa() throws BusinessLogicException{
        
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setCalificacion(-0.1);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCalificacionCalificacionMayorACinco() throws BusinessLogicException{
        
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
        newEntity.setCalificacion(5.1);
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
    }
}
