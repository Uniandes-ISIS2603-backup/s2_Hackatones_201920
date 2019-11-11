/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.UsuarioLogic;
import co.edu.uniandes.csw.hackatones.entities.CredencialesEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.UsuarioPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author s.estupinan
 */
@RunWith(Arquillian.class)
public class UsuarioLogicTest 
{
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    private UsuarioLogic logic;
    
    @Inject
    private UserTransaction utx;
    
    private List<UsuarioEntity> data = new ArrayList<>();
    
    /**
     * Crea el deployment del test
     * @return 
     */
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");

    }
    
    /**
     * Realiza la configuracion del test
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Borra los datos anteriores
     */
    private void clearData() {
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }
    
    /**
     * Inserta datos de prueba
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba la creacion de un usuario
     * @throws BusinessLogicException 
     */
    @Test
    public void createUsuarioTest() throws BusinessLogicException
    {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = logic.createUsuario(newEntity);
        
        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertEquals(entity.getNombre(), result.getNombre());
        Assert.assertEquals(entity.getContrasenha(), result.getContrasenha());
        Assert.assertEquals(entity.getCorreo(), result.getCorreo());
        Assert.assertEquals(entity.getEquipo(), result.getEquipo());
        Assert.assertEquals(entity.getActual(), result.getEquipo());
        Assert.assertEquals(entity.getTecnologias(), result.getTecnologias());
        Assert.assertEquals(entity.getLenguajes(), result.getLenguajes());
        Assert.assertEquals(entity.getIntereses(), result.getIntereses());
        
    }
    
    /**
     * Prueba la creacion de un usuario con nombre nulo
     * @throws BusinessLogicException 
     */
    @Test (expected = Exception.class)
    public void  createUsuarioNombreNull() throws BusinessLogicException
    {
        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        newEntity.setNombre(null);
        UsuarioEntity result = logic.createUsuario(newEntity);
    }
    
    /**
     * Prueba la eliminacion de un usuario
     * @throws BusinessLogicException 
     */
    @Test
    public void deleteUsuarioTest() throws BusinessLogicException {
        UsuarioEntity entity = data.get(0);
        logic.deleteUsuario(entity.getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba la actualizacion de los datos de un usuario
     */
    @Test
    public void updateUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updateUsuario(pojoEntity.getId(), pojoEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());

    }
    
    /**
     * Prueba la busqueda de un usuario
     */
    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity resultEntity = logic.getUsuario(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(resultEntity.getNombre(), entity.getNombre());

    }
}
