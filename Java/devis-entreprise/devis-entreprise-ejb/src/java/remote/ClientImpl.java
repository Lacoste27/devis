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
import models.Client;
import services.ClientService;

/**
 *
 * @author Tsiory
 */
@Stateless
public class ClientImpl implements ClientRemote {
    
    private final ClientService service = new ClientService();
    
    @Override
    public List<Client> findAll() {
        try {
            return this.service.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }

    @Override
    public void save(Client client) {
        try {
            this.service.save(client);
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getLastId() {
        try {
            return this.service.getLastId();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ClientImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
