using food_donation_api.Connection;
using food_donation_api.Helper;
using food_donation_api.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;


namespace food_donation_api.Data
{
    public class SqlOrganizationData : IOrganizationData
    {
         ApplicationDbContext _applicationDbContext;
        public SqlOrganizationData(ApplicationDbContext applicationDbContext)
        {
            _applicationDbContext = applicationDbContext;
        }
        public Organization AddOrganization(Organization organization)
        {

            //organization.GoogleLocation.Organization = organization;
            _applicationDbContext.Organization.Add(organization);
            _applicationDbContext.SaveChanges();

            return organization;
        }

        public List<Organization> GetNearbyOrganizations(GoogleLocation locationFormat)
        {
            List<Organization> list = GetOrganizations();
            for(int i = 0; i < list.Count; i++)
            {
                if (list.ElementAt(i).DistanceFrom(locationFormat) >5)
                {
                    list.RemoveAt(i);
                }
            }
            return list;
        }
        

        public Organization GetOrganization(string email)
        {
            var find = _applicationDbContext.Organization.Find(email);
            return find;
        }

        public List<Organization> GetOrganizations()
        {
           return _applicationDbContext.Organization.ToList();

        }

        public List<Organization> GetOrganizationsByName(string organizationName)
        {
            return _applicationDbContext.Organization
                .Where(org => org.OrganizationName.Contains(organizationName))
                .ToList();
        }
        /*public GoogleLocation GetGoogleLocation(string email)
        {
            List<GoogleLocation> list = _applicationDbContext.Location.ToList();
            for(int i=0;i<list.Count;i++)
            {
                if (list.ElementAt(i).OrganizationEmail==email)
                    return list.ElementAt(i);
            }
            return null;
        }*/

        /*public List<GoogleLocation> GetLocations()
        {
            List<GoogleLocation> list = new List<GoogleLocation>();
            List<Organization> org = GetOrganizations();
            for(int i = 0; i < org.Count; i++)
            {
                list.Add(org.ElementAt(i).GoogleLocation);
            }
            return list;

            return _applicationDbContext.Location.ToList();
        }/*/
    }
}
