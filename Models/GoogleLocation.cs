using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace food_donation_api.Models
{
    public class GoogleLocation
    {
        [Key]
        public Guid LocationId { get; set; } = Guid.NewGuid();
        [Required]
        public double Lat { get; set; }
        [Required]
        public double Long { get; set; }
        
       
    }
}

