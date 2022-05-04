using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using service_web.Models;

namespace service_web.Services
{
    public class BatterieService{
        private Batterie batterie;

        public BatterieService()
        {
            this.batterie = new Batterie();
        }

        public List<Batterie> findAllBatterie() {
            return this.batterie.findAll();
        }

        public Batterie findBatterieById(int id) {
            return this.batterie.findById(id);
        }
    }
}