using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace PersonNet
{
    public class ClientServerDataSerializable
    {
        public static string Serialize(ListPersons listPersons)
        {
            string returnValue = string.Empty;

            foreach (Person person in listPersons)
                returnValue += person.ToXML();

            return returnValue;
        }

        public static ListPersons Deserialize(string str)
        {
            ListPersons returnList = new ListPersons();
            Regex regex = new Regex("<Person>(.+?)</Person>");
            MatchCollection matchCollection = regex.Matches(str);
            if (matchCollection.Count > 0)
            {
                foreach (Match match in matchCollection)
                {
                    Person person = new Person();
                    person.FromXML(match.Value);
                    returnList.Add(person);
                }
            }

            return returnList;
        }
    }
}
