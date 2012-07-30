using System;
using System.Collections.Generic;
using System.Text;
using PersonNet.Formats;

namespace PersonNet
{
    public class ListPersons : List<Person>, IEquatable<ListPersons>
    {
        public bool Equals(ListPersons listPersons)
        {
            bool returnValue = false;

            if (Count == listPersons.Count)
            {
                for (int i = 0; i < Count; i++)
                {
                    if (!this[i].Equals(listPersons[i]))
                    {
                        returnValue = false;
                        break;
                    }
                    returnValue = true;
                }
                if (Count == 0)
                    returnValue = true;
            }
            return returnValue;
        }
    }
}
