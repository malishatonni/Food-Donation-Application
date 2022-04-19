using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;

namespace food_donation_api.Connection
{
    public class UserDbContext:IdentityDbContext<ApplicationUser>
    {
            public UserDbContext(DbContextOptions<UserDbContext> options) : base(options)
            {

            }
     }
  
}
