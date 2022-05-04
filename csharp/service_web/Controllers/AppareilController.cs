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
    public class AppareilController : ControllerBase
    {
        private AppareilService service = new AppareilService();

        [HttpGet("v1/all")]
        public List<Appareil> findAll()
        {
            return this.service.findAll();
        }
    }
}
