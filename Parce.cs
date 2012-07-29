using System;
using System.Collections.Generic;
using System.Text;
using System.Text.RegularExpressions;

namespace PersonNet
{
    public class Parce
    {
        public static string FindFiledValueXML(string field, string str)
        {
            string returnStr = string.Empty;
            string tag = string.Format("<{0}>(.+?)</{0}>", field);
            Regex regex = new Regex(tag);
            Match match = regex.Match(str);
            if (match.Success)
            {
                returnStr = match.Groups[1].Value;
            }
            return returnStr;
        }

    }
}
