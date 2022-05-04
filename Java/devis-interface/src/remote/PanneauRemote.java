/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote;

import java.util.List;
import javax.ejb.Remote;
import models.Panneau;

/**
 *
 * @author Tsiory
 */
@Remote
public interface PanneauRemote {

    List<Panneau> findAll();

    Panneau findById(int id);

}
