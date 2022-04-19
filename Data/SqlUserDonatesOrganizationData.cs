using food_donation_api.Connection;
using food_donation_api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Data
{
    public class SqlUserDonatesOrganizationData : IUserDonatesOrganizationData
    {
        ApplicationDbContext _applicationDbContext;
        public SqlUserDonatesOrganizationData(ApplicationDbContext applicationDbContext)
        {
            _applicationDbContext = applicationDbContext;
        }

        public UserDonatesOrganization AcceptDonation(Guid donationId)
        {
            var find = _applicationDbContext.UserDonatesOrganization.Find(donationId);
            find.RequestStatus = Status.Accepted;
            _applicationDbContext.Update(find);
            _applicationDbContext.SaveChanges();
            return (UserDonatesOrganization)find;
        }

        public UserDonatesOrganization CancelDonation(Guid donationId)
        {
            var find = _applicationDbContext.UserDonatesOrganization.Find(donationId);
            find.RequestStatus = Status.Rejected;
            _applicationDbContext.Update(find);
            _applicationDbContext.SaveChanges();
            return (UserDonatesOrganization)find;
        }

        public UserDonatesOrganization CompleteDonation(Guid donationId)
        {
            var find = _applicationDbContext.UserDonatesOrganization.Find(donationId);
            find.RequestStatus = Status.Completed;
            _applicationDbContext.Update(find);
            _applicationDbContext.SaveChanges();
            return (UserDonatesOrganization)find;
        }

        public UserDonatesOrganization DeleteDonation(Guid donationId)
        {
            var find = _applicationDbContext.UserDonatesOrganization.Find(donationId);
            _applicationDbContext.Remove(find);
            _applicationDbContext.SaveChanges();
            return (UserDonatesOrganization)find;
        }

        public List<UserDonatesOrganization> GetAllDonations(string OrganizationEmail)
        {
            var find = _applicationDbContext.Organization.Find(OrganizationEmail);
            return _applicationDbContext.UserDonatesOrganization
                .Where(u => u.Organization.OrganizationEmail == find.OrganizationEmail)
                .ToList();
        }

        public UserDonatesOrganization NewDonation(UserDonatesOrganization userDonatesOrganization)
        {
            userDonatesOrganization.DonationId = new Guid();
            _applicationDbContext.UserDonatesOrganization.Add(userDonatesOrganization);
            _applicationDbContext.SaveChanges();
            return userDonatesOrganization;
        }

        public int NumberOfDonationsByUser(string UserEmail)
        {
            var find = _applicationDbContext.User.Find(UserEmail);
            return _applicationDbContext.UserDonatesOrganization
                .Where(u => u.User.Email== find.Email)
                .ToList().Count;
        }

        public int NumberOfDonationsToAnOrganization(string Organization)
        {
            var find = _applicationDbContext.Organization.Find(Organization);
            return _applicationDbContext.UserDonatesOrganization
                .Where(u => u.Organization.OrganizationEmail == find.OrganizationEmail)
                .ToList().Count;
        }

        public UserDonatesOrganization RejectDonation(Guid donationId)
        {
            var find = _applicationDbContext.UserDonatesOrganization.Find(donationId);
            find.RequestStatus = Status.Rejected;
            _applicationDbContext.Update(find);
            _applicationDbContext.SaveChanges();
            return (UserDonatesOrganization)find;
        }
    }
}
