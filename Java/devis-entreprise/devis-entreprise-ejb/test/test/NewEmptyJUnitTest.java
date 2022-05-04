/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import remote.PanneauRemote;

/**
 *
 * @author Tsiory
 */
public class NewEmptyJUnitTest {

    PanneauRemote panneauImpl = lookupPanneauImplRemote();
    
    public NewEmptyJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        PanneauRemote remote = lookupPanneauImplRemote();
        remote.findAll();
    }

    private PanneauRemote lookupPanneauImplRemote() {
        try {
            Context c = new InitialContext();
            return (PanneauRemote) c.lookup("java:global/devis-entreprise/devis-entreprise-ejb/PanneauImpl!remote.PanneauRemote");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
