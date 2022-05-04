using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Text.Json;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using service_web.Models;
using service_web.Services;


namespace service_web.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class BatterieController : ControllerBase
    {
        private BatterieService service = new BatterieService();

        [HttpGet("all")]
        public List<Batterie> findAll()
        {
            return this.service.findAllBatterie();
        }

        [HttpGet("stat")]
        public List<StatBatt> findAllStat()
        {
            return StatBatt.findAll();
        }

        [HttpPost("insert/{voltage}/{amperage}")]
        public void insert(int voltage, int amperage)
        {
            Batterie bat = new Batterie(1,"batterie",amperage,voltage,20000,100,"2010-02-02");
            Batterie.insert(bat);
        }

        [HttpPost("insertsupprimer/{voltage}/{amperage}")]
        public void insertsupprimer(int voltage, int amperage)
        {
            BatterieSupprimer bat = new BatterieSupprimer(1,voltage,amperage);
            BatterieSupprimer.insert(bat);
        }
    }   
}
