using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Models
{
    public class User
    {
        [Key]
        [Required]

        public string Email { get; set; }
        [Required]
        [MaxLength(11)]
        [MinLength(11)]
        public string PhoneNumber { get; set; }
        public string FullName { get; set; } = string.Empty;
        public IList<UserDonatesOrganization> UserDonatesOrganizations { get; set; }

    }
}
