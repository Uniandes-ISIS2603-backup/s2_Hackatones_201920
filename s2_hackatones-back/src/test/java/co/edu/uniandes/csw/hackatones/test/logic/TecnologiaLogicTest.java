/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.TecnologiaLogic;
import co.edu.uniandes.csw.hackatones.entities.TecnologiaEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.TecnologiaPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.glassfish.pfl.basic.tools.argparser.ElementParser.factory;
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
public class TecnologiaLogicTest {
    

        
    @Deployment
    public static JavaArchive createDlepoyment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TecnologiaEntity.class.getPackage())
                .addPackage(TecnologiaLogic.class.getPackage())
                .addPackage(TecnologiaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
   
     
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TecnologiaLogic tecnologiaLogic;
    
     @PersistenceContext()
    protected EntityManager em;
     /**
    @Test
    public void createTecnologia() throws BusinessLogicException{
       
        TecnologiaEntity newEntity = factory.manufacturePojo(TecnologiaEntity.class);
        TecnologiaEntity result = tecnologiaLogic.createTecnologia(newEntity);
        Assert.assertNotNull(result);
        
        TecnologiaEntity entity =  em.find(TecnologiaEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
       // Assert.assertEquals(entity.getInteresados(), result.getInteresados());
    }
    */
    @Test (expected = BusinessLogicException.class)
    public void createTecnologiaNombreNull() throws BusinessLogicException{
        
        TecnologiaEntity newEntity = factory.manufacturePojo(TecnologiaEntity.class);
        newEntity.setNombre(null);
        TecnologiaEntity result = tecnologiaLogic.createTecnologia(newEntity);
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createTecnologiaInteresadosNull() throws BusinessLogicException{
        
        TecnologiaEntity newEntity = factory.manufacturePojo(TecnologiaEntity.class);
        newEntity.setNombre(null);
        TecnologiaEntity result = tecnologiaLogic.createTecnologia(newEntity);
    }
 
}
