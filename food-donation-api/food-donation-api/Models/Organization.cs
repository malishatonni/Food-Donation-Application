using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Models
{
    public class Organization
    {

        [Required]
        [Key]
        public string OrganizationEmail { get; set; }
        [Required]
        public string OrganizationName { get; set; }
        [Required]
        public string Location { get; set; }
        [Required]
        [MaxLength(11)]
        [MinLength(11)]
        public string PhoneNumber { get; set; }
        [Required]
        [MinLength(16)]
        [MaxLength(16)]
        public string OrganizationCode { get; set; }
        public IList<UserDonatesOrganization> UserDonatesOrganizations { get; set; }


    }
}
