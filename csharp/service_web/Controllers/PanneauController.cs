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
    public class PanneauController : ControllerBase
    {
        private PanneauService service = new PanneauService();

        [HttpGet("all")]
        public List<Panneau> findAll(){
            return this.service.findAllPanneau();
        }

        [HttpGet("findInterval/{min}/{max}")]
        public List<Panneau> findAll(int min, int max){
            return this.service.findPanneauByInterval(min, max);
        }

        [HttpGet("max")]
        public Double getMax(){
            return this.service.getMax();
        }

        [HttpGet("etat/{intervale}")]
        public List<Etat> getEtat(int intervale){
            return this.service.getEtat(intervale);
        }
    }
}
