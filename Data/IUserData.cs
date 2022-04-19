using food_donation_api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Data
{
    public interface IUserData
    {
        User AddUser(User user);
    }
}
