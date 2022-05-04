/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import models.Appareil;
import services.AppareilService;

/**
 *
 * @author Tsiory
 */
@Stateless
public class AppareilImpl implements AppareilRemote {
    
    private final AppareilService service = new AppareilService();
        
    @Override
    public List<Appareil> findAll() {
        try {
            return this.service.findAll();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AppareilImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }

    @Override
    public List<Appareil> findByClient_id(int id) {
        try {
            return this.service.findByClient_id(id);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AppareilImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }

    @Override
    public void save(Appareil appareil) {
        try {
            this.service.save(appareil);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AppareilImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
