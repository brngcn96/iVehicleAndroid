using Udemy1.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.ComponentModel.DataAnnotations;
using System.Text;
using System.Runtime.Serialization.Json;
using Udemy1.Dtos;

namespace Udemy1.Controllers
{

    [Authorize]
    public class AuthanticateController : ApiController
    {

        private readonly ModelContainer context;

        public AuthanticateController() { this.context = new ModelContainer(); }


        [AllowAnonymous]
        [HttpGet]
        [ActionName("Login")]
        public AuthorizedDto Login([Required]string email, string password)
        {
            if (!ModelState.IsValid)
                return null; ;

            try
            {

                Admin admin = (from c in context.Admin.AsNoTracking() where c.Email == email select c).FirstOrDefault();
                User user = (from c in context.User.AsNoTracking() where c.Email == email select c).FirstOrDefault();
                ChildUser childuser = (from c in context.ChildUser.AsNoTracking() where c.Email == email select c).FirstOrDefault();


                if (admin == null && user == null)
                    return null;

                string strLocalUrl = "http://localhost:60499";
                WebRequest webRequest = WebRequest.Create(strLocalUrl + "/token");
                webRequest.Method = "POST";
                webRequest.ContentType = "application/x-www-form-urlencoded";

                byte[] byteBody = new System.Text.ASCIIEncoding().GetBytes("grant_type=password&username=" + email + "&password=" + password);
                webRequest.ContentLength = byteBody.Length;
                webRequest.GetRequestStream().Write(byteBody, 0, byteBody.Length);
                webRequest.GetRequestStream().Close();

                WebResponse webResponse = webRequest.GetResponse();
                var serializer = new DataContractJsonSerializer(typeof(AuthTokenDto));
                AuthTokenDto authTokenDto = serializer.ReadObject(webResponse.GetResponseStream()) as AuthTokenDto;

                if (admin != null)
                {
                    return new AuthorizedDto()
                    {
                        ID = admin.Admin_ID,
                        Name = admin.Name,
                        Email = admin.Email,
                        ImageURL = admin.ImageUrl,
                        Role = "admin",
                        Token = authTokenDto,
                    };
                }
                else if (user != null)
                {
                    return new AuthorizedDto()
                    {
                        ID = user.User_ID,
                        Name = user.Name,
                        Email = user.Email,
                        ImageURL = user.ImageUrl,
                        Role = "user",
                        Token = authTokenDto,
                    };

                }

                else if (childuser != null)
                {

                    return new AuthorizedDto()
                    {
                        ID = childuser.ChildUser_ID,
                        ParentID = childuser.Parent_ID,
                        Name = childuser.Name,
                        Email = childuser.Email,
                        ImageURL = childuser.ImageUrl,
                        Role = "childuser",
                        Token = authTokenDto,
                    };

                }

                else
                {
                    return null;
                }

            }
            catch (Exception e)
            {
                return null;
            }

        }

        [AllowAnonymous]
        [HttpPost]
        [ActionName("Register")]
        public HttpResponseMessage Register([Required]string name, [Required]string surname, [Required]string email, [Required]string password)
        {
            if (!ModelState.IsValid)
                Request.CreateResponse(HttpStatusCode.InternalServerError, "Model State not Valid!");

            try
            {
                Admin admin = (from c in context.Admin where c.Email == email select c).FirstOrDefault();

                if (admin == null)
                {
                    byte[] passwordHash;
                    byte[] passwordSalt;

                    CreatePasswordHash(password, out passwordHash, out passwordSalt);

                    if (passwordHash != null || passwordSalt != null)
                    {
                        Admin newAdmin = new Admin()
                        {
                            Name = name,
                            Surname = surname,
                            Email = email,
                            PasswordHash = passwordHash,
                            PasswordSalt = passwordSalt,
                            Status = true,
                            CreationDate = DateTime.Now,
                            CreatorIP = ": : :",
                            CreatorRole = "admin",

                        };
                        context.Admin.Add(newAdmin);
                        context.SaveChanges();
                    }
                }

            }
            catch (Exception e)
            {

                Request.CreateResponse(HttpStatusCode.InternalServerError, e.Message);
            }

            return Request.CreateResponse(HttpStatusCode.Accepted);
        }

        private void CreatePasswordHash(string password, out byte[] passwordHash, out byte[] passwordSalt)
        {
            using (var hmac = new System.Security.Cryptography.HMACSHA512())
            {
                passwordSalt = hmac.Key;
                passwordHash = hmac.ComputeHash(System.Text.Encoding.UTF8.GetBytes(password));
            }
        }
    }
}