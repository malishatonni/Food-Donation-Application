using food_donation_api.Connection;
using food_donation_api.Data;
using food_donation_api.Helper;
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
        public string Password { get; set; }
        public double Long { get; set; }
        public double Lat { get; set; }
        public IList<UserDonatesOrganization> UserDonatesOrganizations { get; set; }

        public double DistanceFrom(GoogleLocation temp)
        {
            double lon1 = toRadians(this.Long);
            double lon2 = toRadians(temp.Long);
            double lat1 = toRadians(this.Lat);
            double lat2 = toRadians(temp.Lat);
            double dlon = lon2 - lon1;
            double dlat = lat2 - lat1;
            double a = Math.Pow(Math.Sin(dlat / 2), 2) +
                       Math.Cos(lat1) * Math.Cos(lat2) *
                       Math.Pow(Math.Sin(dlon / 2), 2);

            double c = 2 * Math.Asin(Math.Sqrt(a));

            // Radius of earth in
            // kilometers. Use 3956
            // for miles
            double r = 6371;

            // calculate the result
            return (c * r);

        }
        private double toRadians(
           double angleIn10thofaDegree)
        {
            // Angle in 10th
            // of a degree
            return (angleIn10thofaDegree *
                           Math.PI) / 180;
        }
    }
}