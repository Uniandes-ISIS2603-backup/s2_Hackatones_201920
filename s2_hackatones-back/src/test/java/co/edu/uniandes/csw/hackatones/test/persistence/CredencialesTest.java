/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
import co.edu.uniandes.csw.hackatones.entities.CredencialesPersistence;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class CredencialesTest {
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(CredencialesEntity.class)
                .addClass(CredencialesPersistence.class)
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Inject
    CredencialesPersistence ep;
    
    @Test
    public void createTest()
    {
        //Falta crear
        CredencialesEntity result = ep.create(credencial);
        Assert.assertNotNull(result);
    }
}
