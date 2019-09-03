/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.LugarLogic;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.LugarPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.HTML;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jsoup.nodes.Entities;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author jd.monsalve
 */
@RunWith(Arquillian.class)
public class LugarLogicTest {
    
    private PodamFactory podam = new PodamFactoryImpl();
    
    @Inject
    private LugarLogic lugarLogic;
    
    @PersistenceContext()
    private EntityManager em;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class).addPackage(LugarEntity.class.getPackage()).addPackage(LugarLogic.class.getPackage()).addPackage(LugarPersistence.class.getPackage()).addAsManifestResource("META-INF/persistence.xml", "persistence.xml").addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createLugar() throws BusinessLogicException
    {
        LugarEntity NuevaEntidad = podam.manufacturePojo(LugarEntity.class);
        LugarEntity resultado = lugarLogic.createLugar(NuevaEntidad);
        Assert.assertNotNull(resultado);
        
        LugarEntity entidad = em.find(LugarEntity.class, resultado.getId());
        Assert.assertEquals(entidad.getNombre(), resultado.getNombre());
    }
    
    @Test (expected = BusinessLogicException.class)
    public void createEstudianteNombreNull() throws BusinessLogicException
    {
    LugarEntity nuevaEntidad = podam.manufacturePojo(LugarEntity.class);
    nuevaEntidad.setNombre(null);
    
    LugarEntity resultado  = lugarLogic.createLugar(nuevaEntidad);
    }
    
    
}
