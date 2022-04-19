using food_donation_api.Connection;
using food_donation_api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Data
{
    public class SqlUserData : IUserData
    {
        ApplicationDbContext _applicationDbContext;

        public SqlUserData(ApplicationDbContext applicationDbContext)
        {
            _applicationDbContext = applicationDbContext;
        }
        public User AddUser(User user)
        {
            user.Password = null;
            _applicationDbContext.User.Add(user);
            _applicationDbContext.SaveChanges();
            return user;
        }
    }
}
