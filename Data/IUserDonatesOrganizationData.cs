using food_donation_api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Data
{
    public interface IUserDonatesOrganizationData
    {
        // Add a new Donation request
        UserDonatesOrganization NewDonation(UserDonatesOrganization userDonatesOrganization);
        // Delete a donation
        UserDonatesOrganization DeleteDonation(Guid donationId);

        // Cancel Donation Request
        // From User
        UserDonatesOrganization CancelDonation(Guid donationId);

        // Accept Pending Donation
        // By Organization
        UserDonatesOrganization AcceptDonation(Guid donationId);

        // Reject a donation request
        // By Organization
        UserDonatesOrganization RejectDonation(Guid donationId);

        // Complete Donation
        // By Organization
        UserDonatesOrganization CompleteDonation(Guid donationId);

        // List of Donation to Organization
        List<UserDonatesOrganization> GetAllDonations(string OrganizationEmail);
        // Get number of accepted donations
        int NumberOfDonationsToAnOrganization(string Organization);

        // Get number of successful donations by an user
        int NumberOfDonationsByUser(string UserEmail);



    }
}
