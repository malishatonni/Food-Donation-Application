using food_donation_api.Connection;
using food_donation_api.Data;
using food_donation_api.Models;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace food_donation_api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrganizationController : ControllerBase
    {
        // GET: api/<OrganizationController>
        IOrganizationData _organizationData;
        public OrganizationController(IOrganizationData organizationData)
        {

            _organizationData = organizationData;


        }
        [HttpGet]
        public IActionResult Get()
        {
   
            return Ok(_organizationData.GetOrganizations());
        }

        // GET api/<OrganizationController>/5
        [HttpGet("near-organizations/{lon}/{lat}")]
        public IActionResult GetNearbyOrganizations(double lon,double lat)
        {
            GoogleLocation g = new GoogleLocation();
            g.Long = lon;
            g.Lat = lat;
            return Ok(_organizationData.GetNearbyOrganizations(g));
        }

        // POST api/<OrganizationController>
        [HttpPost]
        public IActionResult Post([FromBody] Organization organization)
        {
            var org=
            _organizationData.AddOrganization(organization);
            if (org != null)
            {
                return Ok(new Response { Status = "Ok", Message = "Organization Created Successfully" });
            }
            return Ok(new Response { Status = "Error", Message = "Can not sign up" });
        }

        // PUT api/<OrganizationController>/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody] string value)
        {
        }

        // DELETE api/<OrganizationController>/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
    class Person
    {
        public int id { get; set; }
        public string name { get; set; }
    }
}
