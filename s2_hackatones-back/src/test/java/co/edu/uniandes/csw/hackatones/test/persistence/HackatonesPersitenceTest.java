/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.hackatones.test.persistence;

import co.edu.uniandes.csw.hackatones.entities.CalificacionEntity;
import co.edu.uniandes.csw.hackatones.entities.EquipoEntity;
import co.edu.uniandes.csw.hackatones.entities.HackatonEntity;
import co.edu.uniandes.csw.hackatones.entities.LugarEntity;
import co.edu.uniandes.csw.hackatones.entities.UsuarioEntity;
import co.edu.uniandes.csw.hackatones.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.hackatones.persistence.EquipoPersistence;
import co.edu.uniandes.csw.hackatones.persistence.HackatonPersistence;
import co.edu.uniandes.csw.hackatones.persistence.LugarPersistence;
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
 * @author jc.higuera
 */
@RunWith(Arquillian.class)
public class HackatonesPersitenceTest {
    

    @Inject
    HackatonPersistence hp;
    
    @PersistenceContext()
    protected EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<HackatonEntity> data = new ArrayList<HackatonEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(HackatonEntity.class.getPackage())
                .addPackage(HackatonPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
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

    private void clearData(){
    em.createQuery("delete from HackatonEntity").executeUpdate();
    }
    
    private void insertData(){
        
    PodamFactory factory = new PodamFactoryImpl();
    
        for (int i = 0; i < 3; i++) {
            
           HackatonEntity entity = factory.manufacturePojo(HackatonEntity.class);
            
           
           em.persist(entity);
           data.add(entity);
            
        }
    }
    
    @Test
    public void createTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        HackatonEntity hackaton = factory.manufacturePojo(HackatonEntity.class);
        HackatonEntity restult = hp.create(hackaton);
        Assert.assertNotNull(restult);
        
        HackatonEntity entity = em.find(HackatonEntity.class, restult.getId());
        Assert.assertTrue(hackaton.equals(entity));
        Assert.assertEquals(hackaton.getNombre(), entity.getNombre());
        Assert.assertEquals(hackaton.getEspecificacion(), entity.getEspecificacion());
        Assert.assertEquals(hackaton.getFechaFin(), entity.getFechaFin());
        Assert.assertEquals(hackaton.getFechaInicio(), entity.getFechaInicio());
        Assert.assertEquals(hackaton.getNivel(), entity.getNivel());
        Assert.assertEquals(hackaton.getTema(), entity.getTema());
        Assert.assertEquals(hackaton.getFinalizada(), entity.getFinalizada());
        Assert.assertEquals(hackaton.hashCode(), entity.hashCode());
        Assert.assertEquals(hackaton.getCalificaciones(), entity.getCalificaciones());
        Assert.assertEquals(hackaton.getEquipoGanador(), entity.getEquipoGanador());
        Assert.assertEquals(hackaton.getEquipos(), entity.getEquipos());
        Assert.assertEquals(hackaton.getTipo(), entity.getTipo());
        Assert.assertEquals(hackaton.getImagen(), entity.getImagen());
        Assert.assertEquals(hackaton.getIniciada(), entity.getIniciada());
        Assert.assertEquals(hackaton.getReglas(), entity.getReglas());
        Assert.assertEquals(hackaton.getRestricciones(), entity.getRestricciones());
    }
    
    @Test
    public void getHackatonesTest(){
    List <HackatonEntity> lista = hp.findAll();
    Assert.assertEquals(data.size(), lista.size());
    
    for(HackatonEntity hackatones : lista){
    
        boolean encontrado = false;
        for (HackatonEntity entity : data){
        
            if(hackatones.getId().equals(entity.getId())){
            encontrado = true;
            }
        }
        Assert.assertTrue(encontrado);
    }
   }
    
    @Test
    public void getHackatonTest(){
        HackatonEntity entidad = data.get(0);
        HackatonEntity nueva_entidad = hp.find(entidad.getId());
        Assert.assertNotNull(nueva_entidad);
        Assert.assertEquals(entidad.getNombre(), nueva_entidad.getNombre());
        Assert.assertEquals(entidad.getEspecificacion(), nueva_entidad.getEspecificacion());
        Assert.assertEquals(entidad.getFechaFin(), nueva_entidad.getFechaFin());
        Assert.assertEquals(entidad.getFechaInicio(), nueva_entidad.getFechaInicio());
        Assert.assertEquals(entidad.getNivel(), nueva_entidad.getNivel());
        Assert.assertEquals(entidad.getTema(), nueva_entidad.getTema());
        Assert.assertEquals(entidad.getFinalizada(), nueva_entidad.getFinalizada());
    }
    
    @Test
   public void deleteHackatonTest(){
   HackatonEntity entidad = data.get(0);
   hp.delete(entidad.getId());
   HackatonEntity eliminada = em.find(HackatonEntity.class, entidad.getId());
   Assert.assertNull(eliminada);
   }
   
   @Test
   public void updateHackatonTest(){
       HackatonEntity entidad = data.get(0);
       PodamFactory factory = new PodamFactoryImpl();
       HackatonEntity nueva_entidad = factory.manufacturePojo(HackatonEntity.class);
       nueva_entidad.setId(entidad.getId());
       
       hp.update(nueva_entidad);
       
       HackatonEntity respuesta = em.find(HackatonEntity.class, entidad.getId());
       Assert.assertEquals(nueva_entidad.getNombre(), respuesta.getNombre());
        Assert.assertEquals(nueva_entidad.getEspecificacion(), respuesta.getEspecificacion());
        Assert.assertEquals(nueva_entidad.getFechaFin(), respuesta.getFechaFin());
        Assert.assertEquals(nueva_entidad.getFechaInicio(), respuesta.getFechaInicio());
        Assert.assertEquals(nueva_entidad.getNivel(), respuesta.getNivel());
        Assert.assertEquals(nueva_entidad.getTema(), respuesta.getTema());
        Assert.assertEquals(nueva_entidad.getFinalizada(), respuesta.getFinalizada());
   }
}
    
