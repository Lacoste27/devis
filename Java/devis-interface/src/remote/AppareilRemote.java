/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote;

import java.util.List;
import javax.ejb.Remote;
import models.Appareil;

/**
 *
 * @author Tsiory
 */
@Remote
public interface AppareilRemote {

    List<Appareil> findAll();

    List<Appareil> findByClient_id(int id);

    void save(Appareil appareil);
}
