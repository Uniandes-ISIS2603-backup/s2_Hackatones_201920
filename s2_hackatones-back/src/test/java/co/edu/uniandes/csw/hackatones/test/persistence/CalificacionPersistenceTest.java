/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;
import co.edu.uniandes.csw.hackatones.persistence.CalificacionPersistence;
import javax.inject.Inject;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author a.pedraza
 */
@RunWith(Arquillian.class)
public class CalificacionPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CalificacionEntity.class)
                .addClass(CalificacionPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    @Inject
    CalificacionPersistence cp;
    
    @Test
    public void createTest(){
        PodamFactory factory = new PodamFactoryImpl();
        CalificacionEntity calificacion = factory.manufacturePojo(CalificacionEntity.class);
        
        
    }
}
