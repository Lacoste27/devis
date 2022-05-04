/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote;

import java.util.List;
import javax.ejb.Remote;
import models.Batterie;

/**
 *
 * @author Tsiory
 */
@Remote
public interface BatterieRemote {

    List<Batterie> findAll();

    Batterie findById(int id);
    
}
