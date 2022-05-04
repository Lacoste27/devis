using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using service_web.Models;

namespace service_web.Services
{
    public class ClientService{
        private Client client;

        public ClientService()
        {
            this.client = new Client();
        }

        public List<Client> findAll() {
            return this.client.findAll();
        }

         public Client findById(int id) {
            return this.client.findById(id);
        }
       
    }
}