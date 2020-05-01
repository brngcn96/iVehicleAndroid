using Udemy1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Udemy1.Controllers
{
    public class AdminController : ApiController
    {
        //
        private readonly ModelContainer context;

        public AdminController() { this.context = new ModelContainer(); }

        // GET api/values
        public IEnumerable<Admin> Get()
        {
            List<Admin> admin = (from c in context.Admin where c.Status==true select c).ToList();

            return admin;
        }

        // GET api/values/5
        public Admin Get(int id)
        {
            Admin admin = (from c in context.Admin where c.Admin_ID == id && c.Status== true select c).First();


            return admin;
        }

        // POST api/values
        public void Post([FromBody]string value)
        {
            Admin admin = new Admin();
            admin.Name = value;
            context.Admin.Add(admin);

            context.SaveChanges();

        }

        // PUT api/values/5
        public void Put(int id, [FromBody]string value)
        {
            Admin admin = (from c in context.Admin where c.Admin_ID == id select c).FirstOrDefault();

            if(admin != null)
                admin.Name = value; 

            context.SaveChanges();

        }

        // DELETE api/values/5
        public void Delete(int id)
        {
            Admin admin = (from c in context.Admin where c.Admin_ID == id select c).FirstOrDefault();

            if (admin != null)
                admin.Status =false;

            context.SaveChanges();

        }
    }
}
