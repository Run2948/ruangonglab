using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ruangong.Models
{
    public class LoginModel
    {
        public string account { get; set; }
        public string password { get; set; }
    }

    public class PasswordModel
    {
        public string account { get; set; }
        public string password { get; set; }
        public string newpass { get; set; }
    }
}