using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using service_web.Models;

namespace service_web.Services
{
    public class PanneauService{
        private Panneau panneau;
        private Etat etat ;

        public PanneauService()
        {
            this.panneau = new Panneau();
            this.etat = new Etat() ;
        }

        public List<Panneau> findAllPanneau() {
            return this.panneau.findAll();
        }

        public Panneau findPanneauById(int id) {
            return this.panneau.findById(id);
        }

        public List<Panneau> findPanneauByInterval(int min, int max) {
            return this.panneau.findAllInterval(min, max);
        }

        public Double getMax() {
            return this.panneau.getMax() ;
        }

        public List<Etat> getEtat(int intervale) {
            return this.etat.GetEtat(intervale) ;
        }
    }
}