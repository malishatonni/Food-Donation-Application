using food_donation_api.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Connection
{
    public class ApplicationDbContext:IdentityDbContext<ApplicationUser>
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {

        }
        public DbSet<User> User { get; set; }
        public DbSet<Organization> Organization { get; set; }
        public DbSet <UserDonatesOrganization> UserDonatesOrganization { get; set; }
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            //
            modelBuilder.Entity<UserDonatesOrganization>().HasKey(sc => new { sc.Email,sc.OrganizationEmail});
            modelBuilder.Entity<UserDonatesOrganization>()
                .HasOne<User>(ud => ud.User)
                .WithMany(u => u.UserDonatesOrganizations)
                .HasForeignKey(ud => ud.OrganizationEmail);
            modelBuilder.Entity<UserDonatesOrganization>()
                .HasOne<Organization>(ud => ud.Organization)
                .WithMany(u => u.UserDonatesOrganizations)
                .HasForeignKey(ud => ud.Email);


            base.OnModelCreating(modelBuilder);
        }
    }
}
