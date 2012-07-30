using System;
using System.Drawing;
using System.IO;

namespace PersonNet
{
    public class Person : IEquatable<Person>
    {
        public int id;
        public string firstName;
        public string lastName;
        public int age;
        //public Image image;

        public Person()
        {
        }

        public Person(string firstName, string lastName, int age)
        {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public static string InsertQuery
        {
            get { return "INSERT INTO [Person] ([FirstName], [LastName], [Age]) VALUES (@FirstName, @LastName, @Age)"; }
        }

        public static string SelectQuery
        {
            get { return "SELECT * FROM Person"; }
        }

        public static string UpdateQuery
        {
            get { return "UPDATE [Person] SET [FirstName] = @FirstName, [LastName] = @LastName, [Age] = @Age WHERE Id = @Id"; }
        }

        public static string DeleteQuery
        {
            get { return "DELETE [Person] WHERE Id = @Id"; }
        }

        public string ToCSV()
        {
            string strResult = id + ",";
            strResult += firstName + ",";
            strResult += lastName + ",";
            strResult += age;
            return strResult;
        }

        public void FromCSV(string str)
        {
            string[] strFields = str.Split(',');

            id = Convert.ToInt32(strFields[0]);
            firstName = strFields[1];
            lastName = strFields[2];
            age = Convert.ToInt32(strFields[3]);
        }

        public string ToXML()
        {
            string strResult = "<Person><id>" + id + "</id>";
            strResult += "<firstName>" + firstName + "</firstName>";
            strResult += "<lastName>" + lastName + "</lastName>";
            strResult += "<age>" + age + "</age></Person>";
            return strResult;
        }

        public void FromXML(string str)
        {
            id = Convert.ToInt32(Parce.FindFiledValueXML("id", str));
            firstName = Parce.FindFiledValueXML("firstName", str);
            lastName = Parce.FindFiledValueXML("lastName", str);
            age = Convert.ToInt32(Parce.FindFiledValueXML("age", str));
        }

        public void GetData(object[] objects)
        {
            id = Convert.ToInt32(objects[0]);
            firstName = objects[1].ToString();
            lastName = objects[2].ToString();
            age = Convert.ToInt32(objects[3]);
        }

        public byte[] GetBytes()
        {
            MemoryStream memoryStream = new MemoryStream();
            //image.Save(memoryStream, System.Drawing.Imaging.ImageFormat.Gif);
            return memoryStream.ToArray();
        }

        public Image GetPictute(byte[] bytes)
        {
            MemoryStream memoryStream = new MemoryStream(bytes);
            Image returnImage = Image.FromStream(memoryStream);
            return returnImage;
        }

        public bool Equals(Person person)
        {
            bool returnValue = true;
            if (id != person.id || firstName != person.firstName || lastName != person.lastName || age != person.age)
            {
                returnValue = false;
            }
            return returnValue;
        }
    }
}
