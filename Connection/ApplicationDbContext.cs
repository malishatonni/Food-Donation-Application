using food_donation_api.Helper;
using food_donation_api.Models;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Connection
{
    public class ApplicationDbContext:DbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {

        }
        public DbSet<User> User { get; set; }
        public DbSet<Organization> Organization { get; set; }
        public DbSet <UserDonatesOrganization> UserDonatesOrganization { get; set; }
        //public DbSet <GoogleLocation> Location { get; set; }
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
            /*modelBuilder.Entity<Organization>()
                .HasOne(u => u.GoogleLocation)
                .WithOne(u => u.Organization)
                .HasForeignKey<GoogleLocation>(gl => gl.OrganizationEmail);/*

            /*
            modelBuilder.Entity<Organization>()
                .HasOne<LocationFormat>(u => u.GoogleLocation);
            modelBuilder.Entity<LocationFormat>()
                .HasOne<Organization>(u => u.Organization);*/

        }
    }
}
