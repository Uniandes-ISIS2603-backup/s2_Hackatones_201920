/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.logic;

import co.edu.uniandes.csw.hackatones.ejb.PatrocinadorLogic;
import co.edu.uniandes.csw.hackatones.entities.PatrocinadorEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.hackatones.persistence.PatrocinadorPersistence;
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
 * @author Estudiante
 */
@RunWith(Arquillian.class)
public class PatrocinadorLogicTest {
        
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    EntityManager em;
    
    @Inject
    private PatrocinadorLogic logic;
    
    @Inject
    private UserTransaction utx;
    
    private List<PatrocinadorEntity> data = new ArrayList<>();

    /**
     * Crea el deployment del test
     * @return 
     */
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
        em.createQuery("delete from PatrocinadorEntity").executeUpdate();
    }
    
    /**
     * Inserta datos de prueba
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            PatrocinadorEntity entity = factory.manufacturePojo(PatrocinadorEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        UsuarioEntity usuario = factory.manufacturePojo(UsuarioEntity.class);
        em.persist(usuario);
        data.get(0).setResponsable(usuario);
    }
    
    /**
     * Prueba la creacion de un patrocinador
     * @throws BusinessLogicException 
     */
    @Test
    public void createPatrocinadorTest() throws BusinessLogicException
    {
        PatrocinadorEntity newEntity = factory.manufacturePojo(PatrocinadorEntity.class);
        PatrocinadorEntity result = logic.createPatrocinador(newEntity);
        
        Assert.assertNotNull(result);
        PatrocinadorEntity entity = em.find(PatrocinadorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getInfoAdicional(), entity.getInfoAdicional());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
        
    }
    
    /**
     * Prueba la busqueda de un patrocinador
     */
    @Test
    public void getPatrocinadorTest() {
        PatrocinadorEntity entity = data.get(0);
        PatrocinadorEntity resultEntity = logic.getPatrocinador(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(resultEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(resultEntity.getInfoAdicional(), entity.getInfoAdicional());
        Assert.assertEquals(resultEntity.getUbicacion(), entity.getUbicacion());
    }
    
    /**
     * Prueba la creacion de un patrocinador con nombre nulo
     * @throws BusinessLogicException 
     */
    @Test (expected = Exception.class)
    public void  createPatrocinadorNombreNull() throws BusinessLogicException
    {
        PatrocinadorEntity newEntity = factory.manufacturePojo(PatrocinadorEntity.class);
        newEntity.setNombre(null);
        PatrocinadorEntity result = logic.createPatrocinador(newEntity);
    }
    
    /**
     * Prueba la eliminacion de un patrocinador
     * @throws BusinessLogicException 
     */
    @Test
    public void deletePatrocinadorTest() throws BusinessLogicException {
        PatrocinadorEntity entity = data.get(0);
        logic.deletePatrocinador(entity.getId());
        PatrocinadorEntity deleted = em.find(PatrocinadorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    /**
     * Prueba la actualizacion de los datos de un patrocinador
     */
    @Test
    public void updatePatrocinadorTest() {
        PatrocinadorEntity entity = data.get(0);
        PatrocinadorEntity pojoEntity = factory.manufacturePojo(PatrocinadorEntity.class);

        pojoEntity.setId(entity.getId());

        logic.updatePatrocinador(pojoEntity.getId(), pojoEntity);

        PatrocinadorEntity resp = em.find(PatrocinadorEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }
    
}
