/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.EquipoLogic;
import co.edu.uniandes.csw.hackatones.ejb.InteresLogic;
import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
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
public class EquipoLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EquipoEntity.class.getPackage())
                .addPackage(EquipoLogic.class.getPackage())
                .addPackage(EquipoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }

    private PodamFactory factory =  new PodamFactoryImpl();
    
    @Inject
    private EquipoLogic equipoLogic;
    
    @PersistenceContext()
    protected EntityManager em;
    @Test
    public void createEquipo() throws BusinessLogicException{
        
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        EquipoEntity result = equipoLogic.createEquipo(newEntity);
        Assert.assertNotNull(result);
        
        EquipoEntity entity = em.find(EquipoEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createEquipoNombreNull() throws BusinessLogicException{
        
        EquipoEntity newEntity = factory.manufacturePojo(EquipoEntity.class);
        newEntity.setNombre(null);
        EquipoEntity result = equipoLogic.createEquipo(newEntity);
    }
}
