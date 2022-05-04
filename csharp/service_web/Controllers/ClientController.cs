using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using service_web.Models;
using service_web.Services;

namespace service_web.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class ClientController : ControllerBase
    {
        private ClientService service = new ClientService();

        [HttpGet("v1/all")]
        public List<Client> findAll()
        {
            return this.service.findAll();
        }

        [HttpGet("v1/id/{id}")]
        public Client findById(int id)
        {
            return this.service.findById(id);
        }
    }
}
