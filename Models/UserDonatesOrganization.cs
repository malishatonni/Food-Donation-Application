using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Models
{
    public class UserDonatesOrganization
    {
        public Guid DonationId { get; set; }
        public User User { get; set; }
        public string Email { get; set; }
        public Organization Organization { get; set; }
        public string OrganizationEmail { get; set; }

        [Range(1,5)]
        public int ReviewFromUser { get; set; }
        public Status RequestStatus { get; set; }


    }
    public enum Status
    {
        Requested,
        Accepted,
        Rejected,
        Completed
    }
}


//https://www.learnentityframeworkcore.com/configuration/many-to-many-relationship-configuration
//https://www.entityframeworktutorial.net/efcore/configure-many-to-many-relationship-in-ef-core.aspx