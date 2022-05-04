using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using service_web.Models;

namespace service_web.Services
{
    public class AppareilService{
        private Appareil appareil;

        public AppareilService()
        {
            this.appareil = new Appareil();
        }

        public List<Appareil> findAll() {
            return this.appareil.findAll();
        }
    }
}